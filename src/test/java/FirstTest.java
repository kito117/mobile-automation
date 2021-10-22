import lib.CoreTestCase;
import lib.ui.ArticlePage;
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

        SearchPage.initSearchInput()
                .typeSearLine("Java")
                .waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPage SearchPage = new SearchPage(driver);

        SearchPage.initSearchInput()
                .waitForCancelButtonToAppear()
                .clickCancelSearch()
                .waitForCancelButtonToDisappear();
    }

    @Test
    public void testCompareArticleTitle() {

        SearchPage SearchPage = new SearchPage(driver);

        SearchPage.initSearchInput()
                .typeSearLine("Java")
                .clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePage ArticlePage = new ArticlePage(driver);
        String article_title = ArticlePage.getArticleTitle();

        Assert.assertEquals("We see unexpected title",
                "Java (programming language)",
                article_title);
    }

    @Test
    public void testSwipeArticle() {

        SearchPage SearchPage = new SearchPage(driver);

        SearchPage.initSearchInput()
                .typeSearLine("Appium");
        SearchPage.clickByArticleWithSubstring("Appium");

        ArticlePage ArticlePage = new ArticlePage(driver);
        ArticlePage.waitForTitleElement();
        ArticlePage.swipeToFooter();
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
