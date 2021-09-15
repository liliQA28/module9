package com.talent;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.RequestLogSpecification;
import org.junit.runner.Request;

public abstract class BaseApi {

    protected final static String BASE_URL= "https://swapi.dev.api";
    protected static Response getRequestResponse(String request){
        RestAssured.baseURI= BASE_URL;
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest.get(request);

    }
}
