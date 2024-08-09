package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public abstract class CustomWebDriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // Obtener la URL del Selenium Grid desde una variable de entorno o propiedad
            // del sistema
            String gridUrl = System.getenv("GRID_URL");
            if (gridUrl == null) {
                gridUrl = System.getProperty("gridUrl");
            }

            // Obtener el navegador deseado desde una propiedad del sistema o variable de
            // entorno
            // String browser = System.getenv("BROWSER");
            String browser = System.getProperty("browser");
            System.out.println("Browser: " + browser);
            if (browser == null || browser.isEmpty()) {
                browser = "chrome"; // Valor por defecto solo si no se pasó el parámetro
            }

            // Configurar las opciones del navegador basado en el browser deseado
            switch (browser.toLowerCase()) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    configureHeadlessMode(firefoxOptions);
                    if (gridUrl != null && !gridUrl.isEmpty()) {
                        try {
                            driver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            throw new RuntimeException("La URL del Selenium Grid es inválida: " + gridUrl);
                        }
                    } else {
                        WebDriverManager.firefoxdriver().setup();
                        driver = new RemoteWebDriver(firefoxOptions);
                    }
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    configureHeadlessMode(edgeOptions);
                    if (gridUrl != null && !gridUrl.isEmpty()) {
                        try {
                            driver = new RemoteWebDriver(new URL(gridUrl), edgeOptions);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            throw new RuntimeException("La URL del Selenium Grid es inválida: " + gridUrl);
                        }
                    } else {
                        WebDriverManager.edgedriver().setup();
                        driver = new RemoteWebDriver(edgeOptions);
                    }
                    break;

                case "safari":
                    SafariOptions safariOptions = new SafariOptions();
                    if (gridUrl != null && !gridUrl.isEmpty()) {
                        try {
                            driver = new RemoteWebDriver(new URL(gridUrl), safariOptions);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            throw new RuntimeException("La URL del Selenium Grid es inválida: " + gridUrl);
                        }
                    } else {
                        driver = new RemoteWebDriver(safariOptions);
                    }
                    break;

                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    configureHeadlessMode(chromeOptions);
                    if (gridUrl != null && !gridUrl.isEmpty()) {
                        try {
                            driver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            throw new RuntimeException("La URL del Selenium Grid es inválida: " + gridUrl);
                        }
                    } else {
                        WebDriverManager.chromedriver().setup();
                        driver = new RemoteWebDriver(chromeOptions);
                    }
                    break;
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.get("https://trello.com/");
        }
        return driver;
    }

    private static void configureHeadlessMode(Object options) {
        String headless = System.getenv("HEADLESS");
        if (headless == null) {
            headless = System.getProperty("headless", "false");
        }

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
