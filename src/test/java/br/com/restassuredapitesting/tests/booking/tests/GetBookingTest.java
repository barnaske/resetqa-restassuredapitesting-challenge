package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SchemaTests;
import br.com.restassuredapitesting.tests.booking.request.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.PostBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.payloads.BookingPayloads;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;

@Feature("List Booking Feature")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    @DisplayName("List all bookings by ID")
    public void checkListAllBookingsById(){
        getBookingRequest.allBookings()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    @DisplayName("List a specific booking by ID")
    public void checkListBookingById(){
        JSONObject payload = bookingPayloads.payloadListBookingById();

        int bookingId = postBookingRequest.createBooking(payload)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");

        getBookingRequest.bookingById(bookingId)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SchemaTests.class, AllTests.class})
    @DisplayName("Validate Booking list's schema")
    public void validateBookingsListSchema(){
        getBookingRequest.allBookings()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SchemaTests.class, AllTests.class})
    @DisplayName("Validate list booking by id schema")
    public void validateListBookingByIdSchema(){
        JSONObject payload = bookingPayloads.payloadCreatingBookingToValidateSchema();

        int bookingId = postBookingRequest.createBooking(payload)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("bookingid");

        getBookingRequest.bookingById(bookingId)
                        .then()
                        .statusCode(200)
                        .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookingbyid"))));
    }
}
