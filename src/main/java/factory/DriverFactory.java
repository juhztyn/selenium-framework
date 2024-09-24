package factory;

import cofig.ConfigurationManager;
import helper.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static final Logger logger = LoggerUtil.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Prevent instantiation
    private DriverFactory() {}

    // Get driver based on config file properties
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = ConfigurationManager.getProperty("browser", "chrome").toLowerCase();
            boolean headless = Boolean.parseBoolean(ConfigurationManager.getProperty("headless", "false"));
            logger.info("Starting WebDriver for browser: {} in headless mode: {}", browser, headless);

            try {
                switch (browser) {
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        if (headless) {
                            firefoxOptions.addArguments("--headless");
                        }
                        driver.set(new FirefoxDriver(firefoxOptions));
                        logger.info("Firefox WebDriver initialized successfully in headless mode: {}", headless);
                        break;
                    case "chrome":
                    default:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        if (headless) {
                            chromeOptions.addArguments("--headless");
                        }
                        chromeOptions.addArguments("--remote-allow-origins=*");
                        driver.set(new ChromeDriver(chromeOptions));
                        logger.info("Chrome WebDriver initialized successfully in headless mode: {}", headless);
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
