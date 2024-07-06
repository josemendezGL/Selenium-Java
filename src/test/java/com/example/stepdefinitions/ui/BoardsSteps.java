package com.example.stepdefinitions.ui;

import com.example.pageobjects.BoardPage;
import com.example.pageobjects.DashboardPage;
import com.example.pageobjects.LandingPage;
import com.example.pageobjects.LoginPage;
import com.example.utils.APIHelper;
import com.example.utils.CustomWebDriverManager;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;

public class BoardsSteps {

    WebDriver driver;
    LandingPage landingPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    BoardPage boardPage;

    private String apiKey = System.getenv("TRELLO_API_KEY");
    private String apiToken = System.getenv("TRELLO_API_TOKEN");
    private APIHelper apiHelper;

    public BoardsSteps() {
        this.driver = CustomWebDriverManager.getDriver();
    }

    @Before
    public void setUp() {
        apiHelper = new APIHelper(apiKey, apiToken);
        apiHelper.deleteAllBoards();
    }

    @When("user creates a new board")
    public void user_creates_a_new_board() {
        driver = CustomWebDriverManager.getDriver();
        dashboardPage = new DashboardPage(driver);
        dashboardPage.createNewBoard();

    }

    @Then("new board is correctly displayed")
    public void new_board_is_correctly_displayed() {
        boardPage = new BoardPage(driver);
        assertTrue(boardPage.isBoardNameLabelVisible());
        CustomWebDriverManager.quitDriver();
    }

}
