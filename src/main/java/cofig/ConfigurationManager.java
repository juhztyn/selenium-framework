package cofig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private static final Properties properties;
    private static final Logger logger = LogManager.getLogger(ConfigurationManager.class);

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
        if (systemProperty != null) {
            return systemProperty;
        }
        return properties.getProperty(key, defaultValue);
    }
}