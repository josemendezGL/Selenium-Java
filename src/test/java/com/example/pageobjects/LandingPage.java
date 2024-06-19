package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {
    WebDriver driver;

    By loginButton = By.xpath("//*[text()='Log in']");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
