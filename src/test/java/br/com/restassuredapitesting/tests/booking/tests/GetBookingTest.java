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

@Feature("List Booking Feature | Funcionalidade de Listagem de Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    @DisplayName("List all bookings by ID | Listar todas as reservas por ID")
    public void checkListAllBookingsById(){
        getBookingRequest.allBookings()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    @DisplayName("List a specific booking by ID | Listar reserva especifica por ID")
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
    @DisplayName("List Booking IDs filtering by firstname | Listar reservas por ID filtrando pelo Primeiro nome")
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
    @DisplayName("List Booking IDs filtering by Lastname | Listar reservas por ID filtrando pelo último nome")
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
                    .body("lastname", equalTo(lastname));
        }
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    @DisplayName("List Booking IDs filtering by Checkin | Listar reservas por ID filtrando pelo checkin")
    public void checkListBookingIdsFilteringByCheckin(){
        String checkinDate = "2022-05-20";
        getBookingRequest.listWithOneFilter("checkin", checkinDate)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    @DisplayName("List Booking IDs filtering by Checkout | Listar reservas por ID filtrando pelo checkout")
    public void checkListBookingIdsFilteringByCheckout(){
        String checkoutDate = "2022-05-22";
        getBookingRequest.listWithOneFilter("checkin", checkoutDate)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    @DisplayName("List Booking IDs filtering by Checkin and Checkout | Listar reservas por ID filtrando por checkin e checkout")
    public void checkListBookingIdsFilteringByCheckinAndCheckout(){
        String checkinDate = "2022-05-20";
        String checkoutDate = "2022-05-22";
        getBookingRequest.listWithTwoFilters("checkin", checkinDate, "checkout", checkoutDate)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTests.class})
    @DisplayName("List Booking IDs filtering by Checkin and Checkout and Firstname | Listar reservas por ID filtrando por Checkin, Checkout e Primeiro nome")
    public void checkListBookingIdsFilteringByCheckinCheckoutAndFirstname(){
        String checkinDate = "2022-05-20";
        String checkoutDate = "2022-05-23";
        String firstname = "Nonewparam";
        getBookingRequest.listWithThreeFilters("checkin", checkinDate, "checkout", checkoutDate, "firstname", firstname)
                .then()
                .log().all()
                .statusCode(200);
    }
//--------------------------------- Acceptance Exception Tests -----------------------------

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceExceptionTests.class})
    @DisplayName("Trying to get bookings with bad formatted filter | Tentando buscar reservas com um filtro mal formatado")
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
    @DisplayName("Validate Booking list's schema | Validar schema da listagem de todas as reservas")
    public void validateBookingsListSchema(){
        getBookingRequest.allBookings()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SchemaTests.class, AllTests.class})
    @DisplayName("Validate list booking by id schema | Validar schema da listagem de reservas por ID especifico")
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
