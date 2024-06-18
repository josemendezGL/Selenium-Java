package com.example.stepdefinitions.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.example.pageobjects.DashboardPage;
import com.example.pageobjects.LandingPage;
import com.example.pageobjects.LoginPage;
import java.time.Duration;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.*;

public class LoginSteps {

    String username = System.getenv("SITE_USERNAME");
    String password = System.getenv("SITE_PASSWORD");

    WebDriver driver;
    LandingPage landingPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // espera implícita de 10 segundos
        driver.get("https://trello.com/");
        landingPage = new LandingPage(driver);
    }

    @When("user logs in with valid credential")
    public void user_clicks_login_button() {
        landingPage.clickLoginButton();
        loginPage = new LoginPage(driver);
        loginPage.loginToApp(username, password);
    }

    @Then("user is navigated to the Dashboard page")
    public void user_is_navigated_to_the_home_page() {
        dashboardPage = new DashboardPage(driver);
        assertTrue(dashboardPage.isBoardsButtonVisible());
        driver.quit();
    }
}