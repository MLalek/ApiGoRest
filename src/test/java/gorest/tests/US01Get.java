package gorest.tests;

import gorest.utilities.ConfigurationReader;
import gorest.utilities.ReusableMethods;
import gorest.utilities.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertTrue;

public class US01Get extends TestBase {

    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;
    int json_code;
    int json_allPages;
    int json_allIds;
    List<Integer> json_idList;
    List<String> json_name_List;
    List<String> json_gender_List;

    //Burada obje olusturuyorsun List veya int objeleri


    @BeforeMethod //TC_0101 status code assertion
    public void setup() {

       ReusableMethods.getResponse(endPoint);

//        response.prettyPrint();
        // response.prettyPeek();
        json = response.jsonPath();
        json_code = json.getInt("code");
        json_allPages = json.getInt("meta.pagination.pages");
        json_idList = json.getList("data.id");
//       json_name_List = json.getList("data.name");
        json_gender_List = json.getList("data.gender");
        //yukardaki objelere değer atadık
        //RestAssured.baseURI = ConfigurationReader.get("goRest.uri");

    }


    @Test //id unique assertion
    public void TC06() {

//        for (int i = 0; i < json_idList.size(); i++) {
//            for (int j = i + 1; j < json_idList.size(); j++) {
//                Assert.assertNotEquals(json_idList.get(i), json_idList.get(j));
//                System.out.println(i + ": " + json_idList.get(i) + " " + json_idList.get(j));
//            }
//        }
        //ikinci yol
        Set<Integer> idSet = new HashSet<>(json_idList);
        Assert.assertEquals(json_idList.size(), idSet.size());
        System.out.println(idSet.size());
        System.out.println(json_idList.size());
    }

    @Test //NULL (isimsiz) olan name var mı, varsa hangi page hangi id?
    public void Tc0107() {

        List<String> allNames = new ArrayList<>();  //once bos list olusturdum

        System.out.println("Total Page: " + json_allPages); // sayfanin basindaki all pagesdan aliyor

        for (int i = 1; i <= json_allPages; i++) {      //burada her sayfadaki 20 er sayfa sayisini i ye atadik

            given().queryParam("page", i). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    when().get(endPoint);
            System.out.println(i); //100
            JsonPath json=response.jsonPath();

            List<String> names = json.getList("data.name");
            System.out.println(names);

            for (int j = 0; j < json_name_List.size(); j++) {        // bu listi olusturdugumuz bos listin icine koyduk.
                allNames.add(json_name_List.get(j));
                System.out.println(j);
                assertTrue(allNames.get(j) != null);
            }
        }

        System.out.println("allNames.size() : " + allNames.size());
    }

    @Test //number of males assertion
    public void TC108() {
        int count = 0;
        for (int i = 0; i < json_allPages; i++) {
            given().queryParam("page", i). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    when().get(endPoint);
            for (String gender : json_gender_List) {
                if (gender.equals("male")) { //burda equals yerine contains desen famale i de sayar cunku "male" kelimesi female da da var
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Test //number of males assertion
    public void TC109() {
        int count = 0;
        for (int i = 0; i < json_allPages; i++) {
            given().queryParam("page", i). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    when().get(endPoint);
            for (String gender : json_gender_List) {
                if (gender.equals("female")) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

//    public static void main(String[] args) {
//        String endPoint = "https://gorest.co.in/public-api/users?page=2";
//        Response response = given(). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
//                when().get(endPoint);
//        response.prettyPrint();
////        System.out.println(i);
//    }

    @Test //give duplicate names with their ids
    public void GetTC110() {
//        List<String> notDupliNames = new ArrayList<>();
//        List<String> dupliNames = new ArrayList<>();
//
//        for (String name : json_name_List) {
//            if (!notDupliNames.equals(name)) {
//                notDupliNames.add(name);
//            } else if (json_name_List.equals(name)) {
//                dupliNames.add(name);
//
//                System.out.println(name);
//            }


//        Set<String> store01 = new HashSet<>(json_name_List);
//        //Set<Integer> store02 = new HashSet<>(json_allIds);
//        int count = 0;
//        for (int i = 0; i < json_allPages; i++) {
//            given().queryParam("page").when().get(endPoint);
//            for (String name : json_name_List) {
//                if (store01.add(name) == false) {
//                    System.out.println("found a duplicate element in array : " + json_name_List);
//
//                } else {
//                    System.out.println("No duplicate names");
//                }
//
//            }
//        }

        List<Map<String, Object>> users = new ArrayList<>(); //once bos liste olusturdum

        System.out.println("Total Page: " + json_allPages); // sayfanin basindaki all pagesdan aliyor

        for (int i = 1; i <= json_allPages; i++) {      //burada her sayfadaki 20 er sayfa sayisini i ye atadik

            spec01.queryParam("page", i); // i 1,2 ... artarak sayfalari cekiyor

            response = given(). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    spec(spec01).
                    when().get();
            System.out.println("page::::::::: "+i); //104

            json = response.jsonPath();
            json_name_List = json.getList("data.name");
            json_idList = json.getList("data.id");
            for (int j=0; j < json_name_List.size(); j++) {

                Map<String, Object> user = new HashMap<>();        // bu ikinci Map ismin üzerine yazmaması için
                user.put("name", json_name_List.get(j));           // bu listi olusturdugumuz bos "user" listin icine koyduk.
                user.put("id", json_idList.get(j));

                System.out.println("users = " + user);
                users.add(user);
            }
        }

        System.out.println("users = " + users);


    }

    @Test
    public void Get111() {

        int countFemale = 0;
        int countMale = 0;
        for (int i = 0; i < json_allPages; i++) {
            for (String genderF : json_gender_List) {
                if (genderF.equals("female")) {
                    countFemale++;
                }
            }
            for (String genderM : json_gender_List) {
                if (genderM.equals("male")) {
                        countMale++;
                }
            }

        }
        System.out.println("female:" + countFemale);
        System.out.println("male:" +countMale);
        //Assert.assertTrue(countFemale>countMale);
        Assert.assertEquals(countFemale, countMale);
    }

}

