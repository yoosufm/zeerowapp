package tests.com.zeerow.qa.api.content.homefeed;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.requestmodel.UserHomeFeedRequestModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 8/24/2015.
 */
public class TestHomeFeed extends APITestBase {

    @BeforeClass
    public void setUp() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        userType = Constants.USER_TYPE_FACEBOOK;
        uniqueId = UUID.randomUUID().toString().split("-")[0];
        createUserAndLogin(userType);
        userHomeFeedRequestModel = new UserHomeFeedRequestModel();

    }

    @Test
    public void testGetHomeFeed() throws  Exception{
        reqBody = userHomeFeedRequestModel.getRequestBody(apiToken, userId, "1");
        sendRequest(userHomeFeedRequestModel);

    }
}
