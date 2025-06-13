package com.typicode.api.cucumber.stepDefinitions;

import com.typicode.api.utils.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class CommentSteps {

    private Response response;
    private TestContext testContext;

    // Cucumber will automatically inject the TestContext instance
    public CommentSteps(TestContext testContext) {
        this.testContext = testContext;
    }
    @Given("I send a POST request to {string} with body:")
    public void i_send_post_request_to(String endpoint, String body) {
        response = ApiUtils.post(endpoint, body);
        if (response == null) {
            throw new RuntimeException("POST request to " + endpoint + " returned null response");
        }
        System.out.println("POST " + endpoint + " => Status: " + response.getStatusCode());
    }


    public Response getResponse() {
        return response;
    }
}