package pages;

import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static cofig.UrlConstants.HOME;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    public final By homeHeader = By.id("app-logo");

    // Common methods
    public boolean isHomeHeaderDisplayed() {
        WebElement header = driver.findElement(homeHeader);
        return header.isDisplayed();
    }
}
