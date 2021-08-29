package gorest.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class TestBase {
    protected RequestSpecification spec01;

    public void spec01(){
        spec01 = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/todos").addParam("id","123").
                build();
    }
}
