package com.example.stepdefinitions.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class APISteps {
    private Response response;

    @Given("an API endpoint {string}")
    public void an_api_endpoint(String endpoint) {
        RestAssured.baseURI = endpoint;
    }

    @When("a GET request is made to {string}")
    public void a_get_request_is_made_to(String path) {
        response = given().when().get(path);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain(String content) {
        response.then().body(containsString(content));
    }
}
