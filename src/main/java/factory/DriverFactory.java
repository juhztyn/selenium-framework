package factory;

import cofig.UrlConstants;
import helper.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {
    private static final Logger logger = LoggerUtil.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Properties properties;

    // Load properties from configuration file
    static {
        properties = new Properties();
        try {
            FileInputStream configStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(configStream);
            configStream.close();
            logger.info("Configuration properties loaded successfully.");
        } catch (IOException e) {
            logger.error("Failed to load configuration properties at src/main/resources/config.properties", e);
            throw new RuntimeException("Failed to load configuration properties.");
        }
    }

    // Prevent instantiation
    private DriverFactory() {}

    // Get driver based on config file properties
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = properties.getProperty("browser", "chrome").toLowerCase();
            logger.info("Starting WebDriver for browser: {}", browser);

            try {
                switch (browser) {
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driver.set(new FirefoxDriver(firefoxOptions));
                        logger.info("Firefox WebDriver initialized successfully.");
                        break;
                    case "chrome":
                    default:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--remote-allow-origins=*");
                        driver.set(new ChromeDriver(chromeOptions));
                        logger.info("Chrome WebDriver initialized successfully.");
                        break;
                }

                driver.get().manage().window().maximize();
                logger.info("Browser window maximized.");
            } catch (Exception e) {
                logger.error("Error while initializing WebDriver for browser: {}", browser, e);
                throw new RuntimeException("Error while initializing WebDriver.", e);
            }
        }

        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
                logger.info("WebDriver quit successfully.");
                driver.remove();
            } catch (Exception e) {
                logger.error("Error while quitting WebDriver.", e);
                throw new RuntimeException("Error while quitting WebDriver.", e);
            }
        }
    }
}
















//public class DriverFactory {
//    private static WebDriver driver;
//
//    // Prevent instantiation
//    private DriverFactory() {}
//
//    public static WebDriver getDriver() {
//        if (driver == null) {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--remote-allow-origins=*");
//
//            driver = new ChromeDriver(options);
//            driver.manage().window().maximize();
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        }
//        System.out.println("Called getDriver()");
//        return driver;
//    }
//
//    public static void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//            System.out.println("WebDriver has been quit and set to null.");
//        }
//    }
//}
