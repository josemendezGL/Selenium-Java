package com.example.stepdefinitions.ui;

import com.example.pageobjects.BoardPage;
import com.example.pageobjects.DashboardPage;
import com.example.utils.APIHelper;
import com.example.utils.CustomWebDriverManager;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class BoardsSteps extends BaseSteps {

    WebDriver driver;
    DashboardPage dashboardPage;
    BoardPage boardPage;
    private APIHelper apiHelper;

    public BoardsSteps() {
        this.driver = CustomWebDriverManager.getDriver();
    }

    @Before
    public void setUp() {
        loadCredentials();
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
