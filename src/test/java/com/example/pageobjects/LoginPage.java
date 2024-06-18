package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    By username = By.id("username");
    By password = By.id("password");
    By loginButton = By.xpath("//*[text()='Log in']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String strUsername) {
        driver.findElement(username).sendKeys(strUsername);
    }

    public void setPassword(String strPassword) {
        driver.findElement(password).sendKeys(strPassword);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void loginToApp(String strUsername, String strPassword) {
        this.setUsername(strUsername);
        this.setPassword(strPassword);
        this.clickLoginButton();
    }
}
