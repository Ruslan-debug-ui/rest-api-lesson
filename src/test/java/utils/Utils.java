package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

public class Utils {

    public static String getValueFromJson(Response Resp, String Field) {

        String readableTest = Resp.asPrettyString();
        JsonObject jsonObject = JsonParser.parseString(readableTest)
                .getAsJsonObject();
        String woo = jsonObject.get(Field).getAsString();

        return woo;


    }
}
