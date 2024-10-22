package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    private final LoginPage loginPage;

    public LoginSteps() {
        BaseSteps baseSteps = new BaseSteps();
        this.loginPage = new LoginPage(baseSteps.getBaseDriver());
    }

    // --- Login Interaction Steps ---
    // This step is used to log the user into the application (both successful and unsuccessful login attempts based on input credentials)
    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    // --- Login Validation Steps ---
    // This step is used to validate an object is displayed on the page
    @Then("the user should see the {string} on the login page")
    public void the_user_should_see_the_on_the_login_page(String elementName) {
        assertTrue(loginPage.isLoginPageElementDisplayed(elementName), "Expected to see " + elementName + " on the login page, but it was not visible.");
    }
}
