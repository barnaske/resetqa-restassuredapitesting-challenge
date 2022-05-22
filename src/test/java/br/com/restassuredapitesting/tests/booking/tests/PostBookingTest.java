package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTests;
import br.com.restassuredapitesting.suites.AcceptanceExceptionTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SchemaTests;
import br.com.restassuredapitesting.tests.booking.request.PostBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.payloads.BookingPayloads;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.google.inject.matcher.Matchers.not;
import static org.hamcrest.Matchers.*;

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

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, SchemaTests.class, AcceptanceExceptionTests.class})
    @DisplayName("Creating a new booking with invalid payload")
    public void createBookingWithInvalidPayload(){

        JSONObject payload = bookingPayloads.invalidPayload();

        postBookingRequest.createBooking(payload)
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SchemaTests.class, AcceptanceExceptionTests.class})
    @DisplayName("Creating 5 new bookings in a row")
    public void createFiveBookingInARow(){
        JSONObject payload = bookingPayloads.payloadValidBooking();

        for (int i = 0; i < 6; i++){
            postBookingRequest.createBooking(payload)
                    .then()
                    .log().all()
                    .statusCode(200);
        }
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SchemaTests.class, AcceptanceExceptionTests.class})
    @DisplayName("Create booking with more params in the payload then expected")
    public void createBookingWithMoreParamsInPayload(){
        JSONObject payload = bookingPayloads.payloadWithNewParam();

            postBookingRequest.createBooking(payload)
                    .then()
                    .log().all()
                    .statusCode(200)
                    .assertThat()
                    .body("paramnew", nullValue());

    }
}
