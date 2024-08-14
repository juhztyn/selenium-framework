package cofig;

import java.util.Map;

public class UrlConstants {

    // Constants
    private static final String PREFIX = "https://www.saucedemo.com/";
    public static final String LOGIN = PREFIX;
    public static final String HOME = PREFIX + "inventory.html";

    // Map of URLs to their corresponding strings
    public static final Map<String, String> URLMap = Map.of(
            "LOGIN", LOGIN,
            "HOME", HOME
            // Add other URLs if needed
    );
}