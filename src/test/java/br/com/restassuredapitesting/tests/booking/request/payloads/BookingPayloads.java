package br.com.restassuredapitesting.tests.booking.request.payloads;

import com.github.javafaker.Faker;
import org.json.JSONObject;

public class BookingPayloads {

    public static JSONObject payloadValidBooking(){
        Faker faker = new Faker();
        JSONObject payloadValidBooking = new JSONObject();
        payloadValidBooking.put("firstname", faker.name().firstName());
        payloadValidBooking.put("lastname",faker.name().lastName());
        payloadValidBooking.put("totalprice",faker.number().numberBetween(1,500));
        payloadValidBooking.put("depositpaid", true);
        JSONObject objectBookingDates = new JSONObject();
        objectBookingDates.put("checkin", "2018-01-01");
        objectBookingDates.put("checkout", "2019-01-01");
        payloadValidBooking.put("bookingdates", objectBookingDates);
        payloadValidBooking.put("additionalneeds","Breakfast");
        return payloadValidBooking;
    }

    public static JSONObject payloadCreatingBookingToValidateSchema(){
        JSONObject payloadCreatingBookingToValidateSchema = new JSONObject();
        payloadCreatingBookingToValidateSchema.put("firstname", "VLbID");
        payloadCreatingBookingToValidateSchema.put("lastname","Schema");
        payloadCreatingBookingToValidateSchema.put("totalprice",111);
        payloadCreatingBookingToValidateSchema.put("depositpaid", true);
        JSONObject objectBookingDates = new JSONObject();
        objectBookingDates.put("checkin", "2018-01-01");
        objectBookingDates.put("checkout", "2019-01-01");
        payloadCreatingBookingToValidateSchema.put("bookingdates", objectBookingDates);
        payloadCreatingBookingToValidateSchema.put("additionalneeds","Breakfast");
        return payloadCreatingBookingToValidateSchema;
    }

    public static JSONObject payloadListBookingById(){
        JSONObject payloadListBookingById = new JSONObject();
        payloadListBookingById.put("firstname", "Joana");
        payloadListBookingById.put("lastname","Brown");
        payloadListBookingById.put("totalprice",111);
        payloadListBookingById.put("depositpaid", true);
        JSONObject objectBookingDates = new JSONObject();
        objectBookingDates.put("checkin", "2018-01-01");
        objectBookingDates.put("checkout", "2019-01-01");
        payloadListBookingById.put("bookingdates", objectBookingDates);
        payloadListBookingById.put("additionalneeds","Breakfast");
        return payloadListBookingById;
    }

    public static JSONObject payloadToFilterByCheckInOut(){
        JSONObject payloadToFilterByCheckInOut = new JSONObject();
        payloadToFilterByCheckInOut.put("firstname", "Joana");
        payloadToFilterByCheckInOut.put("lastname","Pravariar");
        payloadToFilterByCheckInOut.put("totalprice",111);
        payloadToFilterByCheckInOut.put("depositpaid", true);
        JSONObject objectBookingDates = new JSONObject();
        objectBookingDates.put("checkin", "2022-05-20");
        objectBookingDates.put("checkout", "2022-05-21");
        payloadToFilterByCheckInOut.put("bookingdates", objectBookingDates);
        payloadToFilterByCheckInOut.put("additionalneeds","Breakfast");
        return payloadToFilterByCheckInOut;
    }

    public static JSONObject payloadUpdateBookingData(){
        JSONObject payloadUpdateBookingData = new JSONObject();
        payloadUpdateBookingData.put("firstname", "Updating");
        payloadUpdateBookingData.put("lastname","All");
        payloadUpdateBookingData.put("totalprice",111);
        payloadUpdateBookingData.put("depositpaid", true);
        JSONObject objectBookingDates = new JSONObject();
        objectBookingDates.put("checkin", "2018-01-01");
        objectBookingDates.put("checkout", "2019-01-01");
        payloadUpdateBookingData.put("bookingdates", objectBookingDates);
        payloadUpdateBookingData.put("additionalneeds","Data from the booking");
        return payloadUpdateBookingData;
    }

    public static JSONObject invalidPayload(){
        Faker faker = new Faker();
        JSONObject invalidPayload = new JSONObject();
        invalidPayload.put("invaliding", faker.name().firstName());
        invalidPayload.put("the","Pravariar");
        invalidPayload.put("entire",111);
        invalidPayload.put("payload", true);
        JSONObject objectBookingDates = new JSONObject();
        objectBookingDates.put("checkin", "2018-01-01");
        objectBookingDates.put("checkout", "2019-01-01");
        invalidPayload.put("bookingdates", objectBookingDates);
        invalidPayload.put("additionalneeds","Breakfast");
        return invalidPayload;
    }

    public static JSONObject payloadWithNewParam(){

        JSONObject payloadWithNewParam = new JSONObject();
        payloadWithNewParam.put("firstname", "Nonewpar");
        payloadWithNewParam.put("lastname","Pravariar");
        payloadWithNewParam.put("totalprice",111);
        payloadWithNewParam.put("depositpaid", true);
        JSONObject objectBookingDates = new JSONObject();
        objectBookingDates.put("checkin", "2018-01-01");
        objectBookingDates.put("checkout", "2019-01-01");
        payloadWithNewParam.put("bookingdates", objectBookingDates);
        payloadWithNewParam.put("additionalneeds","Breakfast");
        payloadWithNewParam.put("paramnew", "new");
        return payloadWithNewParam;
    }
}
