package com.talent;



import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.bind.BaseApiResponse;
import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class SWApiTestWithRestAssured {

    @Test
    public void whenRequestingAResourceThenLinksToResourcesMustBeReturned() {


        RestAssured
                .given()
                .queryParam("format", "json")
                .config(config().logConfig(logConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails()))
                .when().get("https://swapi.dev/api/people/2/")
                .then().assertThat()
                .statusCode(is(equalTo(200))).assertThat().body("name",equalTo("Luke Skywalker")).log().all();



    }
    @Test
    public void whenTypeString() {




        RestAssured
                .given()
                .queryParam("format", "json")
                .config(config().logConfig(logConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails()))
                .when().get("https://swapi.dev/api/people/2/")
                .then().assertThat()
                .statusCode(is(equalTo(200)))
                .assertThat()
                .body("homeworld",equalTo("https://swapi.dev/api/planets/1/"))
                .log()
                .all();
    }
    @Test
    public void whenTypeList() {


        String response = get("https://swapi.dev/api/people/").asString();

        List<String> films = from(response).getList("results.films[0]");
        System.out.println(films);



    }
    @Test
    public void whenTypeListCondition() {


        List<String> list=new ArrayList<String>();
        Response response = get("https://swapi.dev/api/people/");
        JsonPath jsonPathEvaluator = response.jsonPath();
        list = jsonPathEvaluator.get("results.films");
        assertThat(list.size(), is(10));
    }

    @Test
    public void whenTypeIntCondition() {


        List<String> list=new ArrayList<String>();
        Response response = get("https://swapi.dev/api/people/");
        JsonPath jsonPathEvaluator = response.jsonPath();
        list = jsonPathEvaluator.get("results.films[3]");
        assertThat(list.size(), is(4));
    }
    @Test

        public void getClimate() {


            RestAssured
                    .given()
                    .queryParam("format", "json")
                    .config(config().logConfig(logConfig()
                            .enableLoggingOfRequestAndResponseIfValidationFails()))
                    .when().get("https://swapi.dev/api/planets/5/")
                    .then().assertThat()
                    .statusCode(is(equalTo(200))).assertThat().body("climate",equalTo("murky")).log().all();


        }
}