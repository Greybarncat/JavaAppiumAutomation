package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOsMyListsObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        SYNC_POPUP_CLOSE_BUTTON = "id:Close";
    }

    public IOsMyListsObject (AppiumDriver driver)
    {
        super(driver);
    }
}
