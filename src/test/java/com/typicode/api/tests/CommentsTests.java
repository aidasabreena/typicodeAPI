package com.typicode.api.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/comments.feature",
        glue = {"com.typicode.api.cucumber.stepDefinitions", "com.typicode.api.cucumber.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/login-report.html",
                "json:target/cucumber-reports/login-report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class CommentsTests {
}