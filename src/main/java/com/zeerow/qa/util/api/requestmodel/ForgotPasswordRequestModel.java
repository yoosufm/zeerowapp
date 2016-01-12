package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class ForgotPasswordRequestModel extends ModelBase {

    public String email;


    public ForgotPasswordRequestModel(){
        reqURI = "users/forgotPassword.json";
        reqMethod = "POST";
        this.email = email;

    }

    public String getRequestBody(String email){
        this.email = email;

        String body = "{\n" +
                "\"email\":\""+ this.email +"\"\n" +
                "}";
        return body;
    }
}
