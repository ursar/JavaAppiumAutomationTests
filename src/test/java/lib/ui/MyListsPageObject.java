package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            DELETE_BUTTON_MY_LIST,
            REMOVE_FROM_SAVED_BUTTON;


    /* TEMPLATES METHODS */
    private static String getFolderXPathByName(String name_of_folder) {

        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {

        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title) {

        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */


    public MyListsPageObject(RemoteWebDriver driver) {

        super(driver);
    }

    public void openFolderByName(String name_of_folder) {

        String folder_name_xpath = getFolderXPathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }


    public void waitArticleToAppearByTitle(String article_title) {

        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }


    public void waitArticleToDisappearByTitle(String article_title) {

        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {

        this.waitArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())) {

            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }
        if(Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(
                    DELETE_BUTTON_MY_LIST,
                    "Cannot find and click 'red delete button' when delete article",
                    5);

//            or click point
//            this.clickElementToTheRightUpperCorner(
//                    article_xpath,
//                    "Cannot find saved article"
//            );
        }

        if(Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }

        this.waitArticleToDisappearByTitle(article_title);
    }


    public void openArticleInMyList(String article_title) {

        String article_xpath = getFolderXPathByName(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Cannot find saved article " + article_title,
                5
        );
    }

}
