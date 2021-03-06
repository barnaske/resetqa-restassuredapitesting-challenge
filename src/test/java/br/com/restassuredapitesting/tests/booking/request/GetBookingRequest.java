package br.com.restassuredapitesting.tests.booking.request;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("List all bookings by ID | Listar todas as reservas por ID")
    public Response allBookings(){
        return given()
                .when()
                .get("booking");
    }

    @Step("List specific booking by ID | Listar uma reservar específica pelo seu ID")
    public Response bookingById(Integer id){
        return given()
                .when()
                .get("booking/"+id);
    }

    //Tentativa
    @Step("List of IDS that match a filter | Listar reservas por ID com o filtro batendo")
    public Response bookingsByIdsMatchingFilter(List ids){
        return given()
                .when()
                .get("booking/"+ids);
    }

    @Step("List with one filter | Listar com um filtro")
    public Response listWithOneFilter(String param1, String value1){
        return given()
                .when()
                .queryParam(param1, value1)
                .get("booking");
    }

    @Step("List with two filter | Listar com dois filtros")
    public Response listWithTwoFilters(String param1, String value1, String param2, String value2){
        return given()
                .when()
                .queryParam(param1, value1)
                .queryParam(param2, value2)
                .get("booking");
    }

    @Step("List with two filter | Listar com tres filtros")
    public Response listWithThreeFilters(String param1, String value1, String param2, String value2, String param3, String value3){
        return given()
                .when()
                .queryParam(param1, value1)
                .queryParam(param2, value2)
                .queryParam(param3, value3)
                .get("booking");
    }
}
