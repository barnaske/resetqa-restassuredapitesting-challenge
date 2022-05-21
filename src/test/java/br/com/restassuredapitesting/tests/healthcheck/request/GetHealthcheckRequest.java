package br.com.restassuredapitesting.tests.healthcheck.request;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetHealthcheckRequest {

    @Step("Return API's ping")
    public Response pingReturnApi(){
        return given()
                .header("Content-Type", "appplication/json")
                .when()
                .get("ping");
    }
}
