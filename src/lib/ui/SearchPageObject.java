package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT;

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS*/

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element.", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element.");
    }

    public void waitForCancelSearchButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search button!", 5);
    }

    public void waitForCancelSearchButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search button still present.!", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click cancel search button.", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndClear(SEARCH_INPUT, "Cannot find and type into search input", 5);
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResultBySubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find result of the search with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find result of the search with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find any result search.", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element.", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed to not find any results");
    }
}
