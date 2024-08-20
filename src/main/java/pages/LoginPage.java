package pages;

import helper.CommonUtils;
import helper.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends BasePage {
    private static final Logger logger = LoggerUtil.getLogger(LoginPage.class);

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
        locators.put("password field", By.id("passwordd"));
        locators.put("login button", By.id("login-button"));
        locators.put("login error container", By.cssSelector("[class*='error-message-container']"));
        locators.put("login error", By.cssSelector("[data-test='error']"));
        locators.put("login error close button", By.cssSelector("[class*='error-button']"));

        // Error messages
        locators.put("locked out error message", By.xpath("//h3[contains(text(), 'Sorry, this user has been locked out.')]"));

    }

    // Validation Methods
    public boolean isLoginPageElementDisplayed(String elementName) {
        By locator = locators.get(elementName);
        WebElement element = CommonUtils.waitForVisibility(driver, locator);
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            logger.error("Failed to check visibility for element: {}", elementName, e);
            return false;
        }
    }

    // Interaction Methods
    public void enterUsername(String username) {
        WebElement usernameField = CommonUtils.waitForElementToBeClickable(driver, locators.get("username field"));
        try {
            usernameField.sendKeys(username);
        } catch (Exception e) {
            logger.error("Failed to send keys to the username field: {}", username, e);
            throw new RuntimeException("An error occurred while sending keys to the username field", e);
        }
    }

    public void enterPassword(String password) {
        WebElement passwordField = CommonUtils.waitForElementToBeClickable(driver, locators.get("password field"));
        try {
            passwordField.sendKeys(password);
        } catch (Exception e) {
            logger.error("Failed to send keys password the username field: {}", password, e);
            throw new RuntimeException("An error occurred while sending keys to the password field", e);
        }
    }

    public void clickLogin() {
        WebElement loginButton = CommonUtils.waitForElementToBeClickable(driver, locators.get("login button"));
        try {
            loginButton.click();
        } catch (Exception e) {
            logger.error("Failed to click the login button", e);
            throw new RuntimeException("An error occurred while clicking the login button", e);
        }
    }
}
