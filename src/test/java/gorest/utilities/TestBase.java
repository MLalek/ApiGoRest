package gorest.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    protected RequestSpecification spec01;
    protected RequestSpecification spec02;
    
    @BeforeMethod
    public void setUp01(){
        spec01 = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/todos").
                build();
    }

    @BeforeMethod
    public void setUp02(){
        spec02 = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com/booking").
                build();
    }
}
