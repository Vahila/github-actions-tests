package com.mathworks.ci;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class ResponseUtils {

    public RequestSpecification httpRequest = RestAssured.given();

    public void addHeader(String name, String value){
        httpRequest.header(name, value);
    }

    Response getResponse(Method method, String URL){
        Response response =  httpRequest.request(method, URL);
        assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204);
        return response;
    }
}
