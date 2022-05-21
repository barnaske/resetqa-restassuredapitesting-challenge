package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SchemaTests;
import br.com.restassuredapitesting.tests.booking.request.PostBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.payloads.BookingPayloads;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Booking - Create Booking")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SchemaTests.class, AcceptanceCriticalTests.class})
    @DisplayName("Creating a new booking")
    public void createBooking(){

        JSONObject payload = bookingPayloads.payloadValidBooking();

        postBookingRequest.createBooking(payload)
                .then()
                .statusCode(200);
    }
}
