package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSafeFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIOs()){
            SearchPageObject.clickCancelSearch();
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            try {Thread.sleep(3000);} catch (Exception e) {}
            MyListsPageObject.openFolderByName(name_of_folder);
        } else {
            MyListsPageObject.clickSyncPopupCloseButton();
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaving2Articles()
    {
        String first_article_title = "Java (programming language)";
        String second_article_title = "Python (programming language)";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.waitForTitleElement();
            first_article_title = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.waitForTitleElementIOs(first_article_title);
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.initSearchInput();
        } else {
            SearchPageObject.clearSearchInput();
        }
        SearchPageObject.typeSearchLine("Python");
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.clickByArticleWithSubstring("General-purpose programming language");
        } else {
            SearchPageObject.clickByArticleWithSubstring("General-purpose, high-level programming language");
        }
        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.waitForTitleElement();
            second_article_title = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToMyListInExistFolder(name_of_folder);
        } else {
            ArticlePageObject.waitForTitleElementIOs(second_article_title);
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIOs()){
            SearchPageObject.clickCancelSearch();
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            try {Thread.sleep(5000);} catch (Exception e){}
            MyListsPageObject.openFolderByName(name_of_folder);
        } else {
            MyListsPageObject.clickSyncPopupCloseButton();
        }
        MyListsPageObject.swipeByArticleToDelete(second_article_title);
        MyListsPageObject.assertThereIsArticleWithTitle(first_article_title);
        MyListsPageObject.openArticleByTitle(first_article_title);
        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.waitForTitleElement();
        } else {
            ArticlePageObject.waitForTitleElementIOs(first_article_title);
        }
        if (Platform.getInstance().isAndroid()){
            assertEquals(
                    "Unexpected title in first article after deleting second",
                    first_article_title,
                    ArticlePageObject.getArticleTitle()
            );
        } else {
            ArticlePageObject.clickContentsButton();
            ArticlePageObject.waitForContentsTitleElement(first_article_title);
        }
    }
}
