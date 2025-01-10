package org.example;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@Story("DELETE pettypes")
@Epic("REST API petclinic")
@Feature("Endpoint pettypes")
@Owner("martin.ibersperger@gmx.at")
@Link(name = "OpenAPI Documentation", url = "http://localhost:9966/petclinic/swagger-ui/index.html#/pettypes/deletePetType")
public class TestPetTypeDelete {

    static {
        RestAssured.baseURI = "http://localhost:9966/petclinic/api";
    }

    @Test
    @DisplayName("Delete existing pettype by Id")
    @Description("Delete existing pettype by Id")
    @Issue("Link to Xray here...")
    void deletePetTypeById() {
        int petTypeId = 1; // Make sure this petTypeId exists => reset DB to initial state before!

        given()
            .accept(ContentType.JSON)
            .when()
            .delete("/pettypes/{petTypeId}", petTypeId)
            .then()
            .statusCode(204)
            .log().all();
    }

    @Test
    @DisplayName("Delete nonexistent pettype by Id")
    @Description("Delete nonexistent pettype by Id")
    @Issue("Link to Xray here...")
    void  deletePetTypeByIdNonExistentId() {
        int petTypeId = 9999;

        given()
            .accept(ContentType.JSON)
            .when()
            .delete("/pettypes/{petTypeId}", petTypeId)
            .then()
            .statusCode(404)
            .log().all();
    }


}
