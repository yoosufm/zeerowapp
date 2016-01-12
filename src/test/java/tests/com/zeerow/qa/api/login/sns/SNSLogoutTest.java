package tests.com.zeerow.qa.api.login.sns;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.ApplicationErrorsMessage;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.common.ResponseUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class SNSLogoutTest extends APITestBase {
    protected static String uniqueId;

    @BeforeMethod
    public void setUp() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        userType = Constants.USER_TYPE_FACEBOOK;
        uniqueId = UUID.randomUUID().toString().split("-")[0];

    }

    @Test
    public void testFacebookUserLogout() throws Exception {
        testCaseID = "37";
        userType = Constants.USER_TYPE_FACEBOOK;

        createUserAndLogin(userType);
        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyLogoutSuccessResponse(responseBody);
    }

    @Test
    public void testTwitterUserLogout() throws Exception {
        testCaseID = "38";
        userType = Constants.USER_TYPE_TWITTER;

        createUserAndLogin(userType);
        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyLogoutSuccessResponse(responseBody);
    }

    @Test
    public void testSNSUserLogout() throws Exception {
        testCaseID = "36";
        userType = Constants.USER_TYPE_EMAIL;
        createUserAndLogin(userType);

        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyLogoutSuccessResponse(responseBody);
    }

    @Test
    public void testFacebookUserLogoutWithIncorrectAPIToken() throws Exception {
        testCaseID = "39";
        userType = Constants.USER_TYPE_FACEBOOK;
        createUserAndLogin(userType);

        logoutRequestModel.api_token = "555555666";
        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 23, language_code);
    }

    @Test
    public void testFacebookUserLogoutWithBlankAPIToken() throws Exception {
        testCaseID = "40";

        logoutRequestModel.user_id = "1";
        logoutRequestModel.api_token = "";
        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 12, language_code);
    }

    @Test
    public void testFacebookUserLogoutWithIncorrectUserId() throws Exception {
        testCaseID = "41";
        userType = Constants.USER_TYPE_FACEBOOK;
        createUserAndLogin(userType);

        logoutRequestModel.user_id = "invlaid";
        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyLoginFailResponse(responseBody, ApplicationErrorsMessage.fromInt(4).errorMessageEn, ApplicationErrorsMessage.fromInt(4).errorCode);
    }

    @Test
    public void testFacebookUserLogoutWithBlankUserId() throws Exception {
        testCaseID = "42";
        userType = Constants.USER_TYPE_FACEBOOK;
        createUserAndLogin(userType);
        logoutRequestModel.user_id = "";
        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 6, language_code);
    }
}
