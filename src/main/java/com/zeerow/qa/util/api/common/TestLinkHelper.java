package com.zeerow.qa.util.api.common;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.Platform;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class TestLinkHelper {
	
	static URL URL;
    Integer testCaseId;
    Integer testCaseExternalId;
    Integer testPlanId;
    ExecutionStatus status;
    Integer buildId;
    String notes;
    Boolean guess;
    String bugId;
    Integer platformId;
    String platformName;
    Map<String,String> customFields;
    Boolean overwrite;
	public static TestLinkAPI testLinkAPI;
    String testLinkUrl = System.getProperty("testlink.url", "http://52.4.94.111/testlink/lib/api/xmlrpc/v1/xmlrpc.php");
    String apiKey = System.getProperty("testlink.api.key", "ee044c8c9c73b5f7f8fd2627c7c4a612");
    String project = System.getProperty("testlink.project", "Coinbates");
    String testPlan = System.getProperty("testlink.test.plan", "Progression");
    String buildName = System.getProperty("testlink.build.name", "phase-1");
    String projectPrefix = System.getProperty("testlink.prefix", "CBT");

    public TestLinkHelper(){
        try {
            URL = new URL(testLinkUrl);
            testLinkAPI = new TestLinkAPI(URL, apiKey);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassedTestCase(String testCaseID, ExecutionStatus status, String note){
        testCaseID = projectPrefix + "-" + testCaseID;
        TestCase tc = testLinkAPI.getTestCaseByExternalId(testCaseID, 1);
        testCaseId = tc.getId();
        testCaseExternalId = Integer.valueOf(tc.getFullExternalId().split("-")[1]);
        testPlanId = testLinkAPI.getTestPlanByName(testPlan,project).getId();
        this.status =status ;
        Build build = testLinkAPI.getLatestBuildForTestPlan(testPlanId);
        buildId = build.getId();
        buildName = build.getName();
        notes = note;
        guess = false;
        bugId = "";
        Platform platform = testLinkAPI.getTestPlanPlatforms(testPlanId)[0];
       platformId = platform.getId();
        platformName = platform.getName();
        overwrite = true;

        testLinkAPI.reportTCResult(testCaseId, testCaseExternalId, testPlanId, status, buildId, buildName, notes, guess, bugId, platformId, platformName, customFields, overwrite );
        System.out.println();
    }
	

		
	

	
}
