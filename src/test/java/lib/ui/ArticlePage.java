package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePage extends MainPage {

    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text";

    public ArticlePage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        this.waitForElementPresent(By.id(TITLE), "Cannot find", 15);
        return (WebElement) this;
    }
}
