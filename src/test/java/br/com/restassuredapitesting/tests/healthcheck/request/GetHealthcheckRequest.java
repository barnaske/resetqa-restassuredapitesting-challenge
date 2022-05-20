package br.com.restassuredapitesting.tests.healthcheck.request;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetPingRequest{

    @Step("Retorno a ping da API")
    public Response pingReturnApi(){
        return given()
                .header("Content-Type", "appplication/json")
                .when()
                .get("ping");
    }
}
