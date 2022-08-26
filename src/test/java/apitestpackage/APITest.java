package apitestpackage;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojoclasses.CreateUser;
import pojoclasses.Root;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APITest {

    @Test
    public void getUsers(){
        System.out.println("Inside Get users");
        ValidatableResponse response = given().log().all()
                .when().get("https://reqres.in/api/users?page=2").
                 then().log().headers()
                .assertThat().
                statusCode(200).
                header("Content-Type",equalTo("application/json; charset=utf-8")).
                body("page",equalTo(2)).
                body("per_page",equalTo(6)).
                body("data[0].id",equalTo(7));
        response.log().all();
    }
    @Test
    public void getSingleUser(){
        Root response=
                        given().
                        when().get("https://reqres.in/api/users/2").as(Root.class);
        System.out.println("ID is "+response.getData().getId());
        System.out.println("URL is "+response.getSupport().getUrl());
        Assert.assertEquals(response.getSupport().getUrl(),"https://reqres.in/#support-heading");
    }
    @Test
    public void createUser(){
        String sEndPoint="https://reqres.in/api/users";
        String sBody="{\n" +
                "    \"name\": \"Vishwanath\",\n" +
                "    \"job\": \"VC\"\n" +
                "}";
        ValidatableResponse response = given().
                body(sBody).log().all().
                when().post(sEndPoint)
                .then().statusCode(201);
        response.log().all();
    }
    @Test
    public void fnUpdateAUser(){
        String sEndPoint="https://reqres.in/api/users/2";
        String sBody="{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"id\": \"25\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        ValidatableResponse response=given()
                .body(sBody)
                .when().put(sEndPoint)
                .then().statusCode(200);
        response.log().body();
    }

    @Test
    public void fnDeleteAPI(){
        String myEndPoint="https://reqres.in/api/users/7";
        String sBody="{\n" +
                "\"id\":\"2\"\n" +
                "}";
        ValidatableResponse response = given().log().all()
                .when().delete(myEndPoint)
                .then().statusCode(204);
        response.log().all();

    }

    @Test
    public void fnCreateUserUsingClass(){
        String sEndPoint="https://reqres.in/api/users";
        CreateUser createUser=new CreateUser("fo","foo@gmail.com");
        ValidatableResponse reponse = given().body(createUser)
                .when().post(sEndPoint)
                .then().assertThat().statusCode(201);
        reponse.log().body();

    }

}
