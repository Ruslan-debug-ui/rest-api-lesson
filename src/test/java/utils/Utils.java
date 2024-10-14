package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Utils {

    public static String getValueFromJson(Response Resp, String Field) {

        String readableTest = Resp.asPrettyString();
        JsonObject jsonObject = JsonParser.parseString(readableTest)
                .getAsJsonObject();
        String woo = jsonObject.get(Field).getAsString();

        return woo;
    }

    public static String tokenGetProc() {

        Response response = given()

                .formParam("username", "mediascope_api")
                .formParam("password", "p3C36dpFg%zNMkVxByqy")
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .when()
                .post("/auth/token")
                .then()
                .statusCode(200)
                .extract().response();
        String woo = getValueFromJson(response, "access_token");
        return woo;
    }
}
