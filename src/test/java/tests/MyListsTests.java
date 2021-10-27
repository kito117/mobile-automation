package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePage;
import lib.ui.MyListsScreen;
import lib.ui.NavigationUI;
import lib.ui.SearchPage;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
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
}
