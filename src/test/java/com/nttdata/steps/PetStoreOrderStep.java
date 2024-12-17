package com.nttdata.steps;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Assert;
import java.util.Map;

public class PetStoreOrderStep {

    Response response;
    private String URL_BASE;

    public void probarServicio() {
        URL_BASE = "https://petstore.swagger.io/v2";
    }

    public void crearOrderPOST(String endpoint, DataTable datatable) {
        Map<String, Object> requestBody = datatable.asMap(String.class,Object.class);

        response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .body(requestBody)
                .header("Content-Type", "application/json")
                .baseUri(URL_BASE)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    public void validarStatusCode(int statusCode) {
        Assert.assertEquals("Validacion de la respuesta", statusCode , response.statusCode());
    }

    public void validarJSON() {
        response.then().body("shipDate", Matchers.containsString("2024-09-01T10:00"));
    }

    public void consultarMascota(String endpoint) {
        response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .baseUri(URL_BASE)
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    public void validarOrderId(int id) {
        Assert.assertEquals("El id de la orden no coincide", id, response.jsonPath().getInt("id"));
    }

    public void validarPetId(int petId) {
        Assert.assertEquals("El id de la mascota no coincide", petId, response.jsonPath().getInt("petId"));
    }

    public void validarQuantity(int quantity) {
        Assert.assertEquals("La cantidad no coincide", quantity, response.jsonPath().getInt("quantity"));
    }
}
