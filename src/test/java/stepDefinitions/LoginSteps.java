package stepDefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;

import static cofig.UrlConstants.LOGIN;

public class LoginSteps {
    private final WebDriver driver;
    private final LoginPage loginPage;

    public LoginSteps() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get(LOGIN);
        System.out.println("HERE 0");
    }

    @When("the user enters valid username and password")
    public void the_user_enters_valid_username_and_password() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
    }

    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the homepage")
    public void the_user_should_be_redirected_to_the_homepage() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}
