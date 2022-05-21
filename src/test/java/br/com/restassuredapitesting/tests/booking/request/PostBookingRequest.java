package br.com.restassuredapitesting.tests.booking.request;

import br.com.restassuredapitesting.tests.booking.request.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Create a new booking")
    public Response createBooking(JSONObject payload){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking");
    }


}
