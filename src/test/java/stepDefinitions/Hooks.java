package stepDefinitions;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private static WebDriver driver;


    @Before
    public void setUp() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
        }
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        driver = null;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}