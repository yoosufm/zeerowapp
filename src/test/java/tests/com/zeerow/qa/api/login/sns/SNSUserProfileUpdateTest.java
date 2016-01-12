package tests.com.zeerow.qa.api.login.sns;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.common.ResponseUtil;
import com.zeerow.qa.util.api.requestmodel.RegistrationRequestModel;
import org.json.JSONException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class SNSUserProfileUpdateTest extends APITestBase {

    @BeforeClass
    public void setUp() {
        userType = Constants.USER_TYPE_FACEBOOK;
    }

    @BeforeMethod
    public void initTest() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        uniqueId = UUID.randomUUID().toString().split("-")[0];
        registrationRequestModel = new RegistrationRequestModel(uniqueId, userType);
        createUserAndLogin(userType);
    }

    @Test
    public void testUpdateProfilePicture() throws JSONException {
        testCaseID = "59";
        updateUserProfileRequestModel.setProfilePicture("http://54.152.110.169/profileImages/default/"+ loginRequestModel.nickname +".png");
        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyLoginSuccessResponse(responseBody);
    }

    @Test
    public void testUpdatePassword() throws JSONException {
        testCaseID = "63";

        updateUserProfileRequestModel.password = "lk@52AD8@";
        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyLoginSuccessResponse(responseBody);

        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyLoginSuccessResponse(responseBody);
    }

    @Test
    public void testProfilePictureWithInvalidUserId() throws JSONException{
        testCaseID = "64";

        updateUserProfileRequestModel.setProfilePicture("http://54.152.110.169/profileImages/default/"+ loginRequestModel.nickname +".png");
        updateUserProfileRequestModel.user_id = "000ld55";

        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 4, language_code);
    }

    @Test
    public void testProfilePictureWithInvalidAPIToken() throws JSONException{
        testCaseID = "65";

        updateUserProfileRequestModel.setProfilePicture("http://54.152.110.169/profileImages/default/"+ loginRequestModel.nickname +".png");
        updateUserProfileRequestModel.api_token = "invalid api token";

        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 23, language_code);
    }

    @Test
    public void testUpdateProfileForInactiveUser() throws JSONException{
        testCaseID = "91";

        createUser(userType);
        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 23, language_code);
    }
}
