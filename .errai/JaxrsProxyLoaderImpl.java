package org.jboss.errai.enterprise.client.jaxrs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.redhat.topicindex.extras.client.local.RESTInterfaceV1;
import com.redhat.topicindex.rest.collections.RESTBlobConstantCollectionV1;
import com.redhat.topicindex.rest.collections.RESTCategoryCollectionV1;
import com.redhat.topicindex.rest.collections.RESTImageCollectionV1;
import com.redhat.topicindex.rest.collections.RESTProjectCollectionV1;
import com.redhat.topicindex.rest.collections.RESTPropertyTagCollectionV1;
import com.redhat.topicindex.rest.collections.RESTRoleCollectionV1;
import com.redhat.topicindex.rest.collections.RESTStringConstantCollectionV1;
import com.redhat.topicindex.rest.collections.RESTTagCollectionV1;
import com.redhat.topicindex.rest.collections.RESTTopicCollectionV1;
import com.redhat.topicindex.rest.collections.RESTTranslatedTopicCollectionV1;
import com.redhat.topicindex.rest.collections.RESTTranslatedTopicStringCollectionV1;
import com.redhat.topicindex.rest.collections.RESTUserCollectionV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTBlobConstantV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTCategoryV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTImageV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTProjectV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTPropertyTagV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTRoleV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTStringConstantV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTagV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTopicV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicStringV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTTranslatedTopicV1;
import com.redhat.topicindex.rest.entities.interfaces.RESTUserV1;
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

      public void setRerenderTopic(final Boolean a0) {
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

      public RESTTranslatedTopicStringV1 getJSONTranslatedTopicString(final Integer a0, final String a1) {
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

      public String getJSONPTranslatedTopicString(final Integer a0, final String a1, final String a2) {
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

      public RESTTranslatedTopicStringCollectionV1 getJSONTranslatedTopicStrings(final String a0) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicStringCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String getJSONPTranslatedTopicStrings(final String a0, final String a1) {
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

      public RESTTranslatedTopicStringV1 updateJSONTranslatedTopicString(final String a0, final RESTTranslatedTopicStringV1 a1) {
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

      public String updateJSONPTranslatedTopicString(final String a0, final RESTTranslatedTopicStringV1 a1, final String a2) {
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

      public RESTTranslatedTopicStringCollectionV1 updateJSONTranslatedTopicStrings(final String a0, final RESTTranslatedTopicStringCollectionV1 a1) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicStringCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String updateJSONPTranslatedTopicStrings(final String a0, final RESTTranslatedTopicStringCollectionV1 a1, final String a2) {
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

      public RESTTranslatedTopicStringV1 deleteJSONTranslatedTopicString(final Integer a0, final String a1) {
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

      public String deleteJSONPTranslatedTopicString(final Integer a0, final String a1, final String a2) {
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

      public RESTTranslatedTopicStringCollectionV1 deleteJSONTranslatedTopicStrings(final PathSegment a0, final String a1) {
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
                    RESTInterfaceV1Impl.this.remoteCallback.callback(MarshallingWrapper.fromJSON(response.getText(), RESTTranslatedTopicStringCollectionV1.class, null));
                  }
                }
              } else {
                ResponseException throwable = new ResponseException(response.getStatusText(), response);
                RESTInterfaceV1Impl.this.handleError(throwable, response);
              }
            }
          });
        } catch (RequestException throwable) {
          RESTInterfaceV1Impl.this.handleError(throwable, null);
        }
        return null;
      }

      public String deleteJSONPTranslatedTopicStrings(final PathSegment a0, final String a1, final String a2) {
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

      public String updateJSONPUser(final String a0, final RESTUserV1 a1, final String a2) {
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

      public String updateJSONPUsers(final String a0, final RESTUserCollectionV1 a1, final String a2) {
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

      public String createJSONPUser(final String a0, final RESTUserV1 a1, final String a2) {
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

      public String createJSONPUsers(final String a0, final RESTUserCollectionV1 a1, final String a2) {
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

      public String deleteJSONPUser(final Integer a0, final String a1, final String a2) {
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

      public String deleteJSONPUsers(final PathSegment a0, final String a1, final String a2) {
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

      public RESTUserV1 updateJSONUser(final String a0, final RESTUserV1 a1) {
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

      public RESTUserCollectionV1 deleteJSONUsers(final PathSegment a0, final String a1) {
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

      public String updateJSONPStringConstant(final String a0, final RESTStringConstantV1 a1, final String a2) {
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

      public String updateJSONPStringConstants(final String a0, final RESTStringConstantCollectionV1 a1, final String a2) {
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

      public String createJSONPStringConstant(final String a0, final RESTStringConstantV1 a1, final String a2) {
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

      public String createJSONPStringConstants(final String a0, final RESTStringConstantCollectionV1 a1, final String a2) {
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

      public String deleteJSONPStringConstant(final Integer a0, final String a1, final String a2) {
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

      public String deleteJSONPStringConstants(final PathSegment a0, final String a1, final String a2) {
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

      public RESTStringConstantV1 updateJSONStringConstant(final String a0, final RESTStringConstantV1 a1) {
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

      public RESTStringConstantCollectionV1 deleteJSONStringConstants(final PathSegment a0, final String a1) {
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

      public String updateJSONPTranslatedTopic(final String a0, final RESTTranslatedTopicV1 a1, final String a2) {
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

      public String updateJSONPTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1, final String a2) {
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

      public String createJSONPTranslatedTopic(final String a0, final RESTTranslatedTopicV1 a1, final String a2) {
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

      public String createJSONPTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1, final String a2) {
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

      public String deleteJSONPTranslatedTopic(final Integer a0, final String a1, final String a2) {
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

      public String deleteJSONPTranslatedTopics(final PathSegment a0, final String a1, final String a2) {
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

      public RESTTranslatedTopicCollectionV1 updateJSONTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1) {
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

      public RESTTranslatedTopicCollectionV1 createJSONTranslatedTopics(final String a0, final RESTTranslatedTopicCollectionV1 a1) {
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

      public RESTTranslatedTopicCollectionV1 deleteJSONTranslatedTopics(final PathSegment a0, final String a1) {
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

      public String updateJSONPRole(final String a0, final RESTRoleV1 a1, final String a2) {
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

      public String updateJSONPRoles(final String a0, final RESTRoleCollectionV1 a1, final String a2) {
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

      public String createJSONPRole(final String a0, final RESTRoleV1 a1, final String a2) {
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

      public String createJSONPRoles(final String a0, final RESTRoleCollectionV1 a1, final String a2) {
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

      public String deleteJSONPRole(final Integer a0, final String a1, final String a2) {
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

      public String deleteJSONPRoles(final PathSegment a0, final String a1, final String a2) {
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

      public RESTRoleV1 updateJSONRole(final String a0, final RESTRoleV1 a1) {
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

      public RESTRoleCollectionV1 deleteJSONRoles(final PathSegment a0, final String a1) {
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

      public String updateJSONPPropertyTag(final String a0, final RESTPropertyTagV1 a1, final String a2) {
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

      public String updateJSONPPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1, final String a2) {
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

      public String createJSONPPropertyTag(final String a0, final RESTPropertyTagV1 a1, final String a2) {
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

      public String createJSONPPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1, final String a2) {
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

      public String deleteJSONPPropertyTag(final Integer a0, final String a1, final String a2) {
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

      public String deleteJSONPPropertyTags(final PathSegment a0, final String a1, final String a2) {
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

      public RESTPropertyTagV1 updateJSONPropertyTag(final String a0, final RESTPropertyTagV1 a1) {
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

      public RESTPropertyTagCollectionV1 updateJSONPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1) {
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

      public RESTPropertyTagCollectionV1 createJSONPropertyTags(final String a0, final RESTPropertyTagCollectionV1 a1) {
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

      public RESTPropertyTagCollectionV1 deleteJSONPropertyTags(final PathSegment a0, final String a1) {
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

      public String updateJSONPBlobConstants(final String a0, final RESTBlobConstantCollectionV1 a1, final String a2) {
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

      public String createJSONPBlobConstant(final String a0, final RESTBlobConstantV1 a1, final String a2) {
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

      public String createJSONPBlobConstants(final String a0, final RESTBlobConstantCollectionV1 a1, final String a2) {
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

      public String deleteJSONPBlobConstant(final Integer a0, final String a1, final String a2) {
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

      public String deleteJSONPBlobConstants(final PathSegment a0, final String a1, final String a2) {
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

      public RESTBlobConstantV1 updateJSONBlobConstant(final String a0, final RESTBlobConstantV1 a1) {
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

      public RESTBlobConstantCollectionV1 deleteJSONBlobConstants(final PathSegment a0, final String a1) {
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

      public String updateJSONPProject(final String a0, final RESTProjectV1 a1, final String a2) {
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

      public String updateJSONPProjects(final String a0, final RESTProjectCollectionV1 a1, final String a2) {
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

      public String createJSONPProject(final String a0, final RESTProjectV1 a1, final String a2) {
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

      public String createJSONPProjects(final String a0, final RESTProjectCollectionV1 a1, final String a2) {
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

      public String deleteJSONPProject(final Integer a0, final String a1, final String a2) {
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

      public String deleteJSONPProjects(final PathSegment a0, final String a1, final String a2) {
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

      public RESTProjectV1 updateJSONProject(final String a0, final RESTProjectV1 a1) {
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

      public RESTProjectCollectionV1 updateJSONProjects(final String a0, final RESTProjectCollectionV1 a1) {
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

      public RESTProjectCollectionV1 createJSONProjects(final String a0, final RESTProjectCollectionV1 a1) {
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

      public RESTProjectCollectionV1 deleteJSONProjects(final PathSegment a0, final String a1) {
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

      public String updateJSONPTag(final String a0, final RESTTagV1 a1, final String a2) {
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

      public String updateJSONPTags(final String a0, final RESTTagCollectionV1 a1, final String a2) {
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

      public String createJSONPTag(final String a0, final RESTTagV1 a1, final String a2) {
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

      public String createJSONPTags(final String a0, final RESTTagCollectionV1 a1, final String a2) {
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

      public String deleteJSONPTag(final Integer a0, final String a1, final String a2) {
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

      public String deleteJSONPTags(final PathSegment a0, final String a1, final String a2) {
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

      public RESTTagV1 updateJSONTag(final String a0, final RESTTagV1 a1) {
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

      public RESTTagCollectionV1 updateJSONTags(final String a0, final RESTTagCollectionV1 a1) {
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

      public RESTTagCollectionV1 createJSONTags(final String a0, final RESTTagCollectionV1 a1) {
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

      public RESTTagCollectionV1 deleteJSONTags(final PathSegment a0, final String a1) {
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

      public String updateJSONPCategory(final String a0, final RESTCategoryV1 a1, final String a2) {
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

      public String updateJSONPCategories(final String a0, final RESTCategoryCollectionV1 a1, final String a2) {
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

      public String createJSONPCategory(final String a0, final RESTCategoryV1 a1, final String a2) {
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

      public String createJSONPCategories(final String a0, final RESTCategoryCollectionV1 a1, final String a2) {
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

      public String deleteJSONPCategory(final Integer a0, final String a1, final String a2) {
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

      public String deleteJSONPCategories(final PathSegment a0, final String a1, final String a2) {
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

      public RESTCategoryV1 updateJSONCategory(final String a0, final RESTCategoryV1 a1) {
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

      public RESTCategoryCollectionV1 updateJSONCategories(final String a0, final RESTCategoryCollectionV1 a1) {
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

      public RESTCategoryCollectionV1 createJSONCategories(final String a0, final RESTCategoryCollectionV1 a1) {
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

      public RESTCategoryCollectionV1 deleteJSONCategories(final PathSegment a0, final String a1) {
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

      public String updateJSONPImage(final String a0, final RESTImageV1 a1, final String a2) {
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

      public String updateJSONPImages(final String a0, final RESTImageCollectionV1 a1, final String a2) {
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

      public String createJSONPImage(final String a0, final RESTImageV1 a1, final String a2) {
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

      public String createJSONPImages(final String a0, final RESTImageCollectionV1 a1, final String a2) {
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

      public String deleteJSONPImage(final Integer a0, final String a1, final String a2) {
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

      public String deleteJSONPImages(final PathSegment a0, final String a1, final String a2) {
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

      public RESTImageV1 updateJSONImage(final String a0, final RESTImageV1 a1) {
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

      public RESTImageCollectionV1 updateJSONImages(final String a0, final RESTImageCollectionV1 a1) {
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

      public RESTImageCollectionV1 createJSONImages(final String a0, final RESTImageCollectionV1 a1) {
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

      public RESTImageCollectionV1 deleteJSONImages(final PathSegment a0, final String a1) {
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

      public String updateJSONPTopics(final String a0, final RESTTopicCollectionV1 a1, final String a2) {
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

      public String createJSONPTopic(final String a0, final RESTTopicV1 a1, final String a2) {
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

      public String createJSONPTopics(final String a0, final RESTTopicCollectionV1 a1, final String a2) {
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

      public String deleteJSONPTopic(final Integer a0, final String a1, final String a2) {
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

      public String deleteJSONPTopics(final PathSegment a0, final String a1, final String a2) {
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

      public RESTTopicCollectionV1 getJSONTopicsWithQuery(final Integer a0, final String a1, final String a2) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("1/topics/get/json/query;propertyTag{propertyTagID}={propertyTagValue}".replace("{propertyTagID}", URL.encodePathSegment(new Integer(a0).toString())).replace("{propertyTagValue}", URL.encodePathSegment(new String(a1).toString()))).append("?").append("expand").append("=").append(URL.encodeQueryString(new String(a2).toString()));
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

      public RESTTopicV1 getXMLTopic(final Integer a0, final String a1) {
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

      public RESTTopicV1 getXMLTopicRevision(final Integer a0, final Integer a1, final String a2) {
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

      public String getXMLTopicXMLContained(final Integer a0, final String a1, final String a2) {
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

      public String getXMLTopicXMLNoContainer(final Integer a0, final String a1, final Boolean a2) {
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

      public String getHTMLTopicHTML(final Integer a0, final String a1) {
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

      public String getHTMLTopicRevisionHTML(final Integer a0, final Integer a1, final String a2) {
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

      public RESTTopicV1 updateJSONTopic(final String a0, final RESTTopicV1 a1) {
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

      public RESTTopicCollectionV1 updateJSONTopics(final String a0, final RESTTopicCollectionV1 a1) {
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

      public RESTTopicCollectionV1 createJSONTopics(final String a0, final RESTTopicCollectionV1 a1) {
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

      public RESTTopicCollectionV1 deleteJSONTopics(final PathSegment a0, final String a1) {
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
    }
    RemoteServiceProxyFactory.addRemoteProxy(RESTInterfaceV1.class, new ProxyProvider() {
      public Object getProxy() {
        return new RESTInterfaceV1Impl();
      }
    });
  }
}