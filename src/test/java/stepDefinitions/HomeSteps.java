package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.HomePage;

import static cofig.UrlConstants.HOME;

public class HomeSteps {
    private final BaseSteps baseSteps;
    private final HomePage homePage;

    public HomeSteps() {
        this.baseSteps = new BaseSteps();
        this.homePage = new HomePage(baseSteps.getBaseDriver());
    }

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        baseSteps.getBaseDriver().get(HOME);
    }

    @Then("the user can see the home page header")
    public void the_user_can_see_the_home_page_header() {
        Assert.assertTrue(homePage.isHomeHeaderDisplayed());
    }
}