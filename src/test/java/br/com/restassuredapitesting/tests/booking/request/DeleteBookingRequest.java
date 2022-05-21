package br.com.restassuredapitesting.tests.booking.request;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    @Step("Deleting a booking")
    public Response deleteBooking(int id,String token){
        return given()
                .header("Content-Type","application/json")
                .header("Cookie", token)
                .when()
                .delete("booking/"+id);
    }

}
