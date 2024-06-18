package com.example.testrunners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/ui", glue = { "com.example.stepdefinitions.ui" }, plugin = {
                "pretty", "html:build/reports/cucumber-ui.html", "json:build/reports/cucumber-ui.json" })
public class UITestRunner {
}
