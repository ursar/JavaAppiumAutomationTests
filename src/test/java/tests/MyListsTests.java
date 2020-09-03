package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String NAME_OF_FOLDER = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeSyncDialog();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }


//    @Test
//    public void testSwipeDelete() {
//
//        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
//        NavigationUI.clickMyList();//
//
//        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
//
//        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");
//    }
}
