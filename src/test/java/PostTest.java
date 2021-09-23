import model.PostUserRequest;
import model.PostUserResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class PostTest extends BaseTest{

        @Test
        public void serverResponseTest(){

            PostUserRequest user = new PostUserRequest();
            user.setEmail("eve.holt@reqres.in");
            user.setPassword("cityslicka");

            PostUserResponse userResponse =
                    given()
                    .when()
                    .body(user)
                    .post("login")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(PostUserResponse.class);
        }
        @Test
        public void responseTokenTest() {

        PostUserRequest user = new PostUserRequest();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        PostUserResponse userResponse =
                given()
                        .when()
                        .body(user)
                        .post("login")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .body("token",notNullValue())
                        .extract()
                        .as(PostUserResponse.class);

        assertThat(userResponse.getToken(), equalTo("QpwL5tke4Pnpja7X4"));

        }

        @Test
        public void userBodyResponseTest() {

        PostUserRequest user = new PostUserRequest();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        PostUserResponse userResponse =
                given()
                        .when()
                        .body(user)
                        .post("login")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .contentType(equalTo("application/json; charset=utf-8"))
                        .extract()
                        .body()
                        .as(PostUserResponse.class);
        }
    }

