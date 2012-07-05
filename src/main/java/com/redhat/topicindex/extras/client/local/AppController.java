package com.redhat.topicindex.extras.client.local;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter;
import com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter;
import com.redhat.topicindex.extras.client.local.view.BulkImageUploadView;
import com.redhat.topicindex.extras.client.local.view.TopicImportView;

@ApplicationScoped
public class AppController implements Presenter, ValueChangeHandler<String>
{
	@Inject
	private IOCBeanManager manager;

	@Inject
	private HandlerManager eventBus;

	private HasWidgets container;

	public void bind()
	{
		History.addValueChangeHandler(this);
	}

	public void go(final HasWidgets container)
	{
		this.container = container;
		bind();

		if ("".equals(History.getToken()))
		{
			History.newItem(TopicImportView.HISTORY_TOKEN);
		}
		else
		{
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(final ValueChangeEvent<String> event)
	{
		final String token = event.getValue();
		if (token != null)
		{
			Presenter presenter = null;

			if (token.equals(TopicImportView.HISTORY_TOKEN))
			{
				final IOCBeanDef<TopicImportPresenter> bean = manager.lookupBean(TopicImportPresenter.class);
				if (bean != null)
				{
					presenter = bean.getInstance();
				}
			}
			else if (token.equals(BulkImageUploadView.HISTORY_TOKEN))
			{
				final IOCBeanDef<BulkImageUploadPresenter> bean = manager.lookupBean(BulkImageUploadPresenter.class);
				if (bean != null)
				{
					presenter = bean.getInstance();
				}
			}

			if (presenter != null)
			{
				presenter.go(container);
			}
		}
	}
}
