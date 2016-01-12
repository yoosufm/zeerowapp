package tests.com.zeerow.qa.api.login.email;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.ApplicationErrorsMessage;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.common.ResponseUtil;
import com.zeerow.qa.util.api.requestmodel.LogoutRequestModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class EmailLogoutTest extends APITestBase {
    protected static String uniqueId;

    @BeforeMethod
    public void setUp() {
        userType = Constants.USER_TYPE_FACEBOOK;
        uniqueId = UUID.randomUUID().toString().split("-")[0];
        logoutRequestModel = new LogoutRequestModel();
    }

    @BeforeMethod
    public void testCleanup(){
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
    }

    @Test
    public void testEmailUserLogout() throws Exception {
        testCaseID = "36";
        userType = Constants.USER_TYPE_EMAIL;
        createUserAndLogin(userType);

        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyLogoutSuccessResponse(responseBody);
    }

    @Test
    public void testEmailUserLogoutWithIncorrectAPIToken() throws Exception {
        testCaseID = "39";
        userType = Constants.USER_TYPE_EMAIL;
        createUserAndLogin(userType);

        logoutRequestModel.api_token = "555555666";
        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 23, language_code);
    }

    @Test
    public void testEmailUserLogoutWithBlankAPIToken() throws Exception {
        testCaseID = "40";
        userType = Constants.USER_TYPE_EMAIL;
        createUserAndLogin(userType);

        logoutRequestModel.api_token = "";
        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 12, language_code);
    }

    @Test
    public void testEmailUserLogoutWithIncorrectUserId() throws Exception {
        testCaseID = "41";
        userType = Constants.USER_TYPE_EMAIL;
        createUserAndLogin(userType);

        logoutRequestModel.user_id = "invlaid";
        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyLoginFailResponse(responseBody, ApplicationErrorsMessage.fromInt(4).errorMessageEn, ApplicationErrorsMessage.fromInt(4).errorCode);
    }

    @Test
    public void testEmailUserLogoutWithBlankUserId() throws Exception {
        testCaseID = "42";
        userType = Constants.USER_TYPE_EMAIL;
        createUserAndLogin(userType);
        logoutRequestModel.user_id = "";
        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 6, language_code);
    }





}
