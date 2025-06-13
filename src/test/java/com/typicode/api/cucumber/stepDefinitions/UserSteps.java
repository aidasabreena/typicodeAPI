package com.typicode.api.cucumber.stepDefinitions;

import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class UserSteps {
    private TestContext testContext;

    // Cucumber will automatically inject the TestContext instance
    public UserSteps(TestContext testContext) {
        this.testContext = testContext;
    }
}
