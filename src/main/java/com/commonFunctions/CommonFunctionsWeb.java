package com.commonFunctions;

import com.base.TestBase;
import com.utils.Constants;
import com.utils.GlobalVars;
import com.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.collections.Lists;

import java.time.Duration;
import java.util.*;

public class CommonFunctionsWeb {
    private static int tryCount = 0;
    private static WebDriver driver;
    private static GlobalVars globalVars;
    private static CommonFunctionsWeb commonFunctionsWeb;

    public static CommonFunctionsWeb getInstance(){
        if(commonFunctionsWeb==null){
            commonFunctionsWeb=new CommonFunctionsWeb();
        }
        return commonFunctionsWeb;
    }

    private CommonFunctionsWeb(){
        globalVars= GlobalVars.getInstance();
        driver=globalVars.getWebDriver();
    }

    public boolean clickElement(WebElement element) {
        boolean isElementClicked=false;
        try{
            element.click();
            isElementClicked=true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return isElementClicked;
    }

    public boolean clickElementIfDisplayed(WebElement element, int timeOutInSecond, String elementName) {
        boolean isElementClicked=false;

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            isElementClicked = true;
            Utils.logStepInfo(true, "Clicked on " + elementName + " successfully");

        }
        catch (Exception e) {
            System.out.println("****** Unable to find "+elementName +" within "+timeOutInSecond+" seconds *****");
        }
        return isElementClicked;
    }

    public boolean clickElementIfDisplayed(WebElement element) {
        boolean isElementClicked=false;
        long t1=System.currentTimeMillis();
        if(element.isDisplayed()){
            element.click();
            isElementClicked=true;
        }
        long t2=System.currentTimeMillis();
        System.out.println("clickElementIfDisplayed for : "+globalVars.getProjectName()+" | Time: "+(t2-t1)+" | Result: "+isElementClicked);
        return isElementClicked;
    }


