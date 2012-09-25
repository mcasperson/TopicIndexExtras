package com.redhat.topicindex.extras.client.local.presenter;

import java.util.ArrayList;
import java.util.List;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.PathSegmentImpl;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;
import org.vectomatic.file.File;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.FileUploadExt;
import org.vectomatic.file.events.ErrorHandler;
import org.vectomatic.file.events.LoadEndEvent;
import org.vectomatic.file.events.LoadEndHandler;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.redhat.topicindex.extras.client.local.Presenter;
import com.redhat.topicindex.extras.client.local.constants.Constants;
import com.redhat.topicindex.extras.client.local.exceptions.InputFormatException;
import com.smartgwt.client.widgets.Progressbar;

@Dependent
public class TopicImportPresenter implements Presenter
{
	private static final String REST_SERVER = Constants.BASE_URL + "seam/resource/rest";
	
	/** Regex to describe the format of the tag ids */
	private static final String TAG_IDS_RE = "^(\\d+ )*\\d+$";

	/** Property Tag expansion string */
	private static final String PROPERTY_TAG_EXPAND = "{\"branches\":[{\"trunk\":{\"name\":\"topics\"},\"branches\":[{\"trunk\":{\"name\":\"properties\"}},{\"trunk\":{\"name\":\"tags\"}}]}]}";

	/** The ID of the Original File Name property tag */
	private static final Integer ORIGINAL_FILE_NAME_PROPERTY_TAG_ID = 28;

	/** The ID of the Topic Import Errors property tag */
	private static final Integer TOPIC_IMPORT_ERRORS_PROPERTY_TAG_ID = 29;

	/** The UTF-8 Byte Order Marker that is present in some XML files and needs to be removed when converting the strings to XML */
	private static final String UTF_8_BOM = "ï»¿";
	/** browsers have issues with xi:include if you don't specifically name the namespace, so we have to watch for them and not process the topics */
	private static final String XI_INCLUDE = "xi:include";
	/** The name of a section XML element */
	private static final String SECTION_ELEMENT = "section";
	/** The name of a title XML element */
	private static final String TITLE_ELEMENT = "title";

	public interface Display
	{
		Button getGoButton();

		FileUploadExt getUpload();

		Widget asWidget();

		TextArea getFileList();

		TextArea getLog();

		TextBox getTagIds();

		Progressbar getProgress();

		TextBox getFileNamePrefix();
		
		TextArea getTopicDetails();
		
		TextArea getTopicErrors();
	}

	// @Inject
	// private HandlerManager eventBus;

	@Inject
	private Display display;

