package com.Tests;

import com.APITestCase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class API_GetTests extends APITestCase {

    Response response;

    @Description("Verify that the API starts with an empty store")
    @Test(priority = 1)
    public void emptyStore() {
        response = given()
                .header("Content-type", "application/json")
                .when()
                .get(API_ROOT)
                .then()
                .assertThat().statusCode(204)
                .extract().response();
    }

    @Test(priority = 2)
    public void getUniqueBook() {
         response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/1")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        assertEquals(1, response.jsonPath().getInt("id"));
        assertEquals("John Smith", response.jsonPath().getString("author"));
        assertEquals("Reliability of late night deployments", response.jsonPath().getString("title"));
    }

    @Description("Verify created book is added")
    @Test(priority = 3)
    public void getCreatedBook() {
         response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(  "/?id=3")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        assertEquals(3, response.jsonPath().getInt("id"));
        assertEquals("Kemal Tahir", response.jsonPath().getString("author"));
        assertEquals("Ince Memed", response.jsonPath().getString("title"));
    }

    @Test(priority = 4)
    public void getAllBook() {
         response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API_ROOT)
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        assertEquals(1, response.jsonPath().getInt("firstBook.id"));
        assertEquals("John Smith", response.jsonPath().getString("firstBook.author"));
        assertEquals("Reliability of late night deployments", response.jsonPath().getString("firstBook.title"));
        assertEquals(2, response.jsonPath().getInt("secondBook.id"));
        assertEquals("Jane Archer", response.jsonPath().getString("secondBook.author"));
        assertEquals("DevOps is a lie", response.jsonPath().getString("secondBook.title"));
        assertEquals(3, response.jsonPath().getInt("thirdBook.id"));
        assertEquals("Kemal Tahir", response.jsonPath().getString("thirdBook.author"));
        assertEquals("Ince Memed", response.jsonPath().getString("thirdBook.title"));

    }
}
