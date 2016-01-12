package com.zeerow.qa.util.api.common;

public class HttpHeaderTO {

    private String path;
    private String method;
    private String timestamp;
    private String contentType;
    private String authKey;
    private String contentMD5;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getContentMD5() {
        return contentMD5;
    }

    public void setContentMD5(String contentMD5) {
        this.contentMD5 = contentMD5;
    }

    @Override
    public String toString() {
        return "HttpHeaderTO{" + "reqURI='" + path + '\'' + ", method='" + method + '\'' + ", timestamp='" + timestamp +
                '\'' + ", contentType='" + contentType + '\'' + ", authKey='" + authKey + '\'' + ", contentMD5='" +
                contentMD5 + '\'' + '}';
    }
}