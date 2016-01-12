package tests.com.zeerow.qa.api.content.homefeed;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.requestmodel.UnlikeContentRequestModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 8/24/2015.
 */
public class TestUnlikeContent extends APITestBase {

    @BeforeClass
    public void setUp() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        userType = Constants.USER_TYPE_FACEBOOK;
        uniqueId = UUID.randomUUID().toString().split("-")[0];
        createUserAndLogin(userType);
        unlikeContent = new UnlikeContentRequestModel();

    }

    @Test
    public void testUnlikeContent() throws  Exception{
        reqBody = unlikeContent.getRequestBody(apiToken, userId, "2");
        sendRequest(unlikeContent);

    }
}
