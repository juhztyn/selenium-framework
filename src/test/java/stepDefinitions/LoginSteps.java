package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
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

    // Common interaction steps
    @When("the user enters valid username {string} and password {string}")
    public void the_user_enters_valid_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.clickLogin();
    }
}
