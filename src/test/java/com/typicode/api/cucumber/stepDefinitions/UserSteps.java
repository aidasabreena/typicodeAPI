package com.typicode.api.cucumber.stepDefinitions;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserSteps {
    private Response response;

    @Then("the response should contain a user with id {int}")
    public void response_should_contain_user_id(int id) {
        Response response = CommonSteps.getResponse();  // Use shared response
        assertThat(response.jsonPath().getInt("id"), equalTo(id));
    }
}