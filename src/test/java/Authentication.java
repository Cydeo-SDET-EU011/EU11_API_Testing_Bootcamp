import POJO.*;
import io.restassured.http.*;
import io.restassured.internal.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class Authentication {

    /*
    401 --> you don't have access to api
    403 --> you have partial access to api
     */

    @Test
    @DisplayName("Auth post spartan")
    public void test1(){
        SingleSpartan bodyPojo = new SingleSpartan();
        bodyPojo.setGender("Male");
        bodyPojo.setName("Ayse");
        bodyPojo.setPhone(9874563214L);

        Response response = given().accept(ContentType.JSON) // for response body
                .and().auth().basic("admin","admin")
                .and().contentType(ContentType.JSON) // for post body
                .and().body(bodyPojo)
                .when().post("http://54.152.219.47:7000/api/spartans");

        response.prettyPrint();
    }
}
