package pages;

import helper.CommonUtils;
import helper.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static cofig.UrlConstants.HOME;

public class HomePage extends BasePage {
    private static final Logger logger = LoggerUtil.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
        initLocators();
    }

    // Map to store locators
    private final Map<String, By> locators = new HashMap<>();

    // Initialize locators
    private void initLocators() {
        // General locators
        locators.put("products header", By.xpath("//span[contains(text(),'Products')]"));
    }

    // Validation Methods
    public boolean isHomePageElementDisplayed(String elementName) {
        logger.info("Checking visibility of element '{}' on the Home page.", elementName);
        WebElement element = CommonUtils.waitForVisibility(driver, locators.get(elementName), "Home");

        try {
            boolean isVisible = element.isDisplayed();
            logger.info("Element '{}' is displayed on the Home page: {}", elementName, isVisible);
            return isVisible;
        } catch (NoSuchElementException e) {
            logger.error("Element '{}' not found on the Home page.", elementName, e);
            return false;
        } catch (Exception e) {
            logger.error("Failed to check visibility for element '{}' on the Home page.", elementName, e);
            return false;
        }
    }
}




