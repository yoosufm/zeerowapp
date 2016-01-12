package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 8/24/2015.
 */
public class LikeContentRequestModel extends ModelBase {

    public String requestBody = "";
    public String user_id;
    public String api_token;
    public String language_code = "en";
    public String content_id;

    public LikeContentRequestModel(){
        reqURI = "contents/like.json";
        reqMethod = "POST";
    }

    public String getRequestBody(String apiToken, String userId, String contentId){
        requestBody = "{\n" +
                "\"api_token\": \"" + apiToken + "\",\n" +
                "\"user_id\": \"" +userId + "\",\n" +
                "\"content_id\": \""+contentId+"\",\n" +
                "\"language_code\": \""+language_code+"\"\n" +
                "}\n";
        return requestBody;
    }

    public String getRequestBody(){
        requestBody = "{\n" +
                "\"api_token\": \"" + api_token + "\",\n" +
                "\"user_id\": \"" +user_id + "\",\n" +
                "\"content_id\": \""+content_id+"\",\n" +
                "\"language_code\": \""+language_code+"\"\n" +
                "}\n";
        return requestBody;
    }
}
