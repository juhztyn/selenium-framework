package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;

import static cofig.UrlConstants.HOME;
import static cofig.UrlConstants.LOGIN;

public class LoginSteps {
    private final WebDriver driver;
    private final LoginPage loginPage;

    public LoginSteps() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get(LOGIN);
    }

    @When("the user enters valid username {string} and password {string}")
    public void the_user_enters_valid_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the homepage")
    public void the_user_should_be_redirected_to_the_homepage() {
        Assert.assertEquals(driver.getCurrentUrl(), HOME);
    }
}