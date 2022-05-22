package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTests;
import br.com.restassuredapitesting.suites.AcceptanceExceptionTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.request.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.PostBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.payloads.BookingPayloads;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Delete Bookings Feature")
public class DeleteBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();
    BookingPayloads bookingPayloads = new BookingPayloads();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AcceptanceCriticalTests.class, AllTests.class})
    @DisplayName("Deleting a specific booking by ID")
    public void deleteBooking(){
        JSONObject payload = bookingPayloads.payloadValidBooking();

        int bookingToDeleteId = postBookingRequest.createBooking(payload)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");

        String token = postAuthRequest.getToken();

        deleteBookingRequest.deleteBooking(bookingToDeleteId, token)
                .then()
                .statusCode(201);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceExceptionTests.class})
    @DisplayName("Deleting a non existing booking")
    public void deleteNonExistingBooking(){
        int randomId = 1000000 + (int) (Math.random()*9000001);

        String token = postAuthRequest.getToken();

        deleteBookingRequest.deleteBooking(randomId, token)
                .then()
                .statusCode(405);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceExceptionTests.class})
    @DisplayName("Deleting a booking without auth")
    public void deleteBookingWithoutAuth(){
        JSONObject payload = bookingPayloads.payloadValidBooking();

        int bookingToDeleteId = postBookingRequest.createBooking(payload)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");

        deleteBookingRequest.deleteBookingWithoutAuth(bookingToDeleteId)
                .then()
                .statusCode(403);
    }
}
