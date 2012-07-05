package com.redhat.topicindex.extras.client.local.presenter;

import org.vectomatic.file.File;
import org.vectomatic.file.FileUploadExt;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import com.redhat.topicindex.extras.client.local.Presenter;

@Dependent
public class TopicImportPresenter implements Presenter
{
	public interface Display
	{
		Button getGoButton();
		FileUploadExt getUpload();
		Widget asWidget();
		TextArea getFileList();
	}

	@Inject
	private HandlerManager eventBus;

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
	}

	public void go(final HasWidgets container)
	{
		bind();
		container.clear();
		container.add(display.asWidget());
	}
}
