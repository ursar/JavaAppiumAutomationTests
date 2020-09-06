package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() {

        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {
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
