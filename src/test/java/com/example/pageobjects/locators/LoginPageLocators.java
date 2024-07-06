package com.example.pageobjects.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    public static final By usernameInput = By.id("username");
    public static final By passwordInput = By.id("password");
    public static final By continueButton = By.xpath("//*[text()='Continue']");
    public static final By logInButton = By.xpath("//*[text()='Log in']");
}