package stepDefinitions;

import helper.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.LoginPage;

import static cofig.UrlConstants.HOME;
import static cofig.UrlConstants.LOGIN;

public class LoginSteps {
    private final LoginPage loginPage;

    public LoginSteps() {
        BaseSteps baseSteps = new BaseSteps();
        this.loginPage = new LoginPage(baseSteps.getBaseDriver());
    }

    // Validation steps
    @Then("the user should see the {string} on the login page")
    public void the_user_should_see_the_on_the_login_page(String elementName) {
        Assert.assertTrue(loginPage.isLoginPageElementDisplayed(elementName), "Expected to see " + elementName + " on the login page, but it was not visible.");
    }

    // Interaction steps
    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.clickLogin();
    }
}
