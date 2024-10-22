package cofig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private static final Properties properties;
    private static final Logger logger = LogManager.getLogger(ConfigurationManager.class);

    // Private constructor to prevent instantiation
    private ConfigurationManager() {}

    // Load properties from configuration file
    static {
        properties = new Properties();
        try {
            FileInputStream configStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(configStream);
            configStream.close();
            logger.info("Configuration properties loaded successfully.");
        } catch (IOException e) {
            logger.error("Failed to load configuration properties.", e);
            throw new RuntimeException("Failed to load configuration properties.", e);
        }
    }

    // Provide access to configuration properties
    public static String getProperty(String key, String defaultValue) {
        // Check if the property was passed as a system property (e.g., via VM options)
        String systemProperty = System.getProperty(key);
        return (systemProperty != null) ? systemProperty : properties.getProperty(key, defaultValue);
    }

    // Return environment-specific property
    public static String getEnvironmentProperty(String key) {
        // Read environment from system property or default to 'local'
        String environment = System.getProperty("env", "local");
        String fullKey = key + "." + environment;
        String value = properties.getProperty(fullKey);

        if (value == null) {
            logger.warn("Property '{}' not found for environment '{}'. No specific value set for this environment.", key, environment);
            // Optional: return a default value here if you have one in your config
            String defaultValue = properties.getProperty(key);
            if (defaultValue != null) {
                logger.info("Using default value '{}' for key '{}'.", defaultValue, key);
                return defaultValue;
            } else {
                logger.error("No default value found for key '{}'. Returning null.", key);
            }
        } else {
            logger.info("Returning environment-specific property: '{}' for key '{}' and environment '{}'.", value, key, environment);
        }

        return value;
    }
}