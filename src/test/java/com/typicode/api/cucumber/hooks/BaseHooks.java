package com.typicode.api.cucumber.hooks;

import com.typicode.api.utils.ConfigManager;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseHooks {
    protected static Logger logger;

    @Before
    public void setUp(Scenario scenario) {
        logger = Logger.getLogger(BaseHooks.class.getName());
        logger.setLevel(Level.INFO);

        RestAssured.baseURI = ConfigManager.getBaseUrl();
        logger.info("Setup done, starting scenario" + scenario.getName());
    }
}