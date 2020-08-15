package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject  extends MainPageObject{

    public static final String
            FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";

    /* TEMPLATES METHODS */
    private static String getFolderXPathByName(String name_of_folder) {

        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {

        return ARTICLE_BY_TITLE_TPL.replace("{FOLDER_NAME}", article_title);
    }
    /* TEMPLATES METHODS */


    public MyListsPageObject(AppiumDriver driver) {

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

        String article_xpath = getFolderXPathByName(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }


    public void waitArticleToDisappearByTitle(String article_title) {

        String article_xpath = getFolderXPathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {

        this.waitArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXPathByName(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
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
