package com.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.pageobjects.locators.DashboardPageLocators;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBoardsButtonVisible() {
        WebElement boardsElement = driver.findElement(DashboardPageLocators.boardsButton);
        return boardsElement.isDisplayed();
    }

    public void createNewBoard() {
        this.click(DashboardPageLocators.createButton);
        this.click(DashboardPageLocators.createBoardButton);
        this.sendKeys(DashboardPageLocators.boardTitleInput, "Created by UI - Selenium");
        this.click(DashboardPageLocators.createBoardSubmitButton);
    }
}
