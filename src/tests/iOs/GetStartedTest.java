package tests.iOs;

import lib.iOsTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOsTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForNewWayToExploreText();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForAddOrEditPreferredLangLink();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForLearnMoreAboutDataCollectedLink();
        WelcomePageObject.clickGetStartedButton();
    }
}
