package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.XML;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReqresinTests {
    @Test
    void loginTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", " +
                "\"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void missingPasswordLoginTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\"}";

        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void simple(){
        boolean z = true;
        assertTrue(z);
        System.out.println("Katya");
    }

    @Test
    void myFirst(){
        given()
                .log().all()
                .when()
                .contentType(JSON)
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
       void mySecond(){
        given()
                    .log().all()
                    .post("https://reqres.in/api/login")
                    .then()
                    .log().all()
                    .statusCode(415);

    }

    @Test
    void mySecondExtract(){
        Response response = post("https://reqres.in/api/login")
                .then()
                .extract().response();

        Assertions.assertEquals(415, response.statusCode());
        System.out.println(response.asString());


    }


}
