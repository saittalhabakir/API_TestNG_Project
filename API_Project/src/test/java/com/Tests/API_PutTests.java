package com.Tests;

import com.APITestCase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class API_PutTests extends APITestCase {

    Response response;

    @Description("Verify that title and author are required fields")
    @Test(priority = 1)
    public void putBadRequestWithNoParameter() {
         response = given()
                .header("Content-type", "application/json")
                .when()
                .put(API_ROOT)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        assertEquals("Field author and title is required", response.jsonPath().getString("error"));
    }

    @Description(" Verify that title and author cannot be empty")
    @Test(priority = 2)
    public void putBadRequestWithEmptyAuthor() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .put(API_ROOT+"/?author&title=")
                .then()
                .assertThat().statusCode(405)
                .extract().response();

        assertEquals("Field author and title can not be empty", response.jsonPath().getString("error"));
    }

    @Description("Verify that the id field is read−only")
    @Test(priority = 3)
    public void putBadRequestWithId() {
         response = given()
                .header("Content-type", "application/json")
                .when()
                .put(API_ROOT+"/23")
                .then()
                .assertThat().statusCode(405)
                .extract().response();

        assertEquals("id field is read only", response.jsonPath().getString("error"));
    }

    @Description("Verify that you cannot create a duplicate book.")
    @Test(priority = 4)
    public void putBadRequestForDuplicated() {
         response = given()
                .header("Content-type", "application/json")
                .when()
                .put(API_ROOT+"/?author=John Smith&title=Reliability of late night deployments")
                .then()
                .assertThat().statusCode(405)
                .extract().response();

        assertEquals("Another book with similar author and title already exists", response.jsonPath().getString("error"));
    }

    @Test(priority = 5)
    public void putInternalServerError() {
         response = given()
                .contentType(ContentType.JSON)
                .when()
                .put(API_ROOT+"/500")
                .then()
                .assertThat().statusCode(500)
                .extract().response();

        assertEquals("Internal Server Error", response.jsonPath().getString("error"));
    }

    @Description("Verify that you can create a new book via PUT")
    @Test(priority = 6)
    public void putCreatedBook() {
         response = given()
                .header("Content-type", "application/json")
                .when()
                .put(API_ROOT+"?id=3&author=Kemal Tahir author&title=Ince Memed")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        assertEquals("new book created successfully", response.jsonPath().getString("message"));
    }
}
