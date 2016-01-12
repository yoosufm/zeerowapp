package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class UseTokensRequestModel extends ModelBase {

    public String user_id;
    public String tokens;

    public String api_token;
    public String language_code;

    public UseTokensRequestModel(){
        language_code = "en";
        reqURI = "payments/useTokens.json";
        reqMethod = "POST";
    }

    public String getRequestBody(){
        String body = "";
            body = "{\n" +
                    "  \"api_token\" : \"" + api_token + "\", \n" +
                    "  \"tokens\" : \"" + tokens + "\", \n" +
                    "  \"language_code\" : \"" + language_code + "\", \n" +
                    "  \"user_id\" : \"" + user_id + "\"\n" +
                    " }";
        return body;
    }
}
