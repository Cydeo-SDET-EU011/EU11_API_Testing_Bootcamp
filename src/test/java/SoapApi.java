import io.cucumber.java.ja.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.xml.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class SoapApi {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.219.47:8000";
    }

    @Test
    @DisplayName("Soap api")
    public void test1(){
        Response response = given().accept(ContentType.XML)
                .when().get("/api/spartans");

        //response.prettyPrint();
        System.out.println(response.statusCode()); //200

        XmlPath xmlPath = response.xmlPath();
        String gender1 = xmlPath.getString("List.item[0].gender");
        System.out.println("gender1 = " + gender1);


    }
}
