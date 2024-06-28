package stepDefinitions;

import factory.DriverFactory;
import io.cucumber.java.After;

public class Hooks {
    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
