package com.typicode.api.cucumber.stepDefinitions;

import com.typicode.api.utils.ApiUtils;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

public class CommentSteps {

    private Response response;
    private TestContext testContext;

    public CommentSteps(TestContext testContext) {
        this.testContext = testContext;
    }
    @Given("I send a POST request to {string} with body:")
    public void i_send_post_request_to(String endpoint, String body) {
        Response apiResponse;
        try {
            apiResponse = ApiUtils.post(endpoint, body);
            testContext.setResponse(apiResponse);

            if (apiResponse == null) {
                throw new RuntimeException("POST request to " + endpoint + " returned null response.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred during POST request to " + endpoint + ": " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("POST request failed due to an exception: " + e.getMessage(), e);
        }
    }
}