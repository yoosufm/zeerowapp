package tests.com.zeerow.qa.api;

import com.zeerow.qa.util.DBUtil;
import com.zeerow.qa.util.api.common.APITestBase;
import com.zeerow.qa.util.api.common.Constants;
import com.zeerow.qa.util.api.common.ResponseUtil;
import com.zeerow.qa.util.api.requestmodel.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class EndToEndTest extends APITestBase {
    String contentId = "";
    String contentIds [] = null;
    String productIds [] = null;

    @BeforeClass
    public void setUp() {
    }

    @BeforeMethod
    public void initTest() {
        DBUtil.deleteUserAccount(Constants.EMAIL_ADDRESS);
        uniqueId = UUID.randomUUID().toString().split("-")[0];
        loginRequestModel = new LoginRequestModel();
        logoutRequestModel = new LogoutRequestModel();
        activateUserRequestModel = new ActivateUserRequestModel();

    }

    @Test
    public void testSignup() throws Exception {
        testCaseID = "21";

//        uniqueId = UUID.randomUUID().toString().split("-")[0];
//        userType = Constants.USER_TYPE_FACEBOOK;
//        registrationRequestModel = new RegistrationRequestModel(uniqueId, userType);
//
//        registrationRequestModel.user_type = userType;
//        reqBody = registrationRequestModel.getRequestBody();
//        sendRequest(registrationRequestModel);
//        ResponseUtil.verifyRegistrationSuccessResponse(responseBody);

    }

    @Test(dependsOnMethods = "testSignup", alwaysRun = true)
    public void testLogin() throws Exception {
        loginRequestModel.user_type = Constants.USER_TYPE_EMAIL;
//        loginRequestModel.auth_token = registrationRequestModel.api_token;
//        loginRequestModel.social_id = registrationRequestModel.social_id;
        loginRequestModel.nickname = "yoosufy";
        loginRequestModel.password = "password1";
        reqBody = loginRequestModel.getRequestBody();
        sendRequest(loginRequestModel);
        apiToken = getAPIToken();
        userId = getUserID();
        ResponseUtil.verifyLoginSuccessResponse(responseBody);

    }

    @Test(dependsOnMethods = "testLogin", alwaysRun = true)
    public void testRetrieveUserHomeFeed() throws Exception {
        userHomeFeedRequestModel = new UserHomeFeedRequestModel();
        reqBody = userHomeFeedRequestModel.getRequestBody(apiToken, userId, "1");
        sendRequest(userHomeFeedRequestModel);
        ResponseUtil.verifyGetFreeHomeFeedResponse(responseBody);

    }

    @Test(dependsOnMethods = "testRetrieveUserHomeFeed", alwaysRun = true)
    public void testLikeContent() throws Exception {

        contentId = getContentID();
        contentIds = getContentIDs();
        likeContentRequestModel = new LikeContentRequestModel();
        reqBody = likeContentRequestModel.getRequestBody(apiToken, userId, contentId);
        sendRequest(likeContentRequestModel);
        //        ResponseUtil.verifyLoginSuccessResponse(responseBody);


    }

    @Test(dependsOnMethods = "testLikeContent", alwaysRun = true)
    public void testUnlikeContent() throws Exception {
        unlikeContent = new UnlikeContentRequestModel();
        reqBody = unlikeContent.getRequestBody(apiToken, userId, contentId);
        sendRequest(unlikeContent);
//        ResponseUtil.verifyLoginSuccessResponse(responseBody);

    }

    @Test(dependsOnMethods = "testUnlikeContent", alwaysRun = true)
    public void testAddComment() throws Exception {
        addCommentRequestModel = new AddCommentRequestModel();
        reqBody = addCommentRequestModel.getRequestBody(apiToken, userId, contentId, "test content " + uniqueId);
        sendRequest(addCommentRequestModel);
//        ResponseUtil.verifyLoginSuccessResponse(responseBody);
    }

    @Test(dependsOnMethods = "testAddComment", alwaysRun = true)
    public void testUpdateProfile() throws Exception {

        updateUserProfileRequestModel = new UpdateUserProfileRequestModel(registrationRequestModel.user_type);
        updateUserProfileRequestModel.user_id = userId;
        updateUserProfileRequestModel.api_token = apiToken;
        updateUserProfileRequestModel.password = registrationRequestModel.password;
        updateUserProfileRequestModel.setProfilePicture("http://54.152.110.169/profileImages/default/" + loginRequestModel.nickname + ".png");
        reqBody = updateUserProfileRequestModel.getRequestBody();
        sendRequest(updateUserProfileRequestModel);
        ResponseUtil.verifyLoginSuccessResponse(responseBody);
    }

    @Test(dependsOnMethods = "testUpdateProfile", alwaysRun = true)
    public void testRetrieveUserProfile() throws Exception {

        retrieveUserProfileRequestModel = new RetrieveUserProfileRequestModel();
        retrieveUserProfileRequestModel.user_id = userId;
        retrieveUserProfileRequestModel.api_token = apiToken;
        reqBody = retrieveUserProfileRequestModel.getRequestBody();
        sendRequest(retrieveUserProfileRequestModel);
        ResponseUtil.verifyGetProfileSuccessResponse(responseBody, userId, apiToken);
    }

    @Test(dependsOnMethods = "testRetrieveUserProfile", alwaysRun = true)
    public void testRetrieveUserBalance() throws Exception {
        retrieveUserBalanceRequestModel = new RetrieveUserBalanceRequestModel();
        retrieveUserBalanceRequestModel.user_id = userId;
        retrieveUserBalanceRequestModel.api_token = apiToken;
        reqBody = retrieveUserBalanceRequestModel.getRequestBody();
        sendRequest(retrieveUserBalanceRequestModel);
        ResponseUtil.verifyGetUserBalanceSuccessResponse(responseBody, userId, 0, 0, 0);
    }

    @Test(dependsOnMethods = "testRetrieveUserBalance", alwaysRun = true)
    public void testCollectToken() throws Exception {
        collectTokenRequestModel = new CollectTokenRequestModel();
        collectTokenRequestModel.user_id = userId;
        collectTokenRequestModel.api_token = apiToken;
        reqBody = collectTokenRequestModel.getRequestBody();
        sendRequest(collectTokenRequestModel);
        ResponseUtil.verifyCollectTokenSuccessResponse(responseBody, userId, 0, 0, 100);
        reqBody = retrieveUserBalanceRequestModel.getRequestBody();
        sendRequest(retrieveUserBalanceRequestModel);
        ResponseUtil.verifyGetUserBalanceSuccessResponse(responseBody, userId, 0, 0, 100);
        reqBody = collectTokenRequestModel.getRequestBody();
        sendRequest(collectTokenRequestModel);
        ResponseUtil.verifyFailResponse(responseBody, "No tokens are available to collect", "047");
    }

    @Test(dependsOnMethods = "testCollectToken", alwaysRun = true)
    public void testCollectContent() throws Exception {
        collectContentRequestModel = new CollectContentRequestModel();
        collectContentRequestModel.user_id = userId;
        collectContentRequestModel.api_token = apiToken;
        collectContentRequestModel.content_id = contentIds[0];
        reqBody = collectContentRequestModel.getRequestBody();
        sendRequest(collectContentRequestModel);
        ResponseUtil.verifyCollectContentSuccessResponse(responseBody, userId, 4, 100);
        collectContentRequestModel.content_id = contentIds[1];
        reqBody = collectContentRequestModel.getRequestBody();
        sendRequest(collectContentRequestModel);
        ResponseUtil.verifyCollectContentSuccessResponse(responseBody, userId, 3, 100);
    }

    @Test(dependsOnMethods = "testCollectContent", alwaysRun = true)
    public void testSkipContent() throws Exception {
        skipContentRequestModel = new SkipContentRequestModel();
        skipContentRequestModel.user_id = userId;
        skipContentRequestModel.api_token = apiToken;
        skipContentRequestModel.content_id = contentIds[2];
        reqBody = skipContentRequestModel.getRequestBody();
        sendRequest(skipContentRequestModel);
        ResponseUtil.verifySkipContentSuccessResponse(responseBody, userId, 3);

    }

    @Test(dependsOnMethods = "testSkipContent", alwaysRun = true)
    public void testRewindContent() throws Exception {

        rewindContentRequestModel = new RewindContentRequestModel();
        rewindContentRequestModel.user_id = userId;
        rewindContentRequestModel.api_token = apiToken;
        rewindContentRequestModel.number_of_decission = "2";
        reqBody = rewindContentRequestModel.getRequestBody();
        sendRequest(rewindContentRequestModel);
        ResponseUtil.verifyRewindContentSuccessResponse(responseBody, userId, 5);
    }

    @Test(dependsOnMethods = "testRewindContent", alwaysRun = true)
    public void testUnlockContentByToken() throws Exception {

        reqBody = userHomeFeedRequestModel.getRequestBody(apiToken, userId, "1");
        sendRequest(userHomeFeedRequestModel);
//        ResponseUtil.verifyGetFreeHomeFeedResponse(responseBody);
        contentIds = getContentIDs();

        unlockContentByTokenRequestModel = new UnlockContentByTokenRequestModel();
        unlockContentByTokenRequestModel.user_id = userId;
        unlockContentByTokenRequestModel.api_token = apiToken;
        unlockContentByTokenRequestModel.content_id = contentIds[0];

        reqBody = unlockContentByTokenRequestModel.getRequestBody();
        sendRequest(unlockContentByTokenRequestModel);
        ResponseUtil.verifySkipContentSuccessResponse(responseBody, userId, 3);
    }

    @Test(dependsOnMethods = "testUnlockContentByToken", alwaysRun = true)
    public void testUnlockContentByTickets() throws Exception {

        unlockContentByTicketsRequestModel = new UnlockContentByTicketsRequestModel();
        unlockContentByTicketsRequestModel.user_id = userId;
        unlockContentByTicketsRequestModel.api_token = apiToken;
        unlockContentByTicketsRequestModel.content_id = contentIds[1];
        reqBody = unlockContentByTicketsRequestModel.getRequestBody();
        sendRequest(unlockContentByTicketsRequestModel);
        ResponseUtil.verifySkipContentSuccessResponse(responseBody, userId, 3);
    }

    @Test(dependsOnMethods = "testUnlockContentByTickets", alwaysRun = true)
    public void testConvertTickets() throws Exception {

        convertTicketsRequestModel = new ConvertTicketsRequestModel();
        convertTicketsRequestModel.user_id = userId;
        convertTicketsRequestModel.api_token = apiToken;
        convertTicketsRequestModel.tickets = "1";
        reqBody = convertTicketsRequestModel.getRequestBody();
        sendRequest(convertTicketsRequestModel);
        ResponseUtil.verifySkipContentSuccessResponse(responseBody, userId, 3);
    }

    @Test(dependsOnMethods = "testConvertTickets", alwaysRun = true)
    public void testUseTickets() throws Exception {

        useTicketsRequestModel = new UseTicketsRequestModel();
        useTicketsRequestModel.user_id = userId;
        useTicketsRequestModel.api_token = apiToken;
        useTicketsRequestModel.tickets = "1";
        reqBody = useTicketsRequestModel.getRequestBody();
        sendRequest(useTicketsRequestModel);
        ResponseUtil.verifySkipContentSuccessResponse(responseBody, userId, 3);
    }

    @Test(dependsOnMethods = "testUseTickets", alwaysRun = true)
    public void testUseTokens() throws Exception {
        useTokensRequestModel = new UseTokensRequestModel();
        useTokensRequestModel.user_id = userId;
        useTokensRequestModel.api_token = apiToken;
        useTokensRequestModel.tokens = "1";
        reqBody = useTokensRequestModel.getRequestBody();
        sendRequest(useTokensRequestModel);
        ResponseUtil.verifySkipContentSuccessResponse(responseBody, userId, 3);
    }

    @Test(dependsOnMethods = "testUseTokens", alwaysRun = true)
    public void testProductList() throws Exception {
        productListRequestModel = new ProductListRequestModel();
        productListRequestModel.user_id = userId;
        productListRequestModel.api_token = apiToken;
        productListRequestModel.platform = "1";
        productListRequestModel.type = "1";
        reqBody = productListRequestModel.getRequestBody();
        sendRequest(productListRequestModel);
        ResponseUtil.verifyProductListSuccessResponse(responseBody, userId, "1");
    }

    @Test(dependsOnMethods = "testProductList", alwaysRun = true)
    public void testSubscribedToProduct() throws Exception {
        productIds = getProductIDs();

        subscribedTorProductRequestModel = new SubscribedTorProductRequestModel();
        subscribedTorProductRequestModel.user_id = userId;
        subscribedTorProductRequestModel.api_token = apiToken;
        subscribedTorProductRequestModel.platform = "1";
        subscribedTorProductRequestModel.type = "1";
        subscribedTorProductRequestModel.number = "500";
        subscribedTorProductRequestModel.receipt = "zcxc97zxczxczxcxziy9KHJASD(79";
        subscribedTorProductRequestModel.product_id = productIds[0];
        subscribedTorProductRequestModel.amount = "1.99";
        subscribedTorProductRequestModel.currency = "USD";
        reqBody = subscribedTorProductRequestModel.getRequestBody();
        sendRequest(subscribedTorProductRequestModel);
        ResponseUtil.verifyProductListSuccessResponse(responseBody, userId,  "2");
    }

    @Test(dependsOnMethods = "testSubscribedToProduct", alwaysRun = true)
    public void testLogout() throws Exception {

        logoutRequestModel.user_id = userId;
        logoutRequestModel.api_token = getAPIToken();
        reqBody = logoutRequestModel.getRequestBody();
        sendRequest(logoutRequestModel);
        ResponseUtil.verifyLogoutSuccessResponse(responseBody);

    }

    @Test(dependsOnMethods = "testLogout", alwaysRun = true)
    public void resetTestData() throws Exception {
        resetUserRequestModel = new ResetUserRequestModel();
        resetUserRequestModel.api_token = apiToken;
        resetUserRequestModel.user_id = userId;
        reqBody = resetUserRequestModel.getRequestBody();
        sendRequest(resetUserRequestModel);
    }

    @Test(dependsOnMethods = "resetTestData", alwaysRun = true)
    public void testRetrieveFreeHomeFeed() throws Exception {

        freeHomeFeedRequestModel = new FreeHomeFeedRequestModel();
        for(int i = 1; i < 2; i++) {
            reqBody = freeHomeFeedRequestModel.getRequestBody(String.valueOf(i));
            sendRequest(freeHomeFeedRequestModel);
            ResponseUtil.verifyGetFreeHomeFeedResponse(responseBody);
        }

    }


}
