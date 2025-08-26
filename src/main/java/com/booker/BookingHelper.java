package com.booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookingHelper {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    // Criar booking
    public static int criarBooking(String firstname, String lastname) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{ " +
                        "\"firstname\": \"" + firstname + "\", " +
                        "\"lastname\": \"" + lastname + "\", " +
                        "\"totalprice\": 150, " +
                        "\"depositpaid\": true, " +
                        "\"bookingdates\": { \"checkin\": \"2025-09-01\", \"checkout\": \"2025-09-10\" }" +
                      " }")
            .when()
                .post(BASE_URL + "/booking");

        return response.jsonPath().getInt("bookingid");
    }

    // Atualizar booking (envia o JSON completo)
    public static Response atualizarBooking(int bookingId, String token, String novoSobrenome) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body("{ " +
                        "\"firstname\": \"Cliente\", " +
                        "\"lastname\": \"" + novoSobrenome + "\", " +
                        "\"totalprice\": 150, " +
                        "\"depositpaid\": true, " +
                        "\"bookingdates\": { \"checkin\": \"2025-09-01\", \"checkout\": \"2025-09-10\" }" +
                      " }")
            .when()
                .put(BASE_URL + "/booking/" + bookingId);
    }

    // Deletar booking
    public static Response deletarBooking(int bookingId, String token) {
        return RestAssured.given()
                .cookie("token", token)
            .when()
                .delete(BASE_URL + "/booking/" + bookingId);
    }
}
