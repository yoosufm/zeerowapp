package com.zeerow.qa.util.api.requestmodel;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class LogoutRequestModel extends ModelBase {

    public String user_id;
    public String api_token;
    public String udid;
    public String language_code;

    public LogoutRequestModel(){
        reqURI = "users/logout.json";
        reqMethod = "POST";
        language_code = "en";
        udid = UUID.randomUUID().toString();
    }

    public String getRequestBody(){
        String body = "";
            body = "{\n" +
                    "  \"api_token\" : \"" + api_token + "\", \n" +
                    "  \"language_code\" : \"" + api_token + "\", \n" +
                    "  \"udid\" : \"" + api_token + "\", \n" +
                    "  \"user_id\" : \"" + user_id + "\"\n" +
                    " }";


        return body;
    }
}
