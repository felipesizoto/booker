package com.booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthHelper {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    public static String gerarToken() {
        String authBody = "{ \"username\": \"admin\", \"password\": \"password123\" }";

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(authBody)
                .when()
                .post("/auth");

        return response.jsonPath().getString("token");
    }
}
