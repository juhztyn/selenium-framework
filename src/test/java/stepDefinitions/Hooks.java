package stepDefinitions;

import cofig.ConfigurationManager;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private static final String driverScope = ConfigurationManager.getProperty("driver.scope", "scenario");

    @Before
    public void setUpScenario() {
        logger.info("Scenario is starting...");
        if ("scenario".equals(driverScope) && driver == null) {
            driver = DriverFactory.getDriver();
            logger.info("WebDriver initialized for scenario scope.");
        }
    }

    @After
    public void tearDownScenario() {
        logger.info("Scenario is ending...");
        if ("scenario".equals(driverScope)) {
            DriverFactory.quitDriver();
            driver = null;
            logger.info("WebDriver quit for scenario scope and set to null.");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}