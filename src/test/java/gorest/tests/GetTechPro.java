package gorest.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class GetTechPro {

    Response response;
    @Test
    public void getMethod01(){
        response = given().
                when().
                get("https://restful-booker.herokuapp.com/booking/1001");

        response.prettyPrint();
        response.
                then().
                assertThat().
                statusCode(404);

        Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertFalse(response.asString().contains("Mehmet"));
    }


        @Test
        public void get2(){
            response = given().
                    when().
                    get("https://restful-booker.herokuapp.com/booking");

            response.prettyPrint();
            response.
                    then().
                    assertThat().
                    statusLine("HTTP/1.1 200 OK");

            //How to get all Headers data
                    System.out.println(response.getHeaders());
                    Assert.assertNull(response.getHeader("Expect-CT"));
                    System.out.println(response.getHeader("Expect-CT"));//null

            // How to get a specific Header
            System.out.println(response.header("Server"));
            Assert.assertEquals(response.header("Via"),"1.1 vegur");
        }

        @Test
        public void get030405(){
            response = given().
                    when().
                    get("https://restful-booker.herokuapp.com/booking/1");
            response.prettyPrint();

            response.
                    then().
                    assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    //import org.hamcrest.Matchers; Matchers icin bu import kullaniliyor
//                    body("firstname", Matchers.equalTo("Mary")).// bu da assert etme methodu
//                    body("lastname", Matchers.equalTo("Smith")).
//                    body("totalprice", Matchers.equalTo(221)).//integer
//                    body("depositpaid", Matchers.equalTo(false)).//boolean
//                    body("bookingdates.checkin",Matchers.equalTo("2019-06-17")).
//                    body("bookingdates.checkout",Matchers.equalTo("2019-06-28")).
//                    body("additionalneeds", Matchers.equalTo("Breakfast"));

                    // import static org.hamcrest.Matchers.*; bunu ekledik
                    // equalTo dan onceki Matchers lari sildik
                    // body leri sildik
                    body("firstname", equalTo("Mary"),// bu da assert etme methodu
                    "lastname", equalTo("Smith"),
                    "totalprice", equalTo(221),//integer
                    "depositpaid", equalTo(false),//boolean
                    "bookingdates.checkin",equalTo("2019-06-17"),
                    "bookingdates.checkout",equalTo("2019-06-28"),
                    "additionalneeds", equalTo("Breakfast"));
        }

        @Test
    public void Get06(){
        //How to assert number of data (kac tane calisan var id uzerinden giderek kac tane id varsa onu saydik)
        //Assert is a specific data in is response body or not ("Ashton Cox u bul)
        // How to assert multiple data is in response body or not (yaşa göre 21, 23, 61 yaş varmı bak)
            response = given().
                    when().
                    get("https://dummy.restapiexample.com/api/v1/employees");
            response.prettyPrint();

            response.
                    then().
                    assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("data.id", Matchers.hasSize(24),
                            "data.employee_name", hasItem("Ashton Cox"));
                              //"data.employee_age", hasItems("61", "63", "66"));// bu hata veriyor

        }


            @Test
    public void Get07(){
                response = given().
                        when().
                        get("https://dummy.restapiexample.com/api/v1/employees");
                response.prettyPrint();

                response.
                        then().
                        assertThat().
                        statusCode(200).
                        contentType(ContentType.JSON).
            }

}
