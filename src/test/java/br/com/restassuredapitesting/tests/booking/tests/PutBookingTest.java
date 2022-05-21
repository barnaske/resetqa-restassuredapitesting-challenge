package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.request.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

@Feature("Update Bookings Feature")
public class PutBookingTest extends BaseTest {
    
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(AllTests.class)
    @DisplayName("Update a specific booking with a token")
    public void checkUpdateBookingWithToken(){
        int firstId = getBookingRequest.allBookings()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        String token = postAuthRequest.getToken();

        putBookingRequest.updateBookingWithToken(firstId, token)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}
