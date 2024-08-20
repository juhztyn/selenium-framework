package helper;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class CommonUtils {
    private static final int DEFAULT_TIMEOUT = 5;
    private static final Logger logger = LoggerUtil.getLogger(CommonUtils.class);

    // Private constructor to prevent instantiation
    private CommonUtils() {}

    // Function to pause for 2 seconds
    public static void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Function to explicit wait for element to be visible
    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.error("Element not visible after {} seconds: {}", DEFAULT_TIMEOUT, locator, e);
            throw new RuntimeException("Element not visible: " + locator, e);
        } catch (Exception e) {
            logger.error("An unexpected error occurred while waiting for visibility of element: {}", locator, e);
            throw new RuntimeException("Unexpected error: " + locator, e);
        }
    }

    // Function to explicit wait for element to be clickable
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            logger.error("Element not clickable after {} seconds: {}", DEFAULT_TIMEOUT, locator, e);
            throw new RuntimeException("Element not clickable: " + locator, e);
        } catch (Exception e) {
            logger.error("An unexpected error occurred while waiting for element to be clickable: {}", locator, e);
            throw new RuntimeException("Unexpected error: " + locator, e);
        }
    }

}
