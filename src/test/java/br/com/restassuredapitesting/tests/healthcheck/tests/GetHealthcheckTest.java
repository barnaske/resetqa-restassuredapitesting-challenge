package br.com.restassuredapitesting.tests.healthcheck.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.healthcheck.request.GetHealthcheckRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

@Feature("Healthcheck Feature")
public class GetHealthcheckTest extends BaseTest {

    GetHealthcheckRequest getPingRequest = new GetHealthcheckRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Check if the api is online")
    public void healthCheck() {
        getPingRequest.pingReturnApi()
                .then()
                .statusCode(201);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Check if the api is online - max 3ms")
    public void healthCheckWithinThreeSeconds(){
        getPingRequest.pingReturnApi()
                .then()
                .statusCode(201)
                .time(lessThan(3L), TimeUnit.SECONDS);
    }
}
