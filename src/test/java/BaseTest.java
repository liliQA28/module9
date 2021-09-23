import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTest {

    private static final Logger logger= LogManager.getLogger(BaseTest.class);

    @BeforeClass
    public static void setup() {
        logger.info("Start configuration");
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
         logger.info("Successfully configuration");
    }
    public static RequestSpecification defaultRequestSpecification() {
        logger.info("RequestSpecification");
        List<Filter> filters = new ArrayList<>();
        filters.add(new RequestLoggingFilter());
        filters.add(new ResponseLoggingFilter());

        return new RequestSpecBuilder().setBaseUri("https://reqres.in")
                .setBasePath("/api")
                .addFilters(filters)
                .setContentType(ContentType.JSON).build();
    }
}
