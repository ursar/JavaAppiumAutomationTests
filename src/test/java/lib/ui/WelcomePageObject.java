package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject{

    private static final String
            STEP_THE_FREE_ENCYCLOPEDIA = "id:The free encyclopedia",
            STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
            STEP_SEARCH_IN_NEARLY_300_LANGUAGES = "id:Search in nearly 300 languages",
            STEP_HELP_MAKE_THE_APP_BETTER = "id:Help make the app better",
            NEXT_LINK = "xpath://*[@name='Next']",
            GET_STARTED_BUTTON = "xpath://*[@name='Get started']",
            SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";


    public WelcomePageObject(RemoteWebDriver driver){

        super(driver);
    }

    public void waitForTextTheFreeEncyclopedia() {

        this.waitForElementPresent(
                STEP_THE_FREE_ENCYCLOPEDIA,
                "Cannot find text 'The free encyclopedia'",
                10);
    }

    public void waitForTextNewWaysToExplore() {

        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE,
                "Cannot find text 'New ways to explore'",
                10);
    }

    public void waitForTextSearchInNearly300Languages() {

        this.waitForElementPresent(
                STEP_SEARCH_IN_NEARLY_300_LANGUAGES,
                "Cannot find text 'Search in nearly 300 languages'",
                10);
    }

    public void waitForTextHelpMakeTheAppBetter() {

        this.waitForElementPresent(
                STEP_HELP_MAKE_THE_APP_BETTER,
                "Cannot find text 'Help make the app better'",
                10);
    }

    public void clickNextButton() {

        this.waitForElementAndClick(
                NEXT_LINK,
                "Cannot find and click 'Next button'",
                10);
    }

    public void clickGetStartedButton() {

        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find and click 'Get started button'",
                10);
    }

    public void clickSkip() {

        this.waitForElementAndClick(
                SKIP,
                "Cannot find and click 'Skip Button'",
                5);
    }

}
