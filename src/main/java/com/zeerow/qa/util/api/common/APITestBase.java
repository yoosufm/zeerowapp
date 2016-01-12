package com.zeerow.qa.util.api.common;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.zeerow.qa.util.api.requestmodel.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;

//import org.testng.Assert;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class APITestBase extends TestListenerAdapter {

    protected String reqMethod = null;
    protected String authType = null;
    protected String username = null;
    protected String password = null;
    protected String reqURI = null;
    protected JSONObject reqHeader = null;
    protected String reqBody = null;
    protected static String statusCode = null;
    protected String fullRespBody = null;
    protected String host = "";
    protected String baseUri = "";
    protected int port;
    protected String basePath = "";
    public static Response response = null;
    TestLinkHelper testLinkHelper ;
    protected static String testCaseID;
    protected static JSONObject responseBody = null;


    protected static LoginRequestModel loginRequestModel;
    protected static RegistrationRequestModel registrationRequestModel;
    protected static LogoutRequestModel logoutRequestModel;
    protected static UpdateUserProfileRequestModel updateUserProfileRequestModel;
    protected static ForgotPasswordRequestModel forgotPasswordRequestModel;
    protected static VerifyPinRequestModel verifyPinRequestModel;
    protected static ActivateUserRequestModel activateUserRequestModel;
    protected static ContentUploadRequestModel contentUploadRequestModel;
    protected static FreeHomeFeedRequestModel freeHomeFeedRequestModel;
    protected static UserHomeFeedRequestModel userHomeFeedRequestModel;
    protected static LikeContentRequestModel likeContentRequestModel;
    protected static UnlikeContentRequestModel unlikeContent;
    protected static AddCommentRequestModel addCommentRequestModel;
    protected static RetrieveUserProfileRequestModel retrieveUserProfileRequestModel;
    protected static RetrieveUserBalanceRequestModel retrieveUserBalanceRequestModel;
    protected static CollectTokenRequestModel collectTokenRequestModel;
    protected static CollectContentRequestModel collectContentRequestModel;
    protected static SkipContentRequestModel skipContentRequestModel;
    protected static RewindContentRequestModel rewindContentRequestModel;
    protected static UnlockContentByTokenRequestModel unlockContentByTokenRequestModel;
    protected static UnlockContentByTicketsRequestModel unlockContentByTicketsRequestModel;
    protected static ConvertTicketsRequestModel convertTicketsRequestModel;
    protected static UseTicketsRequestModel useTicketsRequestModel;
    protected static UseTokensRequestModel useTokensRequestModel;
    protected static ProductListRequestModel productListRequestModel;
    protected static SubscribedTorProductRequestModel subscribedTorProductRequestModel;
    protected static ResetUserRequestModel resetUserRequestModel;

    protected static String uniqueId ;
    protected static String userType ;
    protected static String userId = "";
    protected static String apiToken = "";
    protected boolean isActivateUser = true;
    protected static String language_code = System.getProperty("language.code", "en");

    public void setupTest(ModelBase modelBase) {
        ConfigMain cm = new ConfigMain();
        host = cm.getHost();
        baseUri = cm.getBaseUri();
        port = cm.getPort();
        basePath = cm.getBasePath();
        authType = "Basic";

        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;
        reqURI = modelBase.reqURI;
        reqMethod = modelBase.reqMethod;

        setRequestHeaderAsJason();
    }

    public void sendRequest(ModelBase modelBase) {
        setupTest(modelBase);
        System.out.println("************************* Starting send request to : " + baseUri + basePath + reqURI);
        System.out.println("reqHeader                 : " + reqHeader);
        System.out.println("reqMethod                 : " + reqMethod);
        System.out.println("Body                 : " + reqBody);

        boolean success = true;
        RequestSpecification requestSpec = (new RequestSpecUtil()).getRequestSpec(reqMethod, authType, username, password, reqHeader, reqURI, fullRespBody, reqBody);
        RequestSpecification requestSpecification = given().spec(requestSpec);
        response = (new ValidateResponse()).validateAPICallResponse(reqMethod, reqURI, requestSpecification/*, responseSpec*/);
        response.then().log().all();
//        System.out.println(" Status Code : " + response.getStatusCode());
        System.out.println("************************* Ending request *************************");
        cleanUp();
        try {
            responseBody = new JSONObject(response.asString());
//            setResponseData(modelBase);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(isActivateUser & modelBase == registrationRequestModel & getStatusCode().equalsIgnoreCase("+000")){
            userId =  getUserID();
            apiToken = getAPIToken();
            activateUserRequestModel  = new ActivateUserRequestModel(userId, apiToken);
            activateUserRequestModel.activateAccount(getUserActivationUrl());
        }
    }

    public void sendRequest() {
        System.out.println("************************* Starting send request to : " + baseUri + basePath + reqURI);
        System.out.println("reqHeader                 : " + reqHeader);
        System.out.println("reqMethod                 : " + reqMethod);
        System.out.println("Body                 : " + reqBody);

        boolean success = true;
        RequestSpecification requestSpec = (new RequestSpecUtil()).getRequestSpec(reqMethod, authType, username, password, reqHeader, reqURI, fullRespBody, reqBody);
        RequestSpecification requestSpecification = given().spec(requestSpec);
        response = (new ValidateResponse()).validateAPICallResponse(reqMethod, reqURI, requestSpecification/*, responseSpec*/);
        response.then().log().all();
        System.out.println(" Status Code : " + response.getStatusCode());
        System.out.println("************************* Ending request *************************");
        cleanUp();
        try {
            responseBody = new JSONObject(response.asString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void cleanUp() {
        reqBody = null;
    }


    @Override
    public void onTestSuccess(ITestResult itr){
//        testLinkHelper = new TestLinkHelper();
//        testLinkHelper.updatePassedTestCase(testCaseID, ExecutionStatus.PASSED, "");
    }

    @Override
    public void onTestFailure(ITestResult itr){
//        testLinkHelper = new TestLinkHelper();
//        testLinkHelper.updatePassedTestCase(testCaseID, ExecutionStatus.FAILED,  itr.getThrowable().getMessage());
    }





    public void setRequestHeaderAsJason() {

        try {
            reqHeader = new JSONObject( "{\"Content-type\":\"application/json\",\"OTHER HEADERS\":\"\",\"Accept\":\"application/json\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserID() {
        try {
            userId = responseBody.getJSONObject("response").getJSONObject("data").getString("user_id");
        }catch (JSONException e){

        }finally {
            return userId;
        }
    }

    public static String getAPIToken() {
        try {
            apiToken = responseBody.getJSONObject("response").getJSONObject("data").getString("api_token");
        }catch (JSONException e){
//            e.printStackTrace();
        }finally {
            return apiToken;
        }
    }

    public static String getContentID() {
        String contentID = "";
        try {
            contentID = responseBody.getJSONObject("response").getJSONObject("data").getJSONArray("content_list").getJSONObject(0).getString("content_id");
        }catch (JSONException e){
           e.printStackTrace();
        }finally {
            return contentID;
        }
    }

    public static String [] getContentIDs() {
        String [] contentIDs = null;
        try {
            contentIDs = new String[responseBody.getJSONObject("response").getJSONObject("data").getJSONArray("content_list").length()];
            for(int index = 0; index < contentIDs.length; index++) {
                contentIDs[index] = responseBody.getJSONObject("response").getJSONObject("data").getJSONArray("content_list").getJSONObject(index).getString("content_id");
            }
        }catch (JSONException e){
            e.printStackTrace();
        }finally {
            return contentIDs;
        }
    }

    public static String [] getProductIDs() {
        String [] productIds = null;
        try {
            productIds = new String[responseBody.getJSONObject("response").getJSONObject("data").getJSONArray("product_list").length()];
            for(int index = 0; index < productIds.length; index++) {
                productIds[index] = responseBody.getJSONObject("response").getJSONObject("data").getJSONArray("product_list").getJSONObject(index).getString("product_id");
            }
        }catch (JSONException e){
            e.printStackTrace();
        }finally {
            return productIds;
        }
    }

    public static String getFromResponse(String key) {
        try {
            apiToken = responseBody.getJSONObject("response").getJSONObject("data").getString(key);
        }catch (JSONException e){
//            e.printStackTrace();
        }finally {
            return apiToken;
        }
    }

    public static String getUserActivationUrl(){
        return Constants.USER_ACTIVATION_URL + userId +"/"+apiToken;
    }

    public static String getStatusCode() {
        try {
            statusCode = responseBody.getJSONObject("response").getString("status");
        }catch (JSONException e){

        }finally {
            return statusCode;
        }
    }

    public static String getPin(String emailContent){
        String pin = emailContent.substring(emailContent.indexOf("Please use the PIN "), emailContent.indexOf(" to retrieve your password.")).replace("Please use the PIN ","").replace(" to retrieve your password.","");
        pin = emailContent.substring(emailContent.indexOf("<b>")+3, emailContent.indexOf("</b>"));

        return pin;
    }

//    public void setResponseData(ModelBase modelBase){
//        if(modelBase == registrationRequestModel){
//            registrationRequestModel.api_token = getFromResponse("api_token");
//            registrationRequestModel.user_id  = getFromResponse("user_id");
//        }else if(modelBase == loginRequestModel) {
//            loginRequestModel.api_token = getFromResponse("api_token");
//            loginRequestModel.user_id  = getFromResponse("user_id");
//            loginRequestModel.language_code  = getFromResponse("language_code");
//        }else if(modelBase == logoutRequestModel){
//
//        }else if(modelBase == updateUserProfileRequestModel) {
//
//        }else if(modelBase == forgotPasswordRequestModel) {
//
//        }else if(modelBase == verifyPinRequestModel) {
//
//        }else if(modelBase == activateUserRequestModel) {
//
//        }else if(modelBase == contentUploadRequestModel) {
//
//        }
//    }

    public void createUserAndLogin(String userType){
        registrationRequestModel = new RegistrationRequestModel(uniqueId, userType);
        registrationRequestModel.user_type = userType;
        reqBody = registrationRequestModel.getRequestBody();
        sendRequest(registrationRequestModel);
        userId =  getUserID();

        loginRequestModel = new LoginRequestModel();
        loginRequestModel.user_type =userType;
        loginRequestModel.nickname =registrationRequestModel.nickname;
        loginRequestModel.auth_token = apiToken;
        loginRequestModel.password = registrationRequestModel.password;
        loginRequestModel.social_id = registrationRequestModel.social_id;
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        apiToken = getAPIToken();

        logoutRequestModel = new LogoutRequestModel();
        logoutRequestModel.user_id = userId;
        logoutRequestModel.api_token = apiToken;

        updateUserProfileRequestModel = new UpdateUserProfileRequestModel(registrationRequestModel.user_type);
        updateUserProfileRequestModel.user_id = userId;
        updateUserProfileRequestModel.api_token = apiToken;
        updateUserProfileRequestModel.password = registrationRequestModel.password;

    }

    public void createUser(String userType){
        registrationRequestModel.user_type = userType;
        reqBody = registrationRequestModel.getRequestBody();
        sendRequest(registrationRequestModel);
        userId =  getUserID();
        apiToken = getAPIToken();

    }

    public String getBase64String(String fileName) throws IOException {

        String url =  new java.io.File(".").getCanonicalPath() + "/" +"/classes/profileimages/"+fileName;
        String content = null;
        File file = new File(url); //for ex foo.txt

            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();

        return content;
    }








}
