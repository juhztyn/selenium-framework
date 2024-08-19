package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

import static cofig.UrlConstants.HOME;

public class HomeSteps {
    private final HomePage homePage;

    public HomeSteps() {
        BaseSteps baseSteps = new BaseSteps();
        this.homePage = new HomePage(baseSteps.getBaseDriver());
    }

    // Validation steps
    @Then("the user should see the {string} on the home page")
    public void the_user_should_see_the_on_the_home_page(String elementName) {
        Assert.assertTrue(homePage.isHomePageElementDisplayed(elementName), "Expected to see " + elementName + " on the home page, but it was not visible.");
    }
}