package com.example.stepdefinitions.api;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.example.utils.APIHelper;
import com.example.utils.SchemaValidator;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class TrelloAPISteps {

    private String apiKey;
    private String apiToken;
    private Response response;
    private String createdBoardId;
    private APIHelper apiHelper;

    @Before
    public void setUp() {
        loadCredentials();
        apiHelper = new APIHelper(apiKey, apiToken);
        apiHelper.deleteAllBoards();
    }

    protected void loadCredentials() {
        Properties properties = new Properties();
        String env = System.getProperty("env", "dev"); // default value is 'dev'
        String filename = String.format("src/test/resources/credentials-%s.properties", env);

        try (FileInputStream input = new FileInputStream(filename)) {
            properties.load(input);
            apiKey = properties.getProperty("trello.api.key");
            apiToken = properties.getProperty("trello.api.token");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load credentials from properties file: " + filename);
        }
    }

    // Steps for API testing

    @Given("I have a valid Trello API key and token")
    public void i_have_a_valid_trello_api_key_and_token() {
        // No action required
    }

    @When("I send a GET request to organizations endpoint")
    public void i_send_a_get_request_to_organizations_endpoint() {
        response = apiHelper.sendGetRequestToOrganizationBoards();
    }

    // Creation, retrieval, update and deletion steps
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

    // Verification steps

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
