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
            SYNC_YOUR_SAVED_ARTICLES_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_SYNC_OVERLAY,
            CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = this.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of the article", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of the article", 40);
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        // CLICK TO OPTIONS AND SELECT "ADD TO MY LIST"
        this.waitForElementAndClick(OPTIONS_BUTTON, "Cannot find button to open article options", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 15);

        // WORK WITH OVERLAY
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Cannot find 'Got it' tip overlay", 5);
        this.waitForElementAndClear(MY_LIST_NAME_INPUT, "Cannot find input to set name of articles folder", 10);
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, name_of_folder, "Cannot put text into articles folder input", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot press OK button", 5);
    }

    public void addArticlesToMySaved()
    {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
        this.waitForSyncYourSavedArticlesOverlayAndClose();
    }

    public void waitForSyncYourSavedArticlesOverlayAndClose()
    {
        this.waitForElementPresent(SYNC_YOUR_SAVED_ARTICLES_OVERLAY, "Cannot find 'Sync your saved articles' overlay.", 10);
        this.waitForElementAndClick(CLOSE_SYNC_OVERLAY, "Cannot click close 'Sync your saved articles' overlay.", 5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Cannot close article, cannot find X link", 5);
    }
}
