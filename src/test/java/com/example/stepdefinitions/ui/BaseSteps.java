package com.example.stepdefinitions.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseSteps {

    protected String username;
    protected String password;
    protected String apiKey;
    protected String apiToken;

    protected void loadCredentials() {
        Properties properties = new Properties();
        String env = System.getProperty("env", "dev"); // defalt value is "dev"
        String filename = String.format("src/test/resources/credentials-%s.properties", env);

        try (FileInputStream input = new FileInputStream(filename)) {
            properties.load(input);
            username = properties.getProperty("site.username");
            password = properties.getProperty("site.password");
            apiKey = properties.getProperty("trello.api.key");
            apiToken = properties.getProperty("trello.api.token");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load credentials from properties file: " + filename);
        }
    }
}
