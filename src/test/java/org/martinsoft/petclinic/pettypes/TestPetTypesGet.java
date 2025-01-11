package org.martinsoft.petclinic.pettypes;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Epic: REST API petclinic")
@Feature("Feature: Endpoint pettypes")
@Story("Story: GET pettypes")
@Owner("martin.ibersperger@gmx.at")
@Link(name = "OpenAPI Documentation", url = "http://localhost:9966/petclinic/swagger-ui/index.html#/pettypes/getPetType")
public class TestPetTypesGet {

    static {
        RestAssured.baseURI = "http://localhost:9966/petclinic/api";
    }

    @Test
    @DisplayName("Get all existing pettypes")
    @Description("Get all existing pettypes")
    @Issue("Link to Xray here...")
    void getAllPetTypes() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get("/pettypes")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", not(empty()))
            .body("size()", greaterThan(0))
            .body("[0].id", notNullValue())
            .body("[0].name", notNullValue())
            .log().all()
        ;
    }
    @Test
    @DisplayName("Get existing pettype by Id")
    @Description("Get existing pettype by Id")
    @Issue("Link to Xray here...")
    void getPetTypeById() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get("/pettypes/1")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", not(empty()))
            .body("size()", greaterThan(0))
            .body("id", equalTo(1))
            .body("name", equalTo("cat"))
            .log().all()
        ;
    }

    @Test
    @DisplayName("Try to get pettype by Id which doesn't exist")
    @Description("Try to get pettype by Id which doesn't exist")
    @Issue("Link to Xray here...")
    void getPetTypeByIdDoesNotExist() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get("/pettypes/9999")
            .then()
            .statusCode(404)
            .log().all()
        ;
    }

    @Test
    @Disabled("Cannot get ETag header from API => RC always 200")
    @DisplayName("Get existing pettype by Id - check for RC 304")
    @Description("Get existing pettype by Id - check for RC 304")
    @Issue("Link to Xray here...")
    void getPetTypeByIdUnmodified() {
        String etag = given()
            .accept("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
            .header("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8,de;q=0.7")
            .header("Cache-Control", "max-age=0")
            .header("Connection", "keep-alive")
            .header("Cookie", "Idea-965130b5=19e0250b-4a5a-4bbc-bfdf-3dcf62a47b78")
            .header("If-None-Match", "W/\"559-mycaVvEPoBLtlmgMG+fa47mf/ok\"")
            .header("Sec-Fetch-Dest", "document")
            .header("Sec-Fetch-Mode", "navigate")
            .header("Sec-Fetch-Site", "same-origin")
            .header("Sec-Fetch-User", "?1")
            .header("Upgrade-Insecure-Requests", "1")
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Windows\"")
            .log().all()
            .when()
            .get("/pettypes")
            .then()
            .log().all()
            .statusCode(304)
            .extract().header("ETag");
    }

    @Test
    @DisplayName("Try to get pettype by Id - Id is string not integer")
    @Description("Try to get pettype by Id - Id is string not integer")
    @Issue("Link to Xray here...")
    void getPetTypeByIdInvalidDataType() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get("/pettypes/a")
            .then()
            .statusCode(500)
            .contentType(ContentType.JSON)
            .body("title", equalTo("MethodArgumentTypeMismatchException"))
            .log().all()
        ;
    }

    @Test
    @DisplayName("Try to get pettype by Id - Id out of possible range")
    @Description("Try to get pettype by Id - Id out of possible range")
    @Issue("Link to Xray here...")
    void getPetTypeByIdOutOfRange() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get("/pettypes/-1")
            .then()
            .statusCode(500)
            .contentType(ContentType.JSON)
            .body("title", equalTo("ConstraintViolationException"))
            .log().all()
        ;
    }

    @Test
    @DisplayName("Try to get pettype by Id - extra path after endpoint")
    @Description("Try to get pettype by Id - extra path after endpoint")
    @Issue("Link to Xray here...")
    void getPetTypeByIdEndpointTooLong() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get("/pettypes/1/12345")
            .then()
            .statusCode(500)
            .contentType(ContentType.JSON)
            .body("title", equalTo("NoResourceFoundException"))
            .log().all()
        ;
    }

}
