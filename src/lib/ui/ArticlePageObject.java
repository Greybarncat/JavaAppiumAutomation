package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            MY_LIST_FOLDER_BY_NAME_TPL,
            TITLE_IOS_BY_NAME_TPL,
            CONTENTS_BUTTON,
            CONTENTS_TITLE_BY_NAME_TPL;

    /* TEMPLATE METHODS */
    private static String getMyListFolderXpathByName (String name_of_folder)
    {
        return MY_LIST_FOLDER_BY_NAME_TPL.replace("{NAME}", name_of_folder);
    }

    private static String getTitleIOsLocatorByName(String name_of_article)
    {
        return TITLE_IOS_BY_NAME_TPL.replace("{NAME}", name_of_article);
    }

    private static String getContentsTitleLocatorByName(String name_of_article)
    {
        return CONTENTS_TITLE_BY_NAME_TPL.replace("{NAME}", name_of_article);
    }
    /* TEMPLATE METHODS */


    public ArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public WebElement waitForTitleElementIOs(String name)
    {
        String title_locator = getTitleIOsLocatorByName(name);
        return this.waitForElementPresent(title_locator, "Cannot find article title on page", 15);
    }

    public WebElement waitForContentsTitleElement(String name)
    {
        String contents_title_locator = getContentsTitleLocatorByName(name);
        return this.waitForElementPresent(contents_title_locator, "Cannot find title " + name + "on contents", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } return title_element.getAttribute("name");
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Cannot find button to open article options", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find 'Add to reading list' button", 5);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Cannot find 'Got it' tip overlay", 5);
        this.waitForElementAndClear(MY_LIST_NAME_INPUT, "Cannot find input to set name of article folder", 5);
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, name_of_folder, "Cannot put text into articles folder input", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot find 'OK' button", 5);
    }

    public void addArticleToMySaved()
    {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Cannot close article, cannot find X button", 5);
    }

    public void addArticleToMyListInExistFolder(String name_of_folder)
    {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Cannot find button to open article options", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find 'Add to reading list' button", 5);
        String folder_xpath = getMyListFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_xpath, "Cannot find created folder by name "+ name_of_folder +" to save article", 5);
    }

    public void assertThereIsArticleTitle()
    {
        this.assertElementPresent(TITLE, "Cannot find article title without waiting");
    }

    public void clickContentsButton()
    {
        this.waitForElementAndClick(CONTENTS_BUTTON, "Cannot find and click contents button", 5);
    }
}
