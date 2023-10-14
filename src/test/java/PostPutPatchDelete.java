import POJO.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class PostPutPatchDelete {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("Post")
    public void test1(){
        /*
        3 ways to provide post body
        String/json
        Map
        POJO
         */

        String bodyString = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Ahmet\",\n" +
                "  \"phone\": 4569871235\n" +
                "}";

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("gender","Male");
        bodyMap.put("name","Mehmet");
        bodyMap.put("phone",9874563215L);

        SingleSpartan bodyPojo = new SingleSpartan();
        bodyPojo.setGender("Male");
        bodyPojo.setName("Ayse");
        bodyPojo.setPhone(9874563214L);

        Response response = given().accept(ContentType.JSON) // for response body
                .and().contentType(ContentType.JSON) // for post body
                .and().body(bodyMap)
                .when().post("/api/spartans");


        assertThat(201,equalTo(response.statusCode()));

        ///  curl
    }
}
