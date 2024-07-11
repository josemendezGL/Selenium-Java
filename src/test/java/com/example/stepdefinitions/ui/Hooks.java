package com.example.stepdefinitions.ui;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.example.pageobjects.BasePage;
import com.example.utils.CustomWebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks extends BasePage {

    public Hooks(WebDriver driver) {
        super(driver);
    }

    public Hooks() {
        super(CustomWebDriverManager.getDriver());
        driver = CustomWebDriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.log("Scenario failed, taking screenshot");
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot of the error");
        }

    }

}
