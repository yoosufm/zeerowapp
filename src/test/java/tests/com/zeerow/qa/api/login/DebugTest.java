package tests.com.zeerow.qa.api.login;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.common.ResponseUtil;
import com.zeerow.qa.util.api.requestmodel.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class DebugTest extends APITestBase {
    String contentId = "";

    @BeforeClass
    public void setUp() {
    }

    @BeforeMethod
    public void initTest() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        uniqueId = UUID.randomUUID().toString().split("-")[0];
        loginRequestModel = new LoginRequestModel();
        logoutRequestModel = new LogoutRequestModel();
        activateUserRequestModel = new ActivateUserRequestModel();

    }

    @Test
    public void testSignup() throws Exception {
        testCaseID = "21";

//        uniqueId = UUID.randomUUID().toString().split("-")[0];
//        userType = Constants.USER_TYPE_FACEBOOK;
//        registrationRequestModel = new RegistrationRequestModel(uniqueId, userType);
//
//        registrationRequestModel.user_type = userType;
//        reqBody = registrationRequestModel.getRequestBody();
//        sendRequest(registrationRequestModel);
//        ResponseUtil.verifyRegistrationSuccessResponse(responseBody);

    }

    @Test(dependsOnMethods = "testSignup", alwaysRun = true)
    public void testLogin() throws Exception {
        loginRequestModel.user_type = "EMAIL";
//        loginRequestModel.auth_token = registrationRequestModel.api_token;
//        loginRequestModel.social_id = registrationRequestModel.social_id;
        loginRequestModel.nickname = "yoosufy";
        loginRequestModel.password = "password1";

        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        apiToken = getAPIToken();
        userId = getUserID();
        ResponseUtil.verifyLoginSuccessResponse(responseBody);

    }


    @Test(dependsOnMethods = "testLogin", alwaysRun = true)
    public void testDebug1() throws Exception {
        resetUserRequestModel = new ResetUserRequestModel();
        resetUserRequestModel.api_token = apiToken;
        resetUserRequestModel.user_id = userId;
        reqBody = resetUserRequestModel.getRequestBody();
        sendRequest(resetUserRequestModel);
    }




}
