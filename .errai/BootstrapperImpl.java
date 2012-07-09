package org.jboss.errai.ioc.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsRenderable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.UIObject;
import com.redhat.topicindex.extras.client.local.App;
import com.redhat.topicindex.extras.client.local.AppController;
import com.redhat.topicindex.extras.client.local.Presenter;
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter;
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter.Display;
import com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter;
import com.redhat.topicindex.extras.client.local.view.BulkImageUploadView;
import com.redhat.topicindex.extras.client.local.view.TopicImportView;
import java.lang.annotation.Annotation;
import javax.inject.Provider;
import org.jboss.errai.common.client.api.extension.InitVotes;
import org.jboss.errai.enterprise.client.cdi.CDIEventTypeLookup;
import org.jboss.errai.enterprise.client.cdi.EventProvider;
import org.jboss.errai.enterprise.client.cdi.InstanceProvider;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.enterprise.client.jaxrs.JaxrsModuleBootstrapper;
import org.jboss.errai.ioc.client.api.ContextualTypeProvider;
import org.jboss.errai.ioc.client.api.builtin.CallerProvider;
import org.jboss.errai.ioc.client.api.builtin.DisposerProvider;
import org.jboss.errai.ioc.client.api.builtin.IOCBeanManagerProvider;
import org.jboss.errai.ioc.client.api.builtin.InitBallotProvider;
import org.jboss.errai.ioc.client.api.builtin.MessageBusProvider;
import org.jboss.errai.ioc.client.api.builtin.RequestDispatcherProvider;
import org.jboss.errai.ioc.client.api.builtin.RootPanelProvider;
import org.jboss.errai.ioc.client.api.builtin.SenderProvider;
import org.jboss.errai.ioc.client.api.qualifiers.Any;
import org.jboss.errai.ioc.client.api.qualifiers.BuiltInQualifiers;
import org.jboss.errai.ioc.client.container.BeanRef;
import org.jboss.errai.ioc.client.container.CreationalCallback;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.ioc.client.container.ProxyResolver;

public class BootstrapperImpl implements Bootstrapper {
  static class com_redhat_topicindex_extras_client_local_AppController_inj2127_proxy extends AppController {
    private AppController $$_proxy_$$;
    public void bind() {
      $$_proxy_$$.bind();
    }

    public void go(HasWidgets a0) {
      $$_proxy_$$.go(a0);
    }

    public void onValueChange(ValueChangeEvent a0) {
      $$_proxy_$$.onValueChange(a0);
    }

    public int hashCode() {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to hashCode() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.hashCode();
      }
    }

