package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public abstract class CustomWebDriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().driverVersion("126.0.6478.62").setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("window-size=1920x1080");

            // Obtener la variable de entorno o propiedad del sistema para el modo headless
            String headless = System.getenv("HEADLESS");
            if (headless == null) {
                headless = System.getProperty("headless", "false");
            }

            if (Boolean.parseBoolean(headless)) {
                options.addArguments("--headless");
                options.addArguments("--disable-gpu"); // Recomendado para el modo headless
            }

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.get("https://trello.com/");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
