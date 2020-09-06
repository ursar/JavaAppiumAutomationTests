package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {


    // Ex18*: Рефакторинг теста под MW на поиск и верификацию трех результатов выдачи поиска.
    @Test
    public void testCheckSearchResultByTitleAndSubtitle(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        String search_line = "C++";
        String expected_title1 = "C++";
        String expected_description1 = "General-purpose programming language";
        String expected_title2 = "C++11";
        String expected_description2 = "2011 edition of the C++ programming language standard";
        String expected_title3 = "C++Builder";
        String expected_description3 = "Integrated development environment";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.waitForElementByTitleAndDescription(expected_title1, expected_description1);
        SearchPageObject.waitForElementByTitleAndDescription(expected_title2, expected_description2);
        SearchPageObject.waitForElementByTitleAndDescription(expected_title3, expected_description3);
    }


    @Test
    public void testSearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSwipeArticleToFooter() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "sfdsdfsdfsdfs";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}