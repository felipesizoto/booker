package com.booker;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookerApiTest {

    private static String token;

    @BeforeAll
    public static void setup() {
        token = AuthHelper.gerarToken();
        assertThat(token, notNullValue());
        System.out.println("Token gerado: " + token);
    }

    @Test
    public void testCriarBooking() {
        int bookingId = BookingHelper.criarBooking("Cliente", "Teste");
        assertThat(bookingId, greaterThan(0));
        System.out.println("Booking criado: " + bookingId);
    }

    @Test
    public void testAtualizarBooking() {
        int id = BookingHelper.criarBooking("Cliente", "Original");
        Response updateResponse = BookingHelper.atualizarBooking(id, token, "Atualizado");

        assertThat(updateResponse.getStatusCode(), equalTo(200));
        assertThat(updateResponse.jsonPath().getString("lastname"), equalTo("Atualizado"));
        System.out.println("Booking atualizado!");
    }

    @Test
    public void testDeletarBooking() {
        int id = BookingHelper.criarBooking("Cliente", "ParaDeletar");
        Response deleteResponse = BookingHelper.deletarBooking(id, token);

        assertThat(deleteResponse.getStatusCode(), equalTo(201));
        System.out.println("Booking deletado!");
    }
}
