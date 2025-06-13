package com.typicode.api.cucumber.stepDefinitions;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserSteps {
    private Response response;

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the response should contain a user with id {int}")
    public void response_should_contain_user_id(int id) {
        Response response = CommonSteps.getResponse();  // Use shared response
        assertThat(response.jsonPath().getInt("id"), equalTo(id));
    }

    @Then("the response body's {string} should be {string}")
    public void the_response_body_s_should_be(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}