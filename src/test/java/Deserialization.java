import POJO.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;

public class Deserialization {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("Deserialization to Map")
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 25)
                .when().get("/api/spartans/{id}");

        Map<String, Object> responseMap = response.as(Map.class);
        System.out.println(responseMap);
        Object id = responseMap.get("id");
        System.out.println(id.toString());
    }

    @Test
    @DisplayName("Deserialization to list")
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        List<Map<String, Object>> responseList = response.as(List.class);
        System.out.println(responseList);
        Object name = responseList.get(1).get("name");
        System.out.println("name = " + name);
    }

    @Test
    @DisplayName("POJO")
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 25)
                .when().get("/api/spartans/{id}");

        SingleSpartan spartan25 = response.as(SingleSpartan.class);
        System.out.println(spartan25);

    }

    @Test
    @DisplayName("POJO")
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender","Male")
                .and().queryParam("nameContains","er")
                .when().get("/api/spartans/search");

        SearchSpartan maleSpartan = response.as(SearchSpartan.class);
        System.out.println(maleSpartan);
    }

}
