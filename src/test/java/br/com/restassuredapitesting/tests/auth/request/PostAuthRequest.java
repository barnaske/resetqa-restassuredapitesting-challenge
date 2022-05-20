package br.com.restassuredapitesting.tests.auth.request;

import br.com.restassuredapitesting.tests.auth.request.payloads.AuthPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {

    AuthPayloads authPayload = new AuthPayloads();

    @Step("Retorna o token")
    public Response returnToken(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(authPayload.jsonAuthLogin().toString())
                .post("auth");
    }
    @Step("Busca o token")
    public String getToken(){
        return "token="+this.returnToken()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}
