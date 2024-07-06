package com.example.pageobjects;

import org.openqa.selenium.WebDriver;
import com.example.pageobjects.locators.LoginPageLocators;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginToApp(String strUsername, String strPassword) {
        this.sendKeys(LoginPageLocators.usernameInput, strUsername);
        this.click(LoginPageLocators.continueButton);
        this.sendKeys(LoginPageLocators.passwordInput, strPassword);
        this.click(LoginPageLocators.logInButton);
    }
}
