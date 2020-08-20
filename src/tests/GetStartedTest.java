package tests;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() {

        if(this.Platform.isAndroid()) {
            return;
        }

        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForTextTheFreeEncyclopedia();
        WelcomePage.clickNextButton();

        WelcomePage.waitForTextNewWaysToExplore();
        WelcomePage.clickNextButton();

        WelcomePage.waitForTextSearchInNearly300Languages();
        WelcomePage.clickNextButton();

        WelcomePage.waitForTextHelpMakeTheAppBetter();
        WelcomePage.clickGetStartedButton();

    }
}
