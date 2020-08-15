package tests.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {

    @Test
    public void testPassThroughWelcome() {

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
