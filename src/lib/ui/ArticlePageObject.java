package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            CLOSE_ALERT_BUTTON,
            MY_EXITING_LIST_TPL;


    public ArticlePageObject(AppiumDriver driver) {

        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getExsistingListElement(String substring) {
        return MY_EXITING_LIST_TPL.replace("{FOLDER_NAME}", substring);
    }
    /* TEMPLATES METHODS */


    public WebElement waitForTitleElement() {

        return this.waitForElementPresent(
                TITLE,
                "Cannot find article title on page'",
                15
        );
    }

    public String getArticleTitle() {

        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()) {
        return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter() {

        if(Platform.getInstance().isAndroid()){
            this.swipeUPToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        } else {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        }
    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementPresent(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticlesToMySaved() {

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
    }

    public void closeSyncDialog() {

        this.waitForElementAndClick(
                CLOSE_ALERT_BUTTON,
                "Cannot close sync dialog",
                5
        );
    }

    public void closeArticle() {

        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
    }

    public void addArticleToExistsMyList(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementPresent(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find button to add article to reading list",
                5
        );

        String existing_list_xpath = getExsistingListElement(name_of_folder);
        this.waitForElementAndClick(
                existing_list_xpath,
                "Cannot find existing folder",
                5
        );
    }

    public void assertExistsResultOfSearch(){

        this.assertElementPresent(TITLE, "We found more than one title element or didn't find anyone");
    }

}
