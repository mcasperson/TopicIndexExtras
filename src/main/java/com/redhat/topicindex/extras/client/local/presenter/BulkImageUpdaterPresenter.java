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
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
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
	private Integer imageID;
	private String fileRef;

	public Integer getImageID()
	{
		return imageID;
	}

	public void setImageID(Integer imageID)
	{
		this.imageID = imageID;
	}

	public String getFileRef()
	{
		return fileRef;
	}

	public void setFileRef(String fileRef)
	{
		this.fileRef = fileRef;
	}

	public ImageReplacementDetails(final Integer imageID, final String fileRef)
	{
		this.imageID = imageID;
		this.fileRef = fileRef;
	}
}

@Dependent
public class BulkImageUpdaterPresenter implements Presenter
{
	// private static final String REST_SERVER = "http://localhost:8080/TopicIndex/seam/resource/rest";
	private static final String REST_SERVER = "http://skynet-dev.usersys.redhat.com:8080/TopicIndex/seam/resource/rest";
	// private static final String REST_SERVER = "http://skynet.usersys.redhat.com:8080/TopicIndex/seam/resource/rest";

	/** Topics expansion string */
	private static final String PROPERTY_TAG_EXPAND = "{\"branches\":[{\"trunk\":{\"name\":\"topics\"},\"branches\":[{\"trunk\":{\"name\":\"properties\"}}]}]}";

	private static final String IMAGEDATA_FILEREF_RE = "<imagedata.*?fileref=\"(.*?)\"";
	private static final RegExp IMAGEDATA_FILEREF_REGEXP = RegExp.compile(IMAGEDATA_FILEREF_RE);

	private static final String SEARCH_URL_RE = "^http://(.*?)(:\\d+)?/TopicIndex/CustomSearchTopicList.seam([?].*?)(&cid=\\d+)?$";
	private static final RegExp SEARCH_URL_RE_REGEXP = RegExp.compile(SEARCH_URL_RE);

	public interface Display
	{
		Button getBulkUpdate();

		Progressbar getProgress();

		ListBox getTopicMatches();

		Button getGo();

		TextBox getTopicSearch();

		ListBox getImageMatches();

		Button getUpdate();

		TextArea getLog();

		Widget asWidget();
	}

	@Inject
	private Display display;

	private RESTTopicCollectionV1 topics;
	private RESTImageCollectionV1 images;
	private Map<RESTTopicV1, List<ImageReplacementDetails>> imageReplacements;

	public void bind()
	{
		display.getGo().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				/* Start processing the files. We create a chain of methods to simulate synchronous processing */
				final StringBuilder log = new StringBuilder();
				clearUI();
				enableUI(false);
				try
				{
					getTopics(log);
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
				try
				{
					display.getImageMatches().clear();
					
					final Integer topicID = Integer.parseInt(display.getTopicMatches().getValue(display.getTopicMatches().getSelectedIndex()));
					
					for (final RESTTopicV1 topic : imageReplacements.keySet())
					{
						if (topicID.equals(topic.getId()))
						{
							for (final ImageReplacementDetails imgReplace : imageReplacements.get(topic))
							{
								display.getImageMatches().addItem(imgReplace.getImageID() + ": " + imgReplace.getFileRef(), imgReplace.getImageID().toString());
							}
						}
					}
				}
				catch (final NumberFormatException ex)
				{

				}

			}
		});
	}

	private void getTopics(final StringBuilder log)
	{
		final RemoteCallback<RESTTopicCollectionV1> successCallback = new RemoteCallback<RESTTopicCollectionV1>()
		{
			@Override
			public void callback(final RESTTopicCollectionV1 topics)
			{
				System.out.println(topics.getItems().size() + " topics returned.");
				BulkImageUpdaterPresenter.this.topics = topics;
				getImages(log);
			}
		};

		final ErrorCallback errorCallback = new ErrorCallback()
		{
			@Override
			public boolean error(final Message message, final Throwable throwable)
			{
				final String error = "ERROR! REST call to find topics failed with a HTTP error.";
				log.append(error + "\n");
				done(log);
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

			System.out.println("Calling REST method");

			restMethod.getJSONTopicsWithQuery(tagId, PROPERTY_TAG_EXPAND);
		}
		catch (final Exception ex)
		{
			final String error = "ERROR! REST call to find topics failed with an exception.";
			log.append(error + "\n");
			done(log);
		}

	}

	private void getImages(final StringBuilder log)
	{
		final RemoteCallback<RESTImageCollectionV1> successCallback = new RemoteCallback<RESTImageCollectionV1>()
		{
			@Override
			public void callback(final RESTImageCollectionV1 images)
			{
				System.out.println(images.getItems().size() + " images returned.");
				BulkImageUpdaterPresenter.this.images = images;
				processImagesAndTopics(log);
			}
		};

		final ErrorCallback errorCallback = new ErrorCallback()
		{
			@Override
			public boolean error(final Message message, final Throwable throwable)
			{
				final String error = "ERROR! REST call to find images failed with a HTTP error.";
				log.append(error + "\n");
				done(log);
				return true;
			}
		};

		final RESTInterfaceV1 restMethod = RestClient.create(RESTInterfaceV1.class, successCallback, errorCallback);

		try
		{
			System.out.println("Calling REST method");

			restMethod.getJSONImages("");
		}
		catch (final Exception ex)
		{
			final String error = "ERROR! REST call to find images failed with an exception.";
			log.append(error + "\n");
			done(log);
		}
	}

	private void processImagesAndTopics(final StringBuilder log)
	{
		imageReplacements = new HashMap<RESTTopicV1, List<ImageReplacementDetails>>();

		for (final RESTTopicV1 topic : topics.getItems())
		{
			final MatchResult result = IMAGEDATA_FILEREF_REGEXP.exec(topic.getXml());

			if (result != null)
			{
				/* Loop over the filerefs found in the XML */
				for (int i = 0; i < result.getGroupCount(); ++i)
				{
					final String fileref = result.getGroup(i);
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
									if (langImagePathCompnents.length != 0)
									{
										final String originalFileName = langImagePathCompnents[langImagePathCompnents.length - 1];

										/* match file names ignoring case */
										if (fileName.toLowerCase().equals(originalFileName.toLowerCase()))
										{
											if (!imageReplacements.containsKey(topic))
												imageReplacements.put(topic, new ArrayList<ImageReplacementDetails>());

											imageReplacements.get(topic).add(new ImageReplacementDetails(image.getId(), fileref));
										}
									}
								}
							}
						}
					}
				}
			}
		}

		displayResults(log);
	}

	private void displayResults(final StringBuilder log)
	{
		for (final RESTTopicV1 topic : imageReplacements.keySet())
		{
			final List<ImageReplacementDetails> replacementImages = imageReplacements.get(topic);

			display.getTopicMatches().addItem(topic.getId() + ": " + topic.getTitle(), topic.getId().toString() + " - " + replacementImages.size());
		}
	}

	private void done(final StringBuilder log)
	{
		display.getLog().setText(log.toString());
		enableUI(true);
	}

	private void clearUI()
	{
		display.getImageMatches().clear();
		display.getLog().setText("");
		display.getTopicMatches().clear();
	}

	private void enableUI(final boolean enabled)
	{
		display.getBulkUpdate().setEnabled(enabled);
		display.getGo().setEnabled(enabled);
		display.getImageMatches().setEnabled(enabled);
		display.getLog().setEnabled(enabled);
		display.getProgress().setVisible(!enabled);
		display.getTopicMatches().setEnabled(enabled);
		display.getTopicSearch().setEnabled(enabled);
		display.getUpdate().setEnabled(enabled);
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
