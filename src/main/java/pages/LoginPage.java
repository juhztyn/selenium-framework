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
        locators.put("accepted passwords section", By.cssSelector("[class*='login_password']"));

        // Form locators
        locators.put("username field", By.id("user-name"));
        locators.put("password field", By.id("password"));
        locators.put("login button", By.id("login-button"));
        locators.put("login error container", By.cssSelector("[class*='error-message-container']"));
        locators.put("login error message", By.cssSelector("[data-test*='error']"));
        locators.put("login error close button", By.cssSelector("[class*='error-button']"));


    }

    // Validation Methods
    public boolean isLoginPageElementDisplayed(String elementName) {
        try {
            By locator = locators.get(elementName);
            WebElement element = CommonUtils.waitForVisibility(driver, locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Interaction Methods
    public void enterUsername(String username) {
        driver.findElement(locators.get("username field")).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(locators.get("password field")).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(locators.get("login button")).click();
    }
}
