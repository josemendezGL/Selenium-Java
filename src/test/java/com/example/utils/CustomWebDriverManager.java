package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public abstract class CustomWebDriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // Configuración del WebDriver
            WebDriverManager.chromedriver().driverVersion("126.0.6478.62").setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--window-size=1920x1080");

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

            // Cargar la URL desde el archivo de configuración
            String url = loadUrlFromConfig();
            driver.get(url);
        }
        return driver;
    }

    private static String loadUrlFromConfig() {
        Properties properties = new Properties();
        String env = System.getProperty("env", "dev"); // 'dev' es el valor por defecto
        String filename = String.format("src/test/resources/credentials-%s.properties", env);

        try (FileInputStream input = new FileInputStream(filename)) {
            properties.load(input);
            return properties.getProperty("site.url", "https://default.url.com/");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load URL from properties file: " + filename);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
