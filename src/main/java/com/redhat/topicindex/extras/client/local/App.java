package com.redhat.topicindex.extras.client.local;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.vectomatic.file.File;
import org.vectomatic.file.FileList;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.FileUploadExt;
import org.vectomatic.file.events.LoadEndEvent;
import org.vectomatic.file.events.LoadEndHandler;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.redhat.topicindex.rest.entities.interfaces.RESTImageV1;

@EntryPoint
public class App
{
	private static final String RESTIMAGEV1_FILENAME_RE = "\"filename\":\"(.*?)\"";
	private static final RegExp RESTIMAGEV1_FILENAME_EXP = RegExp.compile(RESTIMAGEV1_FILENAME_RE);
	private static final String RESTIMAGEV1_ID_RE = "\"id\":(\\d+)";
	private static final RegExp RESTIMAGEV1_ID_EXP = RegExp.compile(RESTIMAGEV1_ID_RE);
	private static final String REST_SERVER = "http://localhost:8080/TopicIndex/seam/resource/rest";
	// private static final String REST_SERVER = "http://skynet-dev.usersys.redhat.com:8080/TopicIndex/seam/resource/rest";

	private TextArea resultsTextArea;

	@PostConstruct
	public void init()
	{
		/* Init the REST service */
		RestClient.setApplicationRoot(REST_SERVER);
		RestClient.setJacksonMarshallingActive(true);

		final VerticalPanel verticalPanel = new VerticalPanel();

		final Label loadImagesLabel = new Label("Please select the images you wish to upload");
		verticalPanel.add(loadImagesLabel);

		final FileUploadExt upload = new FileUploadExt();
		verticalPanel.add(upload);

		upload.addChangeHandler(new ChangeHandler()
		{
			@Override
			public void onChange(final ChangeEvent event)
			{
				final FileList files = upload.getFiles();
				final StringBuilder text = new StringBuilder();
				for (int i = 0; i < files.getLength(); ++i)
				{
					processImage(files.getItem(i), text, i == files.getLength() - 1);
				}
			}
		});

		final Label resultsLabel = new Label("The results below show the original file names, and the new CCMS file names");
		verticalPanel.add(resultsLabel);

		resultsTextArea = new TextArea();
		resultsTextArea.setReadOnly(true);
		verticalPanel.add(resultsTextArea);

		RootPanel.get().add(verticalPanel);
	}

	private void processImage(final File file, final StringBuilder results, final boolean lastFile)
	{
		final RemoteCallback<String> genericSuccessCallback = new RemoteCallback<String>()
		{
			@Override
			public void callback(final String retValue)
			{
				/* temp workaround for bug at https://community.jboss.org/thread/200710?tstart=0 */				
				final MatchResult filenameMatcher = RESTIMAGEV1_FILENAME_EXP.exec(retValue);
				final boolean filenameMatchFound = RESTIMAGEV1_FILENAME_EXP.test(retValue);
				
				final MatchResult idMatcher = RESTIMAGEV1_ID_EXP.exec(retValue);
				final boolean idMatchFound = RESTIMAGEV1_ID_EXP.test(retValue);
				
				if (filenameMatchFound && idMatchFound)
				{				
					final String filename = filenameMatcher.getGroup(1);
					final String id = idMatcher.getGroup(1);
					
					results.append(id + ": " + filename + "\n");
					if (lastFile)
						resultsTextArea.setText(results.toString());
				}
			}
		};

		final RemoteCallback<RESTImageV1> successCallback = new RemoteCallback<RESTImageV1>()
		{
			@Override
			public void callback(final RESTImageV1 retValue)
			{
				results.append(retValue.getFilename() + ": " + retValue.getId().toString() + "\n");
				if (lastFile)
					resultsTextArea.setText(results.toString());
			}
		};

		final ErrorCallback errorCallback = new ErrorCallback()
		{
			@Override
			public boolean error(Message message, Throwable throwable)
			{
				results.append(file.getName() + ": ERROR!");
				if (lastFile)
					resultsTextArea.setText(results.toString());
				return true;
			}
		};

		final RESTInterfaceV1 restMethod = RestClient.create(RESTInterfaceV1.class, genericSuccessCallback, errorCallback);

		final FileReader reader = new FileReader();
		reader.addLoadEndHandler(new LoadEndHandler()
		{
			@Override
			public void onLoadEnd(LoadEndEvent event)
			{

				final String result = reader.getStringResult();
				final byte[] buffer = getByteArray(result, 1);

				final RESTImageV1 image = new RESTImageV1();
				image.explicitSetImageData(buffer);
				image.explicitSetFilename(file.getName());

				try
				{
					restMethod.createJSONImage("", image);
				}
				catch (final Exception ex)
				{
					ex.printStackTrace();
					Window.alert(ex.toString());

					if (lastFile)
						resultsTextArea.setText(results.toString());
				}
			}
		});

		reader.readAsBinaryString(file);
	}

	public static byte[] getByteArray(final String string, final int bytesPerChar)
	{
		char[] chars = string.toCharArray();
		byte[] toReturn = new byte[chars.length * bytesPerChar];
		for (int i = 0; i < chars.length; i++)
		{
			for (int j = 0; j < bytesPerChar; j++)
				toReturn[i * bytesPerChar + j] = (byte) (chars[i] >>> (8 * (bytesPerChar - 1 - j)));
		}
		return toReturn;
	}
}