    public boolean clickElement(WebElement element, int timeOutInSecond) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return true;
    }

    public boolean clickElement(WebElement element, int timeOutInSecond, String elementName) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        Utils.logStepInfo(true, "Clicked on " + elementName + " successfully");
        return true;
    }

    public boolean clickElementWithActions(WebElement element) {

        Actions actions=new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        return true;
    }


    public boolean clickElementWithJS(WebElement element) {
        boolean isElementClicked=false;
        try{

            JavascriptExecutor jse=(JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click();",element);
            isElementClicked=true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return isElementClicked;
    }


    public boolean clickElementWithJS(WebElement element, int timeOutInSecond) {

        WebDriverWait wait=new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();",element);

        return true;
    }


    public boolean clickElementWithJS(WebElement element, int timeOutInSecond, String elementName) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();", element);

        Utils.logStepInfo(true, "Clicked on " + elementName + " successfully");

        return true;
    }


    public boolean clickElementWithActions(WebElement element, int timeOutInSecond) {
        Actions actions;

        WebDriverWait wait;
        actions=new Actions(driver);
        wait=new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(element));

        actions.moveToElement(element).click().build().perform();

        return true;
    }


    public void sendKey(WebElement element, String key) {
        element.sendKeys(key);
    }


    public boolean sendKeyBoolean(WebElement element, String key) {
        element.sendKeys(key);
        return true;
    }

    public void sendKeys(WebElement element, String key, int timeOutInSecond, String elementName) {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(key);
        Utils.logStepInfo(true, "SendKeys '" + key + "' to " + elementName + " successfully");
    }

    public boolean sendKeyBoolean(WebElement element, String key, int timeOutInSecond) {

        WebDriverWait wait=new WebDriverWait(driver, timeOutInSecond);

        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(key);

        return true;
    }

    public boolean sendKeyBoolean(WebElement element, String key, int timeOutInSecond, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(key);

        Utils.logStepInfo(true, "SendKeys '" + key + "' to " + elementName + " successfully");

        return true;
    }

    public void sendKey(WebElement element, String key, int timeOutInSecond) {
        WebDriverWait wait;
        try {
            wait=new WebDriverWait(driver, timeOutInSecond);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.sendKeys(key);
        }
        catch (StaleElementReferenceException staleElementReferenceException){
            System.out.println("****** Found StaleElementReferenceException *******");
            if(tryCount<1) {
                tryCount++;
                Utils.logStepInfo(false, "Found StaleElementReferenceException Retrying");
                sendKey(element, key, timeOutInSecond);
                tryCount = 0;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendKeyWithActions(WebElement element, String key) {
        Actions actions;
        try {
            actions=new Actions(driver);
            actions.moveToElement(element).sendKeys(key).build().perform();
        }
        catch (StaleElementReferenceException staleElementReferenceException){
            System.out.println("****** Found StaleElementReferenceException *******");
            tryCount++;
            if(tryCount<1) {
                tryCount++;
                Utils.logStepInfo(false, "Found StaleElementReferenceException Retrying");
                sendKeyWithActions(element, key);
                tryCount = 0;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        boolean isElementDisplayed = false;
        try {
            isElementDisplayed=element.isDisplayed();

        }
        catch (StaleElementReferenceException staleElementReferenceException){
            System.out.println("****** Found StaleElementReferenceException *******");
            if(tryCount<1) {
                tryCount++;
                Utils.logStepInfo(false, "Found StaleElementReferenceException Retrying");
                isElementDisplayed = isElementDisplayed(element);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        tryCount = 0;
        return isElementDisplayed;
    }

    public boolean isElementDisplayed(WebElement element, int timeOutInSecond) {
        boolean isElementDisplayed;

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        isElementDisplayed=wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

        return isElementDisplayed;
    }

    public boolean isElementDisplayed(WebElement element, int timeOutInSecond, String elementName) {
        boolean isElementDisplayed;

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.visibilityOf(element));
        isElementDisplayed=element.isDisplayed();
        Utils.logStepInfo(true, elementName + " available");

        return isElementDisplayed;
    }


    public String getElementText(WebElement element) {
        return element.getText();
    }

    public String getElementText(WebElement element, int timeOutInSecond) {
        String text = "";
        WebDriverWait wait;

        wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.visibilityOf(element));
        //wait.until(ExpectedConditions.textToBePresentInElement(element, "Hello, Tester11"));

        text = element.getText().trim();
        Utils.logStepInfo(true, "Get text from element successfully");
        Utils.logStepInfo(true, "Text found: " + text);

        return text;
    }

    public String getElementText(WebElement element, String expectedText, int timeOutInSecond) {
        String text = "";
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));


        text = element.getText().trim();
        Utils.logStepInfo(true, "Get text from element successfully");
        Utils.logStepInfo(true, "Text found: " + text);

        return text;
    }

    public boolean checkElementText(WebElement element, String expectedText, int timeOutInSecond, String elementName) {
        boolean isElementWithTextExist;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        isElementWithTextExist = wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
        if (isElementWithTextExist) {
            Utils.logStepInfo(true, elementName + " verified successfully");
        } else{
            Utils.logStepInfo(false, elementName + " verification failed");
        }
        Utils.logStepInfo(isElementWithTextExist, elementName + " found: " + element.getText().trim());

        return isElementWithTextExist;
    }


    public void switchToWindow(String window) {
        try {
            System.out.println("************ Windows: "+window);
            driver.switchTo().window(window);
            driver.getTitle();
        } catch (Exception e) {
            System.out.println("******* No such window found *******");
            //e.printStackTrace();
        }
    }

    public Set<String> getWindowHandlesWithExpectedNumberOfWindows(int expectedNumberOfWindows) {
        Set<String> handles=new HashSet<>();
        try {

            handles=driver.getWindowHandles();
            new WebDriverWait(driver, 20).until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));

            handles=driver.getWindowHandles();

        } catch (Exception e) {
            System.out.println("********* Exception in getWindowHandlesWithExpectedNumberOfWindows() method *****");
            System.out.println("******* No. of windows available is: "+handles.size() +" ***********");
            //e.printStackTrace();
        }

        return handles;
    }
    //    public Set<String> getWindowHandlesWithExpectedNumberOfWindows(int expectedNoOfWindows) {
//        Set<String> handles=new HashSet<>();
//        try {
//            for(int i=0; i<5; i++){
//                handles =driver.getWindowHandles();
//                System.out.println("****** Inside getWindowHandles method with expected ******"+handles.size());
//                if(handles.size()>=expectedNoOfWindows){
//                    break;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return handles;
//    }

    public void waitForWindow(int expectedNoOfWindows) {
        try {
            new WebDriverWait(driver, 20).until(ExpectedConditions.numberOfWindowsToBe(expectedNoOfWindows));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getWindowHandle() {
        String handle="";
        try {
            handle=driver.getWindowHandle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return handle;
    }

    public Set<String> getWindowHandlesAll() {
        Set<String> handles = new LinkedHashSet<>();
        try {
            handles = driver.getWindowHandles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handles;
    }

    public void deleteCookiesAndNavigateToHomePage() {
        System.out.println("*******************deleteCookiesAndNavigateToHomePage start*******************");
        try {
            closeAllWindowsExceptMasterTab();
            driver=globalVars.getWebDriver();
            String urlVariable = globalVars.getEnvironment() + "Url";
            String url = globalVars.getProp().getProperty(urlVariable);
            driver.get(url);

            driver.manage().deleteAllCookies();
            globalVars.setWebDriver(driver);

            driver.get(url);

            System.out.println("*******************deleteCookiesAndNavigateToHomePage ends*******************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateToHomePage() {
        System.out.println("*******************NavigateToHomePage start*******************");
        try {
        String currentURL = driver.getCurrentUrl();
        String urlVariable = globalVars.getEnvironment() + "Url";
        if (!currentURL.equals(urlVariable)) {
            driver.get(globalVars.getProp().getProperty(urlVariable));
        }
            System.out.println("*******************NavigateToHomePage ends*******************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFacebookCookies() {
        try {
            driver=globalVars.getWebDriver();
            driver.get("https://www.facebook.com/");
            driver.manage().deleteAllCookies();
            globalVars.setWebDriver(driver);
            String urlVariable = globalVars.getEnvironment() + "Url";
            String url = globalVars.getProp().getProperty(urlVariable);
            driver.get(url);
            //**** reinitializing this class object so that the new driver can be attached to this class object
            commonFunctionsWeb=new CommonFunctionsWeb();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteGoogleCookies() {
        try {
            driver=globalVars.getWebDriver();
            driver.get("https://myaccount.google.com/");
            driver.manage().deleteAllCookies();
            globalVars.setWebDriver(driver);
            String urlVariable = globalVars.getEnvironment() + "Url";
            String url = globalVars.getProp().getProperty(urlVariable);
            driver.get(url);

            //**** reinitializing this class object so that the new driver can be attached to this class object
            commonFunctionsWeb=new CommonFunctionsWeb();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeAllWindowsExceptMasterTab() {
        try {

            String title=driver.getTitle();
            if(title.contains("Facebook") && driver.getWindowHandles().size()>1){
                driver.close();
            }

            ArrayList<String> handleList=new ArrayList<>(driver.getWindowHandles());

            if (handleList.size() > 1) {

                for(int i=1; i<handleList.size(); i++){
                    driver.switchTo().window(handleList.get(i));
                    driver.close();
                }

            }
            driver.switchTo().window(handleList.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean switchToFrame(WebElement frameElement, int timeOutInSec) {
        boolean isSwitchSuccessful;
        try {
            driver.switchTo().parentFrame();
            WebDriverWait wait=new WebDriverWait(driver, timeOutInSec);
            wait.until(ExpectedConditions.visibilityOf(frameElement));
            driver.switchTo().frame(frameElement);
            isSwitchSuccessful=true;
        } catch (Exception e) {
            isSwitchSuccessful=false;
            System.out.println("***** Exception in switchToFrame() *****: "+e.getMessage());
        }

        return isSwitchSuccessful;
    }

    public boolean switchToFrame(String frame) {
        boolean isSwitchSuccessful;
        try {
            driver.switchTo().frame(frame);
            isSwitchSuccessful=true;
        } catch (Exception e) {
            isSwitchSuccessful=false;
            System.out.println("***** Exception in switchToFrame() *****: "+e.getMessage());
        }

        return isSwitchSuccessful;
    }

    public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("***** Exception in switchToDefaultContent() *****: "+e.getMessage());
        }

    }

    public boolean isElementDisplayedIgnoringStaleElement(WebElement element, int timeOutInSecond, String elementName) {
        boolean isElementDisplayed;

        Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(timeOutInSecond)).ignoring(StaleElementReferenceException.class).pollingEvery(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
        isElementDisplayed=element.isDisplayed();
        //WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        //isElementDisplayed=wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        Utils.logStepInfo(true, elementName + " available");

        return isElementDisplayed;
    }

    public boolean clickElementWithActions(WebElement element, int timeOutInSecond, String elementName) {
        Actions actions;

        WebDriverWait wait;
        actions=new Actions(driver);
        wait=new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(element));

        actions.moveToElement(element).click().build().perform();
        Utils.logStepInfo(true, "clicked on"+ elementName+" successfully");

        return true;
    }

    public boolean doubleClickElementWithActions(WebElement element, int timeOutInSecond, String elementName) {
        Actions actions;
        WebDriverWait wait;
        actions=new Actions(driver);
        wait=new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(element));

        actions.moveToElement(element).doubleClick().build().perform();
        Utils.logStepInfo(true, " doubleClicked on "+ elementName+" successfully" );

        return true;
    }



    public Set<String> getWindowHandles() {
        Set<String> handles = new HashSet<>();
        try {

            handles = driver.getWindowHandles();
            new WebDriverWait(driver, 20).until(ExpectedConditions.numberOfWindowsToBe(2));

            handles = driver.getWindowHandles();

        } catch (Exception e) {
            System.out.println("Exception in *****getWindowHandles*****");
            e.printStackTrace();
        }

        return handles;
    }

    public void switchToJsAlert( int timeOutInSecond, String elementName) {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println(alertText);
            alert.dismiss();
            System.out.println("***** Alert is Dismissed *****: "+alertText);
        } catch (Exception e) {
            System.out.println("***** Exception in switchToJsAlert() *****: "+e.getMessage());
        }
    }

    public boolean isElementClickable(WebElement element, int timeOutInSecond, String elementName) {
        boolean isElementClickableStatus;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.visibilityOf(element));
        isElementClickableStatus = element.isEnabled();
        Utils.logStepInfo(true, elementName + " clickable");

        return isElementClickableStatus;
    }

    public boolean isElementClickable(WebElement element, int timeOutInSecond) {
        boolean isElementClickableStatus;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.visibilityOf(element));
        isElementClickableStatus = element.isEnabled();
        Utils.logStepInfo(true,  " clickable");

        return isElementClickableStatus;
    }

    public void waitForPageLoading() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
        } catch (Exception e) {
        }
    }

    public void scrollToElement(WebElement element, String elementName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        Utils.logStepInfo(true, "Scrolled To element: " + elementName);
        waitForPageLoading();
    }

    public boolean checkPageURL(String expectedText, int timeOutInSecond, String elementName) {
        boolean isElementPageURLExist;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        String getPageUrl = driver.getCurrentUrl();
        isElementPageURLExist = getPageUrl.contains(expectedText);
        if (isElementPageURLExist) {
            Utils.logStepInfo(true, elementName + " verified successfully");
        } else {
            Utils.logStepInfo(false, elementName + " verification failed");
        }
        Utils.logStepInfo(isElementPageURLExist, elementName + " found: " + getPageUrl);

        return isElementPageURLExist;
    }

    public String getTitle() {
        String title="";
        try {
            title = driver.getTitle();
            System.out.println("Title Found : " + title);
        } catch (Exception e) {
            System.out.println("******* Title Not Found ******* " + e);
            e.printStackTrace();
        }
        return title;
    }

    public boolean checkPageURL(String expectedText, int timeOutInSecond) {
        boolean isElementPageURLExist;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        String getPageUrl = driver.getCurrentUrl();
        isElementPageURLExist = getPageUrl.contains(expectedText);
        if (isElementPageURLExist) {
            Utils.logStepInfo(true, " verified successfully");
        } else {
            Utils.logStepInfo(false,  " verification failed");
        }
        Utils.logStepInfo(isElementPageURLExist,  " found: " + getPageUrl);

        return isElementPageURLExist;
    }

    public void switchToChildWindowsExceptMasterTab() {
        try {

            ArrayList<String> handleList = new ArrayList<>(driver.getWindowHandles());
            if (handleList.size() > 1) {
                for (int i = 1; i < handleList.size(); i++) {
                    driver.switchTo().window(handleList.get(i));
                }
            }
            driver.switchTo().window(handleList.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkElementNotPresent(WebElement element) {
        boolean isElementStatus;
        try {
            if (!element.isDisplayed()) {
                isElementStatus = true;
                Utils.logStepInfo("Page not broken");
            } else {
                isElementStatus = false;
                Utils.logStepInfo("Page broken");
            }
        } catch (Exception e) {
            isElementStatus = true;
            Utils.logStepInfo("Page not broken");
        }
        return isElementStatus;
    }
}
