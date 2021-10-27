package tests;

import lib.CoreTestCase;
import lib.ui.SearchPage;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
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
    public void testAmountOfNotEmptySearch() {

        SearchPage SearchPage = new SearchPage(driver);
        SearchPage.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPage.typeSearLine(search_line);
        int amount_of_search_results = SearchPage.getAmountOfFoundArticles();

        assertTrue("We found few results",
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
}
