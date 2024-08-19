package pages;

import helper.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
        locators.put("login logo", By.className("login_logo"));
        locators.put("accepted usernames section", By.id("login_credentials"));
        locators.put("accepted passwords section", By.className("login_password"));

        // Form locators
        locators.put("username field", By.id("user-name"));
        locators.put("password field", By.id("password"));
        locators.put("login button", By.id("login-button"));
        locators.put("login error container", By.cssSelector("[class*='error-message-container']"));
        locators.put("login error message", By.cssSelector("[data-test='error']"));
        locators.put("login error close button", By.cssSelector("[class*='error-button']"));

        // Error messages
        locators.put("locked out error", By.xpath("//h3[contains(text(), 'Sorry, this user has been locked out.')]"));

    }

    // Validation Methods
    public boolean isLoginPageElementDisplayed(String elementName) {
        By locator = locators.get(elementName);
        WebElement element = CommonUtils.waitForVisibility(driver, locator);
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            System.err.println("Failed to check visibility for element: " + elementName + " - " + e.getMessage());
            return false;
        }
    }

    // Interaction Methods
    public void enterUsername(String username) {
        WebElement usernameField = CommonUtils.waitForElementToBeClickable(driver, locators.get("username field"));
        try {
            usernameField.sendKeys(username);
        } catch (Exception e) {
            System.err.println("Failed to send keys to the username field: " + e.getMessage());
            throw new RuntimeException("An error occurred while sending keys to the username field", e);
        }
    }

    public void enterPassword(String password) {
        WebElement passwordField = CommonUtils.waitForElementToBeClickable(driver, locators.get("password field"));
        try {
            passwordField.sendKeys(password);
        } catch (Exception e) {
            System.err.println("Failed to send keys to the password field: " + e.getMessage());
            throw new RuntimeException("An error occurred while sending keys to the password field", e);
        }
    }

    public void clickLogin() {
        WebElement loginButton = CommonUtils.waitForElementToBeClickable(driver, locators.get("login button"));
        try {
            loginButton.click();
        } catch (Exception e) {
            System.err.println("Failed to click the login button: " + e.getMessage());
            throw new RuntimeException("An error occurred while clicking the login button", e);
        }
    }
}
