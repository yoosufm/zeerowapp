package com.zeerow.qa.util.api.requestmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class ActivateUserRequestModel extends ModelBase {

    public ActivateUserRequestModel(){

    }

    public ActivateUserRequestModel(String userId, String apiToken){
        reqURI = "users/activateAccount/"+ userId +"/"+ apiToken;
        reqMethod = "POST";
    }

    public void activateAccount(String url){
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();

    }
}
