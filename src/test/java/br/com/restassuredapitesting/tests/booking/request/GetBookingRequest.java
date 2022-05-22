package br.com.restassuredapitesting.tests.booking.request;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("List all bookings by ID")
    public Response allBookings(){
        return given()
                .when()
                .get("booking");
    }

    @Step("List specific booking by ID")
    public Response bookingById(Integer id){
        return given()
                .when()
                .get("booking/"+id);
    }

    //Tentativa
    @Step("List of IDS that match a filter")
    public Response bookingsByIdsMatchingFilter(List ids){
        return given()
                .when()
                .get("booking/"+ids);
    }

    @Step("List with one filter")
    public Response listWithOneFilter(String param1, String value1){
        return given()
                .when()
                .queryParam(param1, value1)
                .get("booking");
    }
}
