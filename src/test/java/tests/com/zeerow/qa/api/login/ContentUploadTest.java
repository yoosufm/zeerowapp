package tests.com.zeerow.qa.api.login;

import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.ResponseUtil;
import com.zeerow.qa.util.api.requestmodel.ContentUploadRequestModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class ContentUploadTest extends APITestBase {
    protected static String uniqueId;

    @BeforeMethod
    public void setUp() {
        contentUploadRequestModel = new ContentUploadRequestModel();
    }

    @Test
    public void testUploadContentSuccess() throws Exception {
        testCaseID = "75";
        reqBody = contentUploadRequestModel.getRequestBody();
        sendRequest(contentUploadRequestModel);
        ResponseUtil.verifyFileUploadSuccessResponse(responseBody, "File uploaded succesfully");
    }

    @Test
    public void testUploadContentWithBlankFileContent() throws Exception {
        testCaseID = "81";
        contentUploadRequestModel.file_content="";

        reqBody = contentUploadRequestModel.getRequestBody();
        sendRequest(contentUploadRequestModel);


    }

    @Test
    public void testUploadContentWithBlankFileName() throws Exception {
        testCaseID = "79";
        contentUploadRequestModel.file_name="";

        reqBody = contentUploadRequestModel.getRequestBody();
        sendRequest(contentUploadRequestModel);


    }


}
