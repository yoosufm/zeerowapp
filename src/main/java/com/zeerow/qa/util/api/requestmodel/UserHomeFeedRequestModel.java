package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 8/24/2015.
 */
public class UserHomeFeedRequestModel extends ModelBase {

    public String requestBody = "";
    public String user_id;
    public String api_token;
    public String time = "2015-08-21 09:37:20";
    public String language_code = "en";
    public String page_no;

    public UserHomeFeedRequestModel(){
        reqURI = "contents/getUserFeed.json";
        reqMethod = "POST";
    }

    public String getRequestBody(String apiToken, String userId, String pageNo){
        requestBody = "{\n" +
                "\"api_token\": \"" + apiToken + "\",\n" +
                "\"user_id\": \"" +userId + "\",\n" +
                "\"page\": \"" + pageNo + "\",\n" +
                "\"time\": \""+time+"\",\n" +
                "\"language_code\": \""+language_code+"\"\n" +
                "}\n";
        return requestBody;
    }

    public String getRequestBody(){
        requestBody = "{\n" +
                "\"api_token\": \"" + api_token + "\",\n" +
                "\"user_id\": \"" +user_id + "\",\n" +
                "\"page\": \"" + page_no + "\",\n" +
                "\"time\": \""+time+"\",\n" +
                "\"language_code\": \""+language_code+"\"\n" +
                "}\n";
        return requestBody;
    }
}
