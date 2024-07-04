package org.example.CRUD;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.example.Base.BaseTest;
import org.example.EndPoints.APIConstants;
import org.example.Pojos.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.Pojos.Response;
import org.example.Pojos.Booking;



import static org.hamcrest.MatcherAssert.assertThat;

public class TC_Integration extends BaseTest {
    String token;
    String bookingId;
    String bookingIdPojo;
    private Booking booking;

    // Create a Booking
    // Update the Booking with Token and Booking ID - How to pass the variales from one test to another.
    // 1. Auth - API Key
    // Cookie Based Auth Side.
    // OAuth 2.0 - Method how you can use the OAuth 2.0
    // Delete Also


    // Get a Token - Extract the Token


    // Create a Booking
    @Test(groups = "P0")
    public void testCreateBooking() throws JsonProcessingException {
        token = getToken();


        requestSpecification.basePath(APIConstants.Create_Update_Booking_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.CreatePayload()).post();
        validatableResponse = response.then().log().all();
        jsonPath = JsonPath.from(response.asString());
        validatableResponse.statusCode(200);
        // Direct Extraction from json Path
        bookingId = jsonPath.getString("bookingid");
        Assert.assertNotNull("bookingid","not null");
        // Booking Response Class
        System.out.println(bookingId);




    }

    @Test(groups = "P0", dependsOnMethods = {"testCreateBooking"})
    public void testCreateAndUpdateBooking() throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.Create_Update_Booking_URL + "/" + bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token",token)
                .when().body(payloadManager.updatedPayload()).put();
        validatableResponse = response.then().log().all();
        //validatableResponse.body("firstname", Matchers.is("Lucky"));

        booking = payloadManager.JsonToObjectPUT(response.asString());
        validatableResponse.body("firstname", Matchers.equalTo("Bhagya"));
        validatableResponse.body("lastname",Matchers.equalTo("Lalasa"));
        validatableResponse.body("depositpaid",Matchers.notNullValue());

    }

    // Delete Also
    @Test(groups = "P0",dependsOnMethods = { "testCreateAndUpdateBooking"})
    public void testDeleteCreatedBooking(){
        requestSpecification.basePath(APIConstants.Create_Update_Booking_URL + "/" + bookingId).cookie("token",token);
        ValidatableResponse validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }

    @Test(groups = "P0",dependsOnMethods = { "testDeleteCreatedBooking"})
    public void testDeleteBookingByGet(){
        requestSpecification.basePath(APIConstants.Create_Update_Booking_URL+"/"+bookingId);
        response = RestAssured.given().spec(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
    }


}
