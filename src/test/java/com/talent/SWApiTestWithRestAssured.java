package com.talent;

import com.talent.bind.BaseApiResponse;
import io.restassured.RestAssured;
import org.junit.Before;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.bind.SchemaOutputResolver;

import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.Matchers.*;

public class SWApiTestWithRestAssured {
    @Before
    public void requestBeforeSwapi(){
        RestAssured.baseURI="https://swapi.dev/api";
    }
    @Test
    public void getTestSwap(){

    RestAssured
        .given()
        .log().all()
        .get("https://swapi.dev/api/planets/1/")
        .then().log()
        .ifError()
        .statusCode(is(equalTo(200)))
        .body("population",equalTo("200000")).log().all();

    }

}
