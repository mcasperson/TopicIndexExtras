package org.jboss.errai.enterprise.client.jaxrs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import java.lang.annotation.Annotation;
import java.util.List;
import javax.ws.rs.core.PathSegment;
import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.bus.client.framework.ProxyProvider;
import org.jboss.errai.bus.client.framework.RemoteServiceProxyFactory;
import org.jboss.errai.enterprise.client.jaxrs.api.PathSegmentImpl;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseException;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTBlobConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTIntegerConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTRoleCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTStringConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTUserCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTIntegerConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTRoleV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTUserV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTLogDetailsV1;
import org.jboss.pressgang.ccms.rest.v1.expansion.ExpandDataTrunk;
import org.jboss.pressgang.ccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;

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

      public void setRerenderTopic(final Boolean a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/settings/rerenderTopic").append("?").append("enabled").append("=").append(URL.encodeQueryString(new Boolean(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
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

      public String getJSONPUser(final Integer a0, final String a1, final String a2) {
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

      public String getJSONPUsers(final String a0, final String a1) {
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

      public String getJSONPUsersWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPUser(final String a0, final RESTUserV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPUser(final String a0, final RESTUserV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPUsers(final String a0, final RESTUserCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPUsers(final String a0, final RESTUserCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPUser(final String a0, final RESTUserV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPUser(final String a0, final RESTUserV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPUsers(final String a0, final RESTUserCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPUsers(final String a0, final RESTUserCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPUser(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPUser(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPUsers(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPUsers(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTUserV1 getJSONUser(final Integer a0, final String a1) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserV1.class, null));
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

      public RESTUserCollectionV1 getJSONUsers(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserCollectionV1.class, null));
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

      public RESTUserCollectionV1 getJSONUsersWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserCollectionV1.class, null));
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

      public RESTUserV1 updateJSONUser(final String a0, final RESTUserV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserV1.class, null));
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

      public RESTUserV1 updateJSONUser(final String a0, final RESTUserV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserV1.class, null));
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

      public RESTUserCollectionV1 updateJSONUsers(final String a0, final RESTUserCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserCollectionV1.class, null));
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

      public RESTUserCollectionV1 updateJSONUsers(final String a0, final RESTUserCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserCollectionV1.class, null));
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

      public RESTUserV1 createJSONUser(final String a0, final RESTUserV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserV1.class, null));
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

      public RESTUserV1 createJSONUser(final String a0, final RESTUserV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserV1.class, null));
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

      public RESTUserCollectionV1 createJSONUsers(final String a0, final RESTUserCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserCollectionV1.class, null));
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

      public RESTUserCollectionV1 createJSONUsers(final String a0, final RESTUserCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserCollectionV1.class, null));
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

      public RESTUserV1 deleteJSONUser(final Integer a0, final String a1) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserV1.class, null));
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

      public RESTUserV1 deleteJSONUser(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/user/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserV1.class, null));
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

      public RESTUserCollectionV1 deleteJSONUsers(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserCollectionV1.class, null));
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

      public RESTUserCollectionV1 deleteJSONUsers(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/users/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTUserCollectionV1.class, null));
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

      public String getJSONPStringConstant(final Integer a0, final String a1, final String a2) {
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

      public String getJSONPStringConstants(final String a0, final String a1) {
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

      public String getJSONPStringConstantsWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPStringConstant(final String a0, final RESTStringConstantV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPStringConstant(final String a0, final RESTStringConstantV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPStringConstants(final String a0, final RESTStringConstantCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPStringConstants(final String a0, final RESTStringConstantCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPStringConstant(final String a0, final RESTStringConstantV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPStringConstant(final String a0, final RESTStringConstantV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPStringConstants(final String a0, final RESTStringConstantCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPStringConstants(final String a0, final RESTStringConstantCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPStringConstant(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPStringConstant(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPStringConstants(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPStringConstants(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTStringConstantV1 getJSONStringConstant(final Integer a0, final String a1) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantV1.class, null));
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

      public RESTStringConstantCollectionV1 getJSONStringConstants(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantCollectionV1.class, null));
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

      public RESTStringConstantCollectionV1 getJSONStringConstantsWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantCollectionV1.class, null));
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

      public RESTStringConstantV1 updateJSONStringConstant(final String a0, final RESTStringConstantV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantV1.class, null));
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

      public RESTStringConstantV1 updateJSONStringConstant(final String a0, final RESTStringConstantV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantV1.class, null));
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

      public RESTStringConstantCollectionV1 updateJSONStringConstants(final String a0, final RESTStringConstantCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantCollectionV1.class, null));
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

      public RESTStringConstantCollectionV1 updateJSONStringConstants(final String a0, final RESTStringConstantCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantCollectionV1.class, null));
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

      public RESTStringConstantV1 createJSONStringConstant(final String a0, final RESTStringConstantV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantV1.class, null));
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

      public RESTStringConstantV1 createJSONStringConstant(final String a0, final RESTStringConstantV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantV1.class, null));
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

      public RESTStringConstantCollectionV1 createJSONStringConstants(final String a0, final RESTStringConstantCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantCollectionV1.class, null));
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

      public RESTStringConstantCollectionV1 createJSONStringConstants(final String a0, final RESTStringConstantCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantCollectionV1.class, null));
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

      public RESTStringConstantV1 deleteJSONStringConstant(final Integer a0, final String a1) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantV1.class, null));
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

      public RESTStringConstantV1 deleteJSONStringConstant(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstant/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantV1.class, null));
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

      public RESTStringConstantCollectionV1 deleteJSONStringConstants(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantCollectionV1.class, null));
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

      public RESTStringConstantCollectionV1 deleteJSONStringConstants(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/stringconstants/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTStringConstantCollectionV1.class, null));
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

      public String getJSONPTranslatedTopic(final Integer a0, final String a1, final String a2) {
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

      public String getJSONPTranslatedTopics(final String a0, final String a1) {
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

      public String getJSONPTranslatedTopicsWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPTranslatedTopic(final String a0, final RESTTranslatedTopicV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPTranslatedTopic(final String a0, final RESTTranslatedTopicV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTranslatedTopic(final String a0, final RESTTranslatedTopicV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTranslatedTopic(final String a0, final RESTTranslatedTopicV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPTranslatedTopic(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPTranslatedTopic(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPTranslatedTopics(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPTranslatedTopics(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTTranslatedTopicV1 getJSONTranslatedTopic(final Integer a0, final String a1) {
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

      public RESTTranslatedTopicCollectionV1 getJSONTranslatedTopicsWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicCollectionV1.class, null));
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

      public RESTTranslatedTopicCollectionV1 getJSONTranslatedTopics(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicCollectionV1.class, null));
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

      public RESTTranslatedTopicV1 updateJSONTranslatedTopic(final String a0, final RESTTranslatedTopicV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTTranslatedTopicV1 updateJSONTranslatedTopic(final String a0, final RESTTranslatedTopicV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTTranslatedTopicCollectionV1 updateJSONTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicCollectionV1.class, null));
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

      public RESTTranslatedTopicCollectionV1 updateJSONTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicCollectionV1.class, null));
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

      public RESTTranslatedTopicV1 createJSONTranslatedTopic(final String a0, final RESTTranslatedTopicV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTTranslatedTopicV1 createJSONTranslatedTopic(final String a0, final RESTTranslatedTopicV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTTranslatedTopicCollectionV1 createJSONTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicCollectionV1.class, null));
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

      public RESTTranslatedTopicCollectionV1 createJSONTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicCollectionV1.class, null));
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

      public RESTTranslatedTopicV1 deleteJSONTranslatedTopic(final Integer a0, final String a1) {
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

      public RESTTranslatedTopicV1 deleteJSONTranslatedTopic(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopic/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTTranslatedTopicCollectionV1 deleteJSONTranslatedTopics(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicCollectionV1.class, null));
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

      public RESTTranslatedTopicCollectionV1 deleteJSONTranslatedTopics(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/translatedtopics/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicCollectionV1.class, null));
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

      public String getJSONPRole(final Integer a0, final String a1, final String a2) {
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

      public String getJSONPRoles(final String a0, final String a1) {
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

      public String getJSONPRolesWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPRole(final String a0, final RESTRoleV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPRole(final String a0, final RESTRoleV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPRoles(final String a0, final RESTRoleCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPRoles(final String a0, final RESTRoleCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPRole(final String a0, final RESTRoleV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPRole(final String a0, final RESTRoleV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPRoles(final String a0, final RESTRoleCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPRoles(final String a0, final RESTRoleCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPRole(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPRole(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPRoles(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPRoles(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTRoleV1 getJSONRole(final Integer a0, final String a1) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleV1.class, null));
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

      public RESTRoleCollectionV1 getJSONRoles(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleCollectionV1.class, null));
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

      public RESTRoleCollectionV1 getJSONRolesWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleCollectionV1.class, null));
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

      public RESTRoleV1 updateJSONRole(final String a0, final RESTRoleV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleV1.class, null));
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

      public RESTRoleV1 updateJSONRole(final String a0, final RESTRoleV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleV1.class, null));
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

      public RESTRoleCollectionV1 updateJSONRoles(final String a0, final RESTRoleCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleCollectionV1.class, null));
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

      public RESTRoleCollectionV1 updateJSONRoles(final String a0, final RESTRoleCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleCollectionV1.class, null));
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

      public RESTRoleV1 createJSONRole(final String a0, final RESTRoleV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleV1.class, null));
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

      public RESTRoleV1 createJSONRole(final String a0, final RESTRoleV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleV1.class, null));
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

      public RESTRoleCollectionV1 createJSONRoles(final String a0, final RESTRoleCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleCollectionV1.class, null));
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

      public RESTRoleCollectionV1 createJSONRoles(final String a0, final RESTRoleCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleCollectionV1.class, null));
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

      public RESTRoleV1 deleteJSONRole(final Integer a0, final String a1) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleV1.class, null));
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

      public RESTRoleV1 deleteJSONRole(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/role/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleV1.class, null));
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

      public RESTRoleCollectionV1 deleteJSONRoles(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleCollectionV1.class, null));
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

      public RESTRoleCollectionV1 deleteJSONRoles(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/roles/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTRoleCollectionV1.class, null));
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

      public String getJSONPPropertyTag(final Integer a0, final String a1, final String a2) {
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

      public String getJSONPPropertyTags(final String a0, final String a1) {
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

      public String getJSONPPropertyTagsWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPPropertyTag(final String a0, final RESTPropertyTagV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPPropertyTag(final String a0, final RESTPropertyTagV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPPropertyTag(final String a0, final RESTPropertyTagV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPPropertyTag(final String a0, final RESTPropertyTagV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPPropertyTag(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPPropertyTag(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPPropertyTags(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPPropertyTags(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTPropertyTagV1 getJSONPropertyTag(final Integer a0, final String a1) {
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

      public RESTPropertyTagCollectionV1 getJSONPropertyTags(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagCollectionV1.class, null));
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

      public RESTPropertyTagCollectionV1 getJSONPropertyTagsWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagCollectionV1.class, null));
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

      public RESTPropertyTagV1 updateJSONPropertyTag(final String a0, final RESTPropertyTagV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTPropertyTagV1 updateJSONPropertyTag(final String a0, final RESTPropertyTagV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTPropertyTagCollectionV1 updateJSONPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagCollectionV1.class, null));
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

      public RESTPropertyTagCollectionV1 updateJSONPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagCollectionV1.class, null));
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

      public RESTPropertyTagV1 createJSONPropertyTag(final String a0, final RESTPropertyTagV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTPropertyTagV1 createJSONPropertyTag(final String a0, final RESTPropertyTagV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTPropertyTagCollectionV1 createJSONPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagCollectionV1.class, null));
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

      public RESTPropertyTagCollectionV1 createJSONPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagCollectionV1.class, null));
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

      public RESTPropertyTagV1 deleteJSONPropertyTag(final Integer a0, final String a1) {
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

      public RESTPropertyTagV1 deleteJSONPropertyTag(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytag/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTPropertyTagCollectionV1 deleteJSONPropertyTags(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagCollectionV1.class, null));
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

      public RESTPropertyTagCollectionV1 deleteJSONPropertyTags(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertytags/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyTagCollectionV1.class, null));
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

      public String getJSONPPropertyCategory(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String getJSONPPropertyCategories(final String a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public String getJSONPPropertyCategoriesWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPPropertyCategory(final String a0, final RESTPropertyCategoryV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPPropertyCategory(final String a0, final RESTPropertyCategoryV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPPropertyCategories(final String a0, final RESTPropertyCategoryCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPPropertyCategories(final String a0, final RESTPropertyCategoryCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPPropertyCategory(final String a0, final RESTPropertyCategoryV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPPropertyCategory(final String a0, final RESTPropertyCategoryV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPPropertyCategories(final String a0, final RESTPropertyCategoryCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPPropertyCategories(final String a0, final RESTPropertyCategoryCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPPropertyCategory(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPPropertyCategory(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPPropertyCategories(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPPropertyCategories(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTPropertyCategoryV1 getJSONPropertyCategory(final Integer a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryV1.class, null));
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

      public RESTPropertyCategoryCollectionV1 getJSONPropertyCategories(final String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryCollectionV1.class, null));
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

      public RESTPropertyCategoryCollectionV1 getJSONPropertyCategoriesWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryCollectionV1.class, null));
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

      public RESTPropertyCategoryV1 updateJSONPropertyCategory(final String a0, final RESTPropertyCategoryV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryV1.class, null));
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

      public RESTPropertyCategoryV1 updateJSONPropertyCategory(final String a0, final RESTPropertyCategoryV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryV1.class, null));
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

      public RESTPropertyCategoryCollectionV1 updateJSONPropertyCategories(final String a0, final RESTPropertyCategoryCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryCollectionV1.class, null));
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

      public RESTPropertyCategoryCollectionV1 updateJSONPropertyCategories(final String a0, final RESTPropertyCategoryCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryCollectionV1.class, null));
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

      public RESTPropertyCategoryV1 createJSONPropertyCategory(final String a0, final RESTPropertyCategoryV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryV1.class, null));
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

      public RESTPropertyCategoryV1 createJSONPropertyCategory(final String a0, final RESTPropertyCategoryV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryV1.class, null));
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

      public RESTPropertyCategoryCollectionV1 createJSONPropertyCategories(final String a0, final RESTPropertyCategoryCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryCollectionV1.class, null));
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

      public RESTPropertyCategoryCollectionV1 createJSONPropertyCategories(final String a0, final RESTPropertyCategoryCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryCollectionV1.class, null));
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

      public RESTPropertyCategoryV1 deleteJSONPropertyCategory(final Integer a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryV1.class, null));
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

      public RESTPropertyCategoryV1 deleteJSONPropertyCategory(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategory/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryV1.class, null));
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

      public RESTPropertyCategoryCollectionV1 deleteJSONPropertyCategories(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryCollectionV1.class, null));
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

      public RESTPropertyCategoryCollectionV1 deleteJSONPropertyCategories(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/propertycategories/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTPropertyCategoryCollectionV1.class, null));
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

      public String getJSONPBlobConstant(final Integer a0, final String a1, final String a2) {
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

      public String getJSONPBlobConstantsWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String getJSONPBlobConstants(final String a0, final String a1) {
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

      public String updateJSONPBlobConstant(final String a0, final RESTBlobConstantV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPBlobConstant(final String a0, final RESTBlobConstantV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPBlobConstants(final String a0, final RESTBlobConstantCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPBlobConstants(final String a0, final RESTBlobConstantCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPBlobConstant(final String a0, final RESTBlobConstantV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPBlobConstant(final String a0, final RESTBlobConstantV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPBlobConstants(final String a0, final RESTBlobConstantCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPBlobConstants(final String a0, final RESTBlobConstantCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPBlobConstant(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPBlobConstant(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPBlobConstants(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPBlobConstants(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTBlobConstantV1 getJSONBlobConstant(final Integer a0, final String a1) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantV1.class, null));
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

      public RESTBlobConstantCollectionV1 getJSONBlobConstants(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantCollectionV1.class, null));
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

      public RESTBlobConstantCollectionV1 getJSONBlobConstantsWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantCollectionV1.class, null));
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

      public RESTBlobConstantV1 updateJSONBlobConstant(final String a0, final RESTBlobConstantV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantV1.class, null));
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

      public RESTBlobConstantV1 updateJSONBlobConstant(final String a0, final RESTBlobConstantV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantV1.class, null));
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

      public RESTBlobConstantCollectionV1 updateJSONBlobConstants(final String a0, final RESTBlobConstantCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantCollectionV1.class, null));
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

      public RESTBlobConstantCollectionV1 updateJSONBlobConstants(final String a0, final RESTBlobConstantCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantCollectionV1.class, null));
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

      public RESTBlobConstantV1 createJSONBlobConstant(final String a0, final RESTBlobConstantV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantV1.class, null));
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

      public RESTBlobConstantV1 createJSONBlobConstant(final String a0, final RESTBlobConstantV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantV1.class, null));
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

      public RESTBlobConstantCollectionV1 createJSONBlobConstants(final String a0, final RESTBlobConstantCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantCollectionV1.class, null));
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

      public RESTBlobConstantCollectionV1 createJSONBlobConstants(final String a0, final RESTBlobConstantCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantCollectionV1.class, null));
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

      public RESTBlobConstantV1 deleteJSONBlobConstant(final Integer a0, final String a1) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantV1.class, null));
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

      public RESTBlobConstantV1 deleteJSONBlobConstant(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstant/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantV1.class, null));
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

      public RESTBlobConstantCollectionV1 deleteJSONBlobConstants(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantCollectionV1.class, null));
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

      public RESTBlobConstantCollectionV1 deleteJSONBlobConstants(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/blobconstants/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTBlobConstantCollectionV1.class, null));
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

      public String getJSONPProject(final Integer a0, final String a1, final String a2) {
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

      public String getJSONPProjects(final String a0, final String a1) {
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

      public String getJSONPProjectsWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPProject(final String a0, final RESTProjectV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPProject(final String a0, final RESTProjectV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPProjects(final String a0, final RESTProjectCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPProjects(final String a0, final RESTProjectCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPProject(final String a0, final RESTProjectV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPProject(final String a0, final RESTProjectV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPProjects(final String a0, final RESTProjectCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPProjects(final String a0, final RESTProjectCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPProject(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPProject(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPProjects(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPProjects(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTProjectV1 getJSONProject(final Integer a0, final String a1) {
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

      public RESTProjectCollectionV1 getJSONProjects(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectCollectionV1.class, null));
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

      public RESTProjectCollectionV1 getJSONProjectsWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectCollectionV1.class, null));
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

      public RESTProjectV1 updateJSONProject(final String a0, final RESTProjectV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTProjectV1 updateJSONProject(final String a0, final RESTProjectV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTProjectCollectionV1 updateJSONProjects(final String a0, final RESTProjectCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectCollectionV1.class, null));
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

      public RESTProjectCollectionV1 updateJSONProjects(final String a0, final RESTProjectCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectCollectionV1.class, null));
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

      public RESTProjectV1 createJSONProject(final String a0, final RESTProjectV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTProjectV1 createJSONProject(final String a0, final RESTProjectV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTProjectCollectionV1 createJSONProjects(final String a0, final RESTProjectCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectCollectionV1.class, null));
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

      public RESTProjectCollectionV1 createJSONProjects(final String a0, final RESTProjectCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectCollectionV1.class, null));
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

      public RESTProjectV1 deleteJSONProject(final Integer a0, final String a1) {
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

      public RESTProjectV1 deleteJSONProject(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/project/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTProjectCollectionV1 deleteJSONProjects(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectCollectionV1.class, null));
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

      public RESTProjectCollectionV1 deleteJSONProjects(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/projects/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTProjectCollectionV1.class, null));
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

      public String getJSONPTag(final Integer a0, final String a1, final String a2) {
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

      public String getJSONPTags(final String a0, final String a1) {
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

      public String getJSONPTagsWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPTag(final String a0, final RESTTagV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPTag(final String a0, final RESTTagV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPTags(final String a0, final RESTTagCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPTags(final String a0, final RESTTagCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTag(final String a0, final RESTTagV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTag(final String a0, final RESTTagV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTags(final String a0, final RESTTagCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTags(final String a0, final RESTTagCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPTag(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPTag(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPTags(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPTags(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTTagV1 getJSONTag(final Integer a0, final String a1) {
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

      public RESTTagCollectionV1 getJSONTags(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagCollectionV1.class, null));
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

      public RESTTagCollectionV1 getJSONTagsWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagCollectionV1.class, null));
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

      public RESTTagV1 updateJSONTag(final String a0, final RESTTagV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTTagV1 updateJSONTag(final String a0, final RESTTagV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTTagCollectionV1 updateJSONTags(final String a0, final RESTTagCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagCollectionV1.class, null));
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

      public RESTTagCollectionV1 updateJSONTags(final String a0, final RESTTagCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagCollectionV1.class, null));
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

      public RESTTagV1 createJSONTag(final String a0, final RESTTagV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTTagV1 createJSONTag(final String a0, final RESTTagV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTTagCollectionV1 createJSONTags(final String a0, final RESTTagCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagCollectionV1.class, null));
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

      public RESTTagCollectionV1 createJSONTags(final String a0, final RESTTagCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagCollectionV1.class, null));
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

      public RESTTagV1 deleteJSONTag(final Integer a0, final String a1) {
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

      public RESTTagV1 deleteJSONTag(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tag/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTTagCollectionV1 deleteJSONTags(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagCollectionV1.class, null));
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

      public RESTTagCollectionV1 deleteJSONTags(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/tags/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTagCollectionV1.class, null));
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

      public String getJSONPCategory(final Integer a0, final String a1, final String a2) {
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

      public String getJSONPCategories(final String a0, final String a1) {
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

      public String getJSONPCategoriesWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPCategory(final String a0, final RESTCategoryV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPCategory(final String a0, final RESTCategoryV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPCategories(final String a0, final RESTCategoryCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPCategories(final String a0, final RESTCategoryCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPCategory(final String a0, final RESTCategoryV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPCategory(final String a0, final RESTCategoryV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPCategories(final String a0, final RESTCategoryCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPCategories(final String a0, final RESTCategoryCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPCategory(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPCategory(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPCategories(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPCategories(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTCategoryV1 getJSONCategory(final Integer a0, final String a1) {
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

      public RESTCategoryCollectionV1 getJSONCategories(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryCollectionV1.class, null));
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

      public RESTCategoryCollectionV1 getJSONCategoriesWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryCollectionV1.class, null));
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

      public RESTCategoryV1 updateJSONCategory(final String a0, final RESTCategoryV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTCategoryV1 updateJSONCategory(final String a0, final RESTCategoryV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTCategoryCollectionV1 updateJSONCategories(final String a0, final RESTCategoryCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryCollectionV1.class, null));
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

      public RESTCategoryCollectionV1 updateJSONCategories(final String a0, final RESTCategoryCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryCollectionV1.class, null));
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

      public RESTCategoryV1 createJSONCategory(final String a0, final RESTCategoryV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTCategoryV1 createJSONCategory(final String a0, final RESTCategoryV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTCategoryCollectionV1 createJSONCategories(final String a0, final RESTCategoryCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryCollectionV1.class, null));
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

      public RESTCategoryCollectionV1 createJSONCategories(final String a0, final RESTCategoryCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryCollectionV1.class, null));
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

      public RESTCategoryV1 deleteJSONCategory(final Integer a0, final String a1) {
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

      public RESTCategoryV1 deleteJSONCategory(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/category/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTCategoryCollectionV1 deleteJSONCategories(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryCollectionV1.class, null));
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

      public RESTCategoryCollectionV1 deleteJSONCategories(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/categories/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTCategoryCollectionV1.class, null));
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

      public String getJSONPImage(final Integer a0, final String a1, final String a2) {
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

      public String getJSONPImages(final String a0, final String a1) {
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

      public String getJSONPImagesWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPImage(final String a0, final RESTImageV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPImage(final String a0, final RESTImageV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPImages(final String a0, final RESTImageCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPImages(final String a0, final RESTImageCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPImage(final String a0, final RESTImageV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPImage(final String a0, final RESTImageV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPImages(final String a0, final RESTImageCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPImages(final String a0, final RESTImageCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPImage(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPImage(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPImages(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPImages(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTImageV1 getJSONImage(final Integer a0, final String a1) {
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

      public RESTImageV1 getJSONImageRevision(final Integer a0, final Integer a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/get/json/{id}/r/{rev}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString())).replace("{rev}", URL.encodePathSegment(new Integer(a1).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString()));
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

      public RESTImageCollectionV1 getJSONImages(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageCollectionV1.class, null));
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

      public RESTImageCollectionV1 getJSONImagesWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageCollectionV1.class, null));
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

      public RESTImageV1 updateJSONImage(final String a0, final RESTImageV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTImageV1 updateJSONImage(final String a0, final RESTImageV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTImageCollectionV1 updateJSONImages(final String a0, final RESTImageCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageCollectionV1.class, null));
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

      public RESTImageCollectionV1 updateJSONImages(final String a0, final RESTImageCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageCollectionV1.class, null));
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

      public RESTImageV1 createJSONImage(final String a0, final RESTImageV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTImageV1 createJSONImage(final String a0, final RESTImageV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTImageCollectionV1 createJSONImages(final String a0, final RESTImageCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageCollectionV1.class, null));
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

      public RESTImageCollectionV1 createJSONImages(final String a0, final RESTImageCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageCollectionV1.class, null));
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

      public RESTImageV1 deleteJSONImage(final Integer a0, final String a1) {
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

      public RESTImageV1 deleteJSONImage(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/image/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTImageCollectionV1 deleteJSONImages(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageCollectionV1.class, null));
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

      public RESTImageCollectionV1 deleteJSONImages(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/images/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTImageCollectionV1.class, null));
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

      public String getJSONPTopicsWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String getJSONPTopics(final String a0, final String a1) {
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

      public String getJSONPTopic(final Integer a0, final String a1, final String a2) {
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

      public String updateJSONPTopic(final String a0, final RESTTopicV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPTopic(final String a0, final RESTTopicV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPTopics(final String a0, final RESTTopicCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPTopics(final String a0, final RESTTopicCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTopic(final String a0, final RESTTopicV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTopic(final String a0, final RESTTopicV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTopics(final String a0, final RESTTopicCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPTopics(final String a0, final RESTTopicCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPTopic(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPTopic(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPTopics(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPTopics(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTTopicCollectionV1 getJSONTopics(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicCollectionV1.class, null));
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

      public RESTTopicCollectionV1 getJSONTopicsWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicCollectionV1.class, null));
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

      public RESTTopicCollectionV1 getXMLTopics(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicCollectionV1.class, null));
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

      public RESTTopicV1 getJSONTopic(final Integer a0, final String a1) {
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

      public RESTTopicV1 getJSONTopicRevision(final Integer a0, final Integer a1, final String a2) {
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

      public RESTTopicV1 getXMLTopic(final Integer a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/xml/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString())));
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

      public RESTTopicV1 getXMLTopicRevision(final Integer a0, final Integer a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/xml/{id}/r/{rev}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString())).replace("{rev}", URL.encodePathSegment(new Integer(a1).toString())));
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

      public String getXMLTopicXML(final Integer a0, final String a1) {
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

      public String getXMLTopicXMLContained(final Integer a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/xml/{id}/xmlContainedIn".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("container").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String getXMLTopicXMLNoContainer(final Integer a0, final Boolean a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/xml/{id}/xmlNoContainer".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("includeTitle").append("=").append(URL.encodeQueryString(new Boolean(a1).toString()));
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

      public String getHTMLTopicHTML(final Integer a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/html/{id}/html".replace("{id}", URL.encodePathSegment(new Integer(a0).toString())));
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

      public String getHTMLTopicRevisionHTML(final Integer a0, final Integer a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/get/html/{id}/r/{rev}/html".replace("{id}", URL.encodePathSegment(new Integer(a0).toString())).replace("{rev}", URL.encodePathSegment(new Integer(a1).toString())));
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

      public RESTTopicV1 updateJSONTopic(final String a0, final RESTTopicV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTTopicV1 updateJSONTopic(final String a0, final RESTTopicV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTTopicCollectionV1 updateJSONTopics(final String a0, final RESTTopicCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicCollectionV1.class, null));
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

      public RESTTopicCollectionV1 updateJSONTopics(final String a0, final RESTTopicCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicCollectionV1.class, null));
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

      public RESTTopicV1 createJSONTopic(final String a0, final RESTTopicV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public RESTTopicV1 createJSONTopic(final String a0, final RESTTopicV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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

      public RESTTopicCollectionV1 createJSONTopics(final String a0, final RESTTopicCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicCollectionV1.class, null));
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

      public RESTTopicCollectionV1 createJSONTopics(final String a0, final RESTTopicCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicCollectionV1.class, null));
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

      public RESTTopicV1 deleteJSONTopic(final Integer a0, final String a1) {
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

      public RESTTopicV1 deleteJSONTopic(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topic/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTTopicCollectionV1 deleteJSONTopics(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicCollectionV1.class, null));
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

      public RESTTopicCollectionV1 deleteJSONTopics(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTopicCollectionV1.class, null));
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

      public String getJSONPFilter(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String getJSONPFilters(final String a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public String getJSONPFiltersWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPFilter(final String a0, final RESTFilterV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPFilter(final String a0, final RESTFilterV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPFilters(final String a0, final RESTFilterCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPFilters(final String a0, final RESTFilterCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPFilter(final String a0, final RESTFilterV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPFilter(final String a0, final RESTFilterV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPFilters(final String a0, final RESTFilterCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPFilters(final String a0, final RESTFilterCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPFilter(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPFilter(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPFilters(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPFilters(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTFilterV1 getJSONFilter(final Integer a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterV1.class, null));
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

      public RESTFilterV1 getJSONFilterRevision(final Integer a0, final Integer a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/get/json/{id}/r/{rev}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString())).replace("{rev}", URL.encodePathSegment(new Integer(a1).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterV1.class, null));
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

      public RESTFilterCollectionV1 getJSONFilters(final String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterCollectionV1.class, null));
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

      public RESTFilterCollectionV1 getJSONFiltersWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterCollectionV1.class, null));
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

      public RESTFilterV1 updateJSONFilter(final String a0, final RESTFilterV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterV1.class, null));
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

      public RESTFilterV1 updateJSONFilter(final String a0, final RESTFilterV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterV1.class, null));
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

      public RESTFilterCollectionV1 updateJSONFilters(final String a0, final RESTFilterCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterCollectionV1.class, null));
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

      public RESTFilterCollectionV1 updateJSONFilters(final String a0, final RESTFilterCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterCollectionV1.class, null));
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

      public RESTFilterV1 createJSONFilter(final String a0, final RESTFilterV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterV1.class, null));
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

      public RESTFilterV1 createJSONFilter(final String a0, final RESTFilterV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterV1.class, null));
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

      public RESTFilterCollectionV1 createJSONFilters(final String a0, final RESTFilterCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterCollectionV1.class, null));
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

      public RESTFilterCollectionV1 createJSONFilters(final String a0, final RESTFilterCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterCollectionV1.class, null));
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

      public RESTFilterV1 deleteJSONFilter(final Integer a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterV1.class, null));
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

      public RESTFilterV1 deleteJSONFilter(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filter/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterV1.class, null));
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

      public RESTFilterCollectionV1 deleteJSONFilters(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterCollectionV1.class, null));
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

      public RESTFilterCollectionV1 deleteJSONFilters(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/filters/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTFilterCollectionV1.class, null));
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

      public String getJSONPIntegerConstant(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/get/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String getJSONPIntegerConstants(final String a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/get/jsonp/all").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a1).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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

      public String getJSONPIntegerConstantsWithQuery(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/get/jsonp/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String updateJSONPIntegerConstant(final String a0, final RESTIntegerConstantV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPIntegerConstant(final String a0, final RESTIntegerConstantV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPIntegerConstants(final String a0, final RESTIntegerConstantCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String updateJSONPIntegerConstants(final String a0, final RESTIntegerConstantCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/update/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPIntegerConstant(final String a0, final RESTIntegerConstantV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPIntegerConstant(final String a0, final RESTIntegerConstantV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPIntegerConstants(final String a0, final RESTIntegerConstantCollectionV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String createJSONPIntegerConstants(final String a0, final RESTIntegerConstantCollectionV1 a1, final RESTLogDetailsV1 a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/create/jsonp").append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("data").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1))).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
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

      public String deleteJSONPIntegerConstant(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPIntegerConstant(final Integer a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/delete/jsonp/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public String deleteJSONPIntegerConstants(final PathSegment a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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

      public String deleteJSONPIntegerConstants(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2, final String a3) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/delete/jsonp/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("callback").append("=").append(URL.encodeQueryString(new String(a3).toString())).append("&").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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

      public RESTIntegerConstantV1 getJSONIntegerConstant(final Integer a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/get/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantV1.class, null));
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

      public RESTIntegerConstantCollectionV1 getJSONIntegerConstants(final String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/get/json/all").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantCollectionV1.class, null));
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

      public RESTIntegerConstantCollectionV1 getJSONIntegerConstantsWithQuery(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/get/json/{query}".replace("{query}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantCollectionV1.class, null));
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

      public RESTIntegerConstantV1 updateJSONIntegerConstant(final String a0, final RESTIntegerConstantV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantV1.class, null));
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

      public RESTIntegerConstantV1 updateJSONIntegerConstant(final String a0, final RESTIntegerConstantV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantV1.class, null));
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

      public RESTIntegerConstantCollectionV1 updateJSONIntegerConstants(final String a0, final RESTIntegerConstantCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantCollectionV1.class, null));
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

      public RESTIntegerConstantCollectionV1 updateJSONIntegerConstants(final String a0, final RESTIntegerConstantCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/update/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantCollectionV1.class, null));
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

      public RESTIntegerConstantV1 createJSONIntegerConstant(final String a0, final RESTIntegerConstantV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantV1.class, null));
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

      public RESTIntegerConstantV1 createJSONIntegerConstant(final String a0, final RESTIntegerConstantV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantV1.class, null));
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

      public RESTIntegerConstantCollectionV1 createJSONIntegerConstants(final String a0, final RESTIntegerConstantCollectionV1 a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantCollectionV1.class, null));
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

      public RESTIntegerConstantCollectionV1 createJSONIntegerConstants(final String a0, final RESTIntegerConstantCollectionV1 a1, final RESTLogDetailsV1 a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/create/json").append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a0).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a2)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantCollectionV1.class, null));
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

      public RESTIntegerConstantV1 deleteJSONIntegerConstant(final Integer a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantV1.class, null));
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

      public RESTIntegerConstantV1 deleteJSONIntegerConstant(final Integer a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstant/delete/json/{id}".replace("{id}", URL.encodePathSegment(new Integer(a0).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantV1.class, null));
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

      public RESTIntegerConstantCollectionV1 deleteJSONIntegerConstants(final PathSegment a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a1).toString()));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantCollectionV1.class, null));
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

      public RESTIntegerConstantCollectionV1 deleteJSONIntegerConstants(final PathSegment a0, final RESTLogDetailsV1 a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/integerconstants/delete/json/{ids}".replace("{ids}", ((PathSegmentImpl) a0).getEncodedPathWithParameters())).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString())).append("&").append("logDetails").append("=").append(URL.encodeQueryString(MarshallingWrapper.toJSON(a1)));
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTIntegerConstantCollectionV1.class, null));
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

      public boolean equals(final Object a0) {
        return false;
      }

      public String toString() {
        return null;
      }

      public int hashCode() {
        return 0;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(RESTInterfaceV1.class, new ProxyProvider() {
      public Object getProxy() {
        return new RESTInterfaceV1Impl();
      }
    });
  }
}