package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class ProductListRequestModel extends ModelBase {

    public String user_id;
    public String platform;
    public String type;
    public String api_token;
    public String language_code;

    public ProductListRequestModel(){
        language_code = "en";
        reqURI = "payments/productList.json";
        reqMethod = "POST";
    }

    public String getRequestBody(){
        String body = "";
            body = "{\n" +
                    "  \"api_token\" : \"" + api_token + "\", \n" +
                    "  \"platform\" : \"" + platform + "\", \n" +
                    "  \"type\" : \"" + type + "\", \n" +
                    "  \"language_code\" : \"" + language_code + "\", \n" +
                    "  \"user_id\" : \"" + user_id + "\"\n" +
                    " }";
        return body;
    }
}
