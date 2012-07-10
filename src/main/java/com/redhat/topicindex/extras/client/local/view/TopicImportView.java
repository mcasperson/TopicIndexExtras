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
import com.smartgwt.client.widgets.Progressbar;

public class TopicImportView extends Composite implements TopicImportPresenter.Display
{
	public static final String HISTORY_TOKEN = "TopicImportView";
	private final Grid layoutTable;
	private final Button goButton;
	private final FileUploadExt upload = new FileUploadExt();
	private final TextArea fileList = new TextArea();
	private final TextBox tagIds = new TextBox();
	private final TextArea log = new TextArea();
	private final TextArea topicDetails = new TextArea();
	private final TextArea topicErrors = new TextArea();
	private final Progressbar progress = new Progressbar();
	private final TextBox fileNamePrefix = new TextBox();
	
	public TextArea getTopicDetails()
	{
		return topicDetails;
	}

	public TextBox getFileNamePrefix()
	{
		return fileNamePrefix;
	}

	public Progressbar getProgress()
	{
		return progress;
	}

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
		
		layoutTable = new Grid(6, 2);
		
		final Label fileNamePrefixLabel = new Label("Enter a prefix for the file names.");
		layoutTable.setWidget(0, 0, fileNamePrefixLabel);
		layoutTable.setWidget(0, 1, fileNamePrefix);
		
		final Label fileLabel = new Label("Please select the files to be imported.");
		layoutTable.setWidget(1, 0, fileLabel);
		layoutTable.setWidget(1, 1, upload);
		
		fileList.setReadOnly(true);
		fileList.setWidth("60em");
		fileList.setHeight("30em");
		final Label selectedFilesLabel = new Label("These are the files you have selected.");
		layoutTable.setWidget(2, 0, selectedFilesLabel);
		layoutTable.setWidget(2, 1, fileList);
		
		final Label tagIdsLabel = new Label("Specify the tag ids that will be applied to the new topics. This is a space seperated list of integers.");
		layoutTable.setWidget(3, 0, tagIdsLabel);
		layoutTable.setWidget(3, 1, getTagIds());
		
		log.setReadOnly(true);
		log.setWidth("60em");
		log.setHeight("30em");
		final Label logLabel = new Label("Log");
		layoutTable.setWidget(4, 0, logLabel);
		layoutTable.setWidget(4, 1, log);
		
		goButton = new Button("Go");
		layoutTable.setWidget(5, 0, goButton);
		progress.setVisible(false);
		layoutTable.setWidget(5, 1, progress);
		
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

	public TextArea getTopicErrors()
	{
		return topicErrors;
	}


}
