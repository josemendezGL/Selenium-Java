package com.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.pageobjects.locators.BoardPageLocators;

public class BoardPage extends BasePage {

    public BoardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBoardNameLabelVisible() {
        WebElement boardNameElement = driver.findElement(BoardPageLocators.boardNameLabel);
        return boardNameElement.isDisplayed();
    }

    public String getBoardName() {
        return getText(BoardPageLocators.boardNameLabel);
    }
}
