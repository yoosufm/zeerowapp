package com.zeerow.qa.util.api.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Constants {
    public static final String STR_SLASH = "/";
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    public static final String PUT_METHOD = "PUT";
    public static final String DELETE_METHOD = "DELETE";

    public static final String USER_TYPE_FACEBOOK = "FACEBOOK";
    public static final String USER_TYPE_TWITTER = "TWITTER";
    public static final String USER_TYPE_EMAIL = "EMAIL";
    public static final String USER_TYPE_SINA_WEIBO = "SINA_WEIBO";
    public static final String USER_TYPE_TENCENT_WEIBO = "TENCENT_WEIBO";
    public static final String USER_ACTIVATION_URL = "http://api.staging.ygtimes.com/homes/activateAccount/";
    public static final String FILE_CONTENT = "iVBORw0KGgoAAAANSUhEUgAAABwAAAASCAMAAAB/2U7WAAAABlBMVEUAAAD///+l2Z/dAAAASUlEQVR4XqWQUQoAIAxC2/0vXZDrEX4IJTRkb7lobNUStXsB0jIXIAMSsQnWlsV+wULF4Avk9fLq2r8a5HSE35Q3eO2XP1A1wQkZSgETvDtKdQAAAABJRU5ErkJggg==";
    public static final String PROFILE_PICTURE_BASE64_DATA = getBase64String("same_size.txt");
    //public static final String PROFILE_PICTURE_BASE64_DATA = "iVBORw0KGgoAAAANSUhEUgAAABwAAAASCAMAAAB/2U7WAAAABlBMVEUAAAD///+l2Z/dAAAASUlEQVR4XqWQUQoAIAxC2/0vXZDrEX4IJTRkb7lobNUStXsB0jIXIAMSsQnWlsV+wULF4Avk9fLq2r8a5HSE35Q3eO2XP1A1wQkZSgETvDtKdQAAAABJRU5ErkJggg==";
//    public static final String EMAIL_ADDRESS = "coinbatestest@gmail.com";
    public static final String EMAIL_ADDRESS = "mak83826@gmail.com";
    public static final String FACEBOOK_EMAIL_ADDRESS = "coinbatesface@gmail.com";
    public static final String TWITTER_EMAIL_ADDRESS = "coinbatestwit@gmail.com";
    public static final String SINA_EMAIL_ADDRESS = "coinbatessina@gmail.com";
    public static final String EMAIL_PASSWORD = "Cointest@1";
    public static final String USER_ID = "179";
    public static final String DEFAULT_PROFILE_PICTURE_URL = "http://54.152.110.169/profileImages/default/default_image.png";


    public static  String getBase64String(String fileName)  {
        String content = null;
        String url = null;
        try {
            url = new File(".").getCanonicalPath() + "/target/classes" +"/profileimages/"+ fileName;
            File file = new File(url);
            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

}
