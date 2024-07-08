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
    private BaseSteps baseSteps;
    private final LoginPage loginPage;

    public LoginSteps() {
        this.baseSteps = new BaseSteps();
        this.loginPage = new LoginPage(baseSteps.getBaseDriver());
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        baseSteps.getBaseDriver().get(LOGIN);
    }

    @When("the user enters valid username {string} and password {string}")
    public void the_user_enters_valid_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.clickLogin();
    }

    @And("the user should be redirected to the home page")
    public void the_user_should_be_redirected_to_the_page() {
        Assert.assertEquals(baseSteps.getBaseDriver().getCurrentUrl(), HOME);
    }
}
