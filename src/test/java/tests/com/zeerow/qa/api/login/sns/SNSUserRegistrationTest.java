package tests.com.zeerow.qa.api.login.sns;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.common.ResponseUtil;
import com.zeerow.qa.util.api.requestmodel.RegistrationRequestModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class SNSUserRegistrationTest extends APITestBase {

    @BeforeClass
    public void setUp() {
        userType = Constants.USER_TYPE_FACEBOOK;
    }

    @BeforeMethod
    public void initTest() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        uniqueId = UUID.randomUUID().toString().split("-")[0];
        registrationRequestModel = new RegistrationRequestModel(uniqueId, userType);

    }

    @Test
    public void testRegisterWithBlankUserProfile() throws Exception{
        testCaseID = "26";
        registrationRequestModel.profile_picture = "";
        createUser(userType);
        ResponseUtil.verifyLoginSuccessResponse(responseBody);
    }

    @Test
    public void testRegisterWithBlankNickname() throws Exception{
        testCaseID = "85";

        registrationRequestModel.nickname = "";
        createUser(userType);
        ResponseUtil.verifyFailResponse(responseBody, 28, language_code);
    }

    @Test
    public void testRegisterWithBlankSocialID() throws Exception{
        testCaseID = "85";

        registrationRequestModel.social_id = "";
        createUser(userType);
        ResponseUtil.verifyFailResponse(responseBody, 8, language_code);
    }

    @Test
    public void testFacebookUserRegistraionWithProfilePictureData() throws Exception{
        testCaseID = "52";
        registrationRequestModel = new RegistrationRequestModel(uniqueId, Constants.USER_TYPE_FACEBOOK);
        reqBody = registrationRequestModel.getRequestBody();
        reqBody = reqBody.replace("profile_pic_url","profile_pic_data").replace(Constants.DEFAULT_PROFILE_PICTURE_URL, Constants.PROFILE_PICTURE_BASE64_DATA);
        sendRequest(registrationRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 8, language_code);


    }

    @Test
    public void testTwitterUserRegistraionWithProfilePictureData() throws Exception{
        testCaseID = "51";
        registrationRequestModel = new RegistrationRequestModel(uniqueId, Constants.USER_TYPE_TWITTER);
        reqBody = registrationRequestModel.getRequestBody();
        reqBody = reqBody.replace("profile_pic_url","profile_pic_data").replace(Constants.DEFAULT_PROFILE_PICTURE_URL, Constants.PROFILE_PICTURE_BASE64_DATA);
        sendRequest(registrationRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, 8, language_code);


    }




}
