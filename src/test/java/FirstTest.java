import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

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
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search filed",
                5);

        waitForElementPresent(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic by searching 'Java'",
                15);
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);
        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_container"),
                "Java",
                "cannot type",
                5);
        waitForElementAndClear(By.id("org.wikipedia:id/search_container"),
                "cannot clear",
               5);
        waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "cannot find button",
                5);
        waitForElementNotPresent(
                By.className("android.widget.ImageButton"),
                "cannat fins",
                5);

    }

    @Test
    public void testCompareArticleTitle() {

       waitForElementAndClick(
                        By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                        "Cannot find 'Skip' button",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_container"),
                "Java",
                "cannot type",
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic by searching 'Java'",
                15);


        WebElement title_element = waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article title",
                15
        );
        String article_title = title_element.getAttribute("text");

        Assert.assertEquals("We see unexpected title",
                "Object-oriented programming language",
                article_title);
    }

    @Test
    public void testSwipeArticle() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_container"),
                "Appium",
                "cannot type",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium'",
                15);


        waitForElementPresent(
                By.xpath("//*[@text='Appium']"),
                "Cannot find article title",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find 'Appium'",
                20
        );
    }

    @Test
    public void saveFirstArticleToMyList() throws InterruptedException {

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_container"),
                "Java",
                "cannot type",
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic by searching 'Java'",
                15);
        Thread.sleep(5000);

        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find 'Save article button'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@text='ADD TO LIST']"),
                "Cannot find 'Add to list button'",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/textinput_placeholder"),
                "Learning programming",
                "Cannot find input text",
                5
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find 'OK' button",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "cann not find",
                5
        );

        Thread.sleep(5000);
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "cann not find",
                5
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();

    }

    protected void swipeUpQuick() {
        swipeUp(1000);
    }

    protected void swipeUpToFindElement (By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
}

