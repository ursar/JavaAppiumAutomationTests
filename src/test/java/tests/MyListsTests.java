package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String NAME_OF_FOLDER = "Learning programming";
    private static final String
            login = "Testuser1q",
            password = "11Test11";

    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        } else if (Platform.getInstance().isIOS()){
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeSyncDialog();
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    //    Ex17: Рефакторинг сохранения двух статей
    @Test
    public void testSaveTwoArticleToMyListOneDelete() {

        String first_search_article_title = "Java";
        String first_article_title_full = "bject-oriented programming language";
        String second_search_article_title = "C#";
        String second_article_title_full = "ulti-paradigm (object-oriented) programming language";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_search_article_title);
        SearchPageObject.clickArticleWithSubstring(first_article_title_full);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        } else if (Platform.getInstance().isIOS()){
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeSyncDialog();
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            String article_title = ArticlePageObject.getArticleTitle();
            assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );
            ArticlePageObject.addArticlesToMySaved();
        }


        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        if((Platform.getInstance().isAndroid()) || Platform.getInstance().isIOS()) {
            SearchPageObject.clearSearchInput();
        }

        SearchPageObject.typeSearchLine(second_search_article_title);
        SearchPageObject.clickArticleWithSubstring(second_article_title_full);
        String second_article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        } else if (Platform.getInstance().isIOS()){
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeSyncDialog();
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }

        MyListsPageObject.swipeByArticleToDelete(first_article_title);

        if((Platform.getInstance().isAndroid()) || (Platform.getInstance().isIOS())) {
            SearchPageObject.waitForSearchResult(second_article_title);
        }


        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openArticleInMyList(second_article_title_full);
            String article_title = ArticlePageObject.getArticleTitle();

            assertEquals(
                    "We see unexpected title!",
                    second_article_title_full,
                    article_title
            );
        } else if(Platform.getInstance().isIOS()) {
            String article_title = SearchPageObject.getAttributeNameFromArticleInMyList(second_article_title_full);
            assertEquals(
                    "We see unexpected title!",
                    second_article_title_full,
                    article_title
            );
        } else {
            SearchPageObject.clickByItemWithTitle(second_article_title);
            ArticlePageObject.waitForTitleElement();
            String article_title = ArticlePageObject.getArticleTitle();
            assertEquals(
                    "We see unexpected title!",
                    "C Sharp (programming language)",
                    article_title
            );
        }
    }

}
