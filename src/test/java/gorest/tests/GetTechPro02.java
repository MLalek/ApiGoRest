package gorest.tests;

import gorest.utilities.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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

    @Test // burada path i kullandik  /* JSONPATH: is used to navigate in Json data
    //for multiple data use query param
    public void get01() {
       spec01.pathParam("id", 123); //id si 123 olani cagir
        response = given().
                spec(spec01).
                when().
                get("/{id}"); // burada /{id} olmazsa gelmiyor
        response.prettyPrint();
        //response.prettyPeek();

        //1 task
        JsonPath json = response.jsonPath();

        System.out.println(json.getString("firstname")); //"Susan"
        System.out.println(json.getInt("total price")); //123,   getInt because you call int / number
        System.out.println(json.getBoolean("depositpaid")); //false, get boolean
        System.out.println(json.getString("bookingdates.checkin")); //"2020-12-13"

        //2 task
        response.
                then().
                assertThat().
                statusCode(200);

        Assert.assertEquals(response.getHeader("Server"), "cloudflare");

    }


    @Test // burada query param i kullandik.  /* Query param is use to filter*/ syntax is  ?key=value / "&" is to multiple data
          // birden fazla degeri cagirdik
    public void get02(){
//        spec02.queryParam("firstname", "Jim"); // Isme/firstname  gore ariyoruz
//        spec02.queryParam("totalprice", 2);
        spec02.queryParam("firstname", "Susan", "totalprice",2); // Bu kisa yazilim.
                                                                                          // To remove repetation use "query param"
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

        Assert.assertTrue(response.asString().contains("Susan"));

    }
}

