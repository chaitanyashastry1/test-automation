package com.pages.SmartCast.web;

import com.commonFunctions.CommonFunctionsWeb;
import com.pages.SmartCast.generic.CommonSearchPage;
import com.utils.GlobalVars;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends CommonSearchPage {
    private static WebDriver driver;
    private static GlobalVars globalVars;
    private static CommonFunctionsWeb commonFunctions;

    @FindBy(id="srch")
    private static WebElement searchTextBox;
    @FindBy(xpath="//input[@type='submit'and@class='intsub']")
    private static WebElement searchIcon;
    @FindBy(xpath="//span[@data-btn='shows']")
    private static WebElement showsButton;
    @FindBy(xpath="//parent::h5//a")
    private static WebElement showsResult;
    @FindBy(xpath="//span[@data-btn='episodes']")
    private static WebElement episodesButton;
    @FindBy(xpath="//parent::h6//a")
    private static WebElement episodesResult;

    public SearchPage(){
        globalVars= GlobalVars.getInstance();
        driver =globalVars.getWebDriver();
        PageFactory.initElements(driver, this);
        commonFunctions= CommonFunctionsWeb.getInstance();
        System.out.println("****************** Test started ******************");
        System.out.println("******************" + globalVars.getProjectName() + "******************");

    }

    @Override
    public boolean getShowsSearchResults(String expectedText){
        boolean isShowsSearchSuccessful;
        commonFunctions.sendKeyBoolean(searchTextBox, expectedText,10,"SearchTextBox");
        commonFunctions.clickElementIfDisplayed(searchIcon,10,"SearchIcon");
        commonFunctions.isElementDisplayed(showsButton, 10, "Shows Button");
        commonFunctions.clickElement(showsButton, 10, "Shows Button");
        isShowsSearchSuccessful=commonFunctions.checkElementText(showsResult, expectedText, 10, "Correct text for shows");
        commonFunctions.navigateToHomePage();
        return isShowsSearchSuccessful;

    }

    @Override
    public boolean getEpisodesSearchResults(String expectedText) {
        boolean isEpisodesSearchSuccessful;
        commonFunctions.sendKeyBoolean(searchTextBox, expectedText,10,"SearchTextBox");
        commonFunctions.clickElementIfDisplayed(searchIcon,10,"SearchIcon");
        commonFunctions.isElementDisplayed(episodesButton, 10, "Episodes Button");
        commonFunctions.clickElement(episodesButton, 10, "Episodes Button");
        isEpisodesSearchSuccessful=commonFunctions.checkElementText(episodesResult, expectedText, 10, "Correct text for Episodes");
        commonFunctions.navigateToHomePage();
        return isEpisodesSearchSuccessful;
    }

}
