package org.jboss.errai.ioc.client;

import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.IsRenderable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.redhat.topicindex.extras.client.local.App;
import com.redhat.topicindex.extras.client.local.AppController;
import com.redhat.topicindex.extras.client.local.Presenter;
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUpdaterPresenter;
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUpdaterPresenter.Display;
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter;
import com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter;
import com.redhat.topicindex.extras.client.local.view.BulkImageUpdaterView;
import com.redhat.topicindex.extras.client.local.view.BulkImageUploadView;
import com.redhat.topicindex.extras.client.local.view.TopicImportView;
import java.lang.annotation.Annotation;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Provider;
import org.jboss.errai.common.client.api.extension.InitVotes;
import org.jboss.errai.enterprise.client.cdi.CDIEventTypeLookup;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.enterprise.client.jaxrs.JaxrsModuleBootstrapper;
import org.jboss.errai.ioc.client.api.builtin.IOCBeanManagerProvider;
import org.jboss.errai.ioc.client.container.CreationalCallback;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.IOCBeanManager;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Any _1998831462Any_1572795346 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default _1998831462Default_1010396025 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_19635043Annotation_703288476 = new Annotation[] { _1998831462Any_1572795346, _1998831462Default_1010396025 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<IOCBeanManagerProvider> inj2316_IOCBeanManagerProvider_creational = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      final IOCBeanManagerProvider inj2303_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      context.addBean(context.getBeanReference(IOCBeanManagerProvider.class, arrayOf_19635043Annotation_703288476), inj2303_IOCBeanManagerProvider);
      return inj2303_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj2303_IOCBeanManagerProvider = inj2316_IOCBeanManagerProvider_creational.getInstance(context);
  private final CreationalCallback<BulkImageUpdaterView> inj2319_BulkImageUpdaterView_creational = new CreationalCallback<BulkImageUpdaterView>() {
    public BulkImageUpdaterView getInstance(final CreationalContext context) {
      final BulkImageUpdaterView inj2061_BulkImageUpdaterView = new BulkImageUpdaterView();
      context.addBean(context.getBeanReference(BulkImageUpdaterView.class, arrayOf_19635043Annotation_703288476), inj2061_BulkImageUpdaterView);
      return inj2061_BulkImageUpdaterView;
    }
  };
  private final CreationalCallback<BulkImageUpdaterPresenter> inj2318_BulkImageUpdaterPresenter_creational = new CreationalCallback<BulkImageUpdaterPresenter>() {
    public BulkImageUpdaterPresenter getInstance(final CreationalContext context) {
      final BulkImageUpdaterPresenter inj2317_BulkImageUpdaterPresenter = new BulkImageUpdaterPresenter();
      context.addBean(context.getBeanReference(BulkImageUpdaterPresenter.class, arrayOf_19635043Annotation_703288476), inj2317_BulkImageUpdaterPresenter);
      _$1165855181_display(inj2317_BulkImageUpdaterPresenter, inj2319_BulkImageUpdaterView_creational.getInstance(context));
      return inj2317_BulkImageUpdaterPresenter;
    }
  };
  private final CreationalCallback<AppController> inj2321_AppController_creational = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      final AppController inj2320_AppController = new AppController();
      context.addBean(context.getBeanReference(AppController.class, arrayOf_19635043Annotation_703288476), inj2320_AppController);
      _$2010280978_manager(inj2320_AppController, inj2303_IOCBeanManagerProvider.get());
      return inj2320_AppController;
    }
  };
  private final AppController inj2320_AppController = inj2321_AppController_creational.getInstance(context);
  private final CreationalCallback<App> inj2323_App_creational = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      final App inj2322_App = new App();
      context.addBean(context.getBeanReference(App.class, arrayOf_19635043Annotation_703288476), inj2322_App);
      _$1923906894_appController(inj2322_App, inj2320_AppController);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          inj2322_App.startApp();
        }
      });
      return inj2322_App;
    }
  };
  private final App inj2322_App = inj2323_App_creational.getInstance(context);
  private final CreationalCallback<BulkImageUploadView> inj2326_BulkImageUploadView_creational = new CreationalCallback<BulkImageUploadView>() {
    public BulkImageUploadView getInstance(final CreationalContext context) {
      final BulkImageUploadView inj2059_BulkImageUploadView = new BulkImageUploadView();
      context.addBean(context.getBeanReference(BulkImageUploadView.class, arrayOf_19635043Annotation_703288476), inj2059_BulkImageUploadView);
      return inj2059_BulkImageUploadView;
    }
  };
  private final CreationalCallback<BulkImageUploadPresenter> inj2325_BulkImageUploadPresenter_creational = new CreationalCallback<BulkImageUploadPresenter>() {
    public BulkImageUploadPresenter getInstance(final CreationalContext context) {
      final BulkImageUploadPresenter inj2324_BulkImageUploadPresenter = new BulkImageUploadPresenter();
      context.addBean(context.getBeanReference(BulkImageUploadPresenter.class, arrayOf_19635043Annotation_703288476), inj2324_BulkImageUploadPresenter);
      _$214513453_display(inj2324_BulkImageUploadPresenter, inj2326_BulkImageUploadView_creational.getInstance(context));
      return inj2324_BulkImageUploadPresenter;
    }
  };
  private final CreationalCallback<TopicImportView> inj2329_TopicImportView_creational = new CreationalCallback<TopicImportView>() {
    public TopicImportView getInstance(final CreationalContext context) {
      final TopicImportView inj2058_TopicImportView = new TopicImportView();
      context.addBean(context.getBeanReference(TopicImportView.class, arrayOf_19635043Annotation_703288476), inj2058_TopicImportView);
      return inj2058_TopicImportView;
    }
  };
  private final CreationalCallback<TopicImportPresenter> inj2328_TopicImportPresenter_creational = new CreationalCallback<TopicImportPresenter>() {
    public TopicImportPresenter getInstance(final CreationalContext context) {
      final TopicImportPresenter inj2327_TopicImportPresenter = new TopicImportPresenter();
      context.addBean(context.getBeanReference(TopicImportPresenter.class, arrayOf_19635043Annotation_703288476), inj2327_TopicImportPresenter);
      _$2008244311_display(inj2327_TopicImportPresenter, inj2329_TopicImportView_creational.getInstance(context));
      return inj2327_TopicImportPresenter;
    }
  };
  private void declareBeans_0() {
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj2316_IOCBeanManagerProvider_creational, inj2303_IOCBeanManagerProvider, arrayOf_19635043Annotation_703288476, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj2316_IOCBeanManagerProvider_creational, inj2303_IOCBeanManagerProvider, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(BulkImageUpdaterView.class, BulkImageUpdaterView.class, inj2319_BulkImageUpdaterView_creational, null, arrayOf_19635043Annotation_703288476, null, true);
    injContext.addBean(Display.class, BulkImageUpdaterView.class, inj2319_BulkImageUpdaterView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(Composite.class, BulkImageUpdaterView.class, inj2319_BulkImageUpdaterView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(IsRenderable.class, BulkImageUpdaterView.class, inj2319_BulkImageUpdaterView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(Widget.class, BulkImageUpdaterView.class, inj2319_BulkImageUpdaterView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(EventListener.class, BulkImageUpdaterView.class, inj2319_BulkImageUpdaterView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(HasAttachHandlers.class, BulkImageUpdaterView.class, inj2319_BulkImageUpdaterView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(HasHandlers.class, BulkImageUpdaterView.class, inj2319_BulkImageUpdaterView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(IsWidget.class, BulkImageUpdaterView.class, inj2319_BulkImageUpdaterView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(UIObject.class, BulkImageUpdaterView.class, inj2319_BulkImageUpdaterView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(HasVisibility.class, BulkImageUpdaterView.class, inj2319_BulkImageUpdaterView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(BulkImageUpdaterPresenter.class, BulkImageUpdaterPresenter.class, inj2318_BulkImageUpdaterPresenter_creational, null, arrayOf_19635043Annotation_703288476, null, true);
    injContext.addBean(Presenter.class, BulkImageUpdaterPresenter.class, inj2318_BulkImageUpdaterPresenter_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(AppController.class, AppController.class, inj2321_AppController_creational, inj2320_AppController, arrayOf_19635043Annotation_703288476, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj2321_AppController_creational, inj2320_AppController, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj2321_AppController_creational, inj2320_AppController, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj2321_AppController_creational, inj2320_AppController, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(App.class, App.class, inj2323_App_creational, inj2322_App, arrayOf_19635043Annotation_703288476, null, true);
    injContext.addBean(BulkImageUploadView.class, BulkImageUploadView.class, inj2326_BulkImageUploadView_creational, null, arrayOf_19635043Annotation_703288476, null, true);
    injContext.addBean(com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter.Display.class, BulkImageUploadView.class, inj2326_BulkImageUploadView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(Composite.class, BulkImageUploadView.class, inj2326_BulkImageUploadView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(IsRenderable.class, BulkImageUploadView.class, inj2326_BulkImageUploadView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(Widget.class, BulkImageUploadView.class, inj2326_BulkImageUploadView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(EventListener.class, BulkImageUploadView.class, inj2326_BulkImageUploadView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(HasAttachHandlers.class, BulkImageUploadView.class, inj2326_BulkImageUploadView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(HasHandlers.class, BulkImageUploadView.class, inj2326_BulkImageUploadView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(IsWidget.class, BulkImageUploadView.class, inj2326_BulkImageUploadView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(UIObject.class, BulkImageUploadView.class, inj2326_BulkImageUploadView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(HasVisibility.class, BulkImageUploadView.class, inj2326_BulkImageUploadView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(BulkImageUploadPresenter.class, BulkImageUploadPresenter.class, inj2325_BulkImageUploadPresenter_creational, null, arrayOf_19635043Annotation_703288476, null, true);
    injContext.addBean(Presenter.class, BulkImageUploadPresenter.class, inj2325_BulkImageUploadPresenter_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(TopicImportView.class, TopicImportView.class, inj2329_TopicImportView_creational, null, arrayOf_19635043Annotation_703288476, null, true);
    injContext.addBean(com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter.Display.class, TopicImportView.class, inj2329_TopicImportView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(Composite.class, TopicImportView.class, inj2329_TopicImportView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(IsRenderable.class, TopicImportView.class, inj2329_TopicImportView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(Widget.class, TopicImportView.class, inj2329_TopicImportView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(EventListener.class, TopicImportView.class, inj2329_TopicImportView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(HasAttachHandlers.class, TopicImportView.class, inj2329_TopicImportView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(HasHandlers.class, TopicImportView.class, inj2329_TopicImportView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(IsWidget.class, TopicImportView.class, inj2329_TopicImportView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(UIObject.class, TopicImportView.class, inj2329_TopicImportView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(HasVisibility.class, TopicImportView.class, inj2329_TopicImportView_creational, null, arrayOf_19635043Annotation_703288476, null, false);
    injContext.addBean(TopicImportPresenter.class, TopicImportPresenter.class, inj2328_TopicImportPresenter_creational, null, arrayOf_19635043Annotation_703288476, null, true);
    injContext.addBean(Presenter.class, TopicImportPresenter.class, inj2328_TopicImportPresenter_creational, null, arrayOf_19635043Annotation_703288476, null, false);
  }

  private native static void _$2010280978_manager(AppController instance, IOCBeanManager value) /*-{
    instance.@com.redhat.topicindex.extras.client.local.AppController::manager = value;
  }-*/;

  private native static void _$214513453_display(BulkImageUploadPresenter instance, com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter.Display value) /*-{
    instance.@com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter::display = value;
  }-*/;

  private native static void _$1923906894_appController(App instance, AppController value) /*-{
    instance.@com.redhat.topicindex.extras.client.local.App::appController = value;
  }-*/;

  private native static void _$2008244311_display(TopicImportPresenter instance, com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter.Display value) /*-{
    instance.@com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter::display = value;
  }-*/;

  private native static void _$1165855181_display(BulkImageUpdaterPresenter instance, Display value) /*-{
    instance.@com.redhat.topicindex.extras.client.local.presenter.BulkImageUpdaterPresenter::display = value;
  }-*/;

  // The main IOC bootstrap method.
  public BootstrapperInjectionContext bootstrapContainer() {
    declareBeans_0();
    return injContext;
  }
}