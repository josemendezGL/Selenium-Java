package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

    By loginButton = By.xpath("//*[text()='Log in']");

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
