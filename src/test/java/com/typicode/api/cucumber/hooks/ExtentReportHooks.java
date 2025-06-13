package com.typicode.api.cucumber.hooks;

import com.typicode.api.utils.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class ExtentReportHooks{
    private static final String APIlogs = ExtentManager.get("apilog.dir");
    private static final Logger logger = Logger.getLogger(ExtentReportHooks.class.getName());


    @Before
    public void beforeScenario(Scenario scenario) {
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

            String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
            String logFileName = APIlogs + "/" + safeName + "_log.txt";

            try (FileWriter writer = new FileWriter(logFileName)) {
                writer.write(ExtentManager.getLog());  // Write collected logs
            }

            logger.info("API log saved: " + logFileName);
        } catch (IOException e) {
            logger.warning("Could not save API log: " + e.getMessage());
        }
    }
}