import lib.CoreTestCase;
import lib.ui.MainPage;
import lib.ui.SearchPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FirstTest extends CoreTestCase {

    private MainPage MainPage;

    protected void setUp() throws Exception {
        super.setUp();
        MainPage = new MainPage(driver);
    }

    @Test
    public void testSearch() {
        SearchPage SearchPage = new SearchPage(driver);

        SearchPage.initSearchInput();
        SearchPage.typeSearLine("Java");
        SearchPage.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        MainPage.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);
        MainPage.waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);

        MainPage.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);
        MainPage.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_container"),
                "Java",
                "cannot type",
                5);
        MainPage.waitForElementAndClear(By.id("org.wikipedia:id/search_container"),
                "cannot clear",
                5);
        MainPage.waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "cannot find button",
                5);
        MainPage.waitForElementNotPresent(
                By.className("android.widget.ImageButton"),
                "cannat fins",
                5);
    }

    @Test
    public void testCompareArticleTitle() {

        MainPage.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);

        MainPage.waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);

        MainPage.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);
        MainPage.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_container"),
                "Java",
                "cannot type",
                5);
        MainPage.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic by searching 'Java'",
                15);


        WebElement title_element = MainPage.waitForElementPresent(
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

        MainPage.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);

        MainPage.waitForElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);

        MainPage.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);
        MainPage.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_container"),
                "Appium",
                "cannot type",
                5);
        MainPage.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium'",
                15);

        MainPage.waitForElementPresent(
                By.xpath("//*[@text='Appium']"),
                "Cannot find article title",
                15
        );

        MainPage.swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find 'Appium'",
                20
        );
    }

    @Test
    public void testSaveFirstArticleToMyList() {

        MainPage.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);

        MainPage.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_container"),
                "Java",
                "cannot type",
                5);
        MainPage.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic by searching 'Java'",
                15);

        MainPage.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'Save article button'",
                5
        );
        MainPage.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cannot find 'Add to list button'",
                10
        );

        MainPage.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find got it button",
                5);

        MainPage.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "cannot find input filed",
                5
        );

        MainPage.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Cannot find input text",
                5
        );

        MainPage.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find 'OK' button",
                5);

        MainPage.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Save article button'",
                5
        );

        MainPage.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists'",
                5
        );

        MainPage.waitForElementAndClick(
                By.xpath("//*[@text='Learning programming']"),
                "Cannot find 'My lists'",
                5
        );

        MainPage.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find object to swipe"
        );
        MainPage.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Elent is present",
                2
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        MainPage.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find sear field",
                5);

        String search_line = "Linkin Park Discography";

        MainPage.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_container"),
                search_line,
                "cannot type",
                5);
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        MainPage.waitForElementPresent(
                By.xpath(search_result_locator),
                "cannot find " + search_line,
                15);
        int amount_of_search_results = MainPage.getAmountOfElements(
                By.xpath(search_result_locator)
        );
        Assert.assertTrue("We found few",
                amount_of_search_results > 0);
    }
}
