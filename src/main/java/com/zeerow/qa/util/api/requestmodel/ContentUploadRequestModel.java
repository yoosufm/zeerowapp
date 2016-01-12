package com.zeerow.qa.util.api.requestmodel;

import com.zeerow.qa.util.api.common.Constants;

/**
 * Created by yoosuf on 7/11/2015.
 */
public class ContentUploadRequestModel extends  ModelBase {

    public String file_name;
    public String file_content;
    public String file_overwrite;


    public ContentUploadRequestModel(){
        reqURI = "s3api.json";
        reqMethod = "POST";
        file_content = Constants.FILE_CONTENT;
        file_name = "test.jgg";
        file_overwrite = "false";
    }

    public String getRequestBody(){
       return getRequestBody(file_name, file_content, file_overwrite);
    }

    public String getRequestBody(String filName, String fileContent, String fileOverWrite){

        String body = "{\n"+
                "\"file_name\": \""+file_name+ "\", \n" +
                "\"file_content\": \""+file_content+ "\",\n" +
                "\"file_overwrite\" :\""+file_overwrite+"\"\n" +
                "}";
        return body;
    }
}
