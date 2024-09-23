package stepDefinitions;

import cofig.ConfigurationManager;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class Hooks {

    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private static final String driverScope = ConfigurationManager.getProperty("driver.scope", "scenario");

    // Scenario-level setup using Cucumber hooks
    @Before
    public void setUpScenario() {
        logger.info("Scenario is starting...");
        if ("scenario".equals(driverScope) && driver == null) {
            driver = DriverFactory.getDriver();
            logger.info("WebDriver initialized for scenario scope.");
        }
    }

    // Scenario-level tear down using Cucumber hooks
    @After
    public void tearDownScenario() {
        logger.info("Scenario is ending...");
        if ("scenario".equals(driverScope)) {
            DriverFactory.quitDriver();
            driver = null;
            logger.info("WebDriver quit for scenario scope and set to null.");
        }
    }

    // Class-level setup using TestNG hooks
    @BeforeClass
    public void setUpClass() {
        logger.info("Test class is starting...");
        if ("class".equals(driverScope) && driver == null) {
            driver = DriverFactory.getDriver();
            logger.info("WebDriver initialized for class scope.");
        }
    }

    // Class-level tear down using TestNG hooks
    @AfterClass
    public void tearDownClass() {
        logger.info("Test class is ending...");
        if ("class".equals(driverScope)) {
            DriverFactory.quitDriver();
            driver = null;
            logger.info("WebDriver quit for class scope and set to null.");
        }
    }

    // Suite-level setup using TestNG hooks
    @BeforeSuite
    public void setUpSuite() {
        logger.info("Test suite is starting...");
        if ("suite".equals(driverScope) && driver == null) {
            driver = DriverFactory.getDriver();
            logger.info("WebDriver initialized for suite scope.");
        }
    }

    // Suite-level tear down using TestNG hooks
    @AfterSuite
    public void tearDownSuite() {
        logger.info("Test suite is ending...");
        if ("suite".equals(driverScope)) {
            DriverFactory.quitDriver();
            driver = null;
            logger.info("WebDriver quit for suite scope and set to null.");
        }
    }

    // Getter for WebDriver
    public static WebDriver getDriver() {
        return driver;
    }
}