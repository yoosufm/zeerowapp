package com.zeerow.qa.util.api.requestmodel;

import com.zeerow.qa.util.api.common.Constants;

/**
 * Created by yoosuf on 6/30/2015.
 */
public class ModelBase {
    public String reqURI = "";
    public String reqMethod = "";
    public String getUserType(String userType){
        if(userType.equalsIgnoreCase(Constants.USER_TYPE_FACEBOOK)){
            return "2";
        }else if(userType.equalsIgnoreCase(Constants.USER_TYPE_EMAIL)){
            return "1";
        }else if(userType.equalsIgnoreCase(Constants.USER_TYPE_TWITTER)){
            return "3";
        }else if(userType.equalsIgnoreCase(Constants.USER_TYPE_SINA_WEIBO)){
            return "4";
        }else if(userType.equalsIgnoreCase(Constants.USER_TYPE_TENCENT_WEIBO)){
            return "5";
        }
        return "1";

    }
}
