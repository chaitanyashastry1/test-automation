package com.pages.LiveMint.ios_native;

import com.commonFunctions.CommonFunctionsMobile;
import com.pages.LiveMint.generic.CommonLoginPage;
import com.utils.GetOtpMailSubject;
import com.utils.GlobalVars;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class LoginPage extends CommonLoginPage {

    private static IOSDriver driver;
    private static GlobalVars globalVars;
    private static CommonFunctionsMobile commonFunctions;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Allow']")
    private static MobileElement notificationAllow;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'Not')]")
    private static MobileElement trackingDenielButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Later']")
    private static MobileElement notificationLater;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SKIP']")
    private static MobileElement skipButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Sign in']")
    private static MobileElement signInButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Sign In']")
    private static List<MobileElement> signInButtonAfterLogOut;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Continue']")
    private static MobileElement continueButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'Proceed')]")
    private static MobileElement verifyOtpToProceedButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'Google')]")
    private static MobileElement continueWithGoogleButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Continue']")
    private static MobileElement continueWithGoogleConfirmationPrompt;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
    private static MobileElement proceedWithoutAppleID;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'Account')]")
    private static MobileElement myAccountButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'Facebook')]")
    private static MobileElement continueWithFacebookButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
    private static MobileElement emailTextBoxMainSignInPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Continue']")
    private static MobileElement continueButtonSignInPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField")  //android.widget.EditText[@resource-id='upass']
    private static MobileElement passwordTextBoxMainSignInPage;

    @iOSXCUITFindBy(id = "com.ht.news:id/continue_btn") //login //android.widget.Button[@resource-id='login']
    private static MobileElement signInButtonMainSignInPage;

    @iOSXCUITFindBy(xpath = "//div[text()='Use another account']")
    private static MobileElement useAnotherAccountGmailSignInPage;

    @iOSXCUITFindBy(className = "android.widget.EditText")
    private static MobileElement emailOrPhoneGmailSignInPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField") //m_login_email
    private static MobileElement emailOrPhoneFacebookSignInPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeTextField") //m_login_email
    private static List<MobileElement> otpTextField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField")
    private static MobileElement passwordFacebookSignInPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='main']/XCUIElementTypeButton[@name='Log In']")
    private static MobileElement loginButtonFacebookSignInPage;

    @iOSXCUITFindBy(xpath = "//android.widget.Button[@text='NEXT']")
    private static MobileElement nextButtonGmailSignInPage;

    @iOSXCUITFindBy(xpath = "//android.widget.Button[@text='NEXT']")
    private static MobileElement nextButtonGmailPasswordPage;

    @iOSXCUITFindBy(className = "android.widget.EditText")
    private static MobileElement passwordTextBoxGmailSignInPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[2]")
    private static MobileElement userNameLabelAfterLogin;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='main']/XCUIElementTypeOther[4]//XCUIElementTypeButton")
    private static MobileElement continueButtonFacebookAfterUsernamePassword;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[8]")
    private static MobileElement LogoutButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'OTP')]")
    private static MobileElement generateOtpMainSignInPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'Resend')]")
    private static MobileElement reGenerateOtpMainSignInPage;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'Verify')]")
    private static MobileElement sigInWithOtpButtonMainSignInPage;

    @iOSXCUITFindBy(className = "crossicon")
    private static MobileElement closeIconHamburger;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name ='OK']")
    private static MobileElement okButtonLogoutPopUp;

    @iOSXCUITFindBy(id = "com.ht.news:id/ep_txt")
    private static MobileElement ePaperTextLabel;


    public LoginPage() {

        globalVars = GlobalVars.getInstance();
        this.driver = (IOSDriver) globalVars.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        commonFunctions = CommonFunctionsMobile.getInstance();
        commonFunctions.clickElement(trackingDenielButton, 60);
        commonFunctions.clickElement(notificationAllow, 60);
    }


    @Override
    public boolean loginWithFacebook(String email, String password, String expectedUserName) {
        boolean isUserDisplayed;
        commonFunctions.clickElement(signInButton, 30, "signInButton");
        commonFunctions.clickElement(continueWithFacebookButton, 30, "continueWithFacebookButton");
        commonFunctions.clickElement(continueButtonSignInPage, 30, "continueButtonSignInPage");
        commonFunctions.sendKey(emailOrPhoneFacebookSignInPage, email, 30, "emailOrPhoneFacebookSignInPage");
        commonFunctions.sendKey(passwordFacebookSignInPage, password, 30, "passwordFacebookSignInPage");
        commonFunctions.clickElement(loginButtonFacebookSignInPage, 30, "loginButtonFacebookSignInPage");
        commonFunctions.clickElement(continueButtonFacebookAfterUsernamePassword, 50, "continueButtonFacebookAfterUsernamePassword");
        commonFunctions.clickElement(myAccountButton, 40, "profileButton");
        isUserDisplayed = !commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 30, "userNameLabelAfterLogin");
        return isUserDisplayed;
    }


    @Override
    public boolean logout() {
        boolean isUserSignedOut;
        commonFunctions.clickElement(userNameLabelAfterLogin, 30, "userNameLabelAfterLogin");
        commonFunctions.clickElement(LogoutButton, 30, "LogoutButton");
        commonFunctions.clickElement(okButtonLogoutPopUp, 30, "okButtonLogoutPopUp");
        isUserSignedOut = commonFunctions.isElementDisplayed(signInButton, 60, "loginInButton");
        return isUserSignedOut;

    }


    @Override
    public boolean loginWithGoogle(String email, String password, String expectedUserName) {
        boolean isUserDisplayed = false;
        commonFunctions.clickElement(continueWithGoogleButton, 30, "continueWithGoogleButton");
        commonFunctions.sendKey(emailOrPhoneGmailSignInPage, email, 30, "emailOrPhoneGmailSignInPage");
        commonFunctions.clickElement(nextButtonGmailSignInPage, 30, "nextButtonGmailSignInPage");
        commonFunctions.sendKey(passwordTextBoxGmailSignInPage, password, 30, "passwordTextBoxGmailSignInPage");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElement(nextButtonGmailPasswordPage, 30, "nextButtonGmailPasswordPage");
        commonFunctions.clickElement(myAccountButton, 30, "myAccountButton");
        isUserDisplayed = !commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 30, "userNameLabelAfterLogin");
        return isUserDisplayed;
    }


    @Override
    public boolean loginWithPassword(String email, String password, String expectedUserName) {
        boolean isUserDisplayed;
        commonFunctions.clickElement(signInButton, 20, "signInButton");
        commonFunctions.sendKey(emailTextBoxMainSignInPage, email, 20, "emailTextBoxMainSignInPage");
        commonFunctions.clickElement(continueButtonSignInPage, 20, "continueButtonSignInPage");
        commonFunctions.sendKey(passwordTextBoxMainSignInPage, password, 20, "passwordTextBoxMainSignInPage");
        commonFunctions.clickElement(continueButtonSignInPage, 20, "continueButtonSignInPage");
        commonFunctions.clickElement(proceedWithoutAppleID, 20, "proceedWithoutAppleID");
        commonFunctions.clickElement(myAccountButton, 60, "myAccountButton");
        isUserDisplayed = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "userNameLabelAfterLogin");
        return isUserDisplayed;
    }

    @Override
    public boolean loginWithOTP(String email, String password, String emailSubjectContent, String expectedUserName) {
        boolean isUserDisplayed;
        GetOtpMailSubject getOtpMailSubject;
        commonFunctions.clickElement(signInButton, 30);
        commonFunctions.sendKey(emailTextBoxMainSignInPage, email, 30);
        commonFunctions.clickElement(continueButton, 30);
        commonFunctions.clickElement(generateOtpMainSignInPage, 30);
        getOtpMailSubject = new GetOtpMailSubject();
        String otpPassword = getOtpMailSubject.gmailMailSubjectReader(email, password, emailSubjectContent);
        if (otpPassword == null) {
            commonFunctions.clickElement(reGenerateOtpMainSignInPage);
        } else if (!otpPassword.contains("Exception")) {
            for (int i = 0; i < otpPassword.length(); i++) {
                commonFunctions.sendKeyBoolean(otpTextField.get(i), otpPassword.charAt(i) + "", 20);
                commonFunctions.hideKeyboard();
            }
        }
        commonFunctions.clickElement(sigInWithOtpButtonMainSignInPage, 30);
        commonFunctions.clickElement(proceedWithoutAppleID, 30);
        commonFunctions.clickElement(myAccountButton, 60);
        isUserDisplayed = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "userNameLabelAfterLogin");
        return isUserDisplayed;
    }
}