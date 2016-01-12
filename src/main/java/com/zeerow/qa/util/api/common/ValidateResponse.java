package com.zeerow.qa.util.api.common;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;


public class ValidateResponse {

    public Response validateAPICallResponse(String reqMethod, String reqURI, RequestSpecification requestSpecification, ResponseSpecification responseSpec) {
        Response response = null;

        if (reqMethod.equalsIgnoreCase(Constants.GET_METHOD)) {
            response = requestSpecification
                    .expect()
                    .spec(responseSpec).given()
                    .when()
                    .get(Constants.STR_SLASH + reqURI);
        } else if (reqMethod.equalsIgnoreCase(Constants.POST_METHOD)) {
            response = requestSpecification
                    .expect()
                    .spec(responseSpec)
                    .when()
                    .post(Constants.STR_SLASH + reqURI);
        } else if (reqMethod.equalsIgnoreCase(Constants.PUT_METHOD)) {
            response = requestSpecification
                    .expect()
                    .spec(responseSpec)
                    .when()
                    .put(Constants.STR_SLASH + reqURI);
        } else if (reqMethod.equalsIgnoreCase(Constants.DELETE_METHOD)) {
            response = requestSpecification
                    .expect()
                    .spec(responseSpec)
                    .when()
                    .delete(Constants.STR_SLASH + reqURI);
        }

        return response;
    }

    public Response validateAPICallResponse(String reqMethod, String reqURI, RequestSpecification requestSpecification) {
        Response response = null;
        System.out.println("=============================== url =================  "+ Constants.STR_SLASH+ reqURI);
        requestSpecification.log();
        if (reqMethod.equalsIgnoreCase(Constants.GET_METHOD)) {
            response = requestSpecification
                    .expect()
                    .when()
                    .get(Constants.STR_SLASH + reqURI);
        } else if (reqMethod.equalsIgnoreCase(Constants.POST_METHOD)) {
            response = requestSpecification
                    .expect()
                    .when()
                    .post(Constants.STR_SLASH + reqURI);
        } else if (reqMethod.equalsIgnoreCase(Constants.PUT_METHOD)) {
            response = requestSpecification
                    .expect()
                    .when()
                    .put(Constants.STR_SLASH + reqURI);
        } else if (reqMethod.equalsIgnoreCase(Constants.DELETE_METHOD)) {
            response = requestSpecification
                    .expect()
                    .when()
                    .delete(Constants.STR_SLASH + reqURI);
        }

        return response;
    }

    public Response executionMethodSelector(String reqMethod, RequestSpecification requestSpec, String reqURI) {
        Response response = null;

        if (reqMethod.equalsIgnoreCase(Constants.GET_METHOD)) {
            response = requestSpec
                    .when()
                    .get(Constants.STR_SLASH + reqURI);
        } else if (reqMethod.equalsIgnoreCase(Constants.POST_METHOD)) {
            response = requestSpec
                    .when()
                    .post(Constants.STR_SLASH + reqURI);
        } else if (reqMethod.equalsIgnoreCase(Constants.PUT_METHOD)) {
            response = requestSpec
                    .when()
                    .put(Constants.STR_SLASH + reqURI);
        } else if (reqMethod.equalsIgnoreCase(Constants.DELETE_METHOD)) {
            response = requestSpec
                    .when()
                    .delete(Constants.STR_SLASH + reqURI);
        }

        return response;
    }
}
