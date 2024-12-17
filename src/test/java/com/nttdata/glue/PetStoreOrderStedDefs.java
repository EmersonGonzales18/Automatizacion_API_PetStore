package com.nttdata.glue;

import com.nttdata.steps.PetStoreOrderStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Assert;
import java.util.Map;

public class PetStoreOrderStedDefs {

    @Steps
    PetStoreOrderStep tienda;

    @Given("que el servicio de la tienda de mascotas está disponible")
    public void queElServicioDeLaTiendaDeMascotasEstaDisponible() {
        tienda.probarServicio();
    }

    @When("envío una solicitud POST a {string} con los siguientes datos:")
    public void envioUnaSolicitudPOSTAConLosSiguientesDatos(String endpoint, DataTable dataTable) {
        tienda.crearOrderPOST(endpoint, dataTable);
    }

    @Then("la respuesta debe tener un status code {int}")
    public void laRespuestaDebeTenerUnStatusCode(int statusCode) {
        tienda.validarStatusCode(statusCode);
    }

    @And("validar que el body de la respuesta contenga los datos enviados")
    public void validarQueElBodyDeLaRespuestaContengaLosDatosEnviados() {
        tienda.validarJSON();
    }

    @When("envío una peticion GET a {string}")
    public void envioUnaPeticionGETA(String endpoint) {
        tienda.consultarMascota(endpoint);
    }

    @And("valida que el id es {int}")
    public void validaQueElIdEs(int id) {
        tienda.validarOrderId(id);
    }

    @And("valida que el petId es {int}")
    public void validaQueElPetIdEs(int petId) {
        tienda.validarPetId(petId);
    }

    @And("valida que la cantidad es {int}")
    public void validaQueLaCantidadEs(int quantity) {
        tienda.validarQuantity(quantity);
    }

}
