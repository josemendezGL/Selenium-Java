package com.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "dev"); // Por defecto usa "dev" si no se especifica
        String configFileName = "config-" + env + ".properties";

        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(configFileName)) {
            if (input != null) {
                properties.load(input);
            } else {
                System.out.println("Config file not found: " + configFileName + ". Using default settings.");
                // Aquí podrías establecer valores por defecto, por ejemplo:
                properties.setProperty("site.url", "https://default.example.com");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
