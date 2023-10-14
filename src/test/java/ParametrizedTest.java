import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParametrizedTest {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    /*
    value source
    method source
    csv source
    csv file source
     */

    @ParameterizedTest
    @DisplayName("value source")
    @ValueSource(ints = {10,20,30})
    public void test1(int id){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/" + id);
        response.prettyPrint();
    }

    public static List<String> getNames(){
        List<String> result = Arrays.asList("va","ma","ha");
        return result;
    }


    @ParameterizedTest
    @DisplayName("Method source")
    @MethodSource("getNames")
    public void test2(String name){
        Response responseMap = given().accept(ContentType.JSON)
                .and().queryParam("nameContains",name)
                .when().get("/api/spartans/search");

        responseMap.prettyPrint();
    }


    @ParameterizedTest
    @DisplayName("CSV source")
    @CsvSource({
            "10,Lorenza",
            "15,Meta",
            "85,Ingamar"
    })
    public void test3(int id, String name){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .when().get("/api/spartans/{id}");

        assertThat(response.path("name"),is(name));
        response.prettyPrint();
    }

    @ParameterizedTest
    @DisplayName("CSV file source")
    @CsvFileSource(resources = "/SpartanDataPOST.csv",numLinesToSkip = 1)
    public void test4(String name, String gender, Long phone){
        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("name",name);
        bodyMap.put("gender",gender);
        bodyMap.put("phone",phone);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(bodyMap)
                .when().post("/api/spartans");

        response.prettyPrint();
        String msg = "A Spartan is Born!";
        Assertions.assertEquals(msg,response.path("success"));


    }

}
