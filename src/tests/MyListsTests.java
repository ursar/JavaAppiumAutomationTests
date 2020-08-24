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


    //    Ex5: Тест: сохранение двух статей
    @Test
    public void testSaveTwoArticleToMyList() {

        String first_article_title = "Java";
        String first_article_title_full = "Java (programming language)";
        String second_article_title = "C#";
        String second_article_title_full = "C Sharp (programming language)";
        String name_of_folder = "Learning programming";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(first_article_title);
        SearchPageObject.clickArticleWithSubstring(first_article_title_full);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(second_article_title);
        SearchPageObject.clickArticleWithSubstring(second_article_title_full);

        ArticlePageObject.addArticleToExistsMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(first_article_title_full);
        MyListsPageObject.waitArticleToDisappearByTitle(first_article_title_full);
        MyListsPageObject.waitArticleToAppearByTitle(second_article_title_full);

        MyListsPageObject.openArticleInMyList(second_article_title_full);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title!",
                second_article_title_full,
                article_title
        );

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
