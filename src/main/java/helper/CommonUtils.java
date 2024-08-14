package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonUtils {
    private static final int DEFAULT_TIMEOUT = 10;

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
            System.err.println("Element not visible after " + DEFAULT_TIMEOUT + " seconds: " + locator);
            throw new RuntimeException("Element not visible: " + locator, e);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while waiting for visibility of element: " + locator);
            throw new RuntimeException("Unexpected error: " + locator, e);
        }
    }

}
