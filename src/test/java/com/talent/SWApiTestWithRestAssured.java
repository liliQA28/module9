package com.talent;

import com.talent.bind.BaseApiResponse;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import javax.xml.bind.SchemaOutputResolver;

import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.Matchers.*;

public class SWApiTestWithRestAssured {
    @Test
    public void whenRequestingAResourceThenLinksToResourcesMustBeReturned (){

        RestAssured
                .given()
                .queryParam("format", "json")
                .config(config().logConfig(logConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails()))
                .when().get("https://swapi.dev/api/planets/?format=json")
                .then().assertThat()
                .statusCode(is(equalTo(200)));



    }
}
