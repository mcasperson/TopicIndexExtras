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
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUpdaterPresenter;
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter;
import com.redhat.topicindex.extras.client.local.presenter.BulkImageUploadPresenter.Display;
import com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter;
import com.redhat.topicindex.extras.client.local.view.BulkImageUpdaterView;
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
  static class com_redhat_topicindex_extras_client_local_AppController_inj2132_proxy extends AppController {
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

  private native static void com_redhat_topicindex_extras_client_local_presenter_BulkImageUpdaterPresenter_display(BulkImageUpdaterPresenter instance, com.redhat.topicindex.extras.client.local.presenter.BulkImageUpdaterPresenter.Display value) /*-{
    instance.@com.redhat.topicindex.extras.client.local.presenter.BulkImageUpdaterPresenter::display = value;
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
    final CreationalCallback<RequestDispatcherProvider> inj2124_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
      public RequestDispatcherProvider getInstance(final CreationalContext context) {
        Class beanType = RequestDispatcherProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final RequestDispatcherProvider inj2109_RequestDispatcherProvider = new RequestDispatcherProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2109_RequestDispatcherProvider);
        return inj2109_RequestDispatcherProvider;
      }
    };
    final RequestDispatcherProvider inj2109_RequestDispatcherProvider = inj2124_RequestDispatcherProvider_creationalCallback.getInstance(context);
    final CreationalCallback<MessageBusProvider> inj2125_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
      public MessageBusProvider getInstance(final CreationalContext context) {
        Class beanType = MessageBusProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final MessageBusProvider inj2115_MessageBusProvider = new MessageBusProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2115_MessageBusProvider);
        return inj2115_MessageBusProvider;
      }
    };
    final MessageBusProvider inj2115_MessageBusProvider = inj2125_MessageBusProvider_creationalCallback.getInstance(context);
    final CreationalCallback<IOCBeanManagerProvider> inj2126_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
      public IOCBeanManagerProvider getInstance(final CreationalContext context) {
        Class beanType = IOCBeanManagerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final IOCBeanManagerProvider inj2117_IOCBeanManagerProvider = new IOCBeanManagerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2117_IOCBeanManagerProvider);
        return inj2117_IOCBeanManagerProvider;
      }
    };
    final IOCBeanManagerProvider inj2117_IOCBeanManagerProvider = inj2126_IOCBeanManagerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<BulkImageUpdaterView> inj2129_BulkImageUpdaterView_creationalCallback = new CreationalCallback<BulkImageUpdaterView>() {
      public BulkImageUpdaterView getInstance(final CreationalContext context) {
        Class beanType = BulkImageUpdaterView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final BulkImageUpdaterView inj1878_BulkImageUpdaterView = new BulkImageUpdaterView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1878_BulkImageUpdaterView);
        return inj1878_BulkImageUpdaterView;
      }
    };
    final CreationalCallback<BulkImageUpdaterPresenter> inj2128_BulkImageUpdaterPresenter_creationalCallback = new CreationalCallback<BulkImageUpdaterPresenter>() {
      public BulkImageUpdaterPresenter getInstance(final CreationalContext context) {
        Class beanType = BulkImageUpdaterPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final BulkImageUpdaterPresenter inj2127_BulkImageUpdaterPresenter = new BulkImageUpdaterPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2127_BulkImageUpdaterPresenter);
        com_redhat_topicindex_extras_client_local_presenter_BulkImageUpdaterPresenter_display(inj2127_BulkImageUpdaterPresenter, inj2129_BulkImageUpdaterView_creationalCallback.getInstance(context));
        return inj2127_BulkImageUpdaterPresenter;
      }
    };
    final CreationalCallback<EventProvider> inj2130_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
      public EventProvider getInstance(final CreationalContext context) {
        Class beanType = EventProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final EventProvider inj2119_EventProvider = new EventProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2119_EventProvider);
        return inj2119_EventProvider;
      }
    };
    final EventProvider inj2119_EventProvider = inj2130_EventProvider_creationalCallback.getInstance(context);
    final CreationalCallback<App> inj2131_App_creationalCallback = new CreationalCallback<App>() {
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
        final com_redhat_topicindex_extras_client_local_AppController_inj2132_proxy inj2132_proxy = new com_redhat_topicindex_extras_client_local_AppController_inj2132_proxy();
        context.addUnresolvedProxy(new ProxyResolver<AppController>() {
          public void resolve(AppController obj) {
            inj2132_proxy.__$setProxiedInstance$(obj);
            context.addProxyReference(inj2132_proxy, obj);
          }
        }, AppController.class, new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } });
        com_redhat_topicindex_extras_client_local_App_appController(inj1819_App, inj2132_proxy);
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
    final App inj1819_App = inj2131_App_creationalCallback.getInstance(context);
    final CreationalCallback<AppController> inj2134_AppController_creationalCallback = new CreationalCallback<AppController>() {
      public AppController getInstance(final CreationalContext context) {
        Class beanType = AppController.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final AppController inj2133_AppController = new AppController();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2133_AppController);
        com_redhat_topicindex_extras_client_local_AppController_manager(inj2133_AppController, inj2117_IOCBeanManagerProvider.get());
        com_redhat_topicindex_extras_client_local_AppController_eventBus(inj2133_AppController, com_redhat_topicindex_extras_client_local_App_produceEventBus(inj1819_App));
        return inj2133_AppController;
      }
    };
    final AppController inj2133_AppController = inj2134_AppController_creationalCallback.getInstance(context);
    final CreationalCallback<InitBallotProvider> inj2135_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
      public InitBallotProvider getInstance(final CreationalContext context) {
        Class beanType = InitBallotProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InitBallotProvider inj2113_InitBallotProvider = new InitBallotProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2113_InitBallotProvider);
        return inj2113_InitBallotProvider;
      }
    };
    final InitBallotProvider inj2113_InitBallotProvider = inj2135_InitBallotProvider_creationalCallback.getInstance(context);
    final CreationalCallback<CallerProvider> inj2136_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
      public CallerProvider getInstance(final CreationalContext context) {
        Class beanType = CallerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final CallerProvider inj2105_CallerProvider = new CallerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2105_CallerProvider);
        return inj2105_CallerProvider;
      }
    };
    final CallerProvider inj2105_CallerProvider = inj2136_CallerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<SenderProvider> inj2137_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
      public SenderProvider getInstance(final CreationalContext context) {
        Class beanType = SenderProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final SenderProvider inj2121_SenderProvider = new SenderProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2121_SenderProvider);
        return inj2121_SenderProvider;
      }
    };
    final SenderProvider inj2121_SenderProvider = inj2137_SenderProvider_creationalCallback.getInstance(context);
    final CreationalCallback<RootPanelProvider> inj2138_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
      public RootPanelProvider getInstance(final CreationalContext context) {
        Class beanType = RootPanelProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final RootPanelProvider inj2111_RootPanelProvider = new RootPanelProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2111_RootPanelProvider);
        return inj2111_RootPanelProvider;
      }
    };
    final RootPanelProvider inj2111_RootPanelProvider = inj2138_RootPanelProvider_creationalCallback.getInstance(context);
    final CreationalCallback<BulkImageUploadView> inj2141_BulkImageUploadView_creationalCallback = new CreationalCallback<BulkImageUploadView>() {
      public BulkImageUploadView getInstance(final CreationalContext context) {
        Class beanType = BulkImageUploadView.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final BulkImageUploadView inj1876_BulkImageUploadView = new BulkImageUploadView();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj1876_BulkImageUploadView);
        return inj1876_BulkImageUploadView;
      }
    };
    final CreationalCallback<BulkImageUploadPresenter> inj2140_BulkImageUploadPresenter_creationalCallback = new CreationalCallback<BulkImageUploadPresenter>() {
      public BulkImageUploadPresenter getInstance(final CreationalContext context) {
        Class beanType = BulkImageUploadPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final BulkImageUploadPresenter inj2139_BulkImageUploadPresenter = new BulkImageUploadPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2139_BulkImageUploadPresenter);
        com_redhat_topicindex_extras_client_local_presenter_BulkImageUploadPresenter_display(inj2139_BulkImageUploadPresenter, inj2141_BulkImageUploadView_creationalCallback.getInstance(context));
        return inj2139_BulkImageUploadPresenter;
      }
    };
    final CreationalCallback<InstanceProvider> inj2142_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
      public InstanceProvider getInstance(final CreationalContext context) {
        Class beanType = InstanceProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final InstanceProvider inj2123_InstanceProvider = new InstanceProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2123_InstanceProvider);
        return inj2123_InstanceProvider;
      }
    };
    final InstanceProvider inj2123_InstanceProvider = inj2142_InstanceProvider_creationalCallback.getInstance(context);
    final CreationalCallback<DisposerProvider> inj2143_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
      public DisposerProvider getInstance(final CreationalContext context) {
        Class beanType = DisposerProvider.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final DisposerProvider inj2107_DisposerProvider = new DisposerProvider();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2107_DisposerProvider);
        org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj2107_DisposerProvider, inj2117_IOCBeanManagerProvider.get());
        return inj2107_DisposerProvider;
      }
    };
    final DisposerProvider inj2107_DisposerProvider = inj2143_DisposerProvider_creationalCallback.getInstance(context);
    final CreationalCallback<TopicImportView> inj2146_TopicImportView_creationalCallback = new CreationalCallback<TopicImportView>() {
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
    final CreationalCallback<TopicImportPresenter> inj2145_TopicImportPresenter_creationalCallback = new CreationalCallback<TopicImportPresenter>() {
      public TopicImportPresenter getInstance(final CreationalContext context) {
        Class beanType = TopicImportPresenter.class;
        Annotation[] qualifiers = new Annotation[] { new Any() {
            public Class annotationType() {
              return Any.class;
            }
        } };
        final TopicImportPresenter inj2144_TopicImportPresenter = new TopicImportPresenter();
        BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
        context.addBean(beanRef, inj2144_TopicImportPresenter);
        com_redhat_topicindex_extras_client_local_presenter_TopicImportPresenter_display(inj2144_TopicImportPresenter, inj2146_TopicImportView_creationalCallback.getInstance(context));
        return inj2144_TopicImportPresenter;
      }
    };
    injContext.addBean(RequestDispatcherProvider.class, inj2124_RequestDispatcherProvider_creationalCallback, inj2109_RequestDispatcherProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2124_RequestDispatcherProvider_creationalCallback, inj2109_RequestDispatcherProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(MessageBusProvider.class, inj2125_MessageBusProvider_creationalCallback, inj2115_MessageBusProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2125_MessageBusProvider_creationalCallback, inj2115_MessageBusProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IOCBeanManagerProvider.class, inj2126_IOCBeanManagerProvider_creationalCallback, inj2117_IOCBeanManagerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2126_IOCBeanManagerProvider_creationalCallback, inj2117_IOCBeanManagerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(BulkImageUpdaterView.class, inj2129_BulkImageUpdaterView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.redhat.topicindex.extras.client.local.presenter.BulkImageUpdaterPresenter.Display.class, inj2129_BulkImageUpdaterView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Composite.class, inj2129_BulkImageUpdaterView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsRenderable.class, inj2129_BulkImageUpdaterView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventListener.class, inj2129_BulkImageUpdaterView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasAttachHandlers.class, inj2129_BulkImageUpdaterView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsWidget.class, inj2129_BulkImageUpdaterView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(UIObject.class, inj2129_BulkImageUpdaterView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasVisibility.class, inj2129_BulkImageUpdaterView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(BulkImageUpdaterPresenter.class, inj2128_BulkImageUpdaterPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2128_BulkImageUpdaterPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventProvider.class, inj2130_EventProvider_creationalCallback, inj2119_EventProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2130_EventProvider_creationalCallback, inj2119_EventProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(App.class, inj2131_App_creationalCallback, inj1819_App, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(AppController.class, inj2134_AppController_creationalCallback, inj2133_AppController, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2134_AppController_creationalCallback, inj2133_AppController, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ValueChangeHandler.class, inj2134_AppController_creationalCallback, inj2133_AppController, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InitBallotProvider.class, inj2135_InitBallotProvider_creationalCallback, inj2113_InitBallotProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2135_InitBallotProvider_creationalCallback, inj2113_InitBallotProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(CallerProvider.class, inj2136_CallerProvider_creationalCallback, inj2105_CallerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2136_CallerProvider_creationalCallback, inj2105_CallerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(SenderProvider.class, inj2137_SenderProvider_creationalCallback, inj2121_SenderProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2137_SenderProvider_creationalCallback, inj2121_SenderProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(RootPanelProvider.class, inj2138_RootPanelProvider_creationalCallback, inj2111_RootPanelProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Provider.class, inj2138_RootPanelProvider_creationalCallback, inj2111_RootPanelProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(BulkImageUploadView.class, inj2141_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Display.class, inj2141_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Composite.class, inj2141_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsRenderable.class, inj2141_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventListener.class, inj2141_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasAttachHandlers.class, inj2141_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsWidget.class, inj2141_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(UIObject.class, inj2141_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasVisibility.class, inj2141_BulkImageUploadView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(BulkImageUploadPresenter.class, inj2140_BulkImageUploadPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2140_BulkImageUploadPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(InstanceProvider.class, inj2142_InstanceProvider_creationalCallback, inj2123_InstanceProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2142_InstanceProvider_creationalCallback, inj2123_InstanceProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(DisposerProvider.class, inj2143_DisposerProvider_creationalCallback, inj2107_DisposerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(ContextualTypeProvider.class, inj2143_DisposerProvider_creationalCallback, inj2107_DisposerProvider, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(TopicImportView.class, inj2146_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(com.redhat.topicindex.extras.client.local.presenter.TopicImportPresenter.Display.class, inj2146_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Composite.class, inj2146_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsRenderable.class, inj2146_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(EventListener.class, inj2146_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasAttachHandlers.class, inj2146_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(IsWidget.class, inj2146_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(UIObject.class, inj2146_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(HasVisibility.class, inj2146_TopicImportView_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(TopicImportPresenter.class, inj2145_TopicImportPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    injContext.addBean(Presenter.class, inj2145_TopicImportPresenter_creationalCallback, null, BuiltInQualifiers.DEFAULT_QUALIFIERS);
    return injContext;
  }
}