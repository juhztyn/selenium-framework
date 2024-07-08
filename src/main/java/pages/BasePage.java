package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    private final By hamburgerButton = By.id("react-burger-menu-btn");

    // Common methods
    public void clickHamburgerIcon() {
        driver.findElement(hamburgerButton).click();
    }

    public boolean isHamburgerButtonDisplayed() {
        return driver.findElement(hamburgerButton).isDisplayed();
    }

}
