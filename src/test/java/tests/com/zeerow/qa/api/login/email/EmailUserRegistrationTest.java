package tests.com.zeerow.qa.api.login.email;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.common.ResponseUtil;
import com.zeerow.qa.util.api.requestmodel.RegistrationRequestModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class EmailUserRegistrationTest extends APITestBase {
    protected static String uniqueId;

    @BeforeMethod
    public void setUp() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        uniqueId = UUID.randomUUID().toString().split("-")[0];
        registrationRequestModel = new RegistrationRequestModel(uniqueId, Constants.USER_TYPE_EMAIL);
    }

    @Test
    public void testRegisterWithExistingNickname() throws Exception{
        testCaseID = "17";
        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
        String nickName = uniqueId;
        createUser(Constants.USER_TYPE_EMAIL);
        uniqueId = UUID.randomUUID().toString().split("-")[0];

        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
        registrationRequestModel.nickname = nickName;
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyFailResponse(responseBody, 2, language_code);

    }

    @Test
    public void testRegisterWithPasswordIsNotMatchedWithRequredCombination() throws Exception{
        testCaseID = "84";

        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
        registrationRequestModel.password = "passwords";
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyFailResponse(responseBody, 29, language_code);
    }


    @Test
    public void testRegisterWithNonEnglishNickname() throws Exception{
        testCaseID = "83";

        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
        registrationRequestModel.nickname = "???";
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyFailResponse(responseBody, 28, language_code);
    }

    @Test
    public void testRegisterWithExistingEmail() throws Exception{
        testCaseID = "23";
        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
//        String email = uniqueId;
        createUser(Constants.USER_TYPE_EMAIL);
        uniqueId = UUID.randomUUID().toString().split("-")[0];

        registrationRequestModel = new RegistrationRequestModel(uniqueId, Constants.USER_TYPE_EMAIL);
//        registrationRequestModel.email = email+"@coinbatestest.com";
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyFailResponse(responseBody, 2, language_code);

    }

    @Test
    public void testUserRegistrationWithProfileImageDataWithInvalidEncoding() throws Exception{
        testCaseID = "49";

        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
        registrationRequestModel.setProfilePicture(getBase64String("invalid_format.txt"));
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyFailResponse(responseBody, 15, language_code);
    }

    @Test
    public void testUserRegistrationWithBlankProfilePictureData() throws Exception{
        testCaseID = "50";

        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
        registrationRequestModel.setProfilePicture("");
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyRegistrationSuccessResponse(responseBody);
    }

    @Test
    public void testEmailUserRegistrationWithProfileImageURL() throws Exception{
        testCaseID = "53";

        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_FACEBOOK);
        registrationRequestModel.setProfilePicture(Constants.DEFAULT_PROFILE_PICTURE_URL);
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyFailResponse(responseBody, 21, language_code);
    }

    @Test
    public void testUserRegistrationWithExactlyAllowedImageSize() throws Exception{
        testCaseID = "54";

        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
        registrationRequestModel.setProfilePicture(getBase64String("same_size.txt"));
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyRegistrationSuccessResponse(responseBody);
    }

    @Test
    public void testUserRegistrationWithProfileImageGreaterThanAllowedSize() throws Exception{
        testCaseID = "55";

        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
        registrationRequestModel.setProfilePicture(getBase64String("high.txt"));
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyFailResponse(responseBody, 21, language_code);
    }

    @Test
    public void testUserRegistrationWithRecordMarketingEmailEnabled() throws Exception{
        testCaseID = "56";

        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
        registrationRequestModel.setProfilePicture(getBase64String("same_size.txt"));
        registrationRequestModel.is_rec_marketing_email = "1";
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyRegistrationSuccessResponse(responseBody);
    }

    @Test
    public void testUserRegistrationWithRecordMarketingEmailDisabled() throws Exception{
        testCaseID = "57";

        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
        registrationRequestModel.setProfilePicture(getBase64String("same_size.txt"));
        registrationRequestModel.is_rec_marketing_email = "0";
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyRegistrationSuccessResponse(responseBody);
    }

    @Test
    public void testRegisterWithBlankNickname() throws Exception{
        testCaseID = "86";

        registrationRequestModel = new RegistrationRequestModel(uniqueId,Constants.USER_TYPE_EMAIL);
        registrationRequestModel.nickname = "";
        createUser(Constants.USER_TYPE_EMAIL);
        ResponseUtil.verifyFailResponse(responseBody, 1, language_code);
    }

    @Test
    public void testRegisterWithInvalidUserType() throws Exception{
        testCaseID = "25";
        createUser("invalid");
        ResponseUtil.verifyFailResponse(responseBody, 11, language_code);
    }
}
