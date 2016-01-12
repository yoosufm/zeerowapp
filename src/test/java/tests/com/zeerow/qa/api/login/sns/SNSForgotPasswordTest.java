package tests.com.zeerow.qa.api.login.sns;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.common.ResponseUtil;
import com.zeerow.qa.util.api.requestmodel.ForgotPasswordRequestModel;
import com.zeerow.qa.util.api.requestmodel.VerifyPinRequestModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class SNSForgotPasswordTest extends APITestBase {
    protected static String uniqueId;

    @BeforeMethod
    public void setUp() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        userType = Constants.USER_TYPE_FACEBOOK;
        uniqueId = UUID.randomUUID().toString().split("-")[0];
        forgotPasswordRequestModel = new ForgotPasswordRequestModel();
        verifyPinRequestModel = new VerifyPinRequestModel();
        userId = Constants.USER_ID;
        isActivateUser = true;
    }

    @Test
    public void testForgotPasswordSuccess() throws Exception {
        testCaseID = "66";
        userType = Constants.USER_TYPE_FACEBOOK;

        createUserAndLogin(userType);
        reqBody = forgotPasswordRequestModel.getRequestBody(registrationRequestModel.email);
        sendRequest(forgotPasswordRequestModel);
        ResponseUtil.verifyForgotPasswordSuccessResponse(responseBody, userId);
//       String pin = getPin(EmailClient.getLastEmail());
//        reqBody = verifyPinRequestModel.getRequestBody(userId, pin);
//        sendRequest(verifyPinRequestModel);
//        ResponseUtil.verifyForgotPasswordSuccessResponse(responseBody, userId);

    }

    @Test
      public void testForgotPasswordForInactiveUser()throws Exception{
        testCaseID = "67";
        userType = Constants.USER_TYPE_FACEBOOK;
        registrationRequestModel.email = uniqueId+"@coinbatestest.com";

        isActivateUser = false;
        createUserAndLogin(userType);
        reqBody = forgotPasswordRequestModel.getRequestBody(registrationRequestModel.email);
        sendRequest(forgotPasswordRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 25, language_code);
    }

    @Test
    public void testForgotPasswordWithNotExistingUser()throws Exception{
        testCaseID = "68";
        userType = Constants.USER_TYPE_FACEBOOK;
        registrationRequestModel.email = uniqueId+"@coinbatestest.com";

        reqBody = forgotPasswordRequestModel.getRequestBody(registrationRequestModel.email);
        sendRequest(forgotPasswordRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 32, language_code);
    }

    @Test
    public void testForgotPasswordWithBlankEmail()throws Exception{
        testCaseID = "69";
        userType = Constants.USER_TYPE_FACEBOOK;
        registrationRequestModel.email = "";

        reqBody = forgotPasswordRequestModel.getRequestBody(registrationRequestModel.email);
        sendRequest(forgotPasswordRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 16, language_code);
    }


}
