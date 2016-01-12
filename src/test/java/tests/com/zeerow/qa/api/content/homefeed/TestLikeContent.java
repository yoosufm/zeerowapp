package tests.com.zeerow.qa.api.content.homefeed;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.requestmodel.LikeContentRequestModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 8/24/2015.
 */
public class TestLikeContent extends APITestBase {

    @BeforeClass
    public void setUp() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        userType = Constants.USER_TYPE_FACEBOOK;
        uniqueId = UUID.randomUUID().toString().split("-")[0];
        createUserAndLogin(userType);
        likeContentRequestModel = new LikeContentRequestModel();

    }

    @Test
    public void testLikeContent() throws  Exception{
        reqBody = likeContentRequestModel.getRequestBody(apiToken, userId, "1");
        sendRequest(likeContentRequestModel);

    }
}
