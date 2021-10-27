package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePage;
import lib.ui.SearchPage;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
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

        assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePage.getArticleTitle();

        assertEquals("Article title have been changed after screen rotation",
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
