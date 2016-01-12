package com.zeerow.qa.util.api.common;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.Writer;

/**
 * Created by yoosuf on 6/19/2015.
 */
public class ResponseUtil {

    static String error_message = "";
    static String error_code = "";

    public static void verifyRegistrationSuccessResponse(JSONObject responseBody) throws JSONException {
        Writer writer = null;
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );
        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("api_token").length() > 0, true );
        Assert.assertEquals(responseBody.getString("user_id").length() > 0, true );
//        Assert.assertEquals(responseBody.getString("profile_pic_url"), Constants.DEFAULT_PROFILE_PICTURE_URL);
    }


    public static void verifyGetProfileSuccessResponse(JSONObject responseBody, String userId, String apiToken) throws JSONException {
        Writer writer = null;
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );
        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("api_token"), apiToken );
        Assert.assertEquals(responseBody.getString("user_id"), userId );
        Assert.assertEquals(responseBody.has("nickname"), true );
        Assert.assertEquals(responseBody.has("email"), true );
        Assert.assertEquals(responseBody.has("balance_stars"), true );
        Assert.assertEquals(responseBody.has("balance_tickets"), true );
        Assert.assertEquals(responseBody.has("balance_tokens"), true );
        Assert.assertEquals(responseBody.has("is_rec_marketing_email"), true );
        Assert.assertEquals(responseBody.has("profile_pic_url"), true );
    }

    public static void verifyGetUserBalanceSuccessResponse(JSONObject responseBody, String userId, int balance_stars, int balance_tickets, int balance_tokens) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );
        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("user_id"), userId );
        Assert.assertEquals(responseBody.getString("balance_stars"), String.valueOf(balance_stars ) );
        Assert.assertEquals(responseBody.getString("balance_tickets"), String.valueOf(balance_tickets ) );
        Assert.assertEquals(responseBody.getString("balance_tokens"), String.valueOf(balance_tokens ) );
    }

    public static void verifyCollectTokenSuccessResponse(JSONObject responseBody, String userId, int balanceStars, int balanceTickets, int balanceTokens ) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );
        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("user_id"), userId );
        Assert.assertEquals(responseBody.getString("current_star_balance"), String.valueOf(balanceStars ));
        Assert.assertEquals(responseBody.getString("current_ticket_balance"),  String.valueOf(balanceTickets ));
        Assert.assertEquals(responseBody.getString("current_token_balance"),  String.valueOf(balanceTokens));
    }

    public static void verifyCollectContentSuccessResponse(JSONObject responseBody, String userId, int free_media_saving_left, int current_token_balance ) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );
        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("user_id"), userId );
        Assert.assertEquals(responseBody.getString("free_media_saving_left"), String.valueOf(free_media_saving_left ));
        Assert.assertEquals(responseBody.getString("current_token_balance"),  String.valueOf(current_token_balance ));
    }

    public static void verifySkipContentSuccessResponse(JSONObject responseBody, String userId, int free_media_saving_left ) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );
        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("user_id"), userId );
//        Assert.assertEquals(responseBody.getString("free_media_saving_left"), String.valueOf(free_media_saving_left ));
    }

    public static void verifyProductListSuccessResponse(JSONObject responseBody, String userId, String type ) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );
        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("user_id"), userId );
        Assert.assertEquals(responseBody.has("product_list"), true);
            for(int index = 0; index < responseBody.getJSONArray("product_list").length(); index++) {
                Assert.assertEquals(responseBody.getJSONArray("product_list").getJSONObject(index).getString("type"), type);
            }

    }

    public static void verifyRewindContentSuccessResponse(JSONObject responseBody, String userId, int free_media_saving_left ) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );
        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("user_id"), userId );
    }


    public static void verifyLoginSuccessResponse(JSONObject responseBody) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );

        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("api_token").length() > 0, true );
        Assert.assertEquals(responseBody.getString("user_id").length() > 0, true );

    }

    public static void verifyLogoutSuccessResponse(JSONObject responseBody) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );
//        Assert.assertEquals(responseBody.getString("message"), "Logout Successful!" );
    }

    public static void verifyForgotPasswordSuccessResponse(JSONObject responseBody, String userId) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );

        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("user_id"), userId );
    }

    public static void verifyFileUploadSuccessResponse(JSONObject responseBody, String message) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );

//        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("message"), message );
    }

    public static void verifyLoginFailResponse(JSONObject responseBody, String message, String errorCode) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "-1" );
        Assert.assertEquals(responseBody.getString("error_code"), errorCode );
        Assert.assertEquals(responseBody.getString("error_code_desc"), message );
    }

    public static void verifyLogoutFailResponse(JSONObject responseBody, String message, String errorCode) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "-1" );
        Assert.assertEquals(responseBody.getString("error_code"), errorCode );
        Assert.assertEquals(responseBody.getString("error_code_desc"), message );
    }

    public static void verifyRegistrationFailResponse(JSONObject responseBody, String message, String errorCode) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "-1" );
        Assert.assertEquals(responseBody.getString("error_code"), errorCode );
        Assert.assertEquals(responseBody.getString("error_code_desc"), message );
    }

    public static void verifyFailResponse(JSONObject responseBody, String message, String errorCode) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "-1" );
        Assert.assertEquals(responseBody.getString("error_code"), errorCode );
        Assert.assertEquals(responseBody.getString("error_code_desc"), message );
    }

    public static void verifyForgotPasswordFailResponse(JSONObject responseBody, String message, String errorCode) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "-1" );
        Assert.assertEquals(responseBody.getString("error_code"), errorCode );
        Assert.assertEquals(responseBody.getString("error_code_desc"), message );
    }

    public static void verifyGetFreeHomeFeedResponse(JSONObject responseBody) throws JSONException {
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "+000" );
        responseBody = responseBody.getJSONObject("data");
        Assert.assertEquals(responseBody.getString("next_page"), "2" );
    }

    public static void verifyFailResponse(JSONObject responseBody, int code, String language) throws JSONException{
        if(language.equalsIgnoreCase("en")){
            error_code = ApplicationErrorsMessage.fromInt(code).errorCode;
            error_message = ApplicationErrorsMessage.fromInt(code).errorMessageEn;
        } else  if(language.equalsIgnoreCase("ko")){
            error_code = ApplicationErrorsMessage.fromInt(code).errorCode;
            error_message = ApplicationErrorsMessage.fromInt(code).errorMessageKo;
        }else  if(language.equalsIgnoreCase("zh")){
            error_code = ApplicationErrorsMessage.fromInt(code).errorCode;
            error_message = ApplicationErrorsMessage.fromInt(code).errorMessageCh;
        }else  if(language.equalsIgnoreCase("ja")){
            error_code = ApplicationErrorsMessage.fromInt(code).errorCode;
            error_message = ApplicationErrorsMessage.fromInt(code).errorMessageJp;
        }
        responseBody = responseBody.getJSONObject("response");
        Assert.assertEquals(responseBody.getString("status"), "-1" );
        Assert.assertEquals(responseBody.getString("error_code"), error_code );
        Assert.assertEquals(responseBody.getString("error_code_desc"), error_message );

    }


}
