package com.example.stepdefinitions.ui;

import org.openqa.selenium.WebDriver;
import com.example.pageobjects.DashboardPage;
import com.example.pageobjects.LandingPage;
import com.example.pageobjects.LoginPage;
import com.example.utils.ConfigManager;
import com.example.utils.CustomWebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class LoginSteps {

    // Cargar las credenciales y la URL desde el ConfigManager
    String username = ConfigManager.getProperty("site.username");
    String password = ConfigManager.getProperty("site.password");
    String siteUrl = ConfigManager.getProperty("site.url");

    WebDriver driver;
    LandingPage landingPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    public LoginSteps() {
        this.driver = CustomWebDriverManager.getDriver();
    }

    @Given("user is on Login page")
    public void user_is_on_login_page() {
        // Navegar a la página de inicio de sesión utilizando la URL desde el archivo de
        // configuración
        driver.get(siteUrl + "/login");
        landingPage = new LandingPage(driver);
    }

    @When("user logs in with valid credential")
    public void user_clicks_login_button() {
        // Usar el método de la página para hacer login con las credenciales desde el
        // archivo de configuración
        landingPage.clickLoginButton();
        loginPage = new LoginPage(driver);
        loginPage.loginToApp(username, password);
    }

    @Then("user is navigated to the Dashboard page")
    public void user_is_navigated_to_the_home_page() {
        dashboardPage = new DashboardPage(driver);
        assertTrue(dashboardPage.isBoardsButtonVisible());
        CustomWebDriverManager.quitDriver();
    }
}
