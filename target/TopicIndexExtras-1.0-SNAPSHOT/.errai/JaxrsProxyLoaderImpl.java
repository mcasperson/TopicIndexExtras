package org.jboss.errai.enterprise.client.jaxrs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.redhat.topicindex.extras.client.local.RESTInterfaceV1;
import com.redhat.topicindex.rest.collections.BaseRestCollectionV1;
import com.redhat.topicindex.rest.entities.BlobConstantV1;
import com.redhat.topicindex.rest.entities.RoleV1;
import com.redhat.topicindex.rest.entities.StringConstantV1;
import com.redhat.topicindex.rest.entities.UserV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTCategoryV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTImageV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTProjectV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTPropertyTagV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTagV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTopicV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicStringV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicV1;
import com.redhat.topicindex.rest.expand.ExpandDataTrunk;
import java.lang.annotation.Annotation;
import java.util.List;
import javax.ws.rs.core.PathSegment;
import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.bus.client.framework.ProxyProvider;
import org.jboss.errai.bus.client.framework.RemoteServiceProxyFactory;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseException;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;

public class JaxrsProxyLoaderImpl implements JaxrsProxyLoader {
  public void loadProxies() {
    class RESTInterfaceV1Impl implements RESTInterfaceV1, JaxrsProxy {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      private String baseUrl;
      private List<Integer> successCodes;
      public void setQualifiers(Annotation[] annos) {

      }

      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public void setSuccessCodes(List codes) {
        successCodes = codes;
      }

      public void setBaseUrl(String url) {
        baseUrl = url;
      }

      public String getBaseUrl() {
        if (baseUrl != null) {
          return baseUrl;
        } else {
          return RestClient.getApplicationRoot();
        }
      }

      private void handleError(Throwable throwable, Response response) {
        if (errorCallback != null) {
          errorCallback.error(null, throwable);
        } else if ((RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) && (response != null)) {
          RESTInterfaceV1Impl.this.remoteCallback.callback(response);
        } else {
          GWT.log(throwable.getMessage(), throwable);
        }
      }

      public String deleteJSONPTranslatedTopicStrings(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstrings/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPUsers(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPUser(String a0, UserV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPUsers(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPUser(String a0, UserV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPUsers(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPUser(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPUsers(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONUsers(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public UserV1 updateJSONUser(String a0, UserV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), UserV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONUsers(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public UserV1 createJSONUser(String a0, UserV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), UserV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 createJSONUsers(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTPropertyTagV1 createJSONPropertyTag(String a0, RESTPropertyTagV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 createJSONPropertyTags(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTPropertyTagV1 deleteJSONPropertyTag(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONPropertyTags(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPBlobConstant(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPBlobConstants(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPBlobConstant(String a0, BlobConstantV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPBlobConstants(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPBlobConstant(String a0, BlobConstantV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPBlobConstants(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPBlobConstant(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPBlobConstants(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BlobConstantV1 getJSONBlobConstant(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BlobConstantV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONBlobConstants(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BlobConstantV1 updateJSONBlobConstant(String a0, BlobConstantV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BlobConstantV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONBlobConstants(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BlobConstantV1 createJSONBlobConstant(String a0, BlobConstantV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BlobConstantV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 createJSONBlobConstants(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BlobConstantV1 deleteJSONBlobConstant(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BlobConstantV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONBlobConstants(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPProject(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPProjects(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPProject(String a0, RESTProjectV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPProjects(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPProject(String a0, RESTProjectV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPProjects(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPProject(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPProjects(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTProjectV1 getJSONProject(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONProjects(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTProjectV1 updateJSONProject(String a0, RESTProjectV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONProjects(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTProjectV1 createJSONProject(String a0, RESTProjectV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 createJSONProjects(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTProjectV1 deleteJSONProject(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONProjects(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPTag(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPTags(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPTag(String a0, RESTTagV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPTags(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPTag(String a0, RESTTagV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPTags(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPTag(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPTags(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTagV1 getJSONTag(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONTags(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTagV1 updateJSONTag(String a0, RESTTagV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONTags(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTagV1 createJSONTag(String a0, RESTTagV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 createJSONTags(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTagV1 deleteJSONTag(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONTags(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPCategory(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPCategories(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPCategory(String a0, RESTCategoryV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPCategories(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPCategory(String a0, RESTCategoryV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPCategories(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPCategory(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPCategories(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTCategoryV1 getJSONCategory(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONCategories(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTCategoryV1 updateJSONCategory(String a0, RESTCategoryV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONCategories(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTCategoryV1 createJSONCategory(String a0, RESTCategoryV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 createJSONCategories(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTCategoryV1 deleteJSONCategory(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONCategories(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPImage(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPImages(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPImage(String a0, RESTImageV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPImages(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPImage(String a0, RESTImageV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPImages(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPImage(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPImages(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTImageV1 getJSONImage(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONImages(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTImageV1 updateJSONImage(String a0, RESTImageV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONImages(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTImageV1 createJSONImage(String a0, RESTImageV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 createJSONImages(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTImageV1 deleteJSONImage(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONImages(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPTopicsWithQuery(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/get/jsonp/{query}".replace("{query}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPTopics(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPTopic(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPTopic(String a0, RESTTopicV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPTopics(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPTopic(String a0, RESTTopicV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPTopics(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPTopic(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPTopics(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONTopics(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONTopicsWithQuery(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/get/json/{query}".replace("{query}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getXMLTopics(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/get/xml/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/xml");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTopicV1 getJSONTopic(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTopicV1 getJSONTopicRevision(Integer a0, Integer a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/json/{id}/r/{rev}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString())).replace("{rev}", URL.encodePathSegment(new Integer(a1).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTopicV1 getXMLTopic(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/xml/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/xml");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTopicV1 getXMLTopicRevision(Integer a0, Integer a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/xml/{id}/r/{rev}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString())).replace("{rev}", URL.encodePathSegment(new Integer(a1).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/xml");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getXMLTopicXML(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/xml/{id}/xml".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/xml");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getXMLTopicXMLContained(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/xml/{id}/xmlContainedIn".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("container").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/xml");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getXMLTopicXMLNoContainer(Integer a0, String a1, Boolean a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/xml/{id}/xmlNoContainer".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("includeTitle").append("=").append(URL.encodeQueryString(new Boolean(a2).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "text/plain");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getHTMLTopicHTML(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/html/{id}/html".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/xhtml+xml");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getHTMLTopicRevisionHTML(Integer a0, Integer a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/html/{id}/r/{rev}/html".replace("{id}", URL.encodePathSegment(new Integer(a0).toString())).replace("{rev}", URL.encodePathSegment(new Integer(a1).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/xhtml+xml");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTopicV1 updateJSONTopic(String a0, RESTTopicV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONTopics(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTopicV1 createJSONTopic(String a0, RESTTopicV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 createJSONTopics(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTopicV1 deleteJSONTopic(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONTopics(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public UserV1 deleteJSONUser(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), UserV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONUsers(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPStringConstant(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPStringConstants(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPStringConstant(String a0, StringConstantV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPStringConstants(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPStringConstant(String a0, StringConstantV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPStringConstants(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPStringConstant(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPStringConstants(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public StringConstantV1 getJSONStringConstant(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), StringConstantV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONStringConstants(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public StringConstantV1 updateJSONStringConstant(String a0, StringConstantV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), StringConstantV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONStringConstants(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public StringConstantV1 createJSONStringConstant(String a0, StringConstantV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), StringConstantV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 createJSONStringConstants(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public StringConstantV1 deleteJSONStringConstant(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), StringConstantV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONStringConstants(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPUser(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPTranslatedTopicString(String a0, RESTTranslatedTopicStringV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstring/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONTranslatedTopicStrings(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstrings/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPTranslatedTopicStrings(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstrings/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTranslatedTopicStringV1 deleteJSONTranslatedTopicString(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstring/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicStringV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPTranslatedTopicString(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstring/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONTranslatedTopicStrings(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstrings/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public ExpandDataTrunk getJSONExpandTrunkExample() {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/expanddatatrunk/get/json");
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), ExpandDataTrunk.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPTranslatedTopic(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPTranslatedTopics(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPTranslatedTopic(String a0, RESTTranslatedTopicV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPTranslatedTopics(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPTranslatedTopic(String a0, RESTTranslatedTopicV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPTranslatedTopics(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPTranslatedTopic(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPTranslatedTopics(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTranslatedTopicV1 getJSONTranslatedTopic(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONTranslatedTopicsWithQuery(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/get/json/{query}".replace("{query}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONTranslatedTopics(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTranslatedTopicV1 updateJSONTranslatedTopic(String a0, RESTTranslatedTopicV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONTranslatedTopics(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTranslatedTopicV1 createJSONTranslatedTopic(String a0, RESTTranslatedTopicV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 createJSONTranslatedTopics(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTranslatedTopicV1 deleteJSONTranslatedTopic(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONTranslatedTopics(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPRole(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPRoles(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPRole(String a0, RoleV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPRoles(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPRole(String a0, RoleV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPRoles(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPRole(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPRoles(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RoleV1 getJSONRole(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RoleV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONRoles(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RoleV1 updateJSONRole(String a0, RoleV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RoleV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONRoles(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RoleV1 createJSONRole(String a0, RoleV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RoleV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 createJSONRoles(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/post/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RoleV1 deleteJSONRole(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RoleV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 deleteJSONRoles(PathSegment a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/delete/json/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPPropertyTag(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPPropertyTags(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPPropertyTag(String a0, RESTPropertyTagV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPPropertyTags(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/put/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPPropertyTag(String a0, RESTPropertyTagV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String createJSONPPropertyTags(String a0, BaseRestCollectionV1 a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/post/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPPropertyTag(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPPropertyTags(PathSegment a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/delete/jsonp/{ids}".replace("{ids}", URL.encodePathSegment(MarshallingWrapper.toJSON(a0)))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTPropertyTagV1 getJSONPropertyTag(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONPropertyTags(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTPropertyTagV1 updateJSONPropertyTag(String a0, RESTPropertyTagV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 updateJSONPropertyTags(String a0, BaseRestCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public BaseRestCollectionV1 getJSONTranslatedTopicStrings(String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstrings/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), BaseRestCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPTranslatedTopicStrings(String a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstrings/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTranslatedTopicStringV1 updateJSONTranslatedTopicString(String a0, RESTTranslatedTopicStringV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstring/put/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        try {
          requestBuilder.sendRequest(MarshallingWrapper.toJSON(a1), new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicStringV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public RESTTranslatedTopicStringV1 getJSONTranslatedTopicString(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstring/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicStringV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPTranslatedTopicString(Integer a0, String a1, String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopicstring/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(String.valueOf(response.getText()));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public UserV1 getJSONUser(Integer a0, String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), UserV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public void setRerenderTopic(Boolean a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/settings/rerenderTopic").append("?").append("enabled").append("=").append(URL.encodeQueryString(new Boolean(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.PUT, url.toString());
        requestBuilder.setHeader("Content-Type", "*");
        try {
          requestBuilder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable throwable) {
              RESTInterfaceV1Impl.this.handleError(throwable, null);
            }
            public void onResponseReceived(Request request, Response response) {
              if (((RESTInterfaceV1Impl.this.successCodes == null) || RESTInterfaceV1Impl.this.successCodes.contains(response.getStatusCode())) && ((response.getStatusCode() >= 200) && (response.getStatusCode() < 300))) {
                if (RESTInterfaceV1Impl.this.remoteCallback instanceof ResponseCallback) {
                  RESTInterfaceV1Impl.this.remoteCallback.callback(response);
                } else {
                  if (response.getStatusCode() == 204) {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  } else {
                    RESTInterfaceV1Impl.this.remoteCallback.callback(null);
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(RESTInterfaceV1.class, new ProxyProvider() {
      public Object getProxy() {
        return new RESTInterfaceV1Impl();
      }
    });
  }
}