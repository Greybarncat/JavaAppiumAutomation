package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.android.AndroidMyListsObject;
import lib.ui.ios.IOsMyListsObject;

public class MyListsObjectFactory
{
    public static MyListsPageObject get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListsObject(driver);
        } else {
            return new IOsMyListsObject(driver);
        }
    }
}
