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
        // General locators
        locators.put("hamburger button", By.id("react-burger-menu-btn"));
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