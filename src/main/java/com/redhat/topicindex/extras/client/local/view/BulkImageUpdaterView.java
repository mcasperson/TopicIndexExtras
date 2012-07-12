package com.redhat.topicindex.extras.client.local.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	private final Button bulkUpdate = new Button("Update All Topics With All Images");
	private final Button updateTopic = new Button("Update Selected Topic With all Images");
	private final Button updateImage = new Button("Update Selected Image With all Images");
	private final Button update = new Button("Update selected");
	private final ListBox topicMatches = new ListBox(false);
	private final ListBox imageMatches = new ListBox(false);	
	private final Progressbar progress = new Progressbar();
	private final TextArea log = new TextArea();
	private final TextArea xml = new TextArea();
	
	public Button getUpdateTopic()
	{
		return updateTopic;
	}

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
		
		final Grid layoutGrid = new Grid(6, 2);
		
		topicSearch.setWidth("500px");
		final Label topicSearchLabel = new Label("Enter the tag that identifies the topics");
		layoutGrid.setWidget(0, 0, topicSearchLabel);
		layoutGrid.setWidget(0, 1, topicSearch);
		
		
		topicMatches.setWidth("500px");
		topicMatches.setHeight("300px");
		topicMatches.setVisibleItemCount(10);
		imageMatches.setWidth("500px");
		imageMatches.setHeight("300px");
		imageMatches.setVisibleItemCount(10);
		final Label topicMatchLabel = new Label("The following topics have references to images");
		layoutGrid.setWidget(1, 0, topicMatchLabel);
		
		final HorizontalPanel listPanel = new HorizontalPanel();
		layoutGrid.setWidget(1, 1, listPanel);
		
		listPanel.add(topicMatches);
		listPanel.add(imageMatches);
		
		xml.setWidth("1000px");
		xml.setHeight("300px");
		final Label xmlLabel = new Label("Topic XML");
		layoutGrid.setWidget(2, 0, xmlLabel);
		layoutGrid.setWidget(2, 1, xml);
		
		log.setWidth("1000px");
		log.setHeight("300px");
		final Label logLabel = new Label("Log output");
		layoutGrid.setWidget(3, 0, logLabel);
		layoutGrid.setWidget(3, 1, log);
		
		final HorizontalPanel buttonLayout = new HorizontalPanel();
		layoutGrid.setWidget(4, 0, buttonLayout);
		layoutGrid.setWidget(4, 1, progress);
		
		buttonLayout.add(go);
		buttonLayout.add(bulkUpdate);
		buttonLayout.add(updateTopic);
		buttonLayout.add(updateImage);
		
		contentTableDecorator.add(layoutGrid);
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}

	public Button getUpdateImage()
	{
		return updateImage;
	}
}
