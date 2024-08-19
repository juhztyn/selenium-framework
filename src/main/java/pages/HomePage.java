package pages;

import helper.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

import static cofig.UrlConstants.HOME;

public class HomePage extends BasePage {
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
        By locator = locators.get(elementName);
        WebElement element = CommonUtils.waitForVisibility(driver, locator);
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            System.err.println("Failed to check visibility for element: " + elementName + " - " + e.getMessage());
            return false;
        }
    }
}




