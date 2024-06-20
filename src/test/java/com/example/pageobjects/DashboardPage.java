package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {

    By boardsButton = By.xpath("//*[text()='Boards']");
    By createButton = By.cssSelector("[data-testid='header-create-menu-button']");
    By createBoardButton = By.cssSelector("[data-testid='header-create-board-button']");
    By boardTitleInput = By.cssSelector("[data-testid='create-board-title-input']");
    By createBoardSubmitButton = By.cssSelector("[data-testid='create-board-submit-button']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBoardsButtonVisible() {
        WebElement boardsElement = driver.findElement(boardsButton);
        return boardsElement.isDisplayed();
    }

    public void createNewBoard() {
        WebElement createButtonElement = driver.findElement(createButton);
        createButtonElement.click();
        WebElement createBoardButtoElement = driver.findElement(createBoardButton);
        createBoardButtoElement.click();
        WebElement boardTitleInputElement = driver.findElement(boardTitleInput);
        boardTitleInputElement.sendKeys("Created by UI - Selenium");
        WebElement createBoardSubmitButtonElement = driver.findElement(createBoardSubmitButton);
        createBoardSubmitButtonElement.click();
    }
}
