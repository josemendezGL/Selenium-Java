package com.example.stepdefinitions.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.example.pageobjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.*;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("user is on Login page")
    public void user_is_on_login_page() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.chromedriver().browserVersion("126.0.6478.62").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*"); // Agrega esta línea
        // options.addArguments("--headless"); // Si deseas ejecutar en modo sin cabeza
        driver = new ChromeDriver(options);
        driver.get("https://trello.com/");
        loginPage = new LoginPage(driver);
    }

    @When("user clicks Log in button")
    public void user_clicks_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("user is navigated to the Home page")
    public void user_is_navigated_to_the_home_page() {
        assertTrue(driver.getCurrentUrl().contains("atlassian"));
        driver.quit();
    }
}
