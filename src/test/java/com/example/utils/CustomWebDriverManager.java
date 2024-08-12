package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public abstract class CustomWebDriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // Obtener el navegador deseado desde el archivo de configuración
            String browser = ConfigManager.getProperty("chrome"); // Valor por defecto: "chrome"
            System.out.println("Browser: " + browser);

            // Configurar las opciones del navegador basado en el browser deseado
            switch (browser.toLowerCase()) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    configureHeadlessMode(firefoxOptions);
                    WebDriverManager.firefoxdriver().setup();
                    driver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    configureHeadlessMode(edgeOptions);
                    WebDriverManager.edgedriver().setup();
                    driver = new org.openqa.selenium.edge.EdgeDriver(edgeOptions);
                    break;

                case "safari":
                    SafariOptions safariOptions = new SafariOptions();
                    driver = new org.openqa.selenium.safari.SafariDriver(safariOptions);
                    break;

                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    configureHeadlessMode(chromeOptions);
                    WebDriverManager.chromedriver().setup();
                    driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
                    break;
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.get(ConfigManager.getProperty("https://default.url.com/"));
        }
        return driver;
    }

    private static void configureHeadlessMode(Object options) {
        String headless = ConfigManager.getProperty("false");

        if (Boolean.parseBoolean(headless)) {
            if (options instanceof ChromeOptions) {
                ((ChromeOptions) options).addArguments("--headless", "--disable-gpu", "window-size=1920x1080");
            } else if (options instanceof FirefoxOptions) {
                ((FirefoxOptions) options).addArguments("--headless", "--disable-gpu", "window-size=1920x1080");
            } else if (options instanceof EdgeOptions) {
                ((EdgeOptions) options).addArguments("--headless", "--disable-gpu", "window-size=1920x1080");
            }
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
