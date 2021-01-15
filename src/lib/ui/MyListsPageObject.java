package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject
{
    private static final String
    FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
    ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    /* TEMPLATE METHODS */
    private static String getFolderXpathByName (String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle (String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATE METHODS */

    public MyListsPageObject (AppiumDriver driver){
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(By.xpath(folder_name_xpath), "Cannot find created folder ", 5);
    }

    public void waitForArticleToAppearByTitle (String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_xpath), "Cannot find saved article with title "+article_title, 15);
    }

    public void waitForArticleToDisappearByTitle (String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath), "Saved Article still present with title "+article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(By.xpath(article_xpath), "Cannot swipe article to delete");
        waitForArticleToDisappearByTitle(article_title);
    }

    public void assertThereIsArticleWithTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.assertElementPresent(By.xpath(article_xpath), "We supposed to find article with title " + article_title + " in my lists folder");
    }

    public void openArticleByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(By.xpath(article_xpath), "Cannot find and open saved article with title " + article_title, 5);
    }
}