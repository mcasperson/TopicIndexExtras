package com.redhat.topicindex.extras.client.local.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.redhat.topicindex.extras.client.local.Presenter;
import com.redhat.topicindex.extras.client.local.RESTInterfaceV1;
import com.redhat.topicindex.rest.collections.RESTImageCollectionV1;
import com.redhat.topicindex.rest.collections.RESTTopicCollectionV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTImageV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTLanguageImageV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTopicV1;
import com.smartgwt.client.widgets.Progressbar;

class ImageReplacementDetails
{
	private final Integer imageID;
	private final String docbookFileName;
	private final String fileRef;

	public Integer getImageID()
	{
		return imageID;
	}

	public String getFileRef()
	{
		return fileRef;
	}

	public ImageReplacementDetails(final Integer imageID, final String fileRef, final String docbookFileName)
	{
		this.imageID = imageID;
		this.fileRef = fileRef;
		this.docbookFileName = docbookFileName;
	}

	public String getDocbookFileName()
	{
		return docbookFileName;
	}
}

@Dependent
public class BulkImageUpdaterPresenter implements Presenter
{
	private static final String BASE_URL = "http://skynet-dev.usersys.redhat.com:8080/TopicIndex/";
	// private static final String BASE_URL = "http://localhost.usersys.redhat.com:8080/TopicIndex/";
	// private static final String BASE_URL = "http://skynet.usersys.redhat.com:8080/TopicIndex/";

	private static final String REST_SERVER = BASE_URL + "seam/resource/rest";
	private static final String IMAGE_VIEW_URL = BASE_URL + "ImageFile.seam?imageFileImageFileId=";

	/** Topics expansion string */
	private static final String PROPERTY_TAG_EXPAND = "{\"branches\":[{\"trunk\":{\"name\":\"topics\"},\"branches\":[{\"trunk\":{\"name\":\"properties\"}}]}]}";

	/** Topic expansion */
	private static final String TOPIC_PROPERTY_TAG_EXPAND = "{\"branches\":[{\"trunk\":{\"name\":\"properties\"}}]}";

	/** Images expansion string */
	private static final String IMAGES_EXPAND = "{\"branches\":[{\"trunk\":{\"name\":\"images\"},\"branches\":[{\"trunk\":{\"name\":\"languageimages\"}}]}]}";

	private static final String IMAGEDATAS_FILEREF_RE = "<imagedata.+?fileref=\"(.+?)\"";
	private static final RegExp IMAGEDATAS_FILEREF_REGEXP = RegExp.compile(IMAGEDATAS_FILEREF_RE, "g");

	// private static final String SEARCH_URL_RE = "^http://(.*?)(:\\d+)?/TopicIndex/CustomSearchTopicList.seam([?].*?)(&cid=\\d+)?$";
	// private static final RegExp SEARCH_URL_RE_REGEXP = RegExp.compile(SEARCH_URL_RE);

	public interface Display
	{
		TextArea getXml();

		Button getBulkUpdate();

		Progressbar getProgress();

		ListBox getTopicMatches();

		Button getGo();

		TextBox getTopicSearch();

		ListBox getImageMatches();

		Button getUpdate();

		TextArea getLog();

		Widget asWidget();

		Button getUpdateTopic();

		Button getUpdateImage();
	}

	@Inject
	private Display display;

	private Map<RESTTopicV1, List<ImageReplacementDetails>> imageReplacements;
	private StringBuilder log;

	public void bind()
	{
		display.getBulkUpdate().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				try
				{
					enableUI(false);

					final float size = imageReplacements.size();
					int i = 0;

					for (final RESTTopicV1 topic : imageReplacements.keySet())
					{
						final int percentDone = (int) (i / size * 100);
						display.getProgress().setPercentDone(percentDone);

						updateAllOneToOneImages(topic);
						++i;
					}
				}
				finally
				{
					done();
				}
			}
		});

		display.getUpdateTopic().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				try
				{
					enableUI(false);
					final RESTTopicV1 topic = getSelectedTopic();
					updateAllOneToOneImages(topic);
				}
				finally
				{
					done();
				}
			}
		});

		display.getUpdateImage().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				try
				{
					enableUI(false);
					final RESTTopicV1 topic = getSelectedTopic();
					final ImageReplacementDetails imgReplace = getSelectedImageReplacementDetails();
					if (imgReplace != null && topic != null)
					{
						enableUI(false);
						final RESTTopicV1 newTopic = new RESTTopicV1();
						newTopic.setId(topic.getId());
						newTopic.explicitSetXml(topic.getXml().replace(imgReplace.getFileRef(), "images/" + imgReplace.getDocbookFileName()));
						updateTopic(topic, newTopic, new ArrayList<ImageReplacementDetails>()
						{
							{
								add(imgReplace);
							}
						});
					}
				}
				finally
				{
					done();
				}
			}
		});

		display.getGo().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				/* Start processing the files. We create a chain of methods to simulate synchronous processing */
				clearUI();
				enableUI(false);
				try
				{
					getTopics();
				}
				catch (final Exception ex)
				{
					enableUI(true);
				}
			}
		});

		display.getTopicMatches().addChangeHandler(new ChangeHandler()
		{
			@Override
			public void onChange(final ChangeEvent event)
			{
				updateImageList();
			}
		});

		display.getImageMatches().addChangeHandler(new ChangeHandler()
		{
			@Override
			public void onChange(final ChangeEvent event)
			{
				final RESTTopicV1 topic = getSelectedTopic();
				final ImageReplacementDetails imgReplace = getSelectedImageReplacementDetails();
				if (imgReplace != null && topic != null)
				{
					final int startTextFindIndex = topic.getXml().indexOf(imgReplace.getFileRef());

					display.getXml().setText(topic.getXml());
					display.getXml().setSelectionRange(startTextFindIndex, imgReplace.getFileRef().length());
					display.getXml().setFocus(true);
				}
				else
				{
					display.getXml().setText("");
				}
			}
		});

		display.getImageMatches().addDoubleClickHandler(new DoubleClickHandler()
		{

			@Override
			public void onDoubleClick(DoubleClickEvent event)
			{
				final ImageReplacementDetails imgReplace = getSelectedImageReplacementDetails();
				if (imgReplace != null)
				{
					Window.open(IMAGE_VIEW_URL + imgReplace.getImageID(), "_blank", "");
				}
			}
		});

	}

	private void updateAllOneToOneImages(final RESTTopicV1 topic)
	{
		if (imageReplacements.containsKey(topic))
		{
			final RESTTopicV1 newTopic = new RESTTopicV1();
			newTopic.setId(topic.getId());
			newTopic.explicitSetXml(topic.getXml());

			final List<ImageReplacementDetails> images = imageReplacements.get(topic);
			final List<ImageReplacementDetails> processedImages = new ArrayList<ImageReplacementDetails>();

			for (final ImageReplacementDetails imgReplace : images)
			{
				/* Do a second loop through to make sure that the image we are replacing doesn't have more than one option */
				boolean oneToOne = true;
				for (final ImageReplacementDetails imgReplace2 : images)
				{
					if (imgReplace != imgReplace2 && imgReplace.getFileRef().equals(imgReplace2.getFileRef()))
					{
						oneToOne = false;
						log.append("Topic " + topic.getId() + " has an ambiguous image reference " + imgReplace.getFileRef() + "  can can point to " + imgReplace.getImageID() + " or " + imgReplace2.getImageID() + "\n");
						break;
					}
				}

				if (oneToOne)
				{
					newTopic.setXml(newTopic.getXml().replace(imgReplace.getFileRef(), "images/" + imgReplace.getDocbookFileName()));
					processedImages.add(imgReplace);
				}
			}

			log.append("Topic " + topic.getId() + " had " + processedImages.size() + " image references updated.\n");

			/* Proceed only if we have actually found some images that can be replaced */
			if (processedImages.size() != 0)
			{
				updateTopic(topic, newTopic, processedImages);
			}

		}
	}

	private void updateTopicList()
	{
		display.getTopicMatches().clear();
		display.getXml().setText("");

		for (final RESTTopicV1 topic : imageReplacements.keySet())
		{
			final List<ImageReplacementDetails> replacementImages = imageReplacements.get(topic);

			display.getTopicMatches().addItem(topic.getId() + ": " + topic.getTitle() + " - " + replacementImages.size(), topic.getId().toString());
		}
	}

	private void updateImageList()
	{
		try
		{
			display.getImageMatches().clear();
			display.getXml().setText("");

			final RESTTopicV1 topic = getSelectedTopic();

			if (topic != null)
			{
				display.getXml().setText(topic.getXml());

				for (int i = 0; i < imageReplacements.get(topic).size(); ++i)
				{
					final ImageReplacementDetails imgReplace = imageReplacements.get(topic).get(i);
					display.getImageMatches().addItem(imgReplace.getImageID() + ": " + imgReplace.getFileRef(), i + "");
				}
			}
		}
		catch (final NumberFormatException ex)
		{

		}
	}

	/**
	 * Uploads the contents of newTopic, which has been updated with the details in imagReplace, and if successful copies in the new version of the topic into
	 * oldTopic, and removes the processed images.
	 * 
	 * @param oldTopic
	 *            The existing topic
	 * @param newTopic
	 *            The new topic to be saved
	 * @param imgReplace
	 *            The images that were used to update the xml found in newTopic
	 */
	private void updateTopic(final RESTTopicV1 oldTopic, final RESTTopicV1 newTopic, final List<ImageReplacementDetails> imgReplacments)
	{
		final RemoteCallback<RESTTopicV1> successCallback = new RemoteCallback<RESTTopicV1>()
		{
			@Override
			public void callback(final RESTTopicV1 topics)
			{
				final String message = "Successfully updated Topic " + newTopic.getId();
				log.append(message + "\n");

				newTopic.cloneInto(oldTopic, false);

				/* The image has been replaced, so remove it from the list */
				for (final ImageReplacementDetails imgReplace : imgReplacments)
				{
					imageReplacements.get(oldTopic).remove(imgReplace);
				}

				/* If that was the last image to be replaced, remove the image from the list */
				if (imageReplacements.get(oldTopic).size() == 0)
				{
					imageReplacements.remove(oldTopic);
					updateTopicList();
				}

				updateImageList();
			}
		};

		final ErrorCallback errorCallback = new ErrorCallback()
		{
			@Override
			public boolean error(final Message message, final Throwable throwable)
			{
				final String error = "ERROR! REST call to update topic failed with a HTTP error.\nMessage: " + message + "\nException:  " + throwable.toString();
				log.append(error + "\n");
				return true;
			}
		};

		final RESTInterfaceV1 restMethod = RestClient.create(RESTInterfaceV1.class, successCallback, errorCallback);

		try
		{
			System.out.println("Updating Topic via REST interface");

			restMethod.updateJSONTopic(TOPIC_PROPERTY_TAG_EXPAND, newTopic);
		}
		catch (final Exception ex)
		{
			final String error = "ERROR! REST call to update topic failed with an exception.";
			log.append(error + "\n");
		}
	}

	private RESTTopicV1 getSelectedTopic()
	{
		try
		{
			if (display.getTopicMatches().getSelectedIndex() != -1)
			{
				final Integer topicID = Integer.parseInt(display.getTopicMatches().getValue(display.getTopicMatches().getSelectedIndex()));

				for (final RESTTopicV1 topic : imageReplacements.keySet())
				{
					if (topicID.equals(topic.getId()))
						return topic;
				}
			}
		}
		catch (final NumberFormatException ex)
		{

		}

		return null;
	}

	private ImageReplacementDetails getSelectedImageReplacementDetails()
	{
		try
		{
			if (display.getTopicMatches().getSelectedIndex() != -1 && display.getImageMatches().getSelectedIndex() != -1)
			{
				final Integer topicID = Integer.parseInt(display.getTopicMatches().getValue(display.getTopicMatches().getSelectedIndex()));
				final Integer matchIndex = Integer.parseInt(display.getImageMatches().getValue(display.getImageMatches().getSelectedIndex()));

				for (final RESTTopicV1 topic : imageReplacements.keySet())
				{
					if (topicID.equals(topic.getId()))
					{
						final ImageReplacementDetails imgReplace = imageReplacements.get(topic).get(matchIndex);
						return imgReplace;
					}
				}
			}
		}
		catch (final NumberFormatException ex)
		{

		}

		return null;
	}

	private void getTopics()
	{
		final RemoteCallback<RESTTopicCollectionV1> successCallback = new RemoteCallback<RESTTopicCollectionV1>()
		{
			@Override
			public void callback(final RESTTopicCollectionV1 topics)
			{
				final String message = topics.getItems().size() + " topics returned.";
				log.append(message + "\n");
				System.out.println(message);
				getImages(topics);
			}
		};

		final ErrorCallback errorCallback = new ErrorCallback()
		{
			@Override
			public boolean error(final Message message, final Throwable throwable)
			{
				final String error = "ERROR! REST call to find topics failed with a HTTP error.\nMessage: " + message + "\nException:  " + throwable.toString();
				log.append(error + "\n");
				done();
				return true;
			}
		};

		final RESTInterfaceV1 restMethod = RestClient.create(RESTInterfaceV1.class, successCallback, errorCallback);

		try
		{
			/*
			 * final MatchResult results = SEARCH_URL_RE_REGEXP.exec(display.getTopicSearch().getValue().trim()); if (results != null) { final String hostname =
			 * results.getGroup(1); final String port = results.getGroup(2);
			 * 
			 * final String restURL = "http://" + hostname + (port == null ? "" : port) + "/TopicIndex/seam/resource/rest";
			 * 
			 * final String query = results.getGroup(3);
			 * 
			 * final String restQuery = query.replace("?", "query;").replaceAll("&", ";"); }
			 */

			final Integer tagId = Integer.parseInt(display.getTopicSearch().getText());

			System.out.println("Getting Topics from REST interface");

			restMethod.getJSONTopicsWithQuery(tagId, PROPERTY_TAG_EXPAND);
		}
		catch (final Exception ex)
		{
			final String error = "ERROR! REST call to find topics failed with an exception.";
			log.append(error + "\n");
			done();
		}

	}

	private void getImages(final RESTTopicCollectionV1 topics)
	{
		final RemoteCallback<RESTImageCollectionV1> successCallback = new RemoteCallback<RESTImageCollectionV1>()
		{
			@Override
			public void callback(final RESTImageCollectionV1 images)
			{
				final String message = images.getItems().size() + " images returned.";
				log.append(message + "\n");
				System.out.println(message);
				processImagesAndTopics(topics, images);
			}
		};

		final ErrorCallback errorCallback = new ErrorCallback()
		{
			@Override
			public boolean error(final Message message, final Throwable throwable)
			{
				final String error = "ERROR! REST call to find images failed with a HTTP error.\nMessage: " + message + "\nException:  " + throwable.toString();
				log.append(error + "\n");
				done();
				return true;
			}
		};

		final RESTInterfaceV1 restMethod = RestClient.create(RESTInterfaceV1.class, successCallback, errorCallback);

		try
		{
			System.out.println("Getting Images from REST interface");
			display.getProgress().setPercentDone(50);
			restMethod.getJSONImages(IMAGES_EXPAND);
		}
		catch (final Exception ex)
		{
			final String error = "ERROR! REST call to find images failed with an exception.";
			log.append(error + "\n");
			done();
		}
	}

	private void processImagesAndTopics(final RESTTopicCollectionV1 topics, final RESTImageCollectionV1 images)
	{
		imageReplacements = new HashMap<RESTTopicV1, List<ImageReplacementDetails>>();

		for (final RESTTopicV1 topic : topics.getItems())
		{
			/* Get the results for all the images and their filerefs */

			for (MatchResult result = IMAGEDATAS_FILEREF_REGEXP.exec(topic.getXml()); result != null; result = IMAGEDATAS_FILEREF_REGEXP.exec(topic.getXml()))
			{
				final String fileref = result.getGroup(1);

				final String[] fileRefPathCompnents = fileref.trim().split("[\\/]");

				if (fileRefPathCompnents.length != 0)
				{
					final String fileName = fileRefPathCompnents[fileRefPathCompnents.length - 1];

					/* Loop over all the images */
					for (final RESTImageV1 image : images.getItems())
					{
						/* Loop over all the image locales */
						for (final RESTLanguageImageV1 langImage : image.getLanguageImages_OTM().getItems())
						{
							/* Find a matching locale */
							if (langImage.getLocale().equals(topic.getLocale()))
							{
								final String[] langImagePathCompnents = langImage.getFilename().trim().split("[\\/]");
								final String[] langImageExtensionCompnents = langImage.getFilename().trim().split("[.]");

								if (langImagePathCompnents.length != 0 && langImageExtensionCompnents.length != 0)
								{
									final String originalFileName = langImagePathCompnents[langImagePathCompnents.length - 1];
									final String originalFileNameExtension = langImageExtensionCompnents[langImageExtensionCompnents.length - 1];

									/* match file names ignoring case */
									if (fileName.toLowerCase().equals(originalFileName.toLowerCase()))
									{
										final String message = "Found a replacement image for topic " + topic.getId() + " and image " + image.getId() + " for fileref " + fileref;
										log.append(message + "\n");
										System.out.println(message);

										if (!imageReplacements.containsKey(topic))
											imageReplacements.put(topic, new ArrayList<ImageReplacementDetails>());

										imageReplacements.get(topic).add(new ImageReplacementDetails(image.getId(), fileref, image.getId() + "." + originalFileNameExtension));
									}
								}
							}
						}
					}
				}

			}
		}

		updateTopicList();
		updateImageList();
		done();
	}

	private void done()
	{
		display.getLog().setText(log.toString());
		enableUI(true);
	}

	private void clearUI()
	{
		display.getImageMatches().clear();
		display.getLog().setText("");
		display.getTopicMatches().clear();
		display.getXml().setText("");
		log = new StringBuilder();
	}

	private void enableUI(final boolean enabled)
	{
		display.getGo().setEnabled(enabled);
		display.getImageMatches().setEnabled(enabled);
		display.getLog().setEnabled(enabled);
		display.getProgress().setVisible(!enabled);
		display.getTopicMatches().setEnabled(enabled);
		display.getTopicSearch().setEnabled(enabled);
		display.getUpdate().setEnabled(enabled);
		display.getXml().setEnabled(enabled);
		display.getUpdateTopic().setEnabled(enabled);
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
		enableUI(true);
		clearUI();
	}
}
