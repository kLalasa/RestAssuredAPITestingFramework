package org.example.Modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.example.Pojos.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class PayloadManager {


    ObjectMapper objectMapper;




    public String CreatePayload() throws JsonProcessingException{

        Faker faker= new Faker();

        Booking booking=new Booking();
        String expectFirstname= faker.name().firstName();

        booking.setFirstname(expectFirstname);
        booking.setLastname("Bhagya");
        booking.setTotalprice(110);
        booking.setDepositpaid(true);

        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin("2024-02-12");
        bookingdates.setCheckout("20204-02-15");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        String payload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return  payload;

    }
    public Response JsonToObject(String jsonString) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
       Response bookingRespons = objectMapper.readValue(jsonString, Response.class);
        return bookingRespons;
    }

    public Booking JsonToObjectPUT(String jsonString) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking bookingRespons = objectMapper.readValue(jsonString, Booking.class);
        return bookingRespons;
    }

    public String updatedPayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Bhagya");
        booking.setLastname("Lalasa");
        booking.setTotalprice(199);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Breakfast, lunch");
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-10-01");
        bookingdates.setCheckout("2022-10-01");
        booking.setBookingdates(bookingdates);
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }

    public String updatedPayloadPatch() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Bhagya");
        booking.setLastname("Lalasa");
        booking.setTotalprice(199);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Breakfast, lunch");
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-10-01");
        bookingdates.setCheckout("2022-10-01");
        booking.setBookingdates(bookingdates);
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }

    public String updatePayload(){
        return null;
    };



    public String setToken() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(auth);

    }


}