    public boolean equals(Object o) {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to equal() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.equals(o);
      }
    }

    public void __$setProxiedInstance$(AppController proxy) {
      $$_proxy_$$ = proxy;
    }
  }
  private native static void org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void com_redhat_topicindex_extras_client_local_AppController_manager(AppController instance, IOCBeanManager value) /*-{
    instance.@com.redhat.topicindex.extras.client.local.AppController::manager = value;
  }-*/;

  private native static void com_redhat_topicindex_extras_client_local_presenter_BulkImageUploadPresenter_display(BulkImageUploadPresenter instance, Display value) /*-{
    instance.@com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter::display = value;
  }-*/;

  private native static void com_redhat_topicindex_extras_client_local_App_appController(App instance, AppController value) /*-{
    instance.@com.redhat.topicindex.extras.client.local.App::appController = value;
  }-*/;

  private native static void com_redhat_topicindex_extras_client_local_AppController_eventBus(AppController instance, HandlerManager value) /*-{
    instance.@com.redhat.topicindex.extras.client.local.AppController::eventBus = value;
  }-*/;

  private native static void com_redhat_topicindex_extras_client_local_presenter_TopicImportPresenter_display(TopicImportPresenter instance, com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter.Display value) /*-{
    instance.@com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter::display = value;
  }-*/;

  public native static HandlerManager com_redhat_topicindex_extras_client_local_App_produceEventBus(App instance) /*-{
    return instance.@com.redhat.topicindex.extras.client.local.App::produceEventBus()();
  }-*/;

  // The main IOC bootstrap method.
  public BootstrapperInjectionContext bootstrapContainer() {
    new CDI().__resetSubsystem();
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
    CreationalContext context = injContext.getRootContext();
    new JaxrsModuleBootstrapper().run();
    final CreationalCallback<CallerProvider> inj2122_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
      public CallerProvider getInstance(final CreationalContext context) {
        Class beanType = CallerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final CallerProvider inj2103_CallerProvider = new CallerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2103_CallerProvider);
        return inj2103_CallerProvider;
      }
    };
    final CallerProvider inj2103_CallerProvider = inj2122_CallerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<RequestDispatcherProvider> inj2123_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
      public RequestDispatcherProvider getInstance(final CreationalContext context) {
        Class beanType = RequestDispatcherProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final RequestDispatcherProvider inj2107_RequestDispatcherProvider = new RequestDispatcherProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2107_RequestDispatcherProvider);
        return inj2107_RequestDispatcherProvider;
      }
    };
    final RequestDispatcherProvider inj2107_RequestDispatcherProvider = inj2123_RequestDispatcherProvider_creationalCallback.getInstance(context);
    final CreationalCallback<SenderProvider> inj2124_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
      public SenderProvider getInstance(final CreationalContext context) {
        Class beanType = SenderProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final SenderProvider inj2119_SenderProvider = new SenderProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2119_SenderProvider);
        return inj2119_SenderProvider;
      }
    };
    final SenderProvider inj2119_SenderProvider = inj2124_SenderProvider_creationalCallback.getInstance(context);
    final CreationalCallback<RootPanelProvider> inj2125_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
      public RootPanelProvider getInstance(final CreationalContext context) {
        Class beanType = RootPanelProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final RootPanelProvider inj2109_RootPanelProvider = new RootPanelProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2109_RootPanelProvider);
        return inj2109_RootPanelProvider;
      }
    };
    final RootPanelProvider inj2109_RootPanelProvider = inj2125_RootPanelProvider_creationalCallback.getInstance(context);
    final CreationalCallback<App> inj2126_App_creationalCallback = new CreationalCallback<App>() {
      public App getInstance(final CreationalContext context) {
        Class beanType = App.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final App inj1818_App = new App();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1818_App);
        final com_redhat_topicindex_extras_client_local_AppController_inj2127_proxy inj2127_proxy = new com_redhat_topicindex_extras_client_local_AppController_inj2127_proxy();
        context.addUnresolvedProxy(new ProxyResolver<AppController>() {
          public void resolve(AppController obj) {
            inj2127_proxy.__$setProxiedInstance$(obj);
            context.addProxyReference(inj2127_proxy, obj);
          }
        }, AppController.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } });
        com_redhat_topicindex_extras_client_local_App_appController(inj1818_App, inj2127_proxy);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj1818_App.startApp();
              }
            });
          }
        });
        return inj1818_App;
      }
    };
    final App inj1818_App = inj2126_App_creationalCallback.getInstance(context);
    final CreationalCallback<IOCBeanManagerProvider> inj2130_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
      public IOCBeanManagerProvider getInstance(final CreationalContext context) {
        Class beanType = IOCBeanManagerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final IOCBeanManagerProvider inj2115_IOCBeanManagerProvider = new IOCBeanManagerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2115_IOCBeanManagerProvider);
        return inj2115_IOCBeanManagerProvider;
      }
    };
    final IOCBeanManagerProvider inj2115_IOCBeanManagerProvider = inj2130_IOCBeanManagerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<AppController> inj2129_AppController_creationalCallback = new CreationalCallback<AppController>() {
      public AppController getInstance(final CreationalContext context) {
        Class beanType = AppController.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final AppController inj2128_AppController = new AppController();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2128_AppController);
        com_redhat_topicindex_extras_client_local_AppController_manager(inj2128_AppController, inj2115_IOCBeanManagerProvider.get());
        com_redhat_topicindex_extras_client_local_AppController_eventBus(inj2128_AppController, com_redhat_topicindex_extras_client_local_App_produceEventBus(inj1818_App));
        return inj2128_AppController;
      }
    };
    final AppController inj2128_AppController = inj2129_AppController_creationalCallback.getInstance(context);
    final CreationalCallback<MessageBusProvider> inj2131_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
      public MessageBusProvider getInstance(final CreationalContext context) {
        Class beanType = MessageBusProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MessageBusProvider inj2113_MessageBusProvider = new MessageBusProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2113_MessageBusProvider);
        return inj2113_MessageBusProvider;
      }
    };
    final MessageBusProvider inj2113_MessageBusProvider = inj2131_MessageBusProvider_creationalCallback.getInstance(context);
    final CreationalCallback<BulkImageUploadView> inj2134_BulkImageUploadView_creationalCallback = new CreationalCallback<BulkImageUploadView>() {
      public BulkImageUploadView getInstance(final CreationalContext context) {
        Class beanType = BulkImageUploadView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final BulkImageUploadView inj1874_BulkImageUploadView = new BulkImageUploadView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1874_BulkImageUploadView);
        return inj1874_BulkImageUploadView;
      }
    };
    final CreationalCallback<BulkImageUploadPresenter> inj2133_BulkImageUploadPresenter_creationalCallback = new CreationalCallback<BulkImageUploadPresenter>() {
      public BulkImageUploadPresenter getInstance(final CreationalContext context) {
        Class beanType = BulkImageUploadPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final BulkImageUploadPresenter inj2132_BulkImageUploadPresenter = new BulkImageUploadPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2132_BulkImageUploadPresenter);
        com_redhat_topicindex_extras_client_local_presenter_BulkImageUploadPresenter_display(inj2132_BulkImageUploadPresenter, inj2134_BulkImageUploadView_creationalCallback.getInstance(context));
        return inj2132_BulkImageUploadPresenter;
      }
    };
    final CreationalCallback<InstanceProvider> inj2135_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
      public InstanceProvider getInstance(final CreationalContext context) {
        Class beanType = InstanceProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InstanceProvider inj2121_InstanceProvider = new InstanceProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2121_InstanceProvider);
        return inj2121_InstanceProvider;
      }
    };
    final InstanceProvider inj2121_InstanceProvider = inj2135_InstanceProvider_creationalCallback.getInstance(context);
    final CreationalCallback<EventProvider> inj2136_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
      public EventProvider getInstance(final CreationalContext context) {
        Class beanType = EventProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final EventProvider inj2117_EventProvider = new EventProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2117_EventProvider);
        return inj2117_EventProvider;
      }
    };
    final EventProvider inj2117_EventProvider = inj2136_EventProvider_creationalCallback.getInstance(context);
    final CreationalCallback<DisposerProvider> inj2137_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
      public DisposerProvider getInstance(final CreationalContext context) {
        Class beanType = DisposerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DisposerProvider inj2105_DisposerProvider = new DisposerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2105_DisposerProvider);
        org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj2105_DisposerProvider, inj2115_IOCBeanManagerProvider.get());
        return inj2105_DisposerProvider;
      }
    };
    final DisposerProvider inj2105_DisposerProvider = inj2137_DisposerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<InitBallotProvider> inj2138_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
      public InitBallotProvider getInstance(final CreationalContext context) {
        Class beanType = InitBallotProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InitBallotProvider inj2111_InitBallotProvider = new InitBallotProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2111_InitBallotProvider);
        return inj2111_InitBallotProvider;
      }
    };
    final InitBallotProvider inj2111_InitBallotProvider = inj2138_InitBallotProvider_creationalCallback.getInstance(context);
    final CreationalCallback<TopicImportView> inj2141_TopicImportView_creationalCallback = new CreationalCallback<TopicImportView>() {
      public TopicImportView getInstance(final CreationalContext context) {
        Class beanType = TopicImportView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final TopicImportView inj1875_TopicImportView = new TopicImportView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1875_TopicImportView);
        return inj1875_TopicImportView;
      }
    };
    final CreationalCallback<TopicImportPresenter> inj2140_TopicImportPresenter_creationalCallback = new CreationalCallback<TopicImportPresenter>() {
      public TopicImportPresenter getInstance(final CreationalContext context) {
        Class beanType = TopicImportPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final TopicImportPresenter inj2139_TopicImportPresenter = new TopicImportPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2139_TopicImportPresenter);
        com_redhat_topicindex_extras_client_local_presenter_TopicImportPresenter_display(inj2139_TopicImportPresenter, inj2141_TopicImportView_creationalCallback.getInstance(context));
        return inj2139_TopicImportPresenter;
      }
    };
    injContext.addBean(CallerProvider.class, inj2122_CallerProvider_creationalCallback, inj2103_CallerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2122_CallerProvider_creationalCallback, inj2103_CallerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(RequestDispatcherProvider.class, inj2123_RequestDispatcherProvider_creationalCallback, inj2107_RequestDispatcherProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2123_RequestDispatcherProvider_creationalCallback, inj2107_RequestDispatcherProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(SenderProvider.class, inj2124_SenderProvider_creationalCallback, inj2119_SenderProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2124_SenderProvider_creationalCallback, inj2119_SenderProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(RootPanelProvider.class, inj2125_RootPanelProvider_creationalCallback, inj2109_RootPanelProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2125_RootPanelProvider_creationalCallback, inj2109_RootPanelProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(App.class, inj2126_App_creationalCallback, inj1818_App, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IOCBeanManagerProvider.class, inj2130_IOCBeanManagerProvider_creationalCallback, inj2115_IOCBeanManagerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2130_IOCBeanManagerProvider_creationalCallback, inj2115_IOCBeanManagerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(AppController.class, inj2129_AppController_creationalCallback, inj2128_AppController, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2129_AppController_creationalCallback, inj2128_AppController, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ValueChangeHandler.class, inj2129_AppController_creationalCallback, inj2128_AppController, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MessageBusProvider.class, inj2131_MessageBusProvider_creationalCallback, inj2113_MessageBusProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2131_MessageBusProvider_creationalCallback, inj2113_MessageBusProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(BulkImageUploadView.class, inj2134_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Display.class, inj2134_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Composite.class, inj2134_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsRenderable.class, inj2134_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventListener.class, inj2134_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasAttachHandlers.class, inj2134_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsWidget.class, inj2134_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(UIObject.class, inj2134_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasVisibility.class, inj2134_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(BulkImageUploadPresenter.class, inj2133_BulkImageUploadPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2133_BulkImageUploadPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InstanceProvider.class, inj2135_InstanceProvider_creationalCallback, inj2121_InstanceProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2135_InstanceProvider_creationalCallback, inj2121_InstanceProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventProvider.class, inj2136_EventProvider_creationalCallback, inj2117_EventProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2136_EventProvider_creationalCallback, inj2117_EventProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DisposerProvider.class, inj2137_DisposerProvider_creationalCallback, inj2105_DisposerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2137_DisposerProvider_creationalCallback, inj2105_DisposerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InitBallotProvider.class, inj2138_InitBallotProvider_creationalCallback, inj2111_InitBallotProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2138_InitBallotProvider_creationalCallback, inj2111_InitBallotProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(TopicImportView.class, inj2141_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter.Display.class, inj2141_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Composite.class, inj2141_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsRenderable.class, inj2141_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventListener.class, inj2141_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasAttachHandlers.class, inj2141_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsWidget.class, inj2141_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(UIObject.class, inj2141_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasVisibility.class, inj2141_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(TopicImportPresenter.class, inj2140_TopicImportPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2140_TopicImportPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    return injContext;
  }
}