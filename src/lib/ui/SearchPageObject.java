package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_LABEL = "//*[@text='No results found']",
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{DESCRIPTION}']/../*[@text='{TITLE}']",
            SEARCH_INPUT_FIELD = "org.wikipedia:id/search_src_text",
            SEARCH_RESULT_TITLE = "org.wikipedia:id/page_list_item_title";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement (String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultByTitleAndDescription (String title, String description)
    {
        String search_result_with_title = SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title);
        String search_result_with_title_and_description =search_result_with_title.replace("{DESCRIPTION}",description);
        return search_result_with_title_and_description;
    }
    /* TEMPLATE METHODS */

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and tipe into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFindArticles ()
    {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find anything by the request", 15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_LABEL), "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }

    public void waitForElementByTitleAndDiscription(String title, String description)
    {
        String search_result_xpath = getSearchResultByTitleAndDescription(title, description);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with title " + title + " and description " + description, 10);
    }

    public void assertSearchInputHasText(String text)
    {
        this.assertElementHasText(By.id(SEARCH_INPUT_FIELD), text, "We supposed search line has text " + text);
    }

    public void assertAllSearchResultContainsText(String text)
    {
        this.waitForElementPresent(By.id(SEARCH_RESULT_TITLE), "Cannot find search result", 10);

        ArrayList<WebElement> results = (ArrayList<WebElement>) driver.findElements(By.id(SEARCH_RESULT_TITLE));

        for (int i=0;i<results.size();i++){
            String title = results.get(i).getAttribute("text");
            Assert.assertTrue("Result don't contain " + text ,title.toLowerCase().contains(text));
        }
    }
}
