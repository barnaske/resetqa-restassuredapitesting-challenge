package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.request.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.PostBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.PutBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.payloads.BookingPayloads;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

@Feature("Update Bookings Feature")
public class PutBookingTest extends BaseTest {
    BookingPayloads bookingPayloads = new BookingPayloads();
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AcceptanceCriticalTests.class, AllTests.class})
    @DisplayName("Update a specific booking with a token")
    public void checkUpdateBookingWithToken(){
        JSONObject payload = bookingPayloads.payloadValidBooking();

        int updatingThisBookingById = postBookingRequest.createBooking(payload)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("bookingid");

        String token = postAuthRequest.getToken();

        putBookingRequest.updateBookingWithToken(updatingThisBookingById, token)
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AcceptanceCriticalTests.class, AllTests.class})
    @DisplayName("Update a specific booking with a token")
    public void checkUpdateBookingWithBasicAuth(){
        JSONObject payload = bookingPayloads.payloadValidBooking();

        int updatingThisBookingById = postBookingRequest.createBooking(payload)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("bookingid");

        putBookingRequest.updateBookingWithBasicAuth(updatingThisBookingById)
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}
