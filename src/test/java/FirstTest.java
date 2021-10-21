import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FirstTest extends CoreTestCase {

    @Test
    public void testSearch() {
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
    public void testSaveFirstArticleToMyList() {

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

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'Save article button'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cannot find 'Add to list button'",
                10
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find got it button",
                5);

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "cannot find input filed",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Cannot find input text",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find 'OK' button",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Save article button'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Learning programming']"),
                "Cannot find 'My lists'",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find object to swipe"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Elent is present",
                2
        );
    }

    @Test
    public void testAmountOfNotEmptySearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);

        String search_line = "Linkin Park Discography";

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_container"),
                search_line,
                "cannot type",
                5);
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "cannot find " + search_line,
                15);
        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );
        Assert.assertTrue("We found few",
                amount_of_search_results > 0);
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

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message
        );
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }
}

