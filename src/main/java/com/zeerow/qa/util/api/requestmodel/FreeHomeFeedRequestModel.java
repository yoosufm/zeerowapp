package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 8/24/2015.
 */
public class FreeHomeFeedRequestModel extends ModelBase {

    public String requestBody = "";
    public String time = "2015-08-21 09:37:20";
    public String language_code = "en";
    public String page_no;

    public FreeHomeFeedRequestModel(){
        reqURI = "contents/getFeed.json";
            reqMethod = "POST";
    }
    public String getRequestBody(String pageNo){
        requestBody = "{\n" +
                "\"page\": \"" + pageNo + "\",\n" +
                "\"time\": \""+time+"\",\n" +
                "\"language_code\": \""+language_code+"\"\n" +
                "}\n";
        return requestBody;
    }

    public String getRequestBody(){
        requestBody = "{\n" +
                "\"page\": \"" + page_no + "\",\n" +
                "\"time\": \""+time+"\",\n" +
                "\"language_code\": \""+language_code+"\"\n" +
                "}\n";
        return requestBody;
    }
}
