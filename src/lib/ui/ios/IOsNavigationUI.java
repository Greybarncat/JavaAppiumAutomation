package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOsNavigationUI extends NavigationUI
{
    static {
        MY_LISTS_LINK = "id:Saved";
    }

    public IOsNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}
