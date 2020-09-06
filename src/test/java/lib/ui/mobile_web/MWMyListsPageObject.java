package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'content-unstyled page-list')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'content-unstyled page-list thumbs page-summary-list mw-mf-watchlist-page-list')]//h3[contains(text(),'{TITLE}')]/../../a[contains(@class,'watched')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
