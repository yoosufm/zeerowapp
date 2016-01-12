package com.zeerow.qa.util.api.common;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class ConfigMain {
    protected static String host = System.getProperty("ygtimes.host","http://api-test.zeerowapp.com/");
    private String baseUri = System.getProperty("ygtimes.host","http://api-test.zeerowapp.com/");
    //private String baseUri = System.getProperty("payment.gateway.host","http://expensecount.org/");

    private int port;
    private String basePath = System.getProperty("ygtimes.base.reqURI","v1/");


    public String getHost() {
        return host;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public int getPort() {
        return port;
    }

    public String getBasePath() {
        return basePath;
    }
}
