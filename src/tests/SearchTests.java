package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class SearchTests extends CoreTestCase {


    // Ex12*: Рефакторинг теста под iOS на поиск и верификацию трех результатов выдачи поиска.
    @Test
    public void testCheckSearchResultByTitleAndSubtitle(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        String search_line = "C++";
        String expected_title1 = "C++";
        String expected_description1 = "General purpose high-level programming language";
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


    //    Ex3: отмена поиска
    @Test
    public void testCancelAndClearSearch () {

        String search_line = "Windows";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int result = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "Not found search results on the screen",
                result > 0);

        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    public void testSearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
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