package org.martinsoft.petclinic.pettypes;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

public class PlayGroundTests {

    static {
        RestAssured.baseURI = "http://localhost:9966/petclinic/api";
    }

    @Test
    @DisplayName("GET ALL EXISTING PETTYPES")
    @Description("Get all existing pettypes")
    @Issue("Link to Jira here...")
    @Owner("martin.ibersperger@gmx.at")
    @Link(name = "OpenAPI Documentation", url = "http://localhost:9966/petclinic/swagger-ui/index.html#/pettypes/getPetType")
    void getJsonElements() {
        Response response = given()
            .accept(ContentType.JSON)
            .when()
            .get("/pettypes");

        Allure.addAttachment("Request/Response Logs", "text/plain", "Log content here", ".txt");

        String jsonResponse = response.asString();
        System.out.println("Full JSON Response: " + jsonResponse);

        int size = response.jsonPath().getInt("size()");
        System.out.println("Size of the array: " + size);

        String firstId = response.jsonPath().getString("[0].id");
        System.out.println("First Pet Type ID: " + firstId);

        String firstName = response.jsonPath().getString("[0].name");
        System.out.println("First Pet Type Name: " + firstName);
    }

    @Test
    void getPetTypeByIdInvalidInput() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get("/pettypes/1")
            .then()
//            .statusCode(500)
//            .contentType(ContentType.JSON)
//            .body("title", equalTo("MethodArgumentTypeMismatchException"))
//            .body("$", not(empty()))
//            .body("size()", greaterThan(0))
//            .body("id", equalTo(1))
//            .body("name", equalTo("cat"))
            .log().all()
        ;
    }

    @Test
    @Tag("String from @Tag")
    void createPetTypeHttpMessageNotReadableException() {
        String requestBody = "{ \"name\": fish }";

        given()
            .accept(ContentType.JSON)  // Specify that we accept JSON response
            .contentType(ContentType.JSON)  // Specify that the request body is in JSON format
            .body(requestBody)
//            .log().all()
            .when()
            .post("/pettypes")  // The endpoint to send the POST request to
            .then()
//            .statusCode(201)  // Verify that the status code is 200 (OK)
//            .contentType(ContentType.JSON)  // Verify that the response content type is JSON
//            .body("name", equalTo(newPet))  // Verify that the "name" in the response is "cat"
//            .body("id", greaterThan(0))  // Verify that the "id" is greater than 0 (indicating the pet type was created)
            .log().all()
            ;
    }

    @Test
    void createPetType() {
        String requestBody = "{ \"name\": \"fish\" }";

        given()
            .accept(ContentType.JSON)  // Specify that we accept JSON response
            .contentType(ContentType.JSON)  // Specify that the request body is in JSON format
            .body(requestBody)
            .log().all()
            .when()
            .post("/pettypes")  // The endpoint to send the POST request to
            .then()
//            .statusCode(201)  // Verify that the status code is 200 (OK)
//            .contentType(ContentType.JSON)  // Verify that the response content type is JSON
//            .body("name", equalTo(newPet))  // Verify that the "name" in the response is "cat"
//            .body("id", greaterThan(0))  // Verify that the "id" is greater than 0 (indicating the pet type was created)
            .log().all()
        ;
    }

}
