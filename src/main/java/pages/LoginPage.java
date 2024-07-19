package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        initLocators();
    }

    // Map to store locators
    private final Map<String, By> locators = new HashMap<>();

    // Initialize locators
    private void initLocators() {
        // General locators
        locators.put("loginLogo", By.className("login_logo"));
        locators.put("acceptedUsernamesHeader", By.xpath("//h4[contains(text(), 'Accepted usernames are:')]"));
        locators.put("acceptedPasswordsHeader", By.xpath("//h4[contains(text(), 'Password for all users:')]"));

        // Form locators
        locators.put("usernameField", By.id("user-name"));
        locators.put("passwordField", By.id("password"));
        locators.put("loginButton", By.id("login-button"));
        locators.put("loginError", By.xpath("//h3[@data-test='error']"));

    }

    // Validation Methods
    public boolean isLoginPageElementDisplayed(String elementName) {
        try {
            By locator = locators.get(elementName);
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Interaction Methods
    public void enterUsername(String username) {
        driver.findElement(locators.get("usernameField")).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(locators.get("passwordField")).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(locators.get("loginButton")).click();
    }
}
