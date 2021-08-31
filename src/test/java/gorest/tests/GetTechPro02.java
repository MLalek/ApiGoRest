package gorest.tests;

import gorest.utilities.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class GetTechPro02 extends TestBase {

    Response response;
         // Assert that; "Server" in Headers should be "cloudflare",
         // "userid" should be 7,
         // title should be " esse et quis iste est earum aut impedit",
         // completed should be false

    @Test // burada path i kullandik
    public void get01() {
       spec01.pathParam("id", 123); //id si 123 olani cagir
        response = given().
                spec(spec01).
                when().
                get("/{id}"); // burada /{id} olmazsa gelmiyor
        response.prettyPrint();
        //response.prettyPeek();

        response.
                then().
                assertThat().
                statusCode(200);
        Assert.assertEquals(response.getHeader("Server"), "cloudflare");

    }


    @Test // burada query param i kullandik
    public void get02(){
        spec02.queryParam("firstname", "Jim"); // Isme/firstname  gore ariyoruz
        response = given().
                spec(spec02).
                when().
                get();//get("?firstname=Jim&lastname=Jones") bu code un icinde olmasi karisikliga sebep
                      // olacagi icin ilk satirda query param da bu bilgiye ulasiyoruz.
                      // print: "bookingid": 10  u veya 10 numarali id de boyle bir bilgi yoksa [] bos geliyor
        response.prettyPrint();

        response = given().
                spec(spec02).
                when().
                get("/10"); // burda yukarda sorguladigimizda 10 verince 10 u cagirip assert etmis  oluyoruz ve tum liste geliyor
                                // print:10 numarali id nin tum bilgileri geliyor
        response.prettyPrint();

    }
}

