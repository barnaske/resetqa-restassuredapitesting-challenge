package br.com.restassuredapitesting.tests.booking.request;

import br.com.restassuredapitesting.tests.booking.request.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Create a new booking")
    public Response createBooking(){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .post("booking");
    }

    public Response createBookingToValidateListByIdScheam(){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPayloads.payloadCreatingBookingToValidateSchema().toString())
                .post("booking");
    }

}
