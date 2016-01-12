package com.zeerow.qa.util.api.requestmodel;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class RetrieveUserProfileRequestModel extends ModelBase {

    public String user_id;
    public String api_token;
    public String language_code;

    public RetrieveUserProfileRequestModel(){
        reqURI = "users/getProfile.json";
        reqMethod = "POST";
        language_code = "en";
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
