package br.com.restassuredapitesting.tests.booking.request.payloads;

import org.json.JSONObject;

public class BookingPayloads {

    public static JSONObject payloadValidBooking(){
        JSONObject payloadValidBooking = new JSONObject();
        payloadValidBooking.put("firstname", "James");
        payloadValidBooking.put("lastname","Brown");
        payloadValidBooking.put("totalprice",111);
        payloadValidBooking.put("depositpaid", true);
        JSONObject objectBookingDates = new JSONObject();
        objectBookingDates.put("checkin", "2018-01-01");
        objectBookingDates.put("checkout", "2019-01-01");
        payloadValidBooking.put("bookingdates", objectBookingDates);
        payloadValidBooking.put("additionalneeds","Breakfast");
        return payloadValidBooking;
    }

}
