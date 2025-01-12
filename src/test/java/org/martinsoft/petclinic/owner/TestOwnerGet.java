package org.martinsoft.petclinic.owner;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Epic: REST API petclinic")
@Feature("Feature: Endpoint owner")
@Story("Story: GET owner")
@Owner("martin.ibersperger@gmx.at")
@Link(name = "OpenAPI Documentation", url = "http://localhost:9966/petclinic/swagger-ui/index.html#/owner/getOwner")
public class TestOwnerGet {

    static {
        RestAssured.baseURI = "http://localhost:9966/petclinic/api";
    }

    @Test
    @DisplayName("Get all existing owners")
    @Description("Get all existing owners")
    @Issue("Link to Xray here...")
    void getAllOwners() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get("/owners")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", not(empty()))
            .body("size()", greaterThan(0))
            .body("[0].id", notNullValue())
            .body("[0].firstName", notNullValue())
            .body("[0].lastName", notNullValue())
            .body("[0].address", notNullValue())
            .body("[0].city", notNullValue())
            .body("[0].telephone", notNullValue())
            .log().all()
        ;
    }


    @Test
    @DisplayName("Get existing owner by Id")
    @Description("Get existing owner by Id")
    @Issue("Link to Xray here...")
    void getOwnerById() {
        String ownerId = "1";
        //TODO
//      String ownerId = "-1"; => 500, ConstraintViolationException
//      String ownerId = "a"; => 500, MethodArgumentTypeMismatchException
//      String ownerId = "1/abc"; => 400 Bad Request
//      String ownerId = "1/9999"; => 404 Not Found
        given()
            .accept(ContentType.JSON)
            .pathParam("ownerId", ownerId)
            .when()
            .get("/owners/{ownerId}")
            .then()
            .log().all()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", not(empty()))
            .body("size()", greaterThan(0))
            .body("id",  equalTo(Integer.parseInt(ownerId)))
            .body("firstName", notNullValue())
            .body("lastName", notNullValue())
            .body("address", notNullValue())
            .body("city", notNullValue())
            .body("telephone", notNullValue())
            .log().all()
        ;
    }

    @Test
    void getPetByIdForOwner() {
        int ownerId = 1;
        int petId = 1;

        given()
            .accept(ContentType.JSON)
            .pathParam("ownerId", ownerId)
            .pathParam("petId", petId)
            .when()
            .get("/api/owners/{ownerId}/pets/{petId}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(petId))
            .body("ownerId", equalTo(ownerId))
            .body("name", notNullValue())
            .body("birthDate", notNullValue())
            .body("type.name", notNullValue())
            .body("visits", not(empty()))
            .log().all();
    }


}
