import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.nio.file.WatchEvent;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTest");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/duiss/Projects/java-maven-appium/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementByIdAndClick("org.wikipedia:id/fragment_onboarding_skip_button",
                "Cannot find 'Skip' button",
                5);

        waitForElementByIdAndClick("org.wikipedia:id/search_container",
                "Cannot find sear field",
                5);

        waitForElementByIdAndSendKeys("org.wikipedia:id/search_src_text",
                "Java",
                "Cannot find search filed",
                5);
        

        waitForElementPresentByXpath(
                "//*[@text='Object-oriented programming language']",
                "Cannot find 'Object-oriented programming language' topic by searching 'Java'",
                15);
    }

    @Test
    public void testCancelSearch() {
        waitForElementByIdAndClick("org.wikipedia:id/fragment_onboarding_skip_button",
                "Cannot find 'Skip' button",
                5);
        waitForElementPresentById("org.wikipedia:id/search_container",
                "Cannot find sear field",
                5);

        waitForElementByIdAndClick("org.wikipedia:id/search_container",
                "Cannot find sear field",
                5);
        waitForElementByClassNameAndClick("android.widget.ImageButton",
                "cannot find button",
                5);
        waitForElementNotPresent("android.widget.ImageButton",
                "cannat fins",
                5);

    }

    private WebElement waitForElementPresentById(String id, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresentById(String id, String error_message) {
        return waitForElementPresentById(id, error_message, 5);
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private WebElement waitForElementPresentByXpath(String xpath, String error_message) {
        return waitForElementPresentByXpath(xpath, error_message, 5);
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementByIdAndClick(String id, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresentById(id, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementByIdAndSendKeys(String id, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresentById(id, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementByClassNameAndClick(String className, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresentByClassName(className, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementPresentByClassName(String className, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.className(className);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private boolean waitForElementNotPresent(String className, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.className(className);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}

