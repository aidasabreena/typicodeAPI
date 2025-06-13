package com.typicode.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ExtentPropertiesManager {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ExtentPropertiesManager.class
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
}
