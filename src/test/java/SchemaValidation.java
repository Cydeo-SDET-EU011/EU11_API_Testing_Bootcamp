import io.restassured.*;
import io.restassured.http.*;
import io.restassured.module.jsv.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class SchemaValidation {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("Schema Validation")
    public  void test1(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/1")
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("singleSpartanSchema.json"))
                .extract().response();


    }
}
