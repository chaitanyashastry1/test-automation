package com.pages.LiveMint.android_mweb;

import com.commonFunctions.CommonFunctionsMobile;
import com.pages.LiveMint.generic.CommonLoginPage;
import com.utils.GlobalVars;
import com.utils.MailReader;
import com.utils.Utils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginPage extends CommonLoginPage {

    private static AndroidDriver<WebElement> driver;
    private static GlobalVars globalVars;
    private static CommonFunctionsMobile commonFunctions;

    @FindBy(className = "btnExplore")
    private static WebElement hamBurger;
    @FindBy(className = "userSignin")
    private static WebElement singInButton;
    @FindBy(id = "customBtn")
    private static WebElement continueWithGoogleButton;
    @FindBy(xpath = "//a[text()='Sign in with Apple']")
    private static WebElement continueWithAppleID;
    @FindBy(id = "fbmBtn")
    private static WebElement continueWithFacebookButton;
    @FindBy(id = "phoneEmail")
    private static WebElement emailTextBoxMainSignInPage;
    @FindBy(id = "btSubmit")
    private static WebElement continueButtonSignInPage;
    @FindBy(id = "upass")
    private static WebElement passwordTextBoxMainSignInPage;
    @FindBy(id = "btnLogin")
    private static WebElement signInButtonMainSignInPage;
    @FindBy(xpath = "//div[text()='Use another account']")
    private static WebElement useAnotherAccountGmailSignInPage;
    @FindBy(id = "identifierId")
    private static WebElement emailOrPhoneGmailSignInPage;
    @FindBy(id = "account_name_text_field")
    private static WebElement emailAppleSignInPage;
    @FindBy(id = "password_text_field")
    private static WebElement passwordAppleSignInPage;
    @FindBy(id = "sign-in")
    private static WebElement appleSignInNextButton;
    @FindBy(id = "m_login_email")
    private static WebElement emailOrPhoneFacebookSignInPage;
    @FindBy(id = "m_login_password")
    private static WebElement passwordFacebookSignInPage;
    @FindBy(xpath = "//button[@value='Log In']")
    private static WebElement loginButtonFacebookSignInPage;
    @FindBy(xpath = "//span[text()='Next']")
    private static WebElement nextButtonGmailSignInPage;
    @FindBy(xpath = "//span[text()='Next']")
    private static WebElement nextButtonGmailPasswordPage;
    @FindBy(xpath = "//div[@id='password']//input")
    private static WebElement passwordTextBoxGmailSignInPage;
    @FindBy(xpath = "//div[@id='uName']//strong")
    private static WebElement userNameLabelAfterLogin;
    @FindBy(xpath = "//button[contains(text(),'Continue as')]")
    private static WebElement continueAsFacebookButton;
    @FindBy(xpath = "//a[text()='Logout']")
    private static WebElement LogoutButton;
    @FindBy(id = "generate_otp")
    private static WebElement generateOtpMainSignInPage;
    @FindBy(id ="reGenOtpForgot")
    private static WebElement reGenerateOtpMainSignInPage;
    @FindBy(id="verifyLoginOTP")
    private static WebElement sigInWithOtpButtonMainSignInPage;
    @FindBy(className="closebtn")
    private static WebElement closeIconHamburger;
    @FindBy(xpath = "//a[@title='epaper']")
    private static WebElement epaperIcon;
    @FindBy(className = "otp")
    private static List<WebElement> OTPInputBoxList;
    @FindBy(xpath = "//a[text()='My Account']")
    private static WebElement MyAccount;

    public LoginPage() {
        globalVars = GlobalVars.getInstance();
        driver = (AndroidDriver<WebElement>) globalVars.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        commonFunctions = CommonFunctionsMobile.getInstance();
        System.out.println("****************** Test started ******************");
        System.out.println("******************" + globalVars.getProjectName() + "******************");
    }

    @Override
    public boolean loginWithFacebook(String email, String password, String expectedUserName) {
        boolean isLoginSuccessful;
        preSetInitializationForFaceBook();
        commonFunctions.clickElement(singInButton, 10, "Sign In Button");
        commonFunctions.clickElementWithActions(continueWithFacebookButton, 10, "Login with Facebook");
        ArrayList<String> windowsList=new ArrayList<>(commonFunctions.getWindowHandlesWithExpectedNumberOfWindows(2));
        commonFunctions.switchToWindow(windowsList.get(windowsList.size()-1));
        commonFunctions.sendKeyBoolean(emailOrPhoneFacebookSignInPage, email, 20, "Email Text Box");
        commonFunctions.sendKeyBoolean(passwordFacebookSignInPage, password, 10, "Password Textbox");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElementWithActions(loginButtonFacebookSignInPage, 10, "Login Button");
        windowsList=new ArrayList<>(commonFunctions.getWindowHandlesWithExpectedNumberOfWindows(1));
        commonFunctions.switchToWindow(windowsList.get(0));
        commonFunctions.clickElement(MyAccount, 35, "My Account Button");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean loginWithGoogle(String email, String password, String expectedUserName) {
        boolean isLoginSuccessful;
        preSetInitializationForGoogle();
        commonFunctions.clickElement(singInButton, 35, "Sign In Button");
        commonFunctions.clickElement(continueWithGoogleButton, 10, "Google Login Button");
        commonFunctions.sendKeyBoolean(emailOrPhoneGmailSignInPage, email, 10, "Email Textbox");
        commonFunctions.clickElementWithActions(nextButtonGmailSignInPage, 10, "Next Button");
        commonFunctions.sendKeyBoolean(passwordTextBoxGmailSignInPage, password, 10, "Password textbox");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElementWithActions(nextButtonGmailPasswordPage, 10, "Next Button");
        commonFunctions.clickElement(MyAccount, 45, "My Account Button");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean loginWithPassword(String email, String password, String expectedUserName) {
        boolean isLoginSuccessful;
        commonFunctions.navigateToHomePage();
        commonFunctions.clickElement(singInButton, 45, "Sign In Button");
        commonFunctions.sendKeyBoolean(emailTextBoxMainSignInPage, email, 10, "email or phone box");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElement(continueButtonSignInPage, 10, "continue button");
        commonFunctions.sendKeyBoolean(passwordTextBoxMainSignInPage, password, 10, "Password box");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElement(signInButtonMainSignInPage, 10, "Login Button");
        commonFunctions.clickElement(MyAccount, 45, "My Account Button");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 35, "UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean loginWithOTP(String email, String password, String emailSubjectContent, String expectedUserName) {
        boolean isLoginSuccessful;
        long date = System.currentTimeMillis();
        MailReader getOtp = new MailReader();
        commonFunctions.navigateToHomePage();
        commonFunctions.clickElement(singInButton, 45, "Sign In Button");
        commonFunctions.sendKeyBoolean(emailTextBoxMainSignInPage, email, 10, "email or phone box");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElement(continueButtonSignInPage, 10, "continue button");
        commonFunctions.clickElement(generateOtpMainSignInPage, 10, "Generate OTP");
        String OTPPassword = getOtp.getOTPFromGmail(email, password, emailSubjectContent, new Date(date));
        if (OTPPassword == null) {
            date = System.currentTimeMillis();
            Utils.logStepInfo(false, "OTP is null retrying....");
            commonFunctions.clickElement(reGenerateOtpMainSignInPage, 30, "re-generate OTP button");
            OTPPassword = getOtp.getOTPFromGmail(email, password, emailSubjectContent, new Date(date));
        }
        if (OTPPassword != null) {
            Utils.logStepInfo(true, "OTP: " + OTPPassword);
            for (int i = 0; i < OTPPassword.length(); i++) {
                System.out.println("OTP Digit: " + OTPPassword.charAt(i));
                commonFunctions.sendKeyBoolean(OTPInputBoxList.get(i), OTPPassword.charAt(i) + "", 5);
            }
            commonFunctions.hideKeyboard();
        }
        commonFunctions.clickElement(sigInWithOtpButtonMainSignInPage, 25, "Login Button");
        commonFunctions.clickElement(MyAccount, 45, "My Account Button");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean logout() {
        boolean isLogoutSuccessful;
        commonFunctions.clickElement(LogoutButton, 10, "Logout");
        isLogoutSuccessful = commonFunctions.isElementDisplayed(singInButton, 40, "Sign-In button");
        return isLogoutSuccessful;
    }

    public void preSetInitializationForFaceBook(){
        commonFunctions.deleteFacebookCookies();
        driver = (AndroidDriver<WebElement>) globalVars.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void preSetInitializationForGoogle(){
        commonFunctions.deleteGoogleCookies();
        driver = (AndroidDriver<WebElement>) globalVars.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
