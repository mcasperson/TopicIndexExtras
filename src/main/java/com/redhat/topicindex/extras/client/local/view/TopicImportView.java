package com.redhat.topicindex.extras.client.local.view;

import org.vectomatic.file.FileUploadExt;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter;

public class TopicImportView extends Composite implements TopicImportPresenter.Display
{
	public static final String HISTORY_TOKEN = "TopicImportView";
	private final Grid layoutTable;
	private final Button goButton;
	private final FileUploadExt upload = new FileUploadExt();
	private final TextArea fileList = new TextArea();
	private final TextBox tagIds = new TextBox();
	private final TextArea log = new TextArea();
	
	public TextArea getLog()
	{
		return log;
	}

	@Override
	public TextArea getFileList()
	{
		return fileList;
	}

	@Override
	public FileUploadExt getUpload()
	{
		return upload;
	}

	@Override
	public Button getGoButton()
	{
		return goButton;
	}
	
	public TopicImportView()
	{
		final DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		
		layoutTable = new Grid(5, 2);
		
		final Label fileLabel = new Label("Please select the files to be imported.");
		layoutTable.setWidget(0, 0, fileLabel);
		layoutTable.setWidget(0, 1, upload);
		
		fileList.setReadOnly(true);
		final Label selectedFilesLabel = new Label("These are the files you have selected.");
		layoutTable.setWidget(1, 0, selectedFilesLabel);
		layoutTable.setWidget(1, 1, fileList);
		
		final Label tagIdsLabel = new Label("Specify the tag ids that will be applied to the new topics.");
		layoutTable.setWidget(2, 0, tagIdsLabel);
		layoutTable.setWidget(2, 1, getTagIds());
		
		log.setReadOnly(true);
		final Label logLabel = new Label("Log");
		layoutTable.setWidget(3, 0, logLabel);
		layoutTable.setWidget(3, 1, log);
		
		goButton = new Button("Go");
		layoutTable.setWidget(4, 0, goButton);
		
		contentTableDecorator.add(layoutTable);
	}

	public Grid getLayoutTable()
	{
		return layoutTable;
	}

	@Override
	public Widget asWidget()
	{
		return this;
	}

	public TextBox getTagIds()
	{
		return tagIds;
	}


}
