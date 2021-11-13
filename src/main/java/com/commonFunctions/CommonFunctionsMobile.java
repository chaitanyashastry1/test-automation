package com.commonFunctions;

import com.base.TestBase;
import com.utils.GlobalVars;
import com.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.tools.ant.taskdefs.Touch;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CommonFunctionsMobile {
    private static AppiumDriver driver;
    private static GlobalVars globalVars;
    private static CommonFunctionsMobile commonFunctionsMobile;

    public static CommonFunctionsMobile getInstance(){
        if(commonFunctionsMobile==null){
            commonFunctionsMobile=new CommonFunctionsMobile();
        }
        return commonFunctionsMobile;
    }

    public CommonFunctionsMobile(){
        globalVars=GlobalVars.getInstance();
        driver=globalVars.getAppiumDriver();
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

    public boolean clickElement(MobileElement element) {
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

    public boolean clickElementWithJS(WebElement element) {
        boolean isElementClicked=false;
        try{
            Thread.sleep(1000);
            JavascriptExecutor jse= driver;
            jse.executeScript("arguments[0].click();",element);
            isElementClicked=true;
            Thread.sleep(500);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return isElementClicked;
    }

    public boolean clickElementWithJS(WebElement element, int timeOutInSec) {
        boolean isElementClicked=false;
        try{
            //Thread.sleep(5000);
            WebDriverWait wait=new WebDriverWait(driver, timeOutInSec);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            JavascriptExecutor jse= driver;
            jse.executeScript("arguments[0].click();",element);
            isElementClicked=true;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return isElementClicked;
    }

    public boolean clickElementWithJS(WebElement element, int timeOutInSec, String elementName) {
        boolean isElementClicked=false;
        try{
            WebDriverWait wait=new WebDriverWait(driver, timeOutInSec);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            JavascriptExecutor jse= driver;
            jse.executeScript("arguments[0].click();",element);
            isElementClicked=true;
            Utils.logStepInfo(true, "click on " + elementName + " successfully");
        }
        catch (Exception e) {
            Utils.logStepInfo(true, "click on " + elementName + " failed");
            e.printStackTrace();
        }
        return isElementClicked;
    }

    public boolean clickElementUsingTouchAction(WebElement element) {
        boolean isElementClicked=false;

        try{
            //TouchActions actions=new TouchActions(driver);
            TouchAction action=new TouchAction(driver);
            PointOption pointOption= PointOption.point(element.getLocation());
            action.moveTo(pointOption).tap(pointOption).perform();
            isElementClicked=true;
            Thread.sleep(500);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return isElementClicked;
    }

    public boolean clickElement(WebElement element, int timeOut) {
        boolean isElementClicked=false;
        WebDriverWait wait;
        try{
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            isElementClicked=true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return isElementClicked;
    }

    public boolean clickElement(MobileElement element, int timeOut) {
        boolean isElementClicked=false;
        WebDriverWait wait;
        try{
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            isElementClicked=true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return isElementClicked;
    }

    public boolean clickElement(MobileElement element, int timeOut, String elementName) {
        boolean isElementClicked=false;
        WebDriverWait wait;
        try{
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            isElementClicked=true;
            Utils.logStepInfo(true, "click on " + elementName + " successfully");
        }
        catch (Exception e) {
            Utils.logStepInfo(false, "click on " + elementName + " failed");
            e.printStackTrace();
        }
        return isElementClicked;
    }

    public boolean clickElement(WebElement element, int timeOut, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        Utils.logStepInfo(true, "click on " + elementName + " successfully");
        return true;
    }

    public boolean clickElementWithActions(WebElement element, int timeOut) {
        boolean isElementClicked=false;
        WebDriverWait wait;
        Actions actions;
        try{
            actions=new Actions(driver);
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            actions.moveToElement(element).click().build().perform();
            isElementClicked=true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return isElementClicked;
    }

    public boolean clickElementWithActions(WebElement element, int timeOut, String elementName) {
        boolean isElementClicked=false;
        WebDriverWait wait;
        Actions actions;
        try{
            actions=new Actions(driver);
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            actions.moveToElement(element).click().build().perform();
            isElementClicked=true;
            Utils.logStepInfo(true, "click on " + elementName + " successfully");
        }
        catch (Exception e) {
            Utils.logStepInfo(false, "click on " + elementName + " failed");
            e.printStackTrace();
        }
        return isElementClicked;
    }

    public void switchToWindow(String window) {
        try {
            driver.switchTo().window(window);driver.getTitle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendKey(WebElement element, String key) {
        try {
            element.sendKeys(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendKey(WebElement element, String key, int timeOut) {
        WebDriverWait wait;
        try {
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.sendKeys(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean sendKeyBoolean(WebElement element, String key, int timeOut) {
        WebDriverWait wait;
        try {
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.sendKeys(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sendKeyBoolean(WebElement element, String key, int timeOut, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(key);
        Utils.logStepInfo(true, "Send " + key + " to " + elementName + " successfully");
        return true;
    }

    public boolean isElementDisplayed(WebElement element) {
        boolean isElementDisplayed=false;
        try {
            isElementDisplayed=element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isElementDisplayed;
    }

    public boolean isElementDisplayed(WebElement element, int timeOut) {
        boolean isElementDisplayed;
        WebDriverWait wait;
        try {
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            isElementDisplayed=element.isDisplayed();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return isElementDisplayed;
    }

    public boolean isElementDisplayed(WebElement element, int timeOut, String elementName) {
        boolean isElementDisplayed=false;
        WebDriverWait wait;
        try {
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            if(element.isDisplayed()){
                isElementDisplayed = true;
                Utils.logStepInfo(true, elementName + " displayed successfully");
            }else {
                Utils.logStepInfo(false, elementName + " displayed failed");
            }
        } catch (Exception e) {
            Utils.logStepInfo(false, elementName + " displayed failed");
            System.out.println("************** Exception in isElementDisplayed() method : "+e.getMessage());
        }
        return isElementDisplayed;
    }

    public boolean isElementDisplayedByXpath(String xpath, int timeOut) {
        boolean isElementDisplayed;
        WebDriverWait wait;
        try {
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
            isElementDisplayed=driver.findElement(By.xpath(xpath)).isDisplayed();

        } catch (Exception e) {
            e.printStackTrace();
            isElementDisplayed=false;
        }
        return isElementDisplayed;
    }

    public String getElementText(WebElement element) {
        String text="";
        try {
            text=element.getText();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public String getElementText(WebElement element, int timeOut) {
        String text="";
        WebDriverWait wait;
        try {
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(element));
            text=element.getText();
            Utils.logStepInfo(true, "text got from element: " + text + " successfully");
        } catch (Exception e) {
            Utils.logStepInfo(false, "get text from element failed");
            e.printStackTrace();
        }
        return text;
    }

    public WebElement getElementByXpath(String xpath) {
        WebElement element = null;
        try {
            element=driver.findElement(By.xpath(xpath));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    public boolean checkElementText(WebElement element, String expectedText, int timeOutInSecond, String elementName) {
        boolean isElementWithTextExist;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        System.out.println("********** Expected element text: "+ expectedText);
        System.out.println("******** Actual element text found: "+element.getText());
        isElementWithTextExist = wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
        if (isElementWithTextExist) {
            Utils.logStepInfo(true, elementName + " verified successfully");
        } else{
            Utils.logStepInfo(false, elementName + " verification failed");
        }
        Utils.logStepInfo(isElementWithTextExist, elementName + " found: " + element.getText().trim());

        return isElementWithTextExist;
    }

    public boolean checkElementTextException(WebElement element, String expectedText, int timeOutInSecond, String elementName) {
        boolean isElementWithTextExist;
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
            isElementWithTextExist = wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
            if (isElementWithTextExist) {
                Utils.logStepInfo(true, elementName + " verified successfully");
            } else {
                Utils.logStepInfo(false, elementName + " verification failed");
            }
            Utils.logStepInfo(isElementWithTextExist, elementName + " found: " + element.getText().trim());
        }catch (Exception ex){
            return false;
        }
        return isElementWithTextExist;
    }

    public void hideKeyboard(){
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCookiesAndNavigateToHomePage() {
        System.out.println("******************* deleteCookiesAndNavigateToHomePage starts here *******************");
        try {

            closeAllWindowsExceptMasterTab();
            driver=globalVars.getAppiumDriver();
            String urlVariable = globalVars.getEnvironment() + "Url";
            String url = globalVars.getProp().getProperty(urlVariable);
            driver.get(url);

            driver.manage().deleteAllCookies();
            globalVars.setAppiumDriver(driver);

            driver.get(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("******************* deleteCookiesAndNavigateToHomePage() ends here *******************");
    }

    public void deleteFacebookCookies() {
        try {

            driver=globalVars.getAppiumDriver();
            driver.get("https://www.facebook.com/");
            driver.manage().deleteAllCookies();

            globalVars.setWebDriver(driver);
            String urlVariable = globalVars.getEnvironment() + "Url";
            String url = globalVars.getProp().getProperty(urlVariable);
            driver.get(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteGoogleCookies() {
        try {
            driver=globalVars.getAppiumDriver();
            driver.get("https://myaccount.google.com/");
            driver.manage().deleteAllCookies();
            globalVars.setWebDriver(driver);
            String urlVariable = globalVars.getEnvironment() + "Url";
            String url = globalVars.getProp().getProperty(urlVariable);
            driver.get(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<String> getWindowHandles() {
        Set<String> handles=new HashSet<>();
        try {
            handles=driver.getWindowHandles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handles;
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

    public Set<String> getWindowHandlesWithExpectedNumberOfWindows(int expectedNumberOfWindows) {
        Set<String> handles=new HashSet<>();
        try {

            handles=driver.getWindowHandles();
            new WebDriverWait(driver, 20).until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));

            handles=driver.getWindowHandles();

        } catch (Exception e) {
            System.out.println("********* Exception in getWindowHandlesWithExpectedNumberOfWindows() method *****");
            System.out.println("******* No. of windows available is: "+handles.size() +" ***********");
        }

        return handles;
    }

    public boolean isElementDisplayedIgnoringStaleElementReferenceException(WebElement element, int timeOutInSecond, String elementName) {
        boolean isElementDisplayed;

        Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(timeOutInSecond)).ignoring(StaleElementReferenceException.class).pollingEvery(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
        isElementDisplayed=element.isDisplayed();
        Utils.logStepInfo(true, elementName + " available");

        return isElementDisplayed;
    }

    public void clickElementIgnoringStaleElementReferenceException(WebElement element, int timeOut, String elementName) {

        Actions actions=new Actions(driver);
        Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(timeOut)).ignoring(StaleElementReferenceException.class).pollingEvery(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
        actions.moveToElement(element).click().build().perform();
        //element.click();
        Utils.logStepInfo(true, "click on " + elementName + " successfully");
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

    public void clickElementAndHoldWithActions(WebElement elementToBeClicked, WebElement elementToBeVisible, int timeOut, String elementName) {

        WebDriverWait wait=new WebDriverWait(driver, timeOut/4);

        try {
            //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.hdr-hmcnt")));
            for(int i=0; i<4; i++) {
                //wait.until(ExpectedConditions.visibilityOf(elementToBeClicked));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.hdr-hmcnt")));
                Thread.sleep(3000);
                Actions actions = new Actions(driver);
                actions.clickAndHold(elementToBeClicked).release().perform();
                if(isElementDisplayed(elementToBeVisible)){
                    break;
                }
            }

        } catch (Exception exception) {
            System.out.println("************* Exception in clickElementWithSendKeys() method : "+ exception.getMessage());
        }

        Utils.logStepInfo(true, "click on " + elementName + " successfully");

    }

    public boolean switchToFrame(WebElement element, int timeOut){
        boolean isSwitchSuccessful=false;
        WebDriverWait wait;
        try {
            driver.switchTo().parentFrame();
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(element));
            driver.switchTo().frame(element);
            isSwitchSuccessful=true;
        } catch (Exception exception) {
            System.out.println("****** Exception in switchToFrame() method: "+exception.getMessage());
        }

        return isSwitchSuccessful;
    }

    public boolean switchToFrame(String frame, int timeOut){
        boolean isSwitchSuccessful=false;
        WebDriverWait wait;
        try {
            driver.switchTo().parentFrame();
            wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
            driver.switchTo().frame(frame);
            isSwitchSuccessful=true;
        } catch (Exception exception) {
            System.out.println("****** Exception in switchToFrame() method: "+exception.getMessage());
        }

        return isSwitchSuccessful;
    }

    public void navigateToHomePage(){
        String urlVariable = globalVars.getEnvironment() + "Url";
        driver.get(globalVars.getProp().getProperty(urlVariable));
    }

    public boolean sendKeyWithAction(String key, String elementName) {
        try {
            Actions action = new Actions(driver);
            action.sendKeys(key).build().perform();
            Utils.logStepInfo(true, "Send " + key + " to " + elementName + " successfully");
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public void sendKey(WebElement element, String key, int timeOut, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(key);
        Utils.logStepInfo(true, "Send " + key + " to " + elementName + " successfully");
    }

    public void closeAppAndRelaunch(){
        System.out.println("******************* closeAppAndRelaunch() starts here ********");
        try {
            driver=globalVars.getAppiumDriver();
            if(driver!=null){
                driver.quit();
                globalVars.setAppiumDriver(null);
            }
            TestBase testBase= TestBase.getInstance();
            testBase.initializeDriver();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("******************* closeAppAndRelaunch() ends here ********");
    }

    public boolean isElementEnabled(WebElement element, int timeOut, String elementName) {
        boolean isElementEnabled = false;
        WebDriverWait wait;
        try {
            wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(element));
            if (element.isEnabled()) {
                isElementEnabled = true;
                Utils.logStepInfo(true, elementName + " Enabled successfully");
            } else {
                Utils.logStepInfo(false, elementName + " Enabled failed");
            }
        } catch (Exception e) {
            Utils.logStepInfo(false, elementName + " displayed failed");
            System.out.println("Exception captured: " + e.getMessage());
        }
        return isElementEnabled;
    }

    public void pageRefresh(){
        driver.navigate().refresh();
    }
}
