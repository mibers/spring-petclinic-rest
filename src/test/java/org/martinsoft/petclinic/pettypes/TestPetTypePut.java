package org.martinsoft.petclinic.pettypes;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@Epic("Epic: REST API petclinic")
@Feature("Feature: Endpoint pettypes")
@Story("Story: PUT pettypes")
@Owner("martin.ibersperger@gmx.at")
@Link(name = "OpenAPI Documentation", url = "http://localhost:9966/petclinic/swagger-ui/index.html#/pettypes/updatePetType")
public class TestPetTypePut {

    static {
        RestAssured.baseURI = "http://localhost:9966/petclinic/api";
    }

    @Test
    @DisplayName("Update existing pettype by Id")
    @Description("Update existing pettype by Id")
    @Issue("Link to Xray here...")
    void updatePetTypeById() {
        int petTypeId = 3;
        String newPetTypeName = "dragon";
        String updatedPetType = "{ \"name\": \"" + newPetTypeName + "\", \"id\": " + petTypeId + " }";

        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(updatedPetType)
            .when()
            .put("/pettypes/{petTypeId}", petTypeId)
            .then()
            .statusCode(204)
            .contentType(ContentType.JSON)
            .log().all();
    }


}
