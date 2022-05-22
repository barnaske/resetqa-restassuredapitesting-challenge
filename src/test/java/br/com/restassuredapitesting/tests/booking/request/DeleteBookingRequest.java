package br.com.restassuredapitesting.tests.booking.request;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    @Step("Deleting a booking | Excluir uma reserva")
    public Response deleteBooking(int id,String token){
        return given()
                .header("Content-Type","application/json")
                .header("Cookie", token)
                .when()
                .delete("booking/"+id);
    }

    @Step("Deleting a booking without auth | Excluir uma reserva sem autenticação")
    public Response deleteBookingWithoutAuth(int id){
        return given()
                .header("Content-Type","application/json")
                .when()
                .delete("booking/"+id);
    }

}
