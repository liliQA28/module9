package com.talent;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class SWApiTestWithRestAssured {
    @Test
    public void requestAresourcesThenLinktoReturn (){
        String body = RestAssured
                .given()
                .baseUri("https://swapi.dev/api")
                .and()
                .queryParam("format","json")
                .when()
                .get("/")
                .then()
                .log().all()
                .and().assertThat().statusCode(is(equalTo(200)))

    }
}
