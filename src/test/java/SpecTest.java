import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class SpecTest {

    RequestSpecification reqSpec = given().accept(ContentType.JSON);
    ResponseSpecification resSpec = reqSpec.expect().statusCode(200);
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";

    }

    @Test
    public void test1(){
        Response response = given().spec(reqSpec)
                .when().get("/api/spartans");
    }


}
