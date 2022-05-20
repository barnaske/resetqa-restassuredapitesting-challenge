package br.com.restassuredapitesting.tests.booking.request;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Lista reservas por ID")
    public Response bookingReturnIds(){
        return given()
                .when()
                .get("booking");
    }
}
