package com.booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthHelper {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    public static String gerarToken() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{ \"username\": \"admin\", \"password\": \"password123\" }")
            .when()
                .post(BASE_URL + "/auth")
            .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}

