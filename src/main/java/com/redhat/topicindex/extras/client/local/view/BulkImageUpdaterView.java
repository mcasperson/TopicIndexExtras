package com.redhat.topicindex.extras.client.local.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.Progressbar;
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUpdaterPresenter;

public class BulkImageUpdaterView extends Composite implements BulkImageUpdaterPresenter.Display
{
	public static final String HISTORY_TOKEN = "BulkImageUpdaterView";
	
	private final TextBox topicSearch = new TextBox();
	private final Button go = new Button("Search");
	private final Button bulkUpdate = new Button("Update all");
	private final Button update = new Button("Update selected");
	private final ListBox topicMatches = new ListBox(false);
	private final ListBox imageMatches = new ListBox(false);	
	private final Progressbar progress = new Progressbar();
	private final TextArea log = new TextArea();
	private final TextArea xml = new TextArea();
	
	public TextArea getXml()
	{
		return xml;
	}

	public TextArea getLog()
	{
		return log;
	}

	public Button getBulkUpdate()
	{
		return bulkUpdate;
	}

	public Progressbar getProgress()
	{
		return progress;
	}

	public ListBox getTopicMatches()
	{
		return topicMatches;
	}

	public Button getGo()
	{
		return go;
	}

	public TextBox getTopicSearch()
	{
		return topicSearch;
	}

	public ListBox getImageMatches()
	{
		return imageMatches;
	}

	public Button getUpdate()
	{
		return update;
	}
	
	public BulkImageUpdaterView()
	{
		final DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		
		progress.setVisible(false);
		log.setReadOnly(true);
		xml.setReadOnly(true);
		
		final Grid layoutGrid = new Grid(5, 3);
		
		topicSearch.setWidth("500px");
		final Label topicSearchLabel = new Label("Enter the tag that identifies the topics");
		layoutGrid.setWidget(0, 0, topicSearchLabel);
		layoutGrid.setWidget(0, 1, topicSearch);
		layoutGrid.setWidget(0, 2, go);
		layoutGrid.setWidget(0, 3, progress);
		
		topicMatches.setWidth("500px");
		topicMatches.setHeight("300px");
		topicMatches.setVisibleItemCount(10);
		imageMatches.setWidth("500px");
		imageMatches.setHeight("300px");
		imageMatches.setVisibleItemCount(10);
		final Label topicMatchLabel = new Label("The following topics have references to images");
		layoutGrid.setWidget(1, 0, topicMatchLabel);
		layoutGrid.setWidget(1, 1, topicMatches);
		layoutGrid.setWidget(1, 2, imageMatches);
		
		final Label bulkUpdateLabel = new Label("Click this button to update all topics with only 1 match");
		layoutGrid.setWidget(2, 0, bulkUpdateLabel);
		layoutGrid.setWidget(2, 1, bulkUpdate);
		
		xml.setWidth("500px");
		xml.setHeight("300px");
		final Label xmlLabel = new Label("Topic XML");
		layoutGrid.setWidget(3, 0, xmlLabel);
		layoutGrid.setWidget(3, 1, xml);
		
		log.setWidth("500px");
		log.setHeight("300px");
		final Label logLabel = new Label("Log output");
		layoutGrid.setWidget(4, 0, logLabel);
		layoutGrid.setWidget(4, 1, log);
		
		contentTableDecorator.add(layoutGrid);
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}
}
