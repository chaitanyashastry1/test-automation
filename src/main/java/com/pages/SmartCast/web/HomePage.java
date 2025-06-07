package com.pages.SmartCast.web;

import com.commonFunctions.CommonFunctionsWeb;
import com.pages.SmartCast.generic.CommonHomePage;
import com.utils.GlobalVars;
import com.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends CommonHomePage {
	private static WebDriver driver;
	private static GlobalVars globalVars;
	private static CommonFunctionsWeb commonFunctions;

	@FindBy(xpath="(//img[@alt='logo'])[1]")
	private static WebElement smartCastLogo;
	@FindBy(xpath="//div/small[@class='liner2']")
	private static WebElement hamburgerLinesOpen;
	@FindBy(xpath="//div/small[@class='liner2']")
	private static WebElement hamburgerLinesClosed;
	@FindBy(xpath = "//nav[@class='primaryMenu']/ul/li/a")
	private static List<WebElement> hamburgerTopmost;
	@FindBy(xpath="//div[normalize-space()='Home']")
	private static WebElement hamburgerHome;
	@FindBy(xpath="//div[normalize-space()='Trending']")
	private static WebElement hamburgerTrending;
	@FindBy(xpath="//div[normalize-space()='Publishers']")
	private static WebElement hamburgerPublishers;
	@FindBy(xpath = "//div[normalize-space()='Categories']")
	private static WebElement hamburgerCategories;
	@FindBy(xpath = "//ul[@class='childMenu']/li/a")
	private static List<WebElement> hamburgerCategoriesList;




	public HomePage(){
		globalVars= GlobalVars.getInstance();
		driver =globalVars.getWebDriver();
		PageFactory.initElements(driver, this);
		commonFunctions= CommonFunctionsWeb.getInstance();
		System.out.println("****************** Test started ******************");
		System.out.println("******************" + globalVars.getProjectName() + "******************");

	}

	@Override
	public String getSmartCastTitle(String expectedTitle) {
		return commonFunctions.getTitle();
	}

	@Override
	public boolean checkSmartCastLogo() {
		return commonFunctions.isElementDisplayed(smartCastLogo ,5, "smartCast logo");
	}

	@Override
	public boolean checkHamburger() {
		boolean checkHamburgerStatus;
		commonFunctions.navigateToHomePage();
		commonFunctions.isElementDisplayed(hamburgerLinesOpen, 10, "Hamburger Lines");
		boolean isHamburgerPresent = commonFunctions.isElementDisplayed(hamburgerHome, 10, "hamburger Home ")&&commonFunctions.isElementClickable(hamburgerHome, 10, "hamburger Home ")&&
				commonFunctions.isElementDisplayed(hamburgerTrending, 20, "hamburger Trending ")&&
				commonFunctions.isElementDisplayed(hamburgerPublishers, 10, "hamburger Publishers ")&&commonFunctions.isElementClickable(hamburgerPublishers, 10, "hamburger Publishers ")&&
				commonFunctions.isElementDisplayed(hamburgerCategories, 10, "hamburger Categories ")&&commonFunctions.isElementClickable(hamburgerCategories, 10, "hamburger Categories ");
		commonFunctions.navigateToHomePage();
		boolean isHamburgerCategoriesListNotBlank = checkHamburgerCategoriesListNotBlank();
		//boolean isHamburgerTopmostListNotBlank = checkHamburgerTopmostListNotBlank();
		//boolean isHamburgerBottomListNotBlank = checkHamburgerBottomListNotBlank();
		//boolean checkAllTopMostLinksInHamburgerStatus = checkTopMostHamburgerElements();
		boolean checkAllCategoriesLinksInHamburgerStatus = checkAllCategoriesLinksInHamburger();
		//boolean checkAllBottomLinksInHamburgerStatus = checkBottomHamburgerElements();
		//boolean isHamburgerOpensAndCloseStatus = checkHamburgerOpensAndClose();
		if (isHamburgerPresent && isHamburgerCategoriesListNotBlank  ) {
			checkHamburgerStatus = true;
		} else {
			checkHamburgerStatus = false;
		}
		return checkHamburgerStatus;

	}


	public boolean checkAllCategoriesLinksInHamburger() {
		boolean checkAllCategoriesLinksInHamburgerStatus;
		boolean isLinkClickable = false;
		boolean isLinkDisplayed = false;
		boolean isURLCorrectStatus = false;
		boolean isPageBrokenStatus = false;

		try {
			for (int i = 0; i < hamburgerCategoriesList.size(); i++) {
				String hrefValue = hamburgerCategoriesList.get(i).getAttribute("href");
				String propValue = hamburgerCategoriesList.get(i).getText().toLowerCase();
				isLinkDisplayed = commonFunctions.isElementDisplayed(hamburgerCategoriesList.get(i), 60, propValue);
				isLinkClickable = commonFunctions.isElementClickable(hamburgerCategoriesList.get(i), 60, propValue);
				hamburgerCategoriesList.get(i).click();
				isURLCorrectStatus = commonFunctions.checkPageURL(hrefValue, 60, "");
				//isPageBrokenStatus = commonFunctions.checkElementNotPresent(error);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (isLinkClickable && isLinkDisplayed && isURLCorrectStatus) {
			checkAllCategoriesLinksInHamburgerStatus = true;
		} else {
			checkAllCategoriesLinksInHamburgerStatus = false;
		}
		return checkAllCategoriesLinksInHamburgerStatus;

	}

//	public boolean checkTopMostHamburgerElements() {
//		boolean checkAllTopMostLinksInHamburgerStatus;
//		boolean isLinkClickable= false;
//		boolean isLinkDisplayed= false;
//		boolean isURLCorrectStatus= false;
//		boolean isPageBrokenStatus= false;
//		try {
//			for (int i = 0; i < hamburgerTopmost.size(); i++) {
//				String hrefValue = hamburgerTopmost.get(i).getAttribute("href");
//				String propValue = hamburgerTopmost.get(i).getText().toLowerCase();
//				isLinkDisplayed = commonFunctions.isElementDisplayed(hamburgerTopmost.get(i), 60, propValue);
//				isLinkClickable = commonFunctions.isElementClickable(hamburgerTopmost.get(i), 60, propValue);
//				hamburgerTopmost.get(i).click();
//				isURLCorrectStatus = commonFunctions.checkPageURL(hrefValue, 60, "");
//				//isPageBrokenStatus = commonFunctions.checkElementNotPresent(error);
//			}
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		if (isLinkClickable && isLinkDisplayed && isURLCorrectStatus) {
//			checkAllTopMostLinksInHamburgerStatus = true;
//		} else {
//			checkAllTopMostLinksInHamburgerStatus = false;
//		}
//		return checkAllTopMostLinksInHamburgerStatus;
//	}

//	public boolean checkBottomHamburgerElements() {
//		boolean checkAllBottomLinksInHamburgerStatus;
//		boolean isLinkClickable= false;
//		boolean isLinkDisplayed= false;
//		boolean isURLCorrectStatus= false;
//		boolean isPageBrokenStatus= false;
//		try {
//			for (int i = 0; i < hamburgerBottom.size(); i++) {
//				String hrefValue = hamburgerBottom.get(i).getAttribute("href");
//				String propValue = hamburgerBottom.get(i).getText().toLowerCase();
//				isLinkDisplayed = commonFunctions.isElementDisplayed(hamburgerBottom.get(i), 60, propValue);
//				isLinkClickable = commonFunctions.isElementClickable(hamburgerBottom.get(i), 60, propValue);
//				hamburgerBottom.get(i).click();
//				isURLCorrectStatus = commonFunctions.checkPageURL(hrefValue, 60, "");
//				isPageBrokenStatus = commonFunctions.checkElementNotPresent(error);
//			}
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		if (isLinkClickable && isLinkDisplayed && isURLCorrectStatus && isPageBrokenStatus) {
//			checkAllBottomLinksInHamburgerStatus = true;
//		} else {
//			checkAllBottomLinksInHamburgerStatus = false;
//		}
//		return checkAllBottomLinksInHamburgerStatus;
//	}

//	public boolean checkHamburgerTopmostListNotBlank() {
//		boolean checkHamburgerTopmostListNotBlankStatus = false;
//		try {
//			if (hamburgerTopmost.size() != 0) {
//				checkHamburgerTopmostListNotBlankStatus = true;
//				Utils.logStepInfo("Hamburger Topmost List Not Blank");
//			} else {
//				checkHamburgerTopmostListNotBlankStatus = false;
//				Utils.logStepInfo("Hamburger Topmost List is Blank");
//			}
//		} catch (Exception e) {
//			System.out.println("Topmost List Not found in page");
//		}
//		return checkHamburgerTopmostListNotBlankStatus;
//	}

//	public boolean checkHamburgerBottomListNotBlank() {
//		boolean checkHamburgerBottomListNotBlankStatus = false;
//		try {
//			if (hamburgerBottom.size() != 0) {
//				checkHamburgerBottomListNotBlankStatus = true;
//				Utils.logStepInfo("Hamburger Bottom List Not Blank");
//			} else {
//				checkHamburgerBottomListNotBlankStatus = false;
//				Utils.logStepInfo("Hamburger Bottom List is Blank");
//			}
//		} catch (Exception e) {
//			System.out.println("Bottom List Not found in page");
//		}
//		return checkHamburgerBottomListNotBlankStatus;
//	}

	public boolean checkHamburgerCategoriesListNotBlank() {
		boolean checkHamburgerCategoriesListNotBlankStatus = false;
		try {
			if (hamburgerCategoriesList.size() != 0) {
				checkHamburgerCategoriesListNotBlankStatus = true;
				Utils.logStepInfo("Hamburger Categories List Not Blank");
			} else {
				checkHamburgerCategoriesListNotBlankStatus = false;
				Utils.logStepInfo("Hamburger Categories List is Blank");
			}
		} catch (Exception e) {
			System.out.println("Categories Not found in page");
		}
		return checkHamburgerCategoriesListNotBlankStatus;
	}

	public boolean checkHamburgerOpensAndClose() {
		boolean isHamburgerOpensAndCloseStatus;
		boolean isLinkClickable= false;
		boolean isLinkDisplayed= false;
		boolean isHamburgerOpen= false;
		boolean isHamburgerClosed= false;
		try {
			isLinkDisplayed = commonFunctions.isElementDisplayed(hamburgerLinesOpen, 60, "HamburgerLines");
			isLinkClickable = commonFunctions.isElementClickable(hamburgerLinesOpen, 60, "HamburgerLines");
			isHamburgerOpen = commonFunctions.isElementClickable(hamburgerLinesOpen, 60, "Hamburger is Open");

			commonFunctions.clickElementIfDisplayed(hamburgerLinesOpen,60, "Hamburger is Closed");

			isLinkDisplayed = commonFunctions.isElementDisplayed(hamburgerLinesClosed, 60, "HamburgerLines");
			isLinkClickable = commonFunctions.isElementClickable(hamburgerLinesClosed, 60, "HamburgerLines");
			isHamburgerClosed = commonFunctions.isElementClickable(hamburgerLinesClosed, 60, "Hamburger is Closed");

			commonFunctions.clickElementIfDisplayed(hamburgerLinesClosed,60, "Hamburger is Open");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		if (isLinkClickable && isLinkDisplayed && isHamburgerOpen && isHamburgerClosed) {
			isHamburgerOpensAndCloseStatus = true;
		} else {
			isHamburgerOpensAndCloseStatus = false;
		}
		return isHamburgerOpensAndCloseStatus;
	}
}

