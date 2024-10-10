package tests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class SetmeterTests {

    private Object www;
    public String woo;

    @BeforeAll
    static void initDB() {
        System.out.println("### @BeforeAll");
    }

    @Test
    void tokenGetTest() {
//        String body = "{ \"email\": \"eve.holt@reqres.in\", " +
//                "\"password\": \"cityslicka\" }";
        String body = "{\"username\": \"mediascope_api\", \"password\": \"p3C36dpFg%zNMkVxByqy\"}";

        Response response = RestAssured.given()
           //    .auth().basic("mediascope_api", "p3C36dpFg%zNMkVxByqy")
           //     .header("Content-Type","application/x-www-form-urlencoded")
           //     .log().uri()
          //      .log().body()
                .formParam("username", "mediascope_api")
                .formParam("password", "p3C36dpFg%zNMkVxByqy")
         //       .log().all()
         //       .body(body)
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .when()
                .post("https://api1.setmeter.ru/api/v1/auth/token")
                .then()
//                .log().status()
//                .log().body()
                .statusCode(200)
                .extract().response();

        String readableTest = response.asPrettyString();
//        System.out.println(readableTest);

        JsonObject jsonObject = JsonParser.parseString(readableTest)
                .getAsJsonObject();
        String woo = jsonObject.get("access_token").getAsString();
 //       System.out.println(woo);

        response = given().auth().oauth2(woo)
                .log().all()
                .get("https://api1.setmeter.ru/api/v1/users/stlw455wpb")
                .then()
                .log().all()
                .extract().response();

         //       .body("token", is("QpwL5tke4Pnpja7X4"));
    }
}
