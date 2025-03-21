package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import static cofig.UrlConstants.URL_MAP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    // --- Common Navigation Steps ---
    @Given("the user navigates to the {string} page")
    public void the_user_navigates_to_the_page(String urlKey) {
        String expectedUrl = URL_MAP.get(urlKey);

        getBaseDriver().get(expectedUrl);

        assertEquals(getBaseDriver().getCurrentUrl(), expectedUrl);
    }

    // --- Common Interaction Steps ---
    @When("the user clicks on the {string}")
    public void the_user_clicks_on_the(String element) {
        basePage.clickCommonElement(element);
    }

    // --- Common Validation Steps ---
    @Then("the user is on the {string} page")
    public void the_user_is_on_the_page(String urlKey) {
        String expectedUrl = URL_MAP.get(urlKey);

        assertEquals(getBaseDriver().getCurrentUrl(), expectedUrl);
    }

    @Then("the user should see the {string}")
    public void the_user_should_see_the(String elementName) {
        assertTrue(basePage.isCommonElementDisplayed(elementName), "Expected to see " + elementName + ", but it was not visible.");
    }
}
