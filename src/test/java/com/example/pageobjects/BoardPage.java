package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardPage extends BasePage {

    By boardNameLabel = By.cssSelector("[data-testid='board-name-display']");

    public BoardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBoardNameLabelVisible() {
        WebElement boardNameElement = driver.findElement(boardNameLabel);
        return boardNameElement.isDisplayed();
    }
}
