package com.pages.LiveMint.ios_mweb;

import com.commonFunctions.CommonFunctionsMobile;
import com.pages.LiveMint.generic.CommonLoginPage;
import com.utils.GlobalVars;
import com.utils.MailReader;
import com.utils.Utils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class LoginPage extends CommonLoginPage {


    private static IOSDriver<WebElement> driver;
    private static GlobalVars globalVars;
    private static CommonFunctionsMobile commonFunctions;

    @FindBy(className = "btnExplore")
    private static WebElement hamBurger;

    @FindBy(xpath = "//aside[@class='headerIcon']//a[3]")
    private static WebElement signInButton;

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

    @FindBy(xpath = "//div[@id='uName']/strong")
    private static WebElement userNameLabelAfterLogin;

    @FindBy(xpath = "//button[contains(text(),'Continue as')]")
    private static WebElement continueAsFacebookButton;

    @FindBy(xpath = "//a[text()='Logout']")
    private static WebElement logoutButton;

    @FindBy(id = "generate_otp")
    private static WebElement generateOtpMainSignInPage;

    @FindBy(id = "reGenOtpForgot")
    private static WebElement reGenerateOtpMainSignInPage;

    @FindBy(id = "verifyLoginOTP")
    private static WebElement sigInWithOtpButtonMainSignInPage;

    @FindBy(className = "closebtn")
    private static WebElement closeIconHamburger;

    @FindBy(xpath = "//a[@title='epaper']")
    private static WebElement epaperIcon;

    @FindBy(className = "otp")
    private static List<WebElement> OTPInputBoxList;

    @FindBy(id = "agree")
    private static WebElement acceptCookie;

    @FindBy(xpath = "//a[text()='My Account']")
    private static WebElement MyAccount;

    public LoginPage() {
        globalVars = GlobalVars.getInstance();
        driver = (IOSDriver<WebElement>) globalVars.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        commonFunctions = CommonFunctionsMobile.getInstance();
    }

    @Override
    public boolean loginWithFacebook(String email, String password, String expectedUserName) {
        boolean isLoginSuccessful;
        preSetInitializationForFaceBook();
        String mainWindow = commonFunctions.getWindowHandle();
        commonFunctions.navigateToHomePage();
        commonFunctions.clickElement(signInButton, 60, "Sign In Button");
        commonFunctions.clickElement(continueWithFacebookButton, 60, "Login with Facebook");
        Set<String> allWindows = commonFunctions.getWindowHandles();
        for (String fbLoginWindow : allWindows) {
            if (!fbLoginWindow.equals(mainWindow)) {
                commonFunctions.switchToWindow(fbLoginWindow);
                break;
            }
        }
        commonFunctions.sendKeyBoolean(emailOrPhoneFacebookSignInPage, email, 20, "Email Text Box");
        commonFunctions.sendKeyBoolean(passwordFacebookSignInPage, password, 60, "Password Textbox");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElement(loginButtonFacebookSignInPage, 60, "Login Button");
        commonFunctions.switchToWindow(mainWindow);
        commonFunctions.clickElement(MyAccount, 20, "Sign In Button");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean loginWithGoogle(String email, String password, String expectedUserName) {
        boolean isLoginSuccessful;
        preSetInitializationForGoogle();
        commonFunctions.navigateToHomePage();
        commonFunctions.clickElement(signInButton, 60, "Sign In Button");
        commonFunctions.clickElement(continueWithGoogleButton, 60, "Google Login Button");
        commonFunctions.sendKeyBoolean(emailOrPhoneGmailSignInPage, email, 60, "Email Textbox");
        commonFunctions.clickElement(nextButtonGmailSignInPage, 60, "Next Button");
        commonFunctions.sendKeyBoolean(passwordTextBoxGmailSignInPage, password, 60, "Password textbox");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElement(nextButtonGmailPasswordPage, 60, "Next Button");
        commonFunctions.clickElement(signInButton, 60, "Sign In Button");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean loginWithPassword(String email, String password, String expectedUserName) {
        boolean isLoginSuccessful;
        for (int i = 0; i < 4; i++) {
            if (!commonFunctions.isElementEnabled(signInButton, 60,"singInButton")) {
                commonFunctions.pageRefresh();
            } else {
                commonFunctions.clickElement(signInButton, 60,"Sign In Button");
                break;
            }
        }
        commonFunctions.sendKeyBoolean(emailTextBoxMainSignInPage, email, 60,"email or phone box");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElement(continueButtonSignInPage, 60,"continue button");
        commonFunctions.sendKeyBoolean(passwordTextBoxMainSignInPage, password, 60,"Password box");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElement(signInButtonMainSignInPage, 60,"Login Button");
        for (int i = 0; i < 4; i++) {
            if (!commonFunctions.isElementEnabled(MyAccount, 60,"MyAccount")) {
                commonFunctions.pageRefresh();
            } else {
                commonFunctions.clickElement(MyAccount, 60,"Sign In Button");
                break;
            }
        }
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60,"UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean loginWithOTP(String email, String password, String emailSubjectContent, String expectedUserName) {
        boolean isLoginSuccessful;
        long date = System.currentTimeMillis();
        MailReader getOtp = new MailReader();
        commonFunctions.clickElement(signInButton, 60,"Sign In Button");
        commonFunctions.sendKeyBoolean(emailTextBoxMainSignInPage, email, 60,"email or phone box");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElement(continueButtonSignInPage, 60,"continue button");
        commonFunctions.clickElement(generateOtpMainSignInPage, 60,"Generate OTP");
        String OTPPassword = getOtp.getOTPFromGmail(email, password, emailSubjectContent, new Date(date));
        if (OTPPassword == null) {
            date = System.currentTimeMillis();
            Utils.logStepInfo(false, "OTP is null retrying....");
            commonFunctions.clickElement(reGenerateOtpMainSignInPage, 60,"re-generate OTP button");
            OTPPassword = getOtp.getOTPFromGmail(email, password, emailSubjectContent, new Date(date));
        }
        if (OTPPassword != null){
            Utils.logStepInfo(true, "OTP: " + OTPPassword);
            for (int i = 0; i < OTPPassword.length(); i++) {
                System.out.println("OTP Digit: " + OTPPassword.charAt(i));
                commonFunctions.sendKeyBoolean(OTPInputBoxList.get(i), OTPPassword.charAt(i) + "", 60
                );
            }
            commonFunctions.hideKeyboard();
        }
        commonFunctions.clickElement(sigInWithOtpButtonMainSignInPage, 60,"Login Button");
        commonFunctions.clickElement(MyAccount, 60,"Sign In Button");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60,"UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean logout() {
        boolean isLogoutSuccessful = false;
        commonFunctions.clickElement(logoutButton, 60,"Logout");
        for(int i =0;i<10;i++){
            if(!commonFunctions.isElementEnabled(signInButton,60,"signInButton")){
                commonFunctions.pageRefresh();
            }
            else{
                isLogoutSuccessful = commonFunctions.isElementDisplayed(signInButton, 60,"Sign-In button");
                break;
            }
        }
      return isLogoutSuccessful;
    }

    public void preSetInitializationForFaceBook() {
        commonFunctions.deleteFacebookCookies();
        driver = (IOSDriver<WebElement>) globalVars.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void preSetInitializationForGoogle() {
        commonFunctions.deleteGoogleCookies();
        driver = (IOSDriver<WebElement>) globalVars.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
