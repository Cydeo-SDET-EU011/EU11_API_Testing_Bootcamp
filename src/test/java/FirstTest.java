import io.cucumber.gherkin.internal.com.eclipsesource.json.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class FirstTest {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .when().get("http://54.152.219.47:8000/api/spartans/1");

        System.out.println(response.statusCode()); // 200
        System.out.println(response.contentType());
        System.out.println(response.header("Date"));
        response.prettyPrint();
    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",25)
                .when().get("/api/spartans/{id}");

        Assertions.assertEquals(200,response.statusCode());
    }

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender","Male")
                .and().queryParam("nameContains","er")
                .when().get("/api/spartans/search");

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("gender","Male");
        queryParams.put("nameContains","er");

        Response response1 = given().accept(ContentType.JSON)
                .and().queryParams(queryParams)
                .when().get("/api/spartans/search");

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals(200,response1.statusCode());
    }


    @Test
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/1");

        response.prettyPrint();
        /*
        1. path
        2. jsonpath
        3. deserialization
        4. pojo
         */

        // response.path method
        int id = response.path("id");
        String name = response.path("name");
        System.out.println("id = " + id);
        System.out.println("name = " + name);

        // json path method
        JsonPath jsonPath = response.jsonPath();
        String nameJson = jsonPath.getString("name");
        int idJson = jsonPath.getInt("id");
        System.out.println("nameJson = " + nameJson);
        System.out.println("idJson = " + idJson);
    }



}
