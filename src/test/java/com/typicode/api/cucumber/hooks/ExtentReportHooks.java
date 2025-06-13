package com.typicode.api.cucumber.hooks;

import com.typicode.api.utils.ExtentPropertiesManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExtentReportHooks extends BaseHooks{

    private static final String APIlogs = ExtentPropertiesManager.get("apilog.dir");

    @Before
    public void beforeScenario(Scenario scenario) {
        // Log scenario start information
        System.out.println("Starting scenario: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            saveApiLog(scenario);
        }
        System.out.println("Scenario '" + scenario.getName() + "' status: " + scenario.getStatus());
    }
    private void saveApiLog(Scenario scenario) {
        try {
            File dir = new File(APIlogs);
            if (!dir.exists()) dir.mkdirs();

            String logFileName = APIlogs + "/" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + "_log.txt";
            FileWriter writer = new FileWriter(logFileName);
            writer.write(APIlogs());
            writer.close();

            logger.info("API log saved: " + logFileName);
        } catch (IOException e) {
            logger.warning("Could not save API log: " + e.getMessage());
        }
    }
}