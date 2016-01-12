package com.zeerow.qa.util.api.requestmodel;

import com.zeerow.qa.util.api.common.Constants;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class RegistrationRequestModel extends ModelBase {

    public String first_name;
    public String last_name;
    public String email;
    public String social_id;
    public String user_type;
    public String profile_picture;
    public String nickname;
    public String password;
    public String api_token;
    public String language_code;
    public String is_rec_marketing_email;
    public String platform;
    public String device_name;
    public String udid;
    public String user_id;
    String profilePictue = "";
    public String registration_type;

    public RegistrationRequestModel(String prefix, String userType){
        reqURI = "users/registerUser.json";
        reqMethod = "POST";
        first_name= "FN" + prefix;
        last_name= "LN" + prefix;
//        email= Constants.EMAIL_ADDRESS;
        email = prefix + "@moogilu.com";
        registration_type = "1";


        user_type= userType;
        if(user_type.equalsIgnoreCase(Constants.USER_TYPE_FACEBOOK) | user_type.equalsIgnoreCase(Constants.USER_TYPE_TWITTER))
        {
            social_id= prefix;
            profilePictue =                 "\"profile_pic_url\": \""+ Constants.DEFAULT_PROFILE_PICTURE_URL +"\",\n";
        }
        else if(user_type.equalsIgnoreCase(Constants.USER_TYPE_EMAIL)){
            social_id= "";
            profilePictue =  "\"profile_pic_data\": \""+ Constants.PROFILE_PICTURE_BASE64_DATA +"\",\n";
        }
        nickname= prefix;
//        password= prefix;
        password = "password1";
        api_token = prefix;
        language_code = System.getProperty("language.code", "en");;
        is_rec_marketing_email = "1";
        platform = "2";
        udid = UUID.randomUUID().toString();
        device_name = "iPhone6s";
    }

    public void setProfilePicture(String pictureDetails){
        if(user_type.equalsIgnoreCase(Constants.USER_TYPE_FACEBOOK) | user_type.equalsIgnoreCase(Constants.USER_TYPE_TWITTER))
        {
            profilePictue =                 "\"profile_pic_url\": \""+ pictureDetails +"\",\n";
        }
        else if(user_type.equalsIgnoreCase(Constants.USER_TYPE_EMAIL)){
            social_id= "";
            profilePictue =  "\"profile_pic_data\": \""+ pictureDetails +"\",\n";
        }
    }


    public String getRequestBody(){
        registration_type = getUserType(user_type);
        String body = "{\n" +
                "\"email\" :\""+email +"\",\n" +
                "\"social_id\": \""+social_id +"\",\n" +
                "\"registration_type\": \""+registration_type +"\",\n" +
                profilePictue +
                "\"nickname\": \""+nickname +"\",\n" +
                "\"password\" :\""+password +"\",\n" +
                "\"is_rec_marketing_email\" :\""+is_rec_marketing_email +"\",\n" +
                "\"platform\" :\""+platform +"\",\n" +
                "\"udid\" :\""+udid +"\",\n" +
                "\"device_name\" :\""+device_name +"\",\n" +
                "\"language_code\":\""+ language_code +"\"\n" +
                "}";

        return body;
    }


}
