package com.typicode.api.cucumber.stepDefinitions;

import com.typicode.api.utils.ApiUtils;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

public class CommentSteps {
    private Response response;

    @Given("I send a GET request to {string}")
    public void i_send_get_request_to(String endpoint){
        response = ApiUtils.get(endpoint);
    }

    @Given("I send a POST request to {string} with body:")
    public void i_send_post_request_to(String endpoint, String body){
        response = ApiUtils.post(endpoint, body);
    }
}