package com.typicode.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties;
    private static final String CONFIG_FILE = "config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try {
            InputStream inputStream = ConfigManager.class.getClassLoader()
                    .getResourceAsStream(CONFIG_FILE);

            if (inputStream != null) {
                properties.load(inputStream);
                inputStream.close();
            } else {
                System.err.println("Config file not found, using default URL");
            }
        } catch (IOException e) {
            System.err.println("Failed to load config: " + e.getMessage());
        }
    }

    public static String getEnvironment() {
        // Allow system property to override config file
        return System.getProperty("environment",
                properties != null ? properties.getProperty("environment", "dev") : "dev");
    }

    public static String getBaseUrl() {
        String environment = getEnvironment();
        String url = null;

        if (properties != null) {
            url = properties.getProperty(environment + ".url");
        }

        // Fallback to hardcoded URL if config fails
        if (url == null) {
            url = "";
        }

        return url;
    }

}