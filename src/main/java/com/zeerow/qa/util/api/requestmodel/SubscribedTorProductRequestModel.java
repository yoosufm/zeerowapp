package com.zeerow.qa.util.api.requestmodel;

/**
 * Created by yoosuf on 6/6/2015.
 */
public class SubscribedTorProductRequestModel extends ModelBase {

    public String user_id;
    public String platform;
    public String type;
    public String api_token;
    public String language_code;
    public String number;
    public String receipt;
    public String product_id;
    public String amount;
    public String currency;

    public SubscribedTorProductRequestModel(){
        language_code = "en";
        reqURI = "payments/verifyReceiptAndSubscribe.json";
        reqMethod = "POST";
    }

    public String getRequestBody(){
        String body = "";
            body = "{\n" +
                    "  \"api_token\" : \"" + api_token + "\", \n" +
                    "  \"platform\" : \"" + platform + "\", \n" +
                    "  \"type\" : \"" + type + "\", \n" +
                    "  \"number\" : \"" + number + "\", \n" +
                    "  \"receipt\" : \"" + receipt + "\", \n" +
                    "  \"product_id\" : \"" + product_id + "\", \n" +
                    "  \"amount\" : \"" + amount + "\", \n" +
                    "  \"currency\" : \"" + currency + "\", \n" +
                    "  \"language_code\" : \"" + language_code + "\", \n" +
                    "  \"user_id\" : \"" + user_id + "\"\n" +
                    " }";
        return body;
    }
}
