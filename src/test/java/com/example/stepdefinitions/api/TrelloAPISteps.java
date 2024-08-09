
package com.example.stepdefinitions.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.example.utils.APIHelper;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class TrelloAPISteps {

    private String apiKey = System.getenv("TRELLO_API_KEY");
    private String apiToken = System.getenv("TRELLO_API_TOKEN");
    private Response response;
    private String createdBoardId;
    private APIHelper apiHelper;

    @Before
    public void setUp() {
        apiHelper = new APIHelper(apiKey, apiToken);
        apiHelper.deleteAllBoards();
    }

    @Given("I have a valid Trello API key and token")
    public void i_have_a_valid_trello_api_key_and_token() {
    }

    @When("I send a GET request to organizations endpoint")
    public void i_send_a_get_request_to_organizations_endpoint() {
        response = apiHelper.sendGetRequestToOrganizationBoards();
    }

    // CREATE
    @When("I create a new board using API request")
    public void i_create_a_new_board_using_api_request() {
        response = apiHelper.createNewBoard("Created by API - Selenium");
        createdBoardId = response.then().extract().path("id");
    }

    @When("I retrieve the created board")
    public void i_retrieve_the_created_board() {
        response = apiHelper.getBoard(createdBoardId);
    }

    @When("I update the board name to {string}")
    public void i_update_the_board_name_to(String newName) {
        response = apiHelper.updateBoardName(createdBoardId, newName);
    }

    @When("I delete the created board")
    public void i_delete_the_created_board() {
        response = apiHelper.deleteBoard(createdBoardId);
    }

    // Verification

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain a list of boards")
    public void the_response_should_contain_a_list_of_boards() {
        response.then().body("$", notNullValue());
    }

    @Then("the response should contain the board details")
    public void the_response_should_contain_the_board_details() {
        response.then().body("id", notNullValue()).body("name", notNullValue());
    }

    @Then("the board name should be updated to {string}")
    public void the_board_name_should_be_updated_to(String newName) {
        response = apiHelper.getBoard(createdBoardId);
        response.then().body("name", equalTo(newName));

    }

}