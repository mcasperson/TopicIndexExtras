package com.redhat.topicindex.extras.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.redhat.topicindex.rest.collections.RESTTopicCollectionV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTopicV1;
import com.smartgwt.client.widgets.Progressbar;

@Dependent
public class BulkImageUpdaterPresenter implements Presenter
{
	// private static final String REST_SERVER = "http://localhost:8080/TopicIndex/seam/resource/rest";
	private static final String REST_SERVER = "http://skynet-dev.usersys.redhat.com:8080/TopicIndex/seam/resource/rest";
	// private static final String REST_SERVER = "http://skynet.usersys.redhat.com:8080/TopicIndex/seam/resource/rest";
	
	/** Topics expansion string */
	private static final String TOPICS_EXPAND = "{\"branches\":[{\"trunk\":{\"name\":\"topics\"}}]}";

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

	public void bind()
	{
		display.getGo().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				/* Start processing the files. We create a chain of methods to simulate synchronous processing */
				final StringBuilder log = new StringBuilder();
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
	}

	private void getTopics(final StringBuilder log)
	{
		/*final MatchResult results = SEARCH_URL_RE_REGEXP.exec(display.getTopicSearch().getValue().trim());
		
		if (results != null)
		{*/
			final RemoteCallback<RESTTopicCollectionV1> successCallback = new RemoteCallback<RESTTopicCollectionV1>()
			{
				@Override
				public void callback(final RESTTopicCollectionV1 topics)
				{
					System.out.println(topics.getItems().size() + " topics returned.");
					
					for (final RESTTopicV1 topic : topics.getItems())
					{
						display.getTopicMatches().addItem(topic.getId() + ": " + topic.getTitle(), topic.getId().toString());
					}
					
					done(log);
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
				/*final String hostname = results.getGroup(1);
				final String port = results.getGroup(2);				
				
				final String restURL = "http://" + hostname + (port == null ? "" : port) + "/TopicIndex/seam/resource/rest";
				
				final String query = results.getGroup(3);
				
				final String restQuery = query.replace("?", "query;").replaceAll("&", ";");*/
				
				final Integer tagId = Integer.parseInt(display.getTopicSearch().getText());				
				
				System.out.println("Calling REST method");
				
				restMethod.getJSONTopicsWithQuery(tagId, TOPICS_EXPAND);
			}
			catch (final Exception ex)
			{
				final String error = "ERROR! REST call to find topics failed with an exception.";
				log.append(error + "\n");
				done(log);
			}
		//}
	}
	
	private void done(final StringBuilder log)
	{
		display.getLog().setText(log.toString());
		enableUI(true);
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
