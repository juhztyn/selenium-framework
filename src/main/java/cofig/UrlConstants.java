package cofig;

import helper.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class UrlConstants {
    private static final Logger logger = LoggerUtil.getLogger(UrlConstants.class);

    // Load the base URL from the config file
    private static String getBaseUrl() {
        Properties properties = new Properties();
        try {
            FileInputStream configStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(configStream);
            configStream.close();
        } catch (IOException e) {
            logger.error("Failed to load base URL from config file at src/main/resources/config.properties", e);
            throw new RuntimeException("Failed to load base URL from config file.");
        }
        return properties.getProperty("base.url");
    }

    // Constants
    private static final String PREFIX = getBaseUrl();
    public static final String LOGIN = PREFIX;
    public static final String HOME = PREFIX + "inventory.html";

    // Map of URLs to their corresponding strings
    public static final Map<String, String> URLMap = Map.of(
            "LOGIN", LOGIN,
            "HOME", HOME
    );
}