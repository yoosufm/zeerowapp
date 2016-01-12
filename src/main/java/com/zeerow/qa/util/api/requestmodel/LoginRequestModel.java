package com.zeerow.qa.util.api.requestmodel;

import com.zeerow.qa.util.api.common.Constants;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class LoginRequestModel extends ModelBase {

    public String nickname;
    public String password;
    public String language_code;
    public String user_type;
    public String auth_token;
    public String platform;
    public String registration_type;
    public String social_id;
    public String udid;
    public String device_name = "iPhone-6";


    public LoginRequestModel(){
        reqURI = "users/loginAuth.json";
        reqMethod = "POST";
        nickname = "yogesh";
        password= "welcome1";
        language_code = "en";
        user_type = Constants.USER_TYPE_EMAIL;
        udid = UUID.randomUUID().toString();
        platform = "2";

    }

    public String getRequestBody(){
        String body = "";
        registration_type = getUserType(user_type);
        if(user_type.contains(Constants.USER_TYPE_EMAIL)) {
            if(user_type.contains("BLANK")) user_type = "";
            body = "{\n" +
                    "  \"nickname\" : \"" + nickname + "\", \n" +
                    "  \"registration_type\" : \"" + registration_type + "\", \n" +
                    "  \"password\" : \"" + password + "\", \n" +
                    "  \"platform\" : \"" + platform + "\", \n" +
                    "  \"udid\" : \"" + udid + "\", \n" +
                    "  \"device_name\" : \"" + device_name + "\", \n" +
                    "  \"language_code\" : \"" + language_code + "\"\n" +
                    " }";
        } else if(user_type.contains(Constants.USER_TYPE_FACEBOOK)){
            if(user_type.contains("BLANK")) user_type = "";
            body = "{\n" +
                    "  \"nickname\" : \"" + nickname + "\", \n" +
                    "  \"social_id\" : \"" + social_id + "\", \n" +
                    "  \"password\" : \"" + password + "\", \n" +
                    "  \"language_code\" : \""+language_code+"\",\n" +
                    "  \"platform\" : \"" + platform + "\", \n" +
                    "  \"udid\" : \"" + udid + "\", \n" +
                    "  \"device_name\" : \"" + device_name + "\", \n" +
                    "  \"registration_type\" : \"" + registration_type + "\"\n" +
                    " }";
        }  else if(user_type.contains(Constants.USER_TYPE_TWITTER)){
            if(user_type.contains("BLANK")) user_type = "";
            body = "{\n" +
                    "  \"nickname\" : \"" + nickname + "\", \n" +
                    "  \"social_id\" : \"" + social_id + "\", \n" +
//                    "  \"api_token\" : \"" + auth_token + "\", \n" +
                    "  \"language_code\" : \""+language_code+"\",\n" +
                    "  \"platform\" : \"" + platform + "\", \n" +
                    "  \"udid\" : \"" + udid + "\", \n" +
                    "  \"device_name\" : \"" + device_name + "\", \n" +
                    "  \"registration_type\" : \"" + registration_type + "\"\n" +
                    " }";
    }

        return body;
    }
}
