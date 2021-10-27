import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;

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
                .waitForSearchResult("Object-oriented programming language");

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
        SearchPage.waitForSearchResult("Appium");

        ArticlePage ArticlePage = new ArticlePage(driver);
        ArticlePage.waitForTitleElement();
        ArticlePage.swipeToFooter();
    }

    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPage SearchPage = new SearchPage(driver);

        SearchPage.initSearchInput()
                .typeSearLine("Java")
                .waitForSearchResult("Object-oriented programming language");

        ArticlePage ArticlePage = new ArticlePage(driver);
        ArticlePage.waitForTitleElement();
        String article_title = ArticlePage.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePage.addArticleToMyList(name_of_folder);
        ArticlePage.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsScreen MyListsScreen = new MyListsScreen(driver);
        MyListsScreen.openFolderByName(name_of_folder);
        MyListsScreen.swipeArticleToDelete(article_title);
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        SearchPage SearchPage = new SearchPage(driver);
        SearchPage.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPage.typeSearLine(search_line);
        int amount_of_search_results = SearchPage.getAmountOfFoundArticles();

        Assert.assertTrue("We found few results",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPage SearchPage = new SearchPage(driver);
        SearchPage.initSearchInput();
        String search_line = "sdasdasdasfasf";
        SearchPage.typeSearLine(search_line);
        SearchPage.waitForEmptyResultsLabel();
        SearchPage.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testChangeScreenOrientationOnSearchTitle() {
        SearchPage SearchPage = new SearchPage(driver);

        SearchPage.initSearchInput()
                .typeSearLine("Java")
                .waitForSearchResult("Object-oriented programming language");

        ArticlePage ArticlePage = new ArticlePage(driver);
        String title_before_rotation = ArticlePage.getArticleTitle();

        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePage.getArticleTitle();

        Assert.assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePage.getArticleTitle();

        Assert.assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    public void testCheckSearchArticleInBackground() {
        SearchPage SearchPage = new SearchPage(driver);

        SearchPage.initSearchInput()
                .typeSearLine("Java");
        SearchPage.waitForSearchResult("Object-oriented programming language");

        this.backgroundApp(5);
        SearchPage.waitForSearchResult("Object-oriented programming language");
    }
}
