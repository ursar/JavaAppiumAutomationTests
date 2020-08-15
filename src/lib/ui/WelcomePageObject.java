package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{

    private static final String
            STEP_THE_FREE_ENCYCLOPEDIA = "The free encyclopedia",
            STEP_NEW_WAYS_TO_EXPLORE = "New ways to explore",
            STEP_SEARCH_IN_NEARLY_300_LANGUAGES = "Search in nearly 300 languages",
            STEP_HELP_MAKE_THE_APP_BETTER = "Help make the app better",
            NEXT_LINK = "//*[@name='Next']",
            GET_STARTED_BUTTON = "//*[@name='Get started']";


    public WelcomePageObject(AppiumDriver driver){

        super(driver);
    }

    public void waitForTextTheFreeEncyclopedia() {

        this.waitForElementPresent(By.id(STEP_THE_FREE_ENCYCLOPEDIA), "Cannot find text 'The free encyclopedia'", 10);
    }

    public void waitForTextNewWaysToExplore() {

        this.waitForElementPresent(By.id(STEP_NEW_WAYS_TO_EXPLORE), "Cannot find text 'New ways to explore'", 10);
    }

    public void waitForTextSearchInNearly300Languages() {

        this.waitForElementPresent(By.id(STEP_SEARCH_IN_NEARLY_300_LANGUAGES), "Cannot find text 'Search in nearly 300 languages'", 10);
    }

    public void waitForTextHelpMakeTheAppBetter() {

        this.waitForElementPresent(By.id(STEP_HELP_MAKE_THE_APP_BETTER), "Cannot find text 'Help make the app better'", 10);
    }

    public void clickNextButton() {

        this.waitForElementAndClick(By.xpath(NEXT_LINK), "Cannot find and click 'Next button'", 10);
    }

    public void clickGetStartedButton() {

        this.waitForElementAndClick(By.xpath(GET_STARTED_BUTTON), "Cannot find and click 'Get started button'", 10);
    }





}
