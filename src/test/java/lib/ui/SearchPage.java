package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPage extends MainPage {

    private static final String
    SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
    SEARCH_INPUT = "org.wikipedia:id/search_src_text",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']";

    public SearchPage(AppiumDriver driver){
        super(driver);
    }

    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public SearchPage initSearchInput() {
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT), "Cannot find", 5);
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT), "Cannot find", 5);
        return this;
    }

    public SearchPage typeSearLine(String search_line){
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line, "Cannot find", 5);
        return this;
    }

    public SearchPage waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find result with " + substring);
        return this;
    }
}
