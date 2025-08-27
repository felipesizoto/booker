package com.booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookingHelper {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    // Criar booking
    public static int criarBooking(String firstname, String lastname) {
        String body = "{\n" +
                "  \"firstname\" : \"" + firstname + "\",\n" +
                "  \"lastname\" : \"" + lastname + "\",\n" +
                "  \"totalprice\" : 200,\n" +
                "  \"depositpaid\" : true,\n" +
                "  \"bookingdates\" : {\n" +
                "    \"checkin\" : \"2025-09-01\",\n" +
                "    \"checkout\" : \"2025-09-10\"\n" +
                "  },\n" +
                "  \"additionalneeds\" : \"Café da manhã\"\n" +
                "}";

        Response response = RestAssured
            .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
            .when()
                .post("/booking");

        return response.jsonPath().getInt("bookingid");
    }

    // Atualizar booking
    public static Response atualizarBooking(int bookingId, String token, String firstname, String lastname) {
        String body = "{\n" +
                "  \"firstname\" : \"" + firstname + "\",\n" +
                "  \"lastname\" : \"" + lastname + "\",\n" +
                "  \"totalprice\" : 250,\n" +
                "  \"depositpaid\" : false,\n" +
                "  \"bookingdates\" : {\n" +
                "    \"checkin\" : \"2025-09-05\",\n" +
                "    \"checkout\" : \"2025-09-15\"\n" +
                "  },\n" +
                "  \"additionalneeds\" : \"Jantar incluído\"\n" +
                "}";

        return RestAssured
            .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(body)
            .when()
                .put("/booking/" + bookingId);
    }

    // Deletar booking
    public static Response deletarBooking(int bookingId, String token) {
        return RestAssured
            .given()
                .baseUri(BASE_URL)
                .cookie("token", token)
            .when()
                .delete("/booking/" + bookingId);
    }
}
