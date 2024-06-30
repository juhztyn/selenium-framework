package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected final By navbarHomeLink = By.id("navbar-home");
    protected final By navbarProfileLink = By.id("navbar-profile");
    protected final By navbarLogoutLink = By.id("navbar-logout");

    // Common actions
    public void clickHomeLink() {
        driver.findElement(navbarHomeLink).click();
    }

    public void clickProfileLink() {
        driver.findElement(navbarProfileLink).click();
    }

    public void clickLogoutLink() {
        driver.findElement(navbarLogoutLink).click();
    }
}
