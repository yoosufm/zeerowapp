package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class VerifyPinRequestModel extends ModelBase {

    public String user_id;
    public String pin;



    public VerifyPinRequestModel(){
        reqURI = "users/verifyPIN.json";
        reqMethod = "POST";
    }

    public String getRequestBody(String userId, String PIN){
        user_id = userId;
        pin = PIN;
        String body = "{\n" +
                "\"user_id\" :\""+user_id +"\",\n" +
                "\"pin\":\""+ pin+"\"\n" +
                "}";
        return body;
    }
}