	public void bind()
	{
		display.getUpload().addChangeHandler(new ChangeHandler()
		{
			@Override
			public void onChange(final ChangeEvent event)
			{
				display.getFileList().setText("");
				final StringBuilder fileNames = new StringBuilder();

				for (final File file : display.getUpload().getFiles())
				{
					fileNames.append(file.getName() + "\n");
				}

				display.getFileList().setText(fileNames.toString());
			}
		});

		display.getGoButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				/* Start processing the files. We create a chain of methods to simulate synchronous processing */
				final StringBuilder log = new StringBuilder();
				final StringBuilder topicDetails = new StringBuilder();
				final StringBuilder topicErrors = new StringBuilder();
				enableUI(false);
				try
				{
					final List<Integer> tagIds = getTagIds();
					pocessFiles(0, log, topicDetails, topicErrors, tagIds);
				}
				catch (final InputFormatException ex)
				{
					enableUI(true);
				}
			}
		});
	}
	
	private List<Integer> getTagIds() throws InputFormatException
	{
		final String input = display.getTagIds().getValue();
		final List<Integer> retValue = new ArrayList<Integer>();
		
		if (input.isEmpty())
			return retValue;
		
		if (!input.matches(TAG_IDS_RE))
			throw new InputFormatException();
		
		final String[] tagIds = input.split(" ");
		for (final String tagId : tagIds)
		{
			try
			{
				retValue.add(Integer.parseInt(tagId));
			}
			catch (final NumberFormatException ex)
			{
				/* Should never happen if the regex is correctly defined */
			}
		}
		
		return retValue;
	}

	/**
	 * Called when all files have been processed
	 * 
	 * @param log
	 *            The string holding the log
	 */
	private void processingDone(final StringBuilder log, final StringBuilder topicDetails, final StringBuilder topicErrors)
	{
		display.getLog().setText(log.toString());
		display.getTopicDetails().setText(topicDetails.toString());
		display.getTopicErrors().setText(topicErrors.toString());
		enableUI(true);
	}

	/**
	 * Extract the file from the list of upload files and process it, or call processingDone if there are no more files to process.
	 * 
	 * @param index
	 *            The index of the file to process
	 * @param log
	 *            The string holding the log
	 */
	private void pocessFiles(final int index, final StringBuilder log, final StringBuilder topicDetails, final StringBuilder topicErrors, final List<Integer> tagIds)
	{
		final int numFiles = display.getUpload().getFiles().getLength();

		if (index >= numFiles)
		{
			processingDone(log, topicDetails, topicErrors);
		}
		else
		{
			final float percentDone = (index + 1) / (float) numFiles * 100.0f;
			display.getProgress().setPercentDone((int) percentDone);
			findExistingTopic(display.getUpload().getFiles().getItem(index), index, log, topicDetails, topicErrors, tagIds);
		}
	}

	/**
	 * Load a file and then pass it off to have the XML processed. Once the file is loaded and its contents processed, call pocessFiles to process the next
	 * file.
	 * 
	 * @param file
	 *            The file to read
	 * @param index
	 *            The index off the file
	 * @param log
	 *            The string holding the log
	 */
	private void processFile(final RESTTopicV1 topic, final File file, final int index, final StringBuilder log, final StringBuilder topicDetails, final StringBuilder topicErrors, final List<Integer> tagIds)
	{
		final FileReader reader = new FileReader();

		reader.addErrorHandler(new ErrorHandler()
		{
			@Override
			public void onError(final org.vectomatic.file.events.ErrorEvent event)
			{
				pocessFiles(index + 1, log, topicDetails, topicErrors, tagIds);
			}
		});

		reader.addLoadEndHandler(new LoadEndHandler()
		{
			@Override
			public void onLoadEnd(final LoadEndEvent event)
			{
				final String result = reader.getStringResult();

				processXML(topic, result, file, index, log, topicDetails, topicErrors, tagIds);
			}
		});

		reader.readAsBinaryString(file);
	}

	private void processXML(final RESTTopicV1 topic, final String result, final File file, final int index, final StringBuilder log, final StringBuilder topicDetails, final StringBuilder topicErrors, final List<Integer> tagIds)
	{
		try
		{
			String fixedResult = result;

			/* remove utf-8 Byte Order Mark (BOM) if present */
			if (fixedResult.startsWith(UTF_8_BOM))
				fixedResult = fixedResult.replaceFirst(UTF_8_BOM, "");

			/*
			 * It is rare that an XML file will actually list the xmlns:xi="http://www.w3.org/2001/XInclude" attribute, but without it any xi:include will
			 * prevent the XML from being parsed. So if we have any instance of xi:include, make a note and upload the file as is.
			 */
			if (fixedResult.indexOf(XI_INCLUDE) != -1)
			{
				final String error = "ERROR! " + file.getName() + ": This topic contains an xi:include, and has been uploaded as is.";
				log.append(error + "\n");
				topicErrors.append(error + "\n");
				uploadFile(topic, fixedResult, file, index, log, topicDetails, topicErrors, tagIds, error);
			}
			else
			{
				/* A collection to keep a hold of any import errors or warnings */
				final List<String> errors = new ArrayList<String>();

				/* parse the XML document into a DOM */
				final Document doc = XMLParser.parse(fixedResult);

				/* what is the top level element */
				Node toplevelNode = doc.getDocumentElement();

				/* Get the node name */
				final String toplevelNodeName = toplevelNode.getNodeName();

				/* sections can be imported directly */
				if (toplevelNodeName.equals("section"))
				{
					// no processing required
				}
				/* appendicies are turned into sections */
				else if (toplevelNodeName.equals("appendix"))
				{
					final String error = file.getName() + ": This topic has had its document element changed from <appendix> to <section>.";
					log.append(error + "\n");
					toplevelNode = replaceNodeWithSection(toplevelNode);
					errors.add(error);
				}
				/* examples are turned into sections */
				else if (toplevelNodeName.equals("example"))
				{
					final String error = file.getName() + ": This topic has had its document element changed from <example> to <section>.";
					log.append(error + "\n");
					toplevelNode = replaceNodeWithSection(toplevelNode);
					errors.add(error);
				}
				/* procedure are turned into sections */
				else if (toplevelNodeName.equals("procedure"))
				{
					final String error = file.getName() + ": This topic has had its document element changed from <procedure> to <section>.";
					log.append(error + "\n");
					toplevelNode = replaceNodeWithSection(toplevelNode);
					errors.add(error);
				}
				/* formalparas are turned into sections */
				else if (toplevelNodeName.equals("formalpara"))
				{
					final String error = file.getName() + ": This topic has had its document element changed from <formalpara> to <section>.";
					log.append(error + "\n");
					toplevelNode = replaceNodeWithSection(toplevelNode);
					errors.add(error);
				}
				/* variablelist are turned into sections */
				else if (toplevelNodeName.equals("variablelist"))
				{
					final String error = file.getName() + ": This topic has had its document element of <variablelist> wrapped in a <section>.";
					log.append(error + "\n");
					toplevelNode = wrapNodeInSection(toplevelNode);
					errors.add(error);
				}
				/* para are turned into sections */
				else if (toplevelNodeName.equals("para"))
				{
					final String error = file.getName() + ": This topic has had its document element of <para> wrapped in a <section>.";
					log.append(error + "\n");
					toplevelNode = wrapNodeInSection(toplevelNode);
					errors.add(error);
				}
				/* tables are wrapped in sections */
				else if (toplevelNodeName.equals("table"))
				{
					final String error = file.getName() + ": This topic has had its document element of <table> wrapped in a <section>.";
					log.append(error + "\n");
					toplevelNode = wrapNodeInSection(toplevelNode);
					errors.add(error);
				}
				/* screens are wrapped in sections */
				else if (toplevelNodeName.equals("screen"))
				{
					final String error = file.getName() + ": This topic has had its document element of <screen> wrapped in a <section>.";
					log.append("\n");
					toplevelNode = wrapNodeInSection(toplevelNode);
					errors.add(error);
				}
				/* tasks are wrapped in sections */
				else if (toplevelNodeName.equals("task"))
				{
					final String error = file.getName() + ": This topic has had its document element of <task> wrapped in a <section>.";
					log.append("\n");
					toplevelNode = wrapNodeInSection(toplevelNode);
					errors.add(error);
				}
				/* Some unknown node type */
				else
				{
					final String error = "ERROR! " + file.getName() + ": This topic uses an unrecognised parent node of <" + toplevelNodeName + ">. No processing has been done for this topic, and the XML has been included as is.";
					log.append(error + "\n");
					topicErrors.append(error + "\n");
					uploadFile(topic, result, file, index, log, topicDetails, topicErrors, tagIds, error);
					return;
				}

				/* some additional validity checks */
				final String additionalErrors = isNodeValid(toplevelNode);

				if (additionalErrors != null && !additionalErrors.isEmpty())
				{
					final String error = "ERROR! " + file.getName() + ":" + additionalErrors;

					log.append(error + "\n");
					topicErrors.append(error + "\n");
					errors.add(error);
				}

				fixTitle(toplevelNode, file.getName());

				/* Upload the processed XML */
				uploadFile(topic, toplevelNode.getOwnerDocument(), file, index, log, topicDetails, topicErrors, tagIds, errors.toArray(new String[0]));
			}

		}
		catch (final Exception ex)
		{
			/* The xml is not valid, so upload as is */
			final String error = "ERROR! " + file.getName() + ": This topic has invalid XML, and has been uploaded as is."; 
			log.append(error + "\n");
			topicErrors.append(error + "\n");
			uploadFile(topic, result, file, index, log, topicDetails, topicErrors, tagIds);
		}
	}

	/**
	 * Adds a title element to any topic that doesn't have a title
	 * 
	 * @param node
	 *            The topic node
	 * @param title
	 *            The value of the title element if one is not already present
	 */
	private void fixTitle(final Node node, final String title)
	{
		if (getFirstChild(node, "title", false) == null)
		{
			final Node titleNode = node.getOwnerDocument().createElement(TITLE_ELEMENT);
			titleNode.appendChild(node.getOwnerDocument().createTextNode(title));

			final NodeList children = node.getChildNodes();

			if (children.getLength() != 0)
				node.insertBefore(titleNode, node.getChildNodes().item(0));
			else
				node.appendChild(titleNode);
		}
	}

	/**
	 * Upload the text as a new topic to the REST server, and then process the next file.
	 * 
	 * @param topicXML
	 *            The topic's XML
	 * @param file
	 *            The file that was used to generate the XML
	 * @param index
	 *            The index of the file from the file load ui element
	 * @param log
	 *            The String that contains the log
	 */
	private void uploadFile(final RESTTopicV1 topic, final String topicXML, final File file, final int index, final StringBuilder log, final StringBuilder topicDetails, final StringBuilder topicErrors, final List<Integer> tagIds, final String... error)
	{
		log.append("-------------------------------------\n");
		log.append("Uploaded file " + file.getName() + "\n");
		log.append("XML Contents is:\n");
		log.append(topicXML + "\n");
		log.append("-------------------------------------\n");

		topic.explicitSetXml(topicXML);
		topic.explicitSetTitle(file.getName());

		uploadTopic(topic, file, index, log, topicDetails, topicErrors, tagIds, error);
	}

	/**
	 * Upload the XML Document as a new topic to the REST server, and then process the next file.
	 * 
	 * @param topicXML
	 *            The topic's XML
	 * @param file
	 *            The file that was used to generate the XML
	 * @param index
	 *            The index of the file from the file load ui element
	 * @param log
	 *            The String that contains the log
	 */
	private void uploadFile(final RESTTopicV1 topic, final Document topicXML, final File file, final int index, final StringBuilder log, final StringBuilder topicDetails, final StringBuilder topicErrors, final List<Integer> tagIds, final String... error)
	{
		log.append("-------------------------------------\n");
		log.append("Uploaded file " + file.getName() + "\n");
		log.append("XML Contents is:\n");
		log.append(topicXML.toString() + "\n");
		log.append("-------------------------------------\n");

		final Node titleNode = getFirstChild(topicXML.getDocumentElement(), TITLE_ELEMENT, false);
		String title = "";
		if (titleNode != null)
		{
			final NodeList children = titleNode.getChildNodes();
			for (int i = 0; i < children.getLength(); ++i)
			{
				final Node child = children.item(i);
				title += child.toString();
			}
		}

		if (title.isEmpty())
			title = file.getName();

		topic.explicitSetXml(topicXML.toString());
		topic.explicitSetTitle(title);

		uploadTopic(topic, file, index, log, topicDetails, topicErrors, tagIds, error);
	}

	private void findExistingTopic(final File file, final int index, final StringBuilder log, final StringBuilder topicDetails, final StringBuilder topicErrors, final List<Integer> tagIds)
	{
		final String originalFileName = display.getFileNamePrefix().getValue() + file.getName();

		final RemoteCallback<RESTTopicCollectionV1> successCallback = new RemoteCallback<RESTTopicCollectionV1>()
		{
			@Override
			public void callback(final RESTTopicCollectionV1 topics)
			{
				final int size = topics.getItems().size();

				final RESTTopicV1 newTopic = new RESTTopicV1();
				newTopic.explicitSetProperties(new RESTAssignedPropertyTagCollectionV1());
				newTopic.explicitSetTags(new RESTTagCollectionV1());

				if (size == 0)
				{
					processFile(newTopic, file, index, log, topicDetails, topicErrors, tagIds);
				}
				else if (size > 0)
				{
					RESTTopicV1 existingTopic = topics.returnItems().get(0);

					final List<RESTAssignedPropertyTagV1> propertyTags = existingTopic.getProperties().returnItems();
					
					/* Make sure the query actually returned a valid topic. We don't want to overwrite an existing topic based on a bad query. */
                    boolean foundOriginalFileNameTag = false;
					for (final RESTAssignedPropertyTagV1 propTag : propertyTags)
					{
						if (ORIGINAL_FILE_NAME_PROPERTY_TAG_ID.equals(propTag.getId()) && originalFileName.equals(propTag.getValue()))
						{
							foundOriginalFileNameTag = true;
							break;
						}
					}

					if (foundOriginalFileNameTag)
					{
						newTopic.setId(existingTopic.getId());

						/*
						 * We set all existing property tags to be removed. These will then be re-added with any new details that may exist in the file when it
						 * is processed again.
						 */

						for (final RESTAssignedPropertyTagV1 propTag : propertyTags)
						{
							final RESTAssignedPropertyTagV1 newTag = new RESTAssignedPropertyTagV1();
							newTag.setId(propTag.getId());
							newTag.setValue(propTag.getValue());

							newTopic.getProperties().addRemoveItem(newTag);
						}
						
						/* Do the same with the tags */
						final List<RESTTagV1> tags = existingTopic.getTags().returnItems();
						for (final RESTTagV1 tag : tags)
						{
							final RESTTagV1 removeTag = new RESTTagV1();
							removeTag.setId(tag.getId());
							
							newTopic.getTags().addRemoveItem(removeTag);
						}

						if (size > 1)
						{
							final String error = "ERROR! " + originalFileName + ": is not unique; " + size + " topics found. Updating the first topic.";
							log.append(error + "\n");
							topicErrors.append(error + "\n");
						}
					}

					processFile(newTopic, file, index, log, topicDetails, topicErrors, tagIds);
				}
			}
		};

		final ErrorCallback errorCallback = new ErrorCallback()
		{
			@Override
			public boolean error(final Message message, final Throwable throwable)
			{
				final String error = "ERROR! REST call to find existing topics failed.";
				log.append(error + "\n");
				topicErrors.append(error + "\n");
				processingDone(log, topicDetails, topicErrors);
				return true;
			}
		};

		final RESTInterfaceV1 restMethod = RestClient.create(RESTInterfaceV1.class, successCallback, errorCallback);

		try
		{
			final String query = "query;propertyTag" + ORIGINAL_FILE_NAME_PROPERTY_TAG_ID + "=" + URL.encodePathSegment(originalFileName);
			restMethod.getJSONTopicsWithQuery(new PathSegmentImpl(query), PROPERTY_TAG_EXPAND);
		}
		catch (final Exception ex)
		{
			final String error = "ERROR! REST call to find existing topics failed."; 
			log.append(error + "n");
			topicErrors.append(error + "\n");
			processingDone(log, topicDetails, topicErrors);
		}
	}

	private void uploadTopic(final RESTTopicV1 topic, final File file, final int index, final StringBuilder log, final StringBuilder topicDetails, final StringBuilder topicErrors, final List<Integer> tagIds, final String... error)
	{
		final RemoteCallback<RESTTopicV1> successCallback = new RemoteCallback<RESTTopicV1>()
		{
			@Override
			public void callback(final RESTTopicV1 image)
			{
				final String mapping = image.getId() + ": " + file.getName(); 
				log.append(mapping + "\n");
				topicDetails.append(mapping + "\n");
				pocessFiles(index + 1, log, topicDetails, topicErrors, tagIds);
			}
		};

		final ErrorCallback errorCallback = new ErrorCallback()
		{
			@Override
			public boolean error(Message message, Throwable throwable)
			{
				final String error = "ERROR! Upload of " + file.getName() + " was a failure.";
				log.append(error + "\n");
				topicErrors.append(error + "\n");
				pocessFiles(index + 1, log, topicDetails, topicErrors, tagIds);
				return true;
			}
		};

		final RESTInterfaceV1 restMethod = RestClient.create(RESTInterfaceV1.class, successCallback, errorCallback);

		try
		{
			/* Add the errors as property tags */
			for (final String err : error)
			{
				final RESTAssignedPropertyTagV1 propTag = new RESTAssignedPropertyTagV1();
				propTag.setId(TOPIC_IMPORT_ERRORS_PROPERTY_TAG_ID);
				propTag.setValue(err);

				topic.getProperties().addNewItem(propTag);
			}

			/* Add the original file name as a property tag */
			final String originalFileName = display.getFileNamePrefix().getValue() + file.getName();
			final RESTAssignedPropertyTagV1 propTag = new RESTAssignedPropertyTagV1();
			propTag.setId(ORIGINAL_FILE_NAME_PROPERTY_TAG_ID);
			propTag.setValue(originalFileName);
			topic.getProperties().addNewItem(propTag);
			
			/* Add the topic tags */
			for (final Integer tagId : tagIds)
			{
				final RESTTagV1 tag = new RESTTagV1();
				tag.setId(tagId);
				topic.getTags().addNewItem(tag);
			}			

			/* If the topic id is not null, it means we found an existing topic with the same original file name. In this case, we update the existing topic. */
			if (topic.getId() != null)
				restMethod.updateJSONTopic("", topic);
			/* Otherwise we create a new topic */
			else
				restMethod.createJSONTopic("", topic);
		}
		catch (final Exception ex)
		{
			final String myError = "ERROR! Upload of " + file.getName() + " was a failure.";			
			log.append(myError + "\n");
			topicErrors.append(myError + "\n");
			pocessFiles(index + 1, log, topicDetails, topicErrors, tagIds);
		}
	}

	/**
	 * Create a new Document with a <section> document element node that contains the children of the supplied node. This is to work around the lack of a
	 * Document.renameNode() method.
	 * 
	 * @param node
	 *            The node to be replaced
	 * @return The new section node in the new document
	 */
	private Node replaceNodeWithSection(final Node node)
	{
		final Document doc = XMLParser.createDocument();
		final Node section = doc.createElement(SECTION_ELEMENT);
		doc.appendChild(section);

		final NodeList children = node.getChildNodes();

		for (int i = 0; i < children.getLength(); ++i)
		{
			final Node childNode = children.item(i);
			final Node importedNode = doc.importNode(childNode, true);
			section.appendChild(importedNode);
		}

		return section;
	}

	/**
	 * Create a new Document with a <section> document element node that contains the the supplied node.
	 * 
	 * @param node
	 *            The node to be wrapped in a section
	 * @return The new section node in the new document
	 */
	private Node wrapNodeInSection(final Node node)
	{
		final Document doc = XMLParser.createDocument();
		final Node section = doc.createElement(SECTION_ELEMENT);
		doc.appendChild(section);

		final Node importedNode = doc.importNode(node, true);
		section.appendChild(importedNode);

		return section;
	}

	/**
	 * Perform some validity checks on the topic
	 * 
	 * @param node
	 *            The node that holds the topic
	 * @return any errors that were found
	 */
	private String isNodeValid(final Node node)
	{
		final StringBuilder builder = new StringBuilder();

		if (getFirstChild(node, "section", true) != null)
			builder.append(" This topic has illegal nested sections.");
		if (getFirstChild(node, "xref", true) != null)
			builder.append(" This topic has illegal xrefs.");
		if (getFirstChild(node, "title", false) == null)
			builder.append(" This topic has no title.");

		return builder.toString();
	}

	/**
	 * Scans a XML document for a node with the given name
	 * 
	 * @param node
	 *            The node to search
	 * @param nodeName
	 *            The name of the node to find
	 * @param recursive
	 *            true if a search is to be done on the children as well, false otherwise
	 * @return null if no node is found, or the first node with the supplied name that was found
	 */
	private Node getFirstChild(final Node node, final String nodeName, final boolean recursive)
	{
		final NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i)
		{
			final Node child = children.item(i);

			// TODO: check URI (e.g. for xi:include)

			if (child.getNodeName().equals(nodeName))
				return child;
		}

		if (recursive)
		{
			for (int i = 0; i < children.getLength(); ++i)
			{
				final Node child = children.item(i);
				final Node namedChild = getFirstChild(child, nodeName, recursive);
				if (namedChild != null)
					return namedChild;
			}
		}

		return null;
	}

	private void enableUI(final boolean enabled)
	{
		display.getFileList().setEnabled(enabled);
		display.getGoButton().setEnabled(enabled);
		display.getTagIds().setEnabled(enabled);
		display.getUpload().setEnabled(enabled);
		display.getProgress().setVisible(!enabled);
	}

	@Override
	public void go(final HasWidgets container)
	{
		/* Init the REST service */
		RestClient.setApplicationRoot(REST_SERVER);
		RestClient.setJacksonMarshallingActive(true);

		bind();
		container.clear();
		container.add(display.asWidget());
	}
}
