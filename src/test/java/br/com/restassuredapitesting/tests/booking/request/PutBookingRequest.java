package br.com.restassuredapitesting.tests.booking.request;

import br.com.restassuredapitesting.tests.booking.request.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Update an specific booking with a token | Atualizar reserva especifica com um token")
    public Response updateBookingWithToken(int id, String token){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayloads.payloadUpdateBookingData().toString())
                .put("booking/"+ id);
    }

    @Step("Update an specific booking with basic auth | Atualizar reserva especifica com basic authentication")
    public Response updateBookingWithBasicAuth(int id){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(bookingPayloads.payloadUpdateBookingData().toString())
                .put("booking/"+id);
    }

    @Step("Update booking without auth | Atualizar reserva sem autenticação")
    public Response updateBookingWithoutToken(int id){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.payloadUpdateBookingData().toString())
                .put("booking/"+ id);
    }
}
