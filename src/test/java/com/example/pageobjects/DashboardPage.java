package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {
    WebDriver driver;

    // By username = By.id("username");
    // By logInButton = By.xpath("//*[text()='Log in']");
    By boardsButton = By.xpath("//*[text()='Boards']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isBoardsButtonVisible() {
        /*
         * try {
         * WebElement element =
         * wait.until(ExpectedConditions.visibilityOfElementLocated(boardsButton));
         * return element.isDisplayed();
         * } catch (Exception e) {
         * return false;
         * }
         */
        WebElement boardsElement = driver.findElement(boardsButton);
        return boardsElement.isDisplayed();
    }

    /*
     * public void setUsername(String strUsername) {
     * driver.findElement(username).sendKeys(strUsername);
     * }
     * 
     * public void setPassword(String strPassword) {
     * driver.findElement(password).sendKeys(strPassword);
     * }
     * 
     * public void clickContinueButton() {
     * driver.findElement(continueButton).click();
     * }
     * 
     * public void clickLoginButton() {
     * driver.findElement(logInButton).click();
     * 
     * }
     * 
     * public void loginToApp(String strUsername, String strPassword) {
     * this.setUsername(strUsername);
     * this.clickContinueButton();
     * this.setPassword(strPassword);
     * this.clickLoginButton();
     * }
     */
}
