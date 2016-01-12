package com.zeerow.qa.util.api.common;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import static org.hamcrest.core.IsEqual.equalTo;


public class RequestSpecUtil {
    public static final String STR_SLASH = "/";
    public static final String STR_DBL_SLASH = "//";
    public static final String STR_COLON = ":";
    public static final String STR_COMMA = ",";
    public static final String STR_DOT = ".";
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    public static final String PUT_METHOD = "PUT";
    public static final String DELETE_METHOD = "DELETE";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String CONTENT_MD5_HEADER = "content-md5";
    public static final String NA = "N/A";
    public static final String MWS = "MWS";
    public static final String BASIC = "BASIC";
    public static final String OTHER_HEADERS_HEADER_FIELD = "OTHER HEADERS";
    public static final String STRING_TYPE = "String";
    public static final String BOOLEAN_TYPE = "Boolean";
    public static final String INTEGER_TYPE = "Integer";
    public static final String DOUBLE_TYPE = "Double";
    public static final String FLOAT_TYPE = "Float";
    public static final String LONG_TYPE = "Long";

    public static final String LOCATOR = "locator";
    public static final String TYPE = "type";
    public static final String VALUE = "value";
    public static final String PARAM = "param";
    public static final String NON_JSON_FULL_BODY = "nonJSONFullBody";

    public static final String TRUE = "true";
    public static final String FALSE = "false";

    private static final int VALUE_OF_20_BITS = 1048576;
    private static final int NO_OF_BITS_IN_MILLIS = 43;
    private static final Random SEED = new Random();


//    private static final Logger logger = Logger.getLogger(RequestSpecUtil.class);

    public static RequestSpecification getRequestSpec(String reqMethod, String authType, String username, String password, JSONObject reqHeader, String reqURI, String fullRespBody, String reqBody) {

        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();

        Iterator iter = reqHeader.keys();

        while (iter.hasNext()) {
            String key = (String) iter.next();
            if (key.equalsIgnoreCase(OTHER_HEADERS_HEADER_FIELD)) {
                try {
                    if (!reqHeader.getString(key).isEmpty()) {
                        JSONObject otherHeaders = getOtherHeaders(reqHeader.getString(key));
                        Iterator iter2 = otherHeaders.keys();
                        while (iter2.hasNext()) {
                            String key2 = (String) iter2.next();
                            if (!otherHeaders.getString(key2).isEmpty()) {
                                reqSpecBuilder.addHeader(key2, otherHeaders.getString(key2));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            } else {
                try {
                    if (!reqHeader.getString(key).isEmpty()) {

                        String headerVal = reqHeader.getString(key);

                        reqSpecBuilder.addHeader(key, headerVal);
                    }
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

        if (authType.equalsIgnoreCase(MWS)) {

            String fullAuthHeader = null;

            String reqBodyMD5 = null;

            String requestUri = null;

            if (reqURI.contains("?")) {
                requestUri = RestAssured.basePath + STR_SLASH + reqURI.split("\\?")[0];
            } else {
                requestUri = RestAssured.basePath + STR_SLASH + reqURI;
            }

//            MwsTokenGeneration generateToken = new MwsTokenGeneration();

            reqBodyMD5 = getContentMd5(reqBody);

            try {
                fullAuthHeader = generateMwsAuthHeader(username, password, reqMethod, requestUri, reqHeader.getString("Content-type"), reqBodyMD5);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            reqSpecBuilder.addHeader(AUTHORIZATION_HEADER, fullAuthHeader);
            reqSpecBuilder.addHeader(CONTENT_MD5_HEADER, reqBodyMD5);

        }

        if (reqBody !=  null){
            reqSpecBuilder.setBody(reqBody);
        }

        RequestSpecification requestSpec = reqSpecBuilder.build();

        return requestSpec;
    }


    public static Map<String, String> getMWSHeader(String reqMethod, String authType, String username, String password, JSONObject reqHeader, String reqURI, String reqBody) {


        Map<String, String> headers = new HashMap<String, String>();

        Iterator iter = reqHeader.keys();

        while (iter.hasNext()) {
            String key = (String) iter.next();
            if (key.equalsIgnoreCase(OTHER_HEADERS_HEADER_FIELD)) {
                try {
                    if (!reqHeader.getString(key).isEmpty()) {
                        JSONObject otherHeaders = getOtherHeaders(reqHeader.getString(key));
                        Iterator iter2 = otherHeaders.keys();
                        while (iter2.hasNext()) {
                            String key2 = (String) iter2.next();
                            if (!otherHeaders.getString(key2).isEmpty()) {
                                headers.put(key2, otherHeaders.getString(key2));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            } else {
                try {
                    if (!reqHeader.getString(key).isEmpty()) {

                        String headerVal = reqHeader.getString(key);

                        headers.put(key, headerVal);
                    }
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

        if (authType.equalsIgnoreCase(MWS)) {

            String fullAuthHeader = null;

            String reqBodyMD5 = null;

            String requestUri = null;

            if (reqURI.contains("?")) {
                requestUri = RestAssured.basePath + STR_SLASH + reqURI.split("\\?")[0];
            } else {
                requestUri = RestAssured.basePath + STR_SLASH + reqURI;
            }

//            MwsTokenGeneration generateToken = new MwsTokenGeneration();

            reqBodyMD5 = getContentMd5(reqBody);

            try {
                fullAuthHeader = generateMwsAuthHeader(username, password, reqMethod, requestUri, reqHeader.getString("Content-type"), reqBodyMD5);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            headers.put(AUTHORIZATION_HEADER, fullAuthHeader);
            headers.put(CONTENT_MD5_HEADER, reqBodyMD5);

        }
        return headers;
    }

    private static JSONObject getOtherHeaders(String otherHeaders) {
        String strArr[] = otherHeaders.split(";");

        JSONObject jObjOtherHeaders = new JSONObject();

        for (int i = 0; i < strArr.length; i++) {
            try {
                jObjOtherHeaders.put(strArr[i].split(":")[0], strArr[i].split(":")[1]);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return jObjOtherHeaders;
    }


    public static ResponseSpecification getResponseSpec(String respStatus, JSONObject respBody) {

        //Response Specification
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();

        if (!respStatus.isEmpty()) {
            responseSpecBuilder.expectStatusCode(Integer.parseInt(respStatus));
        }

        Iterator iter = respBody.keys();

        while (iter.hasNext()) {
            String key = (String) iter.next();
            try {
                JSONObject jsonObj = respBody.getJSONObject(key);

                if (jsonObj.length() != 0) {

                    String locator = jsonObj.getString(LOCATOR);
                    String type = jsonObj.getString(TYPE);
                    String value = jsonObj.getString(VALUE);

                    if (type.equalsIgnoreCase(STRING_TYPE)) {
                        responseSpecBuilder.expectBody(locator, equalTo(value));
                    } else if (type.equalsIgnoreCase(BOOLEAN_TYPE)) {
                        if (value.equalsIgnoreCase(TRUE)) {
                            responseSpecBuilder.expectBody(locator, equalTo(true));
                        } else if (value.equalsIgnoreCase(FALSE)) {
                            responseSpecBuilder.expectBody(locator, equalTo(false));
                        }
                    } else if (type.equalsIgnoreCase(INTEGER_TYPE)) {
                        responseSpecBuilder.expectBody(locator, equalTo(Integer.parseInt(value)));
                    } else if (type.equalsIgnoreCase(FLOAT_TYPE)) {
                        responseSpecBuilder.expectBody(locator, equalTo(Float.parseFloat(value)));
                    } else if (type.equalsIgnoreCase(DOUBLE_TYPE)) {
                        responseSpecBuilder.expectBody(locator, equalTo(Double.parseDouble(value)));
                    } else if (type.equalsIgnoreCase(LONG_TYPE)) {
                        responseSpecBuilder.expectBody(locator, equalTo(Long.parseLong(value)));
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        ResponseSpecification responseSpec = responseSpecBuilder.build();

        return responseSpec;
    }


    public static String generateMwsAuthHeader(String username,
                                               String password,
                                               String httpMethod,
                                               String requestUri,
                                               String contentType,
                                               String contentMd5) throws IOException {
        HttpHeaderTO httpHeaderTO = new HttpHeaderTO();
        httpHeaderTO.setContentType(contentType);
        httpHeaderTO.setMethod(httpMethod);
        httpHeaderTO.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));
        httpHeaderTO.setPath(requestUri);
        httpHeaderTO.setContentMD5(contentMd5);

        try {
            String hashedPassword = SignatureUtil.sign(password, username);
            return SignatureUtil.generateClientAuthorizationKey(username, hashedPassword, httpHeaderTO);
        } catch (Exception e) {
            throw new IOException("An exception occurred while computing signature", e);
        }
    }

    public static String getContentMd5(String content) {
        if(content == null)content = "";
        return (content != null) ? DigestUtils.md5Hex(content) : null;
    }


    /**
     * The method to generate the userId
     *
     * @return
     */
    public static long generateRandomId() {
        Random randNumGen = new Random(SEED.nextLong());
        long mostSig20Bits = (long) randNumGen.nextInt(VALUE_OF_20_BITS);
        long leastSig43Bits = System.currentTimeMillis();
        long uniqueId = (mostSig20Bits << NO_OF_BITS_IN_MILLIS) + leastSig43Bits;

        if (String.valueOf(uniqueId).length() < 19) {
            int remainder = 19 - String.valueOf(uniqueId).length();

            uniqueId = Long.parseLong(String.valueOf(uniqueId) + String.valueOf(uniqueId).substring(0, remainder));
        }

        return uniqueId;
    }

}