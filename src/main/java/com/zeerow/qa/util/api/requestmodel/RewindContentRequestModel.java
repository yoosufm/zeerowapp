package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class RewindContentRequestModel extends ModelBase {

    public String user_id;
    public String number_of_decission;
    public String api_token;
    public String language_code;

    public RewindContentRequestModel(){
        language_code = "en";
        reqURI = "contents/rewind.json";
        reqMethod = "POST";
    }

    public String getRequestBody(){
        String body = "";
            body = "{\n" +
                    "  \"api_token\" : \"" + api_token + "\", \n" +
                    "  \"number_of_decission\" : \"" + number_of_decission + "\", \n" +
                    "  \"language_code\" : \"" + language_code + "\", \n" +
                    "  \"user_id\" : \"" + user_id + "\"\n" +
                    " }";


        return body;
    }
}
