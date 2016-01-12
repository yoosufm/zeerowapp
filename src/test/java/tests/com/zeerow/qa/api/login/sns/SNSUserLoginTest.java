package tests.com.zeerow.qa.api.login.sns;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.ApplicationErrorsMessage;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.common.ResponseUtil;
import com.zeerow.qa.util.api.requestmodel.LoginRequestModel;
import com.zeerow.qa.util.api.requestmodel.RegistrationRequestModel;
import org.json.JSONException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class SNSUserLoginTest extends APITestBase {

    @BeforeClass
    public void setUp() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        userType = Constants.USER_TYPE_FACEBOOK;
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
        loginRequestModel.social_id = uniqueId;
        loginRequestModel.auth_token = uniqueId;
        loginRequestModel.user_type =userType;
    }

    @Test
    public void testSuccesFacebookLogin() throws JSONException {
        testCaseID = "1";

        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyLoginSuccessResponse(responseBody);
    }

    @Test
    public void testSendFacebookLoginWithBlankSocialID() throws JSONException {
        testCaseID = "2";
        loginRequestModel.social_id = "";
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyLoginFailResponse(responseBody, ApplicationErrorsMessage.fromInt(8).errorMessageEn, ApplicationErrorsMessage.fromInt(8).errorCode);
    }

    @Test
     public void testSendFacebookLoginWithInvalidSocialID() throws JSONException {
        testCaseID = "3";
        loginRequestModel.social_id = "invalid social id";
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyLoginFailResponse(responseBody, ApplicationErrorsMessage.fromInt(4).errorMessageEn, ApplicationErrorsMessage.fromInt(4).errorCode);
    }


    @Test
    public void testSendFacebookLoginWithInvalidUserType() throws JSONException {
        testCaseID = "31";
        loginRequestModel.nickname =uniqueId;
        loginRequestModel.password = uniqueId;
        loginRequestModel.user_type = "invalid";
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyLoginFailResponse(responseBody, ApplicationErrorsMessage.fromInt(15).errorMessageEn, ApplicationErrorsMessage.fromInt(15).errorCode);
    }

    @Test
    public void testSendFacebookLoginWithBlankUserType() throws JSONException {
        testCaseID = "32";
        loginRequestModel.nickname =uniqueId;
        loginRequestModel.password = uniqueId;
        loginRequestModel.user_type = "BLANK_FACEBOOK";
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyLoginFailResponse(responseBody, ApplicationErrorsMessage.fromInt(11).errorMessageEn, ApplicationErrorsMessage.fromInt(11).errorCode);
    }


}
