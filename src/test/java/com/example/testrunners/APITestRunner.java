package com.example.testrunners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/api", glue = { "com.example.stepdefinitions.api" }, plugin = {
        "pretty", "html:build/reports/cucumber-api.html", "json:build/reports/cucumber-api.json" })
public class APITestRunner {
}
