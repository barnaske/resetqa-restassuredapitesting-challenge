package br.com.restassuredapitesting.tests.auth.request;

import br.com.restassuredapitesting.tests.auth.request.payloads.AuthPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {

    AuthPayloads authPayload = new AuthPayloads();

    @Step("Return user token | Retorna token de usuário")
    public Response returnToken(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(authPayload.jsonAuthLogin().toString())
                .post("auth");
    }
    @Step("Get user token | Pega o token de usuário")
    public String getToken(){
        return "token="+this.returnToken()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}
