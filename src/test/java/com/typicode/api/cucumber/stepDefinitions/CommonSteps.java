package com.typicode.api.cucumber.stepDefinitions;

import com.typicode.api.utils.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class CommonSteps {

    private TestContext testContext;
    public CommonSteps(TestContext testContext) {
        this.testContext = testContext;
    }
    private static Response response;
    @Given("I send a GET request to {string}")
    public void i_send_get_request_to(String endpoint) {
        response = ApiUtils.get(endpoint);
        if (response == null) {
            throw new RuntimeException("GET request to " + endpoint + " returned null response");
        }
        System.out.println("GET " + endpoint + " => Status: " + response.getStatusCode());
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        assertEquals("Expected status code did not match", expectedStatusCode.intValue(), testContext.getResponse().getStatusCode());
    }
    @Then("the response body's {string} should be {string}")
    public void the_response_body_s_should_be(String jsonPath, String expectedValue) {
        String actualValue = testContext.getResponse().jsonPath().getString(jsonPath);
        assertEquals("Expected value for JSON path '" + jsonPath + "' did not match", expectedValue, actualValue);
    }
}
