package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.BasePage;
import pages.LoginPage;

public class BaseSteps {
    private final WebDriver driver;
    private final BasePage basePage;

    // Constructor
    public BaseSteps() {
        this.driver = Hooks.getDriver();
        this.basePage = new BasePage(getBaseDriver());
    }

    // Return the base driver
    public WebDriver getBaseDriver() {
        return driver;
    }

    // Common validation steps
    @Then("the user should see the navbar show up")
    public void the_user_should_see_the_navbar_show_up() {
        Assert.assertTrue(basePage.isHamburgerButtonDisplayed(), "Element is visible on the page");
    }

    // Common interaction steps
    @And("the user clicks on the hamburger button")
    public void the_user_clicks_on_the_hamburger_button() {
        basePage.clickHamburgerIcon();
    }
}
