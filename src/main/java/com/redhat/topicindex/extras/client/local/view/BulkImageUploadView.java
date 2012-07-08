package com.redhat.topicindex.extras.client.local.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter;
import com.smartgwt.client.widgets.Progressbar;

public class BulkImageUploadView extends Composite implements BulkImageUploadPresenter.Display
{
	public static final String HISTORY_TOKEN = "BulkImageUploadView";
	
	private final List<ImageUploadData> imageUploadBlocks = new ArrayList<ImageUploadData>();
	private final TextBox prefix = new TextBox();
	private final TextArea finalResults = new TextArea();
	private final Button upload = new Button("Upload");
	private final Button newLangButton = new Button("New Language");
	private final Progressbar progress = new Progressbar();
	private final VerticalPanel verticalPanel = new VerticalPanel();;
	
	@Override
	public VerticalPanel getVerticalPanel()
	{
		return verticalPanel;
	}

	@Override
	public List<ImageUploadData> getImageUploadBlocks()
	{
		return imageUploadBlocks;
	}

	@Override
	public Button getNewLangButton()
	{
		return newLangButton;
	}

	@Override
	public Button getUpload()
	{
		return upload;
	}

	@Override
	public TextArea getFinalResults()
	{
		return finalResults;
	}

	@Override
	public TextBox getPrefix()
	{
		return prefix;
	}
	
	@Override
	public Progressbar getProgress()
	{
		return progress;
	}

	public BulkImageUploadView()
	{
		final DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		
		progress.setVisible(false);
		getFinalResults().setReadOnly(true);
		getFinalResults().setWidth("500px");
		getFinalResults().setHeight("300px");
		getFinalResults().setVisible(false);

		final Grid layoutGrid = new Grid(4, 1);

		final HorizontalPanel prefixLayout = new HorizontalPanel();
		layoutGrid.setWidget(0, 0, prefixLayout);

		final Label prefixLabel = new Label("Enter an optional image name prefix");
		prefixLabel.getElement().getStyle().setPaddingRight(10, Unit.PX);
		prefixLayout.add(prefixLabel);
		prefixLayout.add(getPrefix());

		layoutGrid.setWidget(1, 0, verticalPanel);

		final ImageUploadData initialBlock = new ImageUploadData();
		imageUploadBlocks.add(initialBlock);
		verticalPanel.add(initialBlock.getGrid());

		layoutGrid.setWidget(2, 0, getFinalResults());

		final HorizontalPanel horizontalLayout = new HorizontalPanel();
		layoutGrid.setWidget(3, 0, horizontalLayout);


		horizontalLayout.add(newLangButton);

		horizontalLayout.add(upload);

		horizontalLayout.add(progress);
		
		contentTableDecorator.add(layoutGrid);
	}

	@Override
	public Widget asWidget()
	{
		return this;
	}
}
