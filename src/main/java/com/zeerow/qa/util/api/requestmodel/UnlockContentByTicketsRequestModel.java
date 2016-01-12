package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class UnlockContentByTicketsRequestModel extends ModelBase {

    public String user_id;
    public String content_id;

    public String api_token;
    public String language_code;

    public UnlockContentByTicketsRequestModel(){
        language_code = "en";
        reqURI = "accounts/unlockByTickets.json";
        reqMethod = "POST";
    }

    public String getRequestBody(){
        String body = "";
            body = "{\n" +
                    "  \"api_token\" : \"" + api_token + "\", \n" +
                    "  \"content_id\" : \"" + content_id + "\", \n" +
                    "  \"language_code\" : \"" + language_code + "\", \n" +
                    "  \"user_id\" : \"" + user_id + "\"\n" +
                    " }";


        return body;
    }
}
