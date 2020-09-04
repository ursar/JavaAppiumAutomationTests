package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        DELETE_BUTTON_MY_LIST = "id:swipe action delete";
    }

    public iOSMyListsPageObject(RemoteWebDriver driver) {

        super(driver);
    }
}
