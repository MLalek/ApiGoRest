package gorest.tests;

import gorest.utilities.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class GetTechPro02 extends TestBase {

    Response response;
    @Test // Assert that; "Server" in Headers should be "cloudflare",
         // "userid" should be 7,
         // title should be " esse et quis iste est earum aut impedit",
         // completed should be false
    public void get01() {
       spec01.pathParam("id", 123);
        response = given().
                spec(spec01).
                when().
                get("/{id}");
        response.prettyPrint();
        response.prettyPeek();

        response.
                then().
                assertThat().
                statusCode(200);
        Assert.assertEquals(response.getHeader("Server"), "cloudflare");

    }
}

