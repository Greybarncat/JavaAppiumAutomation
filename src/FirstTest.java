import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class FirstTest extends CoreTestCase
{
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCompareSearchLineText()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.assertSearchInputHasText("Search…");
    }

    @Test
    public void testCorrectSearchResults()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertAllSearchResultContainsText("java");
    }
}