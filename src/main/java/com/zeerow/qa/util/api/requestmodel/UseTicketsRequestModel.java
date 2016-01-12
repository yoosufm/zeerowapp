package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class UseTicketsRequestModel extends ModelBase {

    public String user_id;
    public String tickets;

    public String api_token;
    public String language_code;

    public UseTicketsRequestModel(){
        language_code = "en";
        reqURI = "payments/useTickets.json";
        reqMethod = "POST";
    }

    public String getRequestBody(){
        String body = "";
            body = "{\n" +
                    "  \"api_token\" : \"" + api_token + "\", \n" +
                    "  \"tickets\" : \"" + tickets + "\", \n" +
                    "  \"language_code\" : \"" + language_code + "\", \n" +
                    "  \"user_id\" : \"" + user_id + "\"\n" +
                    " }";
        return body;
    }
}
