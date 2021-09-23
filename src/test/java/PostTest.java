import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static io.restassured.path.json.JsonPath.from;


public class PostTest extends BaseTest{

        /*@Before
        public void setup(){
            RestAssured.baseURI = "https://reqres.in";
            RestAssured.basePath = "/api";
            RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .build();

        }*/

        @Test
        public void loginRegisterTest(){

                    given()
                    .body("{\n" +
                            "    \"email\": \"eve.holt@reqres.in\",\n" +
                            "    \"password\": \"cityslicka\"\n" +
                            "}")
                    .post("login")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("token",notNullValue());
        }

        @Test
        public void loginTest1(){

        String tokenValue = given()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post("login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().getString("token");
                assertThat(tokenValue, equalTo("QpwL5tke4Pnpja7X4"));
         }

        @Test
        public void withoutPassword() {

                    given()
                    .body("{\n" +
                            "    \"email\": \"lili@prueba@com\"\n" +
                            "}")
                    .post("login")
                    .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("error",equalTo("Missing password"));

        }
    }

