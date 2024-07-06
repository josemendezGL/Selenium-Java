package com.example.pageobjects.locators;

import org.openqa.selenium.By;

public class DashboardPageLocators {
    public static final By boardsButton = By.xpath("//*[text()='Boards']");
    public static final By createButton = By.cssSelector("[data-testid='header-create-menu-button']");
    public static final By createBoardButton = By.cssSelector("[data-testid='header-create-board-button']");
    public static final By boardTitleInput = By.cssSelector("[data-testid='create-board-title-input']");
    public static final By createBoardSubmitButton = By.cssSelector("[data-testid='create-board-submit-button']");
}
