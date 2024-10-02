package pages;

import helper.CommonUtils;
import helper.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class BasePage {
    private static final Logger logger = LoggerUtil.getLogger(BasePage.class);
    protected WebDriver driver;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        initLocators();
    }

    // Map to store the locators
    private final Map<String, By> locators = new HashMap<>();

    // --- Initialize locators ---
    private void initLocators() {
        // Header locators
        locators.put("hamburger button", By.id("react-burger-menu-btn"));
        locators.put("app logo", By.className("app_logo"));
        locators.put("cart button", By.className("shopping_cart_link"));

        // Navigation locators
        locators.put("all items link", By.id("inventory_sidebar_link"));
        locators.put("about link", By.id("about_sidebar_link"));
        locators.put("logout link", By.id("logout_sidebar_link"));
        locators.put("reset app state link", By.id("reset_sidebar_link"));

        // Footer locators
        locators.put("footer", By.className("footer"));
        locators.put("social list", By.className("social"));
        locators.put("twitter button", By.cssSelector("[data-test*='social-twitter']"));
        locators.put("facebook button", By.cssSelector("[data-test*='social-facebook']"));
        locators.put("linkedIn button", By.cssSelector("[data-test*='social-linkedin']"));
        locators.put("footer info", By.className("footer_copy"));
    }

    // --- Common Interaction Methods ---
    /**
     * Clicks on a common element on the base page.
     * @param elementName The element to click on the base page
     */
    public void clickCommonElement(String elementName) {
        logger.info("Attempting to click on common element '{}'.", elementName);
        WebElement element = CommonUtils.waitForElementToBeClickable(driver, locators.get(elementName), "Base");

        try {
            element.click();
            logger.info("Successfully clicked on common element '{}'.", elementName);
        } catch (NoSuchElementException e) {
            logger.error("Common element '{}' not found for clicking.", elementName, e);
            throw new RuntimeException("Common element '" + elementName + "' not found.", e);
        } catch (ElementNotInteractableException e) {
            logger.error("Common element '{}' is not interactable.", elementName, e);
            throw new RuntimeException("Common element '" + elementName + "' is not interactable.", e);
        } catch (Exception e) {
            logger.error("Failed to click on common element '{}'.", elementName, e);
            throw new RuntimeException("Failed to click on common element '" + elementName + "'", e);
        }
    }

    // --- Common Validation Elements ---
    /**
     * Validates the visibility of an element
     * @param elementName The element on the base page
     */
    public boolean isCommonElementDisplayed(String elementName) {
        logger.info("Checking visibility of common element '{}'.", elementName);
        WebElement element = CommonUtils.waitForVisibility(driver, locators.get(elementName), "Base");

        try {
            boolean isVisible = element.isDisplayed();
            logger.info("Common element '{}' is displayed: {}", elementName, isVisible);
            return isVisible;
        } catch (NoSuchElementException e) {
            logger.error("Common element '{}' not found.", elementName, e);
            return false;
        } catch (Exception e) {
            logger.error("Failed to check visibility for common element '{}'.", elementName, e);
            return false;
        }
    }
}