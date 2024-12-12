package cofig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static final Properties properties = new Properties();
    private static final Logger logger = LogManager.getLogger(ConfigurationManager.class);
    private static Environment environment;

    // Static block to load properties
    static {
        try (FileInputStream configStream = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(configStream);
            logger.info("Configuration properties loaded successfully.");
        } catch (IOException e) {
            logger.error("Failed to load configuration properties.", e);
            throw new RuntimeException("Failed to load configuration properties.", e);
        }

        // Determine the environment
        String envName = System.getProperty("env", "local").toUpperCase();
        try {
            environment = Environment.valueOf(envName);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid environment '{}'. Defaulting to LOCAL.", envName);
            environment = Environment.LOCAL;
        }

        logger.info("Current environment set to '{}'.", environment.getName());
    }

    public static Environment getEnvironment() {
        return environment;
    }

    public static String getEnvironmentProperty(String key) {
        String fullKey = key + "." + environment.getName();
        String value = properties.getProperty(fullKey);

        if (value == null) {
            logger.warn("Property '{}' not found for environment '{}'. Attempting to retrieve default value.", key, environment.getName());
            value = properties.getProperty(key);
            if (value == null) {
                logger.error("No default value found for key '{}'. Returning null.", key);
            } else {
                logger.info("Using default value '{}' for key '{}'.", value, key);
            }
        } else {
            logger.info("Returning environment-specific property: '{}' for key '{}' and environment '{}'.", value, key, environment.getName());
        }

        return value;
    }

    // Method to retrieve a property with a default value and log the action
    public static String getProperty(String key, String defaultValue) {
        // Check if the property was passed as a system property (e.g., via VM options)
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            logger.info("System property '{}' found with value: '{}'.", key, systemProperty);
            return systemProperty;
        }

        // Check in config properties file
        String value = properties.getProperty(key);
        if (value != null) {
            logger.info("Property '{}' found in config file with value: '{}'.", key, value);
            return value;
        }

        // Log when using the default value
        logger.warn("Property '{}' not found. Using default value: '{}'.", key, defaultValue);
        return defaultValue;
    }

    // Example methods to retrieve specific environment properties
    public static String getBaseUrl() {
        return getEnvironmentProperty("base.url");
    }

    public static String getUsername(String userType) {
        return getEnvironmentProperty(userType + ".username");
    }

    public static String getPassword(String userType) {
        return getEnvironmentProperty(userType + ".password");
    }
}