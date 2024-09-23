package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static Properties properties;

    // Load properties from configuration file
    static {
        properties = new Properties();
        try {
            FileInputStream configStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(configStream);
            configStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration properties.");
        }
    }

    // Prevent instantiation
    private DriverFactory() {}

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = properties.getProperty("browser", "chrome").toLowerCase();

            switch (browser) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;
                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
            }

            driver.get().manage().window().maximize();
        }

        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
















//public class DriverFactory {
//    private static WebDriver driver;
//
//    // Prevent instantiation
//    private DriverFactory() {}
//
//    public static WebDriver getDriver() {
//        if (driver == null) {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--remote-allow-origins=*");
//
//            driver = new ChromeDriver(options);
//            driver.manage().window().maximize();
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        }
//        System.out.println("Called getDriver()");
//        return driver;
//    }
//
//    public static void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//            System.out.println("WebDriver has been quit and set to null.");
//        }
//    }
//}
