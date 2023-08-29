package com.xiaobai.fast.quarkus.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public  class HttpBinVo {

    @JsonProperty("args")
    private ArgsBean args;
    @JsonProperty("headers")
    private HeadersBean headers;
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("url")
    private String url;

    public ArgsBean getArgs() {
        return args;
    }

    public void setArgs(ArgsBean args) {
        this.args = args;
    }

    public HeadersBean getHeaders() {
        return headers;
    }

    public void setHeaders(HeadersBean headers) {
        this.headers = headers;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class ArgsBean {
        @JsonProperty("applicationName")
        private String applicationName;
        private String version;

        public String getApplicationName() {
            return applicationName;
        }

        public void setApplicationName(String applicationName) {
            this.applicationName = applicationName;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class HeadersBean {
        @JsonProperty("Accept")
        private String accept;
        @JsonProperty("Accept-Encoding")
        private String acceptEncoding;
        @JsonProperty("Accept-Language")
        private String acceptLanguage;
        @JsonProperty("Host")
        private String host;
        @JsonProperty("Referer")
        private String referer;
        @JsonProperty("User-Agent")
        private String userAgent;
        @JsonProperty("X-Amzn-Trace-Id")
        private String xAmznTraceId;

        public String getAccept() {
            return accept;
        }

        public void setAccept(String accept) {
            this.accept = accept;
        }

        public String getAcceptEncoding() {
            return acceptEncoding;
        }

        public void setAcceptEncoding(String acceptEncoding) {
            this.acceptEncoding = acceptEncoding;
        }

        public String getAcceptLanguage() {
            return acceptLanguage;
        }

        public void setAcceptLanguage(String acceptLanguage) {
            this.acceptLanguage = acceptLanguage;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getReferer() {
            return referer;
        }

        public void setReferer(String referer) {
            this.referer = referer;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }

        public String getXAmznTraceId() {
            return xAmznTraceId;
        }

        public void setXAmznTraceId(String xAmznTraceId) {
            this.xAmznTraceId = xAmznTraceId;
        }
    }
}
