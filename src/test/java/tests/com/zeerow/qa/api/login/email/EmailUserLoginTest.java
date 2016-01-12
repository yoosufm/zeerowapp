package tests.com.zeerow.qa.api.login.email;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.ApplicationErrorsMessage;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.common.ResponseUtil;
import com.zeerow.qa.util.api.requestmodel.LoginRequestModel;
import com.zeerow.qa.util.api.requestmodel.RegistrationRequestModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class EmailUserLoginTest extends APITestBase {

    @BeforeClass
    public void setUp() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        userType = Constants.USER_TYPE_EMAIL;
        uniqueId = UUID.randomUUID().toString().split("-")[0];
        registrationRequestModel = new RegistrationRequestModel(uniqueId, userType);
        registrationRequestModel.user_type = userType;
        reqBody = registrationRequestModel.getRequestBody();
        sendRequest(registrationRequestModel);
        userId = getUserID();
    }

    @BeforeMethod
    public void initTest() {
        loginRequestModel = new LoginRequestModel();
    }

    @Test
    public void testSucceskLogin() throws JSONException {
        testCaseID = "1";

        loginRequestModel.nickname = uniqueId;
        loginRequestModel.password = registrationRequestModel.password;
        loginRequestModel.user_type =userType;
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);

        ResponseUtil.verifyLoginSuccessResponse(responseBody);
    }

    @Test
    public void testSendFacebookLoginWithBlankNickName() throws JSONException {
        testCaseID = "27";
        loginRequestModel.nickname = "";
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyLoginFailResponse(responseBody, ApplicationErrorsMessage.fromInt(1).errorMessageEn, ApplicationErrorsMessage.fromInt(1).errorCode);
    }

    @Test
     public void testSendFacebookLoginWithInvalidNickName() throws JSONException {
        testCaseID = "28";
        loginRequestModel.nickname = "invalidnickname";
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyFailResponse(responseBody,30, language_code);
    }

    @Test
      public void testSendFacebookLoginWithInvalidPassword() throws JSONException {
        testCaseID = "14";
        loginRequestModel.nickname =uniqueId;
        loginRequestModel.password = uniqueId + uniqueId;
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 31, language_code);
    }

    @Test
    public void testSendFacebookLoginWithBlankPassword() throws JSONException {
        testCaseID = "15";
        loginRequestModel.nickname =uniqueId;
        loginRequestModel.password = "";
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 9, language_code);
    }

    @Test
    public void testSendFacebookLoginWithInvalidUserType() throws JSONException {
        testCaseID = "29";
        loginRequestModel.nickname =uniqueId;
        loginRequestModel.password = uniqueId;
        //loginRequestModel.user_type = Constants.USER_TYPE_FACEBOOK;

        reqBody = loginRequestModel.getRequestBody();
        JSONObject reqBodyJson = new JSONObject(reqBody);
        reqBodyJson.put("user_type", Constants.USER_TYPE_FACEBOOK);
        reqBody = reqBodyJson.toString();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 8, language_code);
    }

    @Test
    public void testSendFacebookLoginWithBlankUserType() throws JSONException {
        testCaseID = "30";
        loginRequestModel.nickname =uniqueId;
        loginRequestModel.password = uniqueId;
        loginRequestModel.user_type = "BLANK_EMAIL";
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 11, language_code);
    }


}
