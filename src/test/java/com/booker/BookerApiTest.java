package com.booker;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookerApiTest {

    private static String token;

    @BeforeAll
    static void setup() {
        token = AuthHelper.gerarToken();
        assertThat("Token deve ser gerado", token, notNullValue());
        System.out.println("Token gerado: " + token);
    }

    @Test
    void testCriarBooking() {
        int bookingId = BookingHelper.criarBooking("Joao", "Souza");
        assertThat("BookingId deve ser gerado", bookingId, greaterThan(0));
        System.out.println("Booking criado com ID: " + bookingId + " | Nome: Joao Souza");
    }

    @Test
    void testAtualizarBooking() {
        int bookingId = BookingHelper.criarBooking("Joao", "Souza");
        System.out.println("Booking criado para atualização, ID: " + bookingId);

        Response response = BookingHelper.atualizarBooking(bookingId, token, "Maria", "Silva");
        response.then().statusCode(200);

        assertThat(response.jsonPath().getString("firstname"), equalTo("Maria"));
        assertThat(response.jsonPath().getString("lastname"), equalTo("Silva"));
        System.out.println("Booking atualizado: " + bookingId + " | Novo Nome: Maria Silva");
    }

    @Test
    void testDeletarBooking() {
        int bookingId = BookingHelper.criarBooking("Joao", "Souza");
        System.out.println("Booking criado para deleção, ID: " + bookingId);

        Response response = BookingHelper.deletarBooking(bookingId, token);
        response.then().statusCode(201);

        System.out.println("Booking deletado: " + bookingId);
    }
}
