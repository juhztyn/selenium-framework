package pages;

import helper.CommonUtils;
import helper.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Represents the Login Page of the application.
 * Contains methods for interacting with the login form.
 */
public class LoginPage extends BasePage {
    private static final Logger logger = LoggerUtil.getLogger(LoginPage.class);

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        initLocators();
    }

    // Map to store locators
    private final Map<String, By> locators = new HashMap<>();

    // --- Initialize locators ---
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
        locators.put("login error", By.cssSelector("[data-test='error']"));
        locators.put("login error close button", By.cssSelector("[class*='error-button']"));

        // Error messages
        locators.put("locked out error message", By.xpath("//h3[contains(text(), 'Sorry, this user has been locked out.')]"));
    }

    // --- Interaction Methods ---
    /**
     * Enters the provided username
     * @param username The username to enter
     */
    public void enterUsername(String username) {
        logger.info("Attempting to enter username '{}' on Login page.", username);
        WebElement usernameField = CommonUtils.waitForElementToBeClickable(driver, locators.get("username field"), "Login");

        try {
            usernameField.sendKeys(username);
            logger.info("Successfully entered username '{}' on Login page.", username);
        } catch (NoSuchElementException e) {
            logger.error("Username field not found on Login page. Locator: {}", locators.get("username field"), e);
            throw new RuntimeException("Username field not found on Login page", e);
        } catch (ElementNotInteractableException e) {
            logger.error("Username field not interactable on Login page. Locator: {}", locators.get("username field"), e);
            throw new RuntimeException("Username field not interactable on Login page", e);
        } catch (Exception e) {
            logger.error("Failed to send keys to the username field on Login page. Username: {}, Locator: {}", username, locators.get("username field"), e);
            throw new RuntimeException("An error occurred while sending keys to the username field", e);
        }
    }

    /**
     * Enters the provided password
     * @param password The password to enter
     */
    public void enterPassword(String password) {
        logger.info("Attempting to enter password on the Login page.");
        WebElement passwordField = CommonUtils.waitForElementToBeClickable(driver, locators.get("password field"), "Login");

        try {
            passwordField.sendKeys(password);
            logger.info("Successfully entered password on the Login page.");
        } catch (NoSuchElementException e) {
            logger.error("Password field not found on the Login page. Locator: {}", locators.get("password field"), e);
            throw new RuntimeException("Password field not found on the Login page", e);
        } catch (ElementNotInteractableException e) {
            logger.error("Password field not interactable on the Login page. Locator: {}", locators.get("password field"), e);
            throw new RuntimeException("Password field not interactable on the Login page", e);
        } catch (Exception e) {
            logger.error("Failed to send keys to the password field on the Login page. Locator: {}", locators.get("password field"), e);
            throw new RuntimeException("An error occurred while sending keys to the password field", e);
        }
    }

    /**
     * Clicks the login button
     */
    public void clickLogin() {
        WebElement loginButton;
        try {
            logger.info("Attempting to click the login button on the Login page.");
            loginButton = CommonUtils.waitForElementToBeClickable(driver, locators.get("login button"), "Login");
            loginButton.click();
            logger.info("Successfully clicked the login button on the Login page.");
        } catch (NoSuchElementException e) {
            logger.error("Login button not found on the Login page. Locator: {}", locators.get("login button"), e);
            throw new RuntimeException("Login button not found on the Login page", e);
        } catch (ElementNotInteractableException e) {
            logger.error("Login button not interactable on the Login page. Locator: {}", locators.get("login button"), e);
            throw new RuntimeException("Login button not interactable on the Login page", e);
        } catch (Exception e) {
            logger.error("Failed to click the login button on the Login page. Locator: {}", locators.get("login button"), e);
            throw new RuntimeException("An error occurred while clicking the login button", e);
        }
    }

    // --- Validation Methods ---
    /**
     * Validates the visibility of an element
     * @param elementName The element on the login page
     */
    public boolean isLoginPageElementDisplayed(String elementName) {
        logger.info("Checking visibility of element '{}' on the Login page.", elementName);
        WebElement element = CommonUtils.waitForVisibility(driver, locators.get(elementName), "Login");

        try {
            boolean isVisible = element.isDisplayed();
            logger.info("Element '{}' is displayed on the Login page: {}", elementName, isVisible);
            return isVisible;
        } catch (NoSuchElementException e) {
            logger.error("Element '{}' not found on the Login page.", elementName, e);
            return false;
        } catch (Exception e) {
            logger.error("Failed to check visibility for element '{}' on the Login page.", elementName, e);
            return false;
        }
    }
}
