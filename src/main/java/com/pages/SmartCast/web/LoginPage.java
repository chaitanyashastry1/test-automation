package com.pages.SmartCast.web;

import com.commonFunctions.CommonFunctionsWeb;
import com.pages.SmartCast.generic.CommonLoginPage;
import com.utils.GlobalVars;
import com.utils.MailReader;
import com.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginPage extends CommonLoginPage {
    private static WebDriver driver;
    private static GlobalVars globalVars;
    private static CommonFunctionsWeb commonFunctions;

    @FindBy(id = "signInBtn")
    private static WebElement singInButton;
    @FindBy(xpath = "//input[@id='phoneEmail']")
    private static WebElement emailTextBoxMainSignInPage;
    @FindBy(id = "btSubmit")
    private static WebElement continueButtonSignInPage;
    @FindBy(id = "upass")
    private static WebElement passwordTextBoxMainSignInPage;
    @FindBy(id = "btnLogin") //login
    private static WebElement signInButtonMainSignInPage;
    @FindBy(id = "userName")
    private static WebElement userNameLabelAfterLogin;
    @FindBy(xpath = "//a[text()='Sign Out']")
    private static WebElement signOutButton;



    public LoginPage(){
        globalVars= GlobalVars.getInstance();
        driver =globalVars.getWebDriver();
        PageFactory.initElements(driver, this);
        commonFunctions= CommonFunctionsWeb.getInstance();
        System.out.println("****************** Test started ******************");
        System.out.println("******************" + globalVars.getProjectName() + "******************");

    }


    @Override
    public boolean loginWithEmailAndPassword(String email, String password, String expectedUserName) {
        boolean isLoginSuccessful;
        commonFunctions.clickElement(singInButton, 30, "Sign In Button");
        ArrayList<String> windowsList = new ArrayList<>(commonFunctions.getWindowHandlesWithExpectedNumberOfWindows(1));
        commonFunctions.switchToWindow(windowsList.get(windowsList.size() - 1));
        commonFunctions.sendKey(emailTextBoxMainSignInPage, email, 60);
        commonFunctions.clickElement(continueButtonSignInPage, 10, "Continue Button");
        commonFunctions.sendKeyBoolean(passwordTextBoxMainSignInPage, password, 10, "Password text box");
        commonFunctions.clickElement(signInButtonMainSignInPage, 10, "Login Button");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean loginWithMobileAndPassword(String phone, String password, String expectedUserName) {
        boolean isLoginSuccessful;
        commonFunctions.clickElement(singInButton, 30, "Sign In Button");
        ArrayList<String> windowsList = new ArrayList<>(commonFunctions.getWindowHandlesWithExpectedNumberOfWindows(1));
        commonFunctions.switchToWindow(windowsList.get(windowsList.size() - 1));
        commonFunctions.sendKey(emailTextBoxMainSignInPage, phone, 60);
        commonFunctions.clickElement(continueButtonSignInPage, 10, "Continue Button");
        commonFunctions.sendKeyBoolean(passwordTextBoxMainSignInPage, password, 10, "Password text box");
        commonFunctions.clickElement(signInButtonMainSignInPage, 10, "Login Button");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean signOut() {
        boolean signOutStatus;
        commonFunctions.clickElement(userNameLabelAfterLogin, 45, "userName");
        commonFunctions.clickElement(signOutButton, 60, "SignOut button");
        signOutStatus = commonFunctions.isElementDisplayed(singInButton, 70, "Sign In");
        return signOutStatus;
    }
}
