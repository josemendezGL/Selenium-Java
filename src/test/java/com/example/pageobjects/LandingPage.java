package com.example.pageobjects;

import org.openqa.selenium.WebDriver;

import com.example.pageobjects.locators.LandingPageLocators;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        driver.findElement(LandingPageLocators.loginButton).click();
    }
}
