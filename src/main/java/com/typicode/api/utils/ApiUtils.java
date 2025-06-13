package com.typicode.api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUtils {

    private static final String BASE_URL = ConfigManager.getBaseUrl();

    public static RequestSpecification request() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json");
    }
    public static Response get(String endpoint) {
        return request().when().get(endpoint);
    }
    public static Response post(String endpoint, String body) {
        return request()
                .body(body)
                .when()
                .post(endpoint);
    }
}
