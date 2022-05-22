package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTests;
import br.com.restassuredapitesting.suites.AcceptanceExceptionTests;
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
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.equalTo;
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

        Integer bookingId = postBookingRequest.createBooking(payload)
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
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    @DisplayName("List Booking IDs filtering by firstname")
    public void checkListBookingIdsFilteringByFisrtname(){
        List<Integer> extractingIdsToMatch = new ArrayList<>();

        String firstname = "Joana";

        Response responseToEvaluate = getBookingRequest.listWithOneFilter("firstname", firstname)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        extractingIdsToMatch = responseToEvaluate.jsonPath().getList("bookingid");
        System.out.println(extractingIdsToMatch);

        for (Integer id : extractingIdsToMatch){
            getBookingRequest.bookingById(id)
                    .then()
                    .assertThat()
                    .body("firstname", equalTo(firstname));
        }
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    @DisplayName("List Booking IDs filtering by Lastname")
    public void checkListBookingIdsFilteringByLastname(){
        List<Integer> extractingIdsToMatch = new ArrayList<>();

        String lastname = "Pravariar";

        Response responseToEvaluate = getBookingRequest.listWithOneFilter("lastname", lastname)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        extractingIdsToMatch = responseToEvaluate.jsonPath().getList("bookingid");
        System.out.println(extractingIdsToMatch);

        for (Integer id : extractingIdsToMatch){
            getBookingRequest.bookingById(id)
                    .then()
                    .assertThat()
                    .body("firstname", equalTo(lastname));
        }
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    @DisplayName("List Booking IDs filtering by Checkin")
    public void checkListBookingIdsFilteringByCheckin(){
        JSONObject payload = bookingPayloads.payloadToFilterByCheckInOut();

        postBookingRequest.createBooking(payload)
                .then()
                .log().all()
                .statusCode(200);

        getBookingRequest.listWithOneFilter("checkin", "2022-05-20")
                .then()
                .log().all()
                .statusCode(200)
                .body("bookingdates.checkin", equalTo("2022-05-20"));
    }
//--------------------------------- Acceptance Exception Tests -----------------------------

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceExceptionTests.class})
    @DisplayName("Trying to get bookings with bad formatted filter")
    public void tryingToGetBookingsWithBadFiltering(){
        List<Integer> extractingIdsToMatch = new ArrayList<>();

        getBookingRequest.listWithOneFilter("that_bad_filter_for_sure", "")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

//--------------------------------- Schema Validations -------------------------------------
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

        Integer bookingId = postBookingRequest.createBooking(payload)
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
