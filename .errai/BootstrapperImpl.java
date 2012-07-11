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
  static class com_redhat_topicindex_extras_client_local_AppController_inj2130_proxy extends AppController {
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
    final CreationalCallback<CallerProvider> inj2123_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
      public CallerProvider getInstance(final CreationalContext context) {
        Class beanType = CallerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final CallerProvider inj2104_CallerProvider = new CallerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2104_CallerProvider);
        return inj2104_CallerProvider;
      }
    };
    final CallerProvider inj2104_CallerProvider = inj2123_CallerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<RequestDispatcherProvider> inj2124_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
      public RequestDispatcherProvider getInstance(final CreationalContext context) {
        Class beanType = RequestDispatcherProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final RequestDispatcherProvider inj2108_RequestDispatcherProvider = new RequestDispatcherProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2108_RequestDispatcherProvider);
        return inj2108_RequestDispatcherProvider;
      }
    };
    final RequestDispatcherProvider inj2108_RequestDispatcherProvider = inj2124_RequestDispatcherProvider_creationalCallback.getInstance(context);
    final CreationalCallback<SenderProvider> inj2125_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
      public SenderProvider getInstance(final CreationalContext context) {
        Class beanType = SenderProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final SenderProvider inj2120_SenderProvider = new SenderProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2120_SenderProvider);
        return inj2120_SenderProvider;
      }
    };
    final SenderProvider inj2120_SenderProvider = inj2125_SenderProvider_creationalCallback.getInstance(context);
    final CreationalCallback<RootPanelProvider> inj2126_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
      public RootPanelProvider getInstance(final CreationalContext context) {
        Class beanType = RootPanelProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final RootPanelProvider inj2110_RootPanelProvider = new RootPanelProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2110_RootPanelProvider);
        return inj2110_RootPanelProvider;
      }
    };
    final RootPanelProvider inj2110_RootPanelProvider = inj2126_RootPanelProvider_creationalCallback.getInstance(context);
    final CreationalCallback<IOCBeanManagerProvider> inj2127_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
      public IOCBeanManagerProvider getInstance(final CreationalContext context) {
        Class beanType = IOCBeanManagerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final IOCBeanManagerProvider inj2116_IOCBeanManagerProvider = new IOCBeanManagerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2116_IOCBeanManagerProvider);
        return inj2116_IOCBeanManagerProvider;
      }
    };
    final IOCBeanManagerProvider inj2116_IOCBeanManagerProvider = inj2127_IOCBeanManagerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<MessageBusProvider> inj2128_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
      public MessageBusProvider getInstance(final CreationalContext context) {
        Class beanType = MessageBusProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MessageBusProvider inj2114_MessageBusProvider = new MessageBusProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2114_MessageBusProvider);
        return inj2114_MessageBusProvider;
      }
    };
    final MessageBusProvider inj2114_MessageBusProvider = inj2128_MessageBusProvider_creationalCallback.getInstance(context);
    final CreationalCallback<App> inj2129_App_creationalCallback = new CreationalCallback<App>() {
      public App getInstance(final CreationalContext context) {
        Class beanType = App.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final App inj1819_App = new App();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1819_App);
        final com_redhat_topicindex_extras_client_local_AppController_inj2130_proxy inj2130_proxy = new com_redhat_topicindex_extras_client_local_AppController_inj2130_proxy();
        context.addUnresolvedProxy(new ProxyResolver<AppController>() {
          public void resolve(AppController obj) {
            inj2130_proxy.__$setProxiedInstance$(obj);
            context.addProxyReference(inj2130_proxy, obj);
          }
        }, AppController.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } });
        com_redhat_topicindex_extras_client_local_App_appController(inj1819_App, inj2130_proxy);
        InitVotes.registerOneTimeInitCallback(new Runnable() {
          public void run() {
            GWT.runAsync(new RunAsyncCallback() {
              public void onFailure(Throwable throwable) {
                throw new RuntimeException("failed to run asynchronously", throwable);
              }
              public void onSuccess() {
                inj1819_App.startApp();
              }
            });
          }
        });
        return inj1819_App;
      }
    };
    final App inj1819_App = inj2129_App_creationalCallback.getInstance(context);
    final CreationalCallback<AppController> inj2132_AppController_creationalCallback = new CreationalCallback<AppController>() {
      public AppController getInstance(final CreationalContext context) {
        Class beanType = AppController.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final AppController inj2131_AppController = new AppController();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2131_AppController);
        com_redhat_topicindex_extras_client_local_AppController_manager(inj2131_AppController, inj2116_IOCBeanManagerProvider.get());
        com_redhat_topicindex_extras_client_local_AppController_eventBus(inj2131_AppController, com_redhat_topicindex_extras_client_local_App_produceEventBus(inj1819_App));
        return inj2131_AppController;
      }
    };
    final AppController inj2131_AppController = inj2132_AppController_creationalCallback.getInstance(context);
    final CreationalCallback<BulkImageUploadView> inj2135_BulkImageUploadView_creationalCallback = new CreationalCallback<BulkImageUploadView>() {
      public BulkImageUploadView getInstance(final CreationalContext context) {
        Class beanType = BulkImageUploadView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final BulkImageUploadView inj1875_BulkImageUploadView = new BulkImageUploadView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1875_BulkImageUploadView);
        return inj1875_BulkImageUploadView;
      }
    };
    final CreationalCallback<BulkImageUploadPresenter> inj2134_BulkImageUploadPresenter_creationalCallback = new CreationalCallback<BulkImageUploadPresenter>() {
      public BulkImageUploadPresenter getInstance(final CreationalContext context) {
        Class beanType = BulkImageUploadPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final BulkImageUploadPresenter inj2133_BulkImageUploadPresenter = new BulkImageUploadPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2133_BulkImageUploadPresenter);
        com_redhat_topicindex_extras_client_local_presenter_BulkImageUploadPresenter_display(inj2133_BulkImageUploadPresenter, inj2135_BulkImageUploadView_creationalCallback.getInstance(context));
        return inj2133_BulkImageUploadPresenter;
      }
    };
    final CreationalCallback<InstanceProvider> inj2136_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
      public InstanceProvider getInstance(final CreationalContext context) {
        Class beanType = InstanceProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InstanceProvider inj2122_InstanceProvider = new InstanceProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2122_InstanceProvider);
        return inj2122_InstanceProvider;
      }
    };
    final InstanceProvider inj2122_InstanceProvider = inj2136_InstanceProvider_creationalCallback.getInstance(context);
    final CreationalCallback<EventProvider> inj2137_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
      public EventProvider getInstance(final CreationalContext context) {
        Class beanType = EventProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final EventProvider inj2118_EventProvider = new EventProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2118_EventProvider);
        return inj2118_EventProvider;
      }
    };
    final EventProvider inj2118_EventProvider = inj2137_EventProvider_creationalCallback.getInstance(context);
    final CreationalCallback<DisposerProvider> inj2138_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
      public DisposerProvider getInstance(final CreationalContext context) {
        Class beanType = DisposerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DisposerProvider inj2106_DisposerProvider = new DisposerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2106_DisposerProvider);
        org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj2106_DisposerProvider, inj2116_IOCBeanManagerProvider.get());
        return inj2106_DisposerProvider;
      }
    };
    final DisposerProvider inj2106_DisposerProvider = inj2138_DisposerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<InitBallotProvider> inj2139_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
      public InitBallotProvider getInstance(final CreationalContext context) {
        Class beanType = InitBallotProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InitBallotProvider inj2112_InitBallotProvider = new InitBallotProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2112_InitBallotProvider);
        return inj2112_InitBallotProvider;
      }
    };
    final InitBallotProvider inj2112_InitBallotProvider = inj2139_InitBallotProvider_creationalCallback.getInstance(context);
    final CreationalCallback<TopicImportView> inj2142_TopicImportView_creationalCallback = new CreationalCallback<TopicImportView>() {
      public TopicImportView getInstance(final CreationalContext context) {
        Class beanType = TopicImportView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final TopicImportView inj1876_TopicImportView = new TopicImportView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1876_TopicImportView);
        return inj1876_TopicImportView;
      }
    };
    final CreationalCallback<TopicImportPresenter> inj2141_TopicImportPresenter_creationalCallback = new CreationalCallback<TopicImportPresenter>() {
      public TopicImportPresenter getInstance(final CreationalContext context) {
        Class beanType = TopicImportPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final TopicImportPresenter inj2140_TopicImportPresenter = new TopicImportPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2140_TopicImportPresenter);
        com_redhat_topicindex_extras_client_local_presenter_TopicImportPresenter_display(inj2140_TopicImportPresenter, inj2142_TopicImportView_creationalCallback.getInstance(context));
        return inj2140_TopicImportPresenter;
      }
    };
    injContext.addBean(CallerProvider.class, inj2123_CallerProvider_creationalCallback, inj2104_CallerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2123_CallerProvider_creationalCallback, inj2104_CallerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(RequestDispatcherProvider.class, inj2124_RequestDispatcherProvider_creationalCallback, inj2108_RequestDispatcherProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2124_RequestDispatcherProvider_creationalCallback, inj2108_RequestDispatcherProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(SenderProvider.class, inj2125_SenderProvider_creationalCallback, inj2120_SenderProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2125_SenderProvider_creationalCallback, inj2120_SenderProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(RootPanelProvider.class, inj2126_RootPanelProvider_creationalCallback, inj2110_RootPanelProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2126_RootPanelProvider_creationalCallback, inj2110_RootPanelProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IOCBeanManagerProvider.class, inj2127_IOCBeanManagerProvider_creationalCallback, inj2116_IOCBeanManagerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2127_IOCBeanManagerProvider_creationalCallback, inj2116_IOCBeanManagerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MessageBusProvider.class, inj2128_MessageBusProvider_creationalCallback, inj2114_MessageBusProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2128_MessageBusProvider_creationalCallback, inj2114_MessageBusProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(App.class, inj2129_App_creationalCallback, inj1819_App, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(AppController.class, inj2132_AppController_creationalCallback, inj2131_AppController, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2132_AppController_creationalCallback, inj2131_AppController, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ValueChangeHandler.class, inj2132_AppController_creationalCallback, inj2131_AppController, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(BulkImageUploadView.class, inj2135_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Display.class, inj2135_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Composite.class, inj2135_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsRenderable.class, inj2135_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventListener.class, inj2135_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasAttachHandlers.class, inj2135_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsWidget.class, inj2135_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(UIObject.class, inj2135_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasVisibility.class, inj2135_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(BulkImageUploadPresenter.class, inj2134_BulkImageUploadPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2134_BulkImageUploadPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InstanceProvider.class, inj2136_InstanceProvider_creationalCallback, inj2122_InstanceProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2136_InstanceProvider_creationalCallback, inj2122_InstanceProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventProvider.class, inj2137_EventProvider_creationalCallback, inj2118_EventProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2137_EventProvider_creationalCallback, inj2118_EventProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DisposerProvider.class, inj2138_DisposerProvider_creationalCallback, inj2106_DisposerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2138_DisposerProvider_creationalCallback, inj2106_DisposerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InitBallotProvider.class, inj2139_InitBallotProvider_creationalCallback, inj2112_InitBallotProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2139_InitBallotProvider_creationalCallback, inj2112_InitBallotProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(TopicImportView.class, inj2142_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter.Display.class, inj2142_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Composite.class, inj2142_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsRenderable.class, inj2142_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventListener.class, inj2142_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasAttachHandlers.class, inj2142_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsWidget.class, inj2142_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(UIObject.class, inj2142_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasVisibility.class, inj2142_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(TopicImportPresenter.class, inj2141_TopicImportPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2141_TopicImportPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    return injContext;
  }
}