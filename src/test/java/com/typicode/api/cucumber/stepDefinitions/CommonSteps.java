package com.typicode.api.cucumber.stepDefinitions;

import com.typicode.api.utils.ApiUtils;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

public class CommonSteps {

    private static Response response;
    @Given("I send a GET request to {string}")
    public void i_send_get_request_to(String endpoint) {
        response = ApiUtils.get(endpoint);
        if (response == null) {
            throw new RuntimeException("GET request to " + endpoint + " returned null response");
        }
        System.out.println("GET " + endpoint + " => Status: " + response.getStatusCode());
    }

    public static Response getResponse() {
        return response;
    }
}
