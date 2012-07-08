package com.redhat.topicindex.extras.client.local;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter;

@EntryPoint
public class App
{
	private HandlerManager eventBus = new HandlerManager(null);

	@Inject
	private AppController appController;

	@AfterInitialization
	public void startApp()
	{
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler()
		{
			@Override
			public void onUncaughtException(final Throwable ex)
			{
				ex.printStackTrace();
				Window.alert("Uncaught exception event");
			}
		});
		
		appController.go(RootPanel.get());

	}

	@Produces
	private HandlerManager produceEventBus()
	{
		return eventBus;
	}	
}