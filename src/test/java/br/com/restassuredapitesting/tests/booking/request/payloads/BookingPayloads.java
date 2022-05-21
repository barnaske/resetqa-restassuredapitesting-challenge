package br.com.restassuredapitesting.tests.booking.request.payloads;

import org.json.JSONObject;

public class BookingPayloads {

    public static JSONObject payloadValidBooking(){
        JSONObject payloadValidBooking = new JSONObject();
        payloadValidBooking.put("firstname", "Joana");
        payloadValidBooking.put("lastname","Pravariar");
        payloadValidBooking.put("totalprice",111);
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
}
