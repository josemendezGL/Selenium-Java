package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    By username = By.id("username");
    By password = By.id("password");
    By continueButton = By.xpath("//*[text()='Continue']");
    By logInButton = By.xpath("//*[text()='Log in']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setUsername(String strUsername) {
        driver.findElement(username).sendKeys(strUsername);
    }

    public void setPassword(String strPassword) {
        driver.findElement(password).sendKeys(strPassword);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(logInButton).click();

    }

    public void loginToApp(String strUsername, String strPassword) {
        this.setUsername(strUsername);
        this.clickContinueButton();
        this.setPassword(strPassword);
        this.clickLoginButton();
    }
}
