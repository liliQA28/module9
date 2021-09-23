package request;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import model.*;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static java.util.logging.LogManager.getLogManager;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Requests1 {

    @Before
    public void setup() {
        // logger.info("Start configuration");
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
        // logger.info("Successfully configuration");

    }


    @Test
    public void getALlUserTest() {

        String response = given()
                .when()
                .get("users?page=2").then().extract().body().asString();

        int page = from(response).get("page");
        int totalPage = from(response).get("total_pages");
        int idFirstUser = from(response).get("data[0].id");

        System.out.println("1.Page"+page);
        System.out.println("2.totalPage"+totalPage);
        System.out.println("3.idFirst"+idFirstUser);

        List<Map> userWhitIdGreather = from(response).get("data.findAll{user-> user.id > 10}");
        System.out.println("\nAQUII LILI"+userWhitIdGreather);
        String number1 = userWhitIdGreather.get(0).get("email").toString();
        System.out.println("\nAQUII LILI: "+number1);

        List<Map> user = from(response).get("data.findAll{user-> user.id >10 && user.last_name=='Howell'}");
        String id = user.get(0).get("first_name").toString();
        System.out.println("\nAQUII LILI: "+id);

    }

    @Test
    public void createUserTest(){
        String response = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("users")
                .then().extract().body().asString();
        User nameUser = from(response).getObject("", User.class);
        System.out.println("AQUI---->"+nameUser.getName());
        System.out.println("AQUI---->"+nameUser.getId());
        System.out.println("AQUI---->"+nameUser.getJob());
        System.out.println("AQUI---->"+nameUser.getCreatedAt());


    }

    @Test
    public void userLoginTest() {
        String response = given()
                .when()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post("login")
                .then().extract().body().asString();
        UserLoginResponse nameUser = from(response).getObject("", UserLoginResponse.class);

        System.out.println("------------------------------------");
        System.out.println("Token: " + nameUser.getToken());
    }

    @Test
    public void updateDataUserTest() {
        String response = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .put("users/2")
                .then().extract().body().asString();
        UpdateInformationUser nameUser = from(response).getObject("", UpdateInformationUser.class);

        System.out.println("------------------------------------");
        System.out.println("Name: " + nameUser.getName());
        System.out.println("Job: " + nameUser.getJob());
        System.out.println("UpdateDate: " + nameUser.getUpdatedAt());

    }

    @Test
    public void userLoginTestS() {

        UserLoginRequest user = new UserLoginRequest();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        UserLoginResponse userResponse =
                given()
                .when()
                .body(user)
                .post("login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(equalTo("application/json; charset=utf-8"))
                .extract()
                .body()
               .as(UserLoginResponse.class);

        assertThat(userResponse.getToken(), equalTo("QpwL5tke4Pnpja7X4"));

    }

    @Test
    public void userUpdateTestS() {

        UpdateUserRequest user = new UpdateUserRequest();
        user.setName("morpheus");
        user.setJob("zion resident");

        UpdateUserResponse userResponse =
                  given()
                 .when()
                 .body(user)
                 .put("users/2")
                 .then()
                 .statusCode(HttpStatus.SC_OK)
                 .contentType(equalTo("application/json; charset=utf-8"))
                 .extract()
                 .body()
                 .as(UpdateUserResponse.class);

        assertThat(userResponse.getName(), equalTo("morpheus"));
        assertThat(userResponse.getJob(), equalTo("zion resident"));

    }
}

