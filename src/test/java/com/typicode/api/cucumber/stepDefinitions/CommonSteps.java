package com.typicode.api.cucumber.stepDefinitions;

import com.typicode.api.utils.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class CommonSteps {
    private TestContext testContext;

    public CommonSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("I send a GET request to {string}")
    public void i_send_get_request_to(String endpoint) {
        Response apiResponse;
        try {
            apiResponse = ApiUtils.get(endpoint);
            testContext.setResponse(apiResponse);
            if (apiResponse == null) {
                throw new RuntimeException("GET request to " + endpoint + " returned null response.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred during GET request to " + endpoint + ": " + e.getMessage());
            e.printStackTrace();
            fail("API request failed due to an exception: " + e.getMessage());
        }
        if (testContext.getResponse() == null) {
            fail("Response was not successfully stored in TestContext.");
        }
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        Response currentResponse = testContext.getResponse();

        if (currentResponse == null) {
            fail("Cannot verify status code: API Response is null in TestContext.");
        }

        assertEquals("Expected status code did not match", expectedStatusCode.intValue(), currentResponse.getStatusCode());
    }

    @Then("the response body's {string} should be {string}")
    public void the_response_body_s_should_be(String jsonPath, String expectedValue) {
        Response currentResponse = testContext.getResponse();
        if (currentResponse == null) {
            fail("Cannot verify response body: API Response is null in TestContext.");
        }

        String actualValue = currentResponse.jsonPath().getString(jsonPath);
        assertEquals("Expected value for JSON path '" + jsonPath + "' did not match", expectedValue, actualValue);
    }

    @Then("the response body's {string} should be greater than {int}")
    public void the_response_body_s_should_be_greater_than(String jsonPath, Integer expectedMinimumValue) {
        Response currentResponse = testContext.getResponse();

        if (currentResponse == null) {
            fail("Cannot verify response body: API Response is null in TestContext.");
        }

        try {
            Integer actualValue = currentResponse.jsonPath().getInt(jsonPath);
            assertTrue("Expected '" + jsonPath + "' is less than 0", actualValue > expectedMinimumValue);
        } catch (io.restassured.path.json.exception.JsonPathException e) {
            fail("Could not find or convert '" + jsonPath + "' to an integer in the response body. Error: " + e.getMessage());
        } catch (Exception e) {
            fail("An error occurred while checking '" + jsonPath + "' greater than " + expectedMinimumValue + ": " + e.getMessage());
        }
    }
}
