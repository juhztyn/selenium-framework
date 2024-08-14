package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.BasePage;

import static cofig.UrlConstants.URLMap;

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
    @And("the user should be redirected to the {string} page")
    public void the_user_should_be_redirected_to_the_page(String urlKey) {
        String expectedUrl = URLMap.get(urlKey);

        Assert.assertEquals(getBaseDriver().getCurrentUrl(), expectedUrl);
    }

    @Then("the user should see the navbar show up")
    public void the_user_should_see_the_navbar_show_up() {
        Assert.assertTrue(basePage.isHamburgerButtonDisplayed(), "Element is visible on the page");
    }

    // Common interaction steps
    @Given("the user is on the {string} page")
    public void the_user_is_on_the_page(String urlKey) {
        String expectedUrl = URLMap.get(urlKey);

        // Navigate to the target page
        getBaseDriver().get(expectedUrl);

        // Assert that we are on the correct page
        Assert.assertEquals(getBaseDriver().getCurrentUrl(), expectedUrl);
    }

    @And("the user clicks on the hamburger button")
    public void the_user_clicks_on_the_hamburger_button() {
        basePage.clickHamburgerIcon();
    }
}
