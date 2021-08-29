package gorest.tests;

import gorest.utilities.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class GetTechPro02 extends TestBase {

    Response response;
    @Test //Assert that "userid" should be 7, title should be " ...", completed should be false
    public void get01() {
       //spec01.pathParam("id", 123);
        response = given().
                spec(spec01).
                when().
                get();
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);
    }
}

