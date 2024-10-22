package cofig;

import helper.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class UrlConstants {
    private static final Logger logger = LogManager.getLogger(UrlConstants.class);

    // Retrieve the base URL dynamically based on the environment
    private static final String BASE_URL = ConfigurationManager.getEnvironmentProperty("base.url");

    // Constants using the base URL
    public static final String LOGIN = BASE_URL;
    public static final String HOME = BASE_URL + "inventory.html";

    // Map of URLs to their corresponding strings
    public static final Map<String, String> URL_MAP = Map.of(
            "LOGIN", LOGIN,
            "HOME", HOME
    );
}