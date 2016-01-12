package tests.com.zeerow.qa.api.login.email;

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
public class EmailUserProfileUpdateTest extends APITestBase {

    @BeforeClass
    public void setUp() {
        userType = Constants.USER_TYPE_EMAIL;
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
        testCaseID = "87";

        updateUserProfileRequestModel.setProfilePicture(Constants.PROFILE_PICTURE_BASE64_DATA);
        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyLoginSuccessResponse(responseBody);
    }

    @Test
    public void testUpdatePassword() throws JSONException {
        testCaseID = "88";

        updateUserProfileRequestModel.api_token = getAPIToken();
        updateUserProfileRequestModel.password = "lk@52AD8@";
        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyLoginSuccessResponse(responseBody);

        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 31,language_code);

        loginRequestModel.password = "lk@52AD8@";
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        ResponseUtil.verifyLoginSuccessResponse(responseBody);
    }

    @Test
    public void testProfilePictureWithInvalidUserId() throws JSONException{
        testCaseID = "89";

        updateUserProfileRequestModel.setProfilePicture(Constants.PROFILE_PICTURE_BASE64_DATA);
        updateUserProfileRequestModel.api_token = getAPIToken();
        updateUserProfileRequestModel.user_id = "000ld55";

        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 4, language_code);
    }

    @Test
    public void testProfilePictureWithInvalidAPIToken() throws JSONException{
        testCaseID = "90";

        updateUserProfileRequestModel.setProfilePicture(Constants.PROFILE_PICTURE_BASE64_DATA);
        updateUserProfileRequestModel.api_token = "invalid api token";

        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 23, language_code);
    }

    @Test
    public void testProfilePictureThanPictureSizeIsGreaterThanAllowedSize() throws Exception{
        testCaseID = "90";

        updateUserProfileRequestModel.setProfilePicture(getBase64String("high.txt"));
        updateUserProfileRequestModel.api_token = getAPIToken();

        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 23, language_code);
    }

    @Test
    public void testUpdateProfileForInactiveUser() throws JSONException{
        testCaseID = "92";
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        createUser(userType);
        updateUserProfileRequestModel.user_id = userId;
        updateUserProfileRequestModel.api_token = apiToken;
        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 24, language_code);
    }
}
