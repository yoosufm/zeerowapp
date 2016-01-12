package tests.com.zeerow.qa.api.login.errorhandling;

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
public class JapanErrorMessageTest extends APITestBase {
    protected static String uniqueId;

    @BeforeMethod
    public void setUp() {
        language_code ="ja";
        userType = Constants.USER_TYPE_FACEBOOK;
        uniqueId = UUID.randomUUID().toString().split("-")[0];

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
        ResponseUtil.verifyFailResponse(responseBody, 22, language_code);
    }

    public void createUser(String userType){
        registrationRequestModel.user_type = userType;
        registrationRequestModel.language_code = language_code;
        reqBody = registrationRequestModel.getRequestBody();
        sendRequest(registrationRequestModel);
        userId =  getUserID();

    }



}
