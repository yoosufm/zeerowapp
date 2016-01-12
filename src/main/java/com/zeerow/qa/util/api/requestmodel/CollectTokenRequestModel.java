package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class CollectTokenRequestModel extends ModelBase {

    public String user_id;
    public String api_token;
    public String language_code;

    public CollectTokenRequestModel(){
        language_code = "en";
        reqURI = "accounts/collectTokens.json";
        reqMethod = "POST";
    }

    public String getRequestBody(){
        String body = "";
            body = "{\n" +
                    "  \"api_token\" : \"" + api_token + "\", \n" +
                    "  \"language_code\" : \"" + language_code + "\", \n" +
                    "  \"user_id\" : \"" + user_id + "\"\n" +
                    " }";


        return body;
    }
}
