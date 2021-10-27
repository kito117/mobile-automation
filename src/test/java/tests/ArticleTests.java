package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePage;
import lib.ui.SearchPage;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {

        SearchPage SearchPage = new SearchPage(driver);

        SearchPage.initSearchInput()
                .typeSearLine("Java")
                .waitForSearchResult("Object-oriented programming language");

        ArticlePage ArticlePage = new ArticlePage(driver);
        String article_title = ArticlePage.getArticleTitle();

        assertEquals("We see unexpected title",
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
}
