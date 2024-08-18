package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    // Constructor that initializes the WebDriver instance
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Clicks on the element found by the locator
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    // Sends the specified text to the element found by the locator
    public void sendKeys(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    // Retrieves the text of the element found by the locator
    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    // Waits for the element to become visible within the specified timeout
    public void waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Checks if the element is present on the page
    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    // Selects an option from a dropdown by visible text
    public void selectFromDropdown(By locator, String value) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(value);
    }

    // Retrieves the specified attribute of the element
    public String getAttribute(By locator, String attribute) {
        return driver.findElement(locator).getAttribute(attribute);
    }

    // Waits for the element to become clickable within the specified timeout
    public void waitForElementClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Switches the WebDriver context to an iframe by the locator
    public void switchToIframe(By locator) {
        driver.switchTo().frame(driver.findElement(locator));
    }

    // Switches the WebDriver context back to the default content from an iframe
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Scrolls the page until the element is in view
    public void scrollToElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }

    // Clears the text field found by the locator
    public void clearField(By locator) {
        driver.findElement(locator).clear();
    }

    // Performs a double-click on the element found by the locator
    public void doubleClick(By locator) {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(locator)).perform();
    }

    // Drags an element from the source locator and drops it on the target locator
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(driver.findElement(sourceLocator), driver.findElement(targetLocator)).perform();
    }

    // Hovers over the element found by the locator
    public void hoverOverElement(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).perform();
    }

    // Performs a right-click (context click) on the element found by the locator
    public void rightClick(By locator) {
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(locator)).perform();
    }

    // Accepts the currently displayed alert
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    // Dismisses the currently displayed alert
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    // Sends text to the currently displayed alert
    public void sendTextToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    // Retrieves the text from the currently displayed alert
    public String getTextFromAlert() {
        return driver.switchTo().alert().getText();
    }

    // Waits for an alert to be present within the specified timeout
    public void waitForAlert(int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    // Refreshes the current page
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // Navigates back to the previous page
    public void navigateBack() {
        driver.navigate().back();
    }

    // Retrieves the current URL of the page
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Navigates to the specified URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    // Finds and returns a WebElement using the locator
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    // Finds and returns a WebElement that contains the specified text
    public WebElement findElementWithText(String text) {
        return driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
    }

    // Finds and clicks on a WebElement that contains the specified text
    public void clickElementWithText(String text) {
        findElementWithText(text).click();
    }
}
