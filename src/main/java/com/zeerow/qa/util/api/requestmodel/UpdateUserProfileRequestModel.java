package com.zeerow.qa.util.api.requestmodel;

import com.zeerow.qa.util.api.common.Constants;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class UpdateUserProfileRequestModel extends ModelBase {

    public String api_token;
    public String user_id;
    public String email;
    public String profile_pic_url;
    public String password;
    public String is_rec_marketing_email;
    public String user_type;
    public String profile_picture;
    public String profilePictue = "";


    public UpdateUserProfileRequestModel(String userType){
        reqURI = "users/updateProfile.json";
            reqMethod = "POST";
        user_type =userType;
        is_rec_marketing_email = "0";
    }

    public void setProfilePicture(String profilePictureDetails){
        if(user_type.equalsIgnoreCase(Constants.USER_TYPE_FACEBOOK) | user_type.equalsIgnoreCase(Constants.USER_TYPE_TWITTER))
        {
            profilePictue =                 "\"profile_pic_url\": \""+ profilePictureDetails +"\",\n";
        }
        else if(user_type.equalsIgnoreCase(Constants.USER_TYPE_EMAIL)){
            profilePictue =  "\"profile_pic_data\": \""+ profilePictureDetails +"\",\n";
        }
    }

    public String getRequestBody(){

        String body = "{\n" +
                "\"api_token\" :\""+api_token +"\",\n" +
                "\"user_id\": \""+user_id +"\",\n" +
//                "\"email\": \""+email +"\",\n" +
                profilePictue +
                "\"password\" :\""+password +"\",\n" +
                "\"is_rec_marketing_email\":\""+ is_rec_marketing_email +"\"\n" +
                "}";

        return body;
    }
}
