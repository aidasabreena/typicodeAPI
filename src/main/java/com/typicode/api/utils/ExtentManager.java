package com.typicode.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ExtentManager {

    private static final Properties properties = new Properties();
    private static final StringBuilder apiLogBuilder = new StringBuilder();

    static {
        try (InputStream input = ExtentManager.class
                .getClassLoader()
                .getResourceAsStream("extent.properties")) {

            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("extent.properties not found in classpath");
            }

        } catch (IOException e) {
            System.err.println("Failed to load extent.properties: " + e.getMessage());
        }
    }
    public static String get(String key) {
        return properties.getProperty(key);
    }
    public static void appendLog(String logLine) {
        apiLogBuilder.append(logLine).append(System.lineSeparator());
    }

    public static String getLog() {
        return apiLogBuilder.toString();
    }

    public static void clearLog() {
        apiLogBuilder.setLength(0);
    }
}
