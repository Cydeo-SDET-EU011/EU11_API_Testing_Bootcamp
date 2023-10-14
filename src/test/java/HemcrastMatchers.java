import io.restassured.*;
import io.restassured.http.*;
import org.hamcrest.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class HemcrastMatchers {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    public void test1() {
        MatcherAssert.assertThat(5, is(5));

        given().accept(ContentType.JSON)
                .when().get("/api/spartans/1")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().body("id", is(1),
                        "name", equalTo("Meade"),
                        "gender", not("Female"),
                        "phone", is(not(52)));
    }
}
