package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestPetTypePost {

    static {
        RestAssured.baseURI = "http://localhost:9966/petclinic/api";
    }

    @Test
    void createPetType() {
        String newPet = "fish";
        String requestBody = "{ \"name\": \"" + newPet + "\" }";

        given()
            .accept(ContentType.JSON)  // Specify that we accept JSON response
            .contentType(ContentType.JSON)  // Specify that the request body is in JSON format
            .body(requestBody)  // The request body (JSON)
            .when()
            .post("/pettypes")  // The endpoint to send the POST request to
            .then()
            .statusCode(201)  // Verify that the status code is 200 (OK)
            .contentType(ContentType.JSON)  // Verify that the response content type is JSON
            .body("name", equalTo(newPet))  // Verify that the "name" in the response is "cat"
            .body("id", greaterThan(0))  // Verify that the "id" is greater than 0 (indicating the pet type was created)
            .log().all();
    }


}
