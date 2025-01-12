package org.martinsoft.petclinic.pettypes;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Epic: REST API petclinic")
@Feature("Feature: Endpoint pettypes")
@Story("Story: POST pettypes")
@Owner("martin.ibersperger@gmx.at")
@Link(name = "OpenAPI Documentation", url = "http://localhost:9966/petclinic/swagger-ui/index.html#/pettypes/addPetType")
public class TestPetTypePost {

    static {
        RestAssured.baseURI = "http://localhost:9966/petclinic/api";
    }

    @Test
    @DisplayName("Create new pettype")
    @Description("Create new pettype")
    @Issue("Link to Xray here...")
    void createPetType() {
        String newPet = "fish";
        String requestBody = "{ \"name\": \"" + newPet + "\" }";

        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/pettypes")
            .then()
            .statusCode(201)
            .contentType(ContentType.JSON)
            .body("name", equalTo(newPet))
            .body("id", greaterThan(0))
            .log().all();
    }


}
