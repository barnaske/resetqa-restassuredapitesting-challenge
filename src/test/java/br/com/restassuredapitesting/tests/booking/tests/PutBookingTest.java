package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceCriticalTests;
import br.com.restassuredapitesting.suites.AcceptanceExceptionTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SecurityTests;
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

@Feature("Update Bookings Feature | Funcionalidade de Atualização de Reservas")
public class PutBookingTest extends BaseTest {
    BookingPayloads bookingPayloads = new BookingPayloads();
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AcceptanceCriticalTests.class, AllTests.class})
    @DisplayName("Update a specific booking with a token | Atualizar uma reserva espeicifca com um token")
    public void checkUpdateBookingWithToken(){
        JSONObject payload = bookingPayloads.payloadValidBooking();

        int updatingThisBookingById = postBookingRequest.createBooking(payload)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");

        String token = postAuthRequest.getToken();

        putBookingRequest.updateBookingWithToken(updatingThisBookingById, token)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AcceptanceCriticalTests.class, AllTests.class})
    @DisplayName("Update a specific booking with basic auth | Atualizar uma reserva espeicifca com basic authentication")
    public void checkUpdateBookingWithBasicAuth(){
        JSONObject payload = bookingPayloads.payloadValidBooking();

        int updatingThisBookingById = postBookingRequest.createBooking(payload)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");

        putBookingRequest.updateBookingWithBasicAuth(updatingThisBookingById)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AcceptanceExceptionTests.class, AllTests.class})
    @DisplayName("Update a booking that does not exists | Atualizar uma reserva que não existe")
    public void updateNonExistingBooking(){

        int randomId = 1000000 + (int) (Math.random()*9000001);

        String token = postAuthRequest.getToken();

        putBookingRequest.updateBookingWithToken(randomId, token)
                .then()
                .statusCode(405);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({SecurityTests.class, AllTests.class})
    @DisplayName("Update booking without sending token | Atualizar reserva sem mandar o token como header")
    public void updateBookingWithoutToken(){

        JSONObject payload = bookingPayloads.payloadValidBooking();

        int updatingThisBookingById = postBookingRequest.createBooking(payload)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");

        putBookingRequest.updateBookingWithoutToken(updatingThisBookingById)
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({SecurityTests.class, AllTests.class})
    @DisplayName("Update booking sending an invalid token | Atualizar reserva mandando um token inválido")
    public void updateBookingWithInvalidToken(){

        JSONObject payload = bookingPayloads.payloadValidBooking();

        int updatingThisBookingById = postBookingRequest.createBooking(payload)
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");

        String token = "making this surely invalid";

        putBookingRequest.updateBookingWithToken(updatingThisBookingById, token)
                .then()
                .statusCode(403);
    }
}
