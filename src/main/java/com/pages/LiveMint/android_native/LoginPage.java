package com.pages.LiveMint.android_native;

import com.commonFunctions.CommonFunctionsMobile;
import com.pages.LiveMint.generic.CommonLoginPage;
import com.utils.GlobalVars;
import com.utils.MailReader;
import com.utils.Utils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Date;

public class LoginPage extends CommonLoginPage {

    static AndroidDriver<MobileElement> driver;
    private static GlobalVars globalVars;
    private static CommonFunctionsMobile commonFunctions;

    @AndroidFindBy(id = "com.ht.news:id/tvSkip")
    private static MobileElement skipButton;

    @AndroidFindBy(accessibility = "Open navigation drawer")
    private static MobileElement hamBurger;

    @AndroidFindBy(id = "com.ht.news:id/ivProfile")
    private static MobileElement singInButton;

    @AndroidFindBy(id = "com.htmedia.mint:id/txtViewEpaper")
    private static MobileElement SignInButton;

    @AndroidFindBy(id = "com.ht.news:id/google_btn")
    private static MobileElement continueWithGoogleButton;

    @AndroidFindBy(id = "com.ht.news:id/apple_btn")
    private static MobileElement continueWithAppleID;

    @AndroidFindBy(id = "com.htmedia.mint:id/fb_login_container")
    private static MobileElement continueWithFacebookButton;

    @AndroidFindBy(id = "com.htmedia.mint:id/email_or_mobile_et")
    private static MobileElement emailTextBoxMainSignInPage;

    @AndroidFindBy(id = "com.htmedia.mint:id/continue_btn")
    private static MobileElement continueButtonSignInPage;

    @AndroidFindBy(id = "com.htmedia.mint:id/password_et")
    private static MobileElement passwordTextBoxMainSignInPage;

    @AndroidFindBy(xpath = "(//android.widget.Button[@text='Continue'])[2]")
    private static MobileElement signInButtonMainSignInPage;

    @AndroidFindBy(xpath = "//div[text()='Use another account']")
    private static MobileElement useAnotherAccountGmailSignInPage;

    @AndroidFindBy(className = "android.widget.EditText")
    private static MobileElement emailOrPhoneGmailSignInPage;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone or Email']")
    private static MobileElement emailOrPhoneFacebookSignInPage;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Password']")
    private static MobileElement passwordFacebookSignInPage;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='LOG IN']")
    private static MobileElement loginButtonFacebookSignInPage;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='NEXT']")
    private static MobileElement nextButtonGmailSignInPage;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='NEXT']")
    private static MobileElement nextButtonGmailPasswordPage;

    @AndroidFindBy(className = "android.widget.EditText")
    private static MobileElement passwordTextBoxGmailSignInPage;

    @AndroidFindBy(id = "com.htmedia.mint:id/name")
    private static MobileElement userNameLabelAfterLogin;

    @AndroidFindBy(id = "com.htmedia.mint:id/txtLogout")
    private static MobileElement LogoutButton;

    @AndroidFindBy(id = "com.htmedia.mint:id/generate_otp_tv")
    private static MobileElement generateOtpMainSignInPage;

    @AndroidFindBy(id ="com.htmedia.mint:id/resend_otp_tv")
    private static MobileElement reGenerateOtpMainSignInPage;

    @AndroidFindBy(id="com.htmedia.mint:id/verify_btn")
    private static MobileElement verifyOTPButton;

    @AndroidFindBy(className="crossicon")
    private static MobileElement closeIconHamburger;

    @AndroidFindBy(id="android:id/button1")
    private static MobileElement yesButtonLogoutPopUp;

    @AndroidFindBy(id="com.ht.news:id/ep_txt")
    private static MobileElement ePaperTextLabel;

    @AndroidFindBy(id = "com.htmedia.mint:id/action_profile")
    private static MobileElement profileButton;

    @AndroidFindBy(id = "com.htmedia.mint:id/digit_one_tv")
    private static MobileElement otpOne;

    public LoginPage() {
        globalVars = GlobalVars.getInstance();
        driver = (AndroidDriver) globalVars.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        commonFunctions = CommonFunctionsMobile.getInstance();
    }

    @Override
    public boolean loginWithFacebook(String email, String password, String expectedUserName) {
        boolean isLoginSuccessful;
        commonFunctions.clickElement(SignInButton, 60, "Sign-In button");
        commonFunctions.clickElement(continueWithFacebookButton, 60, "Login with Facebook");
        commonFunctions.sendKeyBoolean(emailOrPhoneFacebookSignInPage, email, 60, "email TextBox");
        commonFunctions.sendKeyBoolean(passwordFacebookSignInPage, password, 60, "password TextBox");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElement(loginButtonFacebookSignInPage, 60, "FB login button");
        commonFunctions.clickElement(profileButton, 60, "User Profile");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean logout() {
        boolean isUserSignedOut;
        commonFunctions.clickElement(LogoutButton, 60, "Logout Button");
        isUserSignedOut = commonFunctions.isElementDisplayed(SignInButton, 60, "Sign-In Button");
        return isUserSignedOut;
    }

    @Override
    public boolean loginWithPassword(String email, String password, String expectedUserName) {
        boolean isLoginSuccessful;
        commonFunctions.clickElement(SignInButton, 60, "Sign-In button");
        commonFunctions.sendKeyBoolean(emailTextBoxMainSignInPage, email, 60, "Email/Phone TextBox");
        commonFunctions.clickElement(continueButtonSignInPage, 60, "Continue Button");
        commonFunctions.sendKeyBoolean(passwordTextBoxMainSignInPage, password, 60, "Password Textbox");
        commonFunctions.clickElement(signInButtonMainSignInPage, 60, "Sign-In button");
        commonFunctions.clickElement(profileButton, 60, "Profile Icon");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean loginWithOTP(String email, String password, String emailSubjectContent, String expectedUserName) {
        boolean isLoginSuccessful;
        long date = System.currentTimeMillis();
        MailReader getOtp = new MailReader();
        commonFunctions.clickElement(SignInButton, 60, "Sign-In button");
        commonFunctions.sendKeyBoolean(emailTextBoxMainSignInPage, email, 60, "emailOrPhone");
        commonFunctions.clickElement(continueButtonSignInPage, 60, "Continue Button");
        commonFunctions.clickElement(generateOtpMainSignInPage, 60, "Generate OTP");
        String OTPPassword = getOtp.getOTPFromGmail(email, password, emailSubjectContent, new Date(date));
        if (OTPPassword == null) {
            date = System.currentTimeMillis();
            Utils.logStepInfo(false, "OTP is null retrying....");
            commonFunctions.clickElement(reGenerateOtpMainSignInPage, 60, "re-generate OTP button");
            OTPPassword = getOtp.getOTPFromGmail(email, password, emailSubjectContent, new Date(date));
        }
        if (!OTPPassword.isEmpty()) {
            Utils.logStepInfo(true, "OTP: " + OTPPassword);
            commonFunctions.sendKeyWithAction(OTPPassword, "OTP");
            commonFunctions.hideKeyboard();
        }
        commonFunctions.clickElement(verifyOTPButton, 20, "OTP Verify Button");
        commonFunctions.clickElement(profileButton, 60, "Profile Icon");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "UserName");
        return isLoginSuccessful;
    }

    @Override
    public boolean loginWithGoogle(String email, String password, String expectedUserName) {
        boolean isLoginSuccessful;
        commonFunctions.clickElement(SignInButton, 60, "Login Button");
        commonFunctions.clickElement(continueWithGoogleButton, 60, "Login with Google");
        commonFunctions.sendKeyBoolean(emailOrPhoneGmailSignInPage, email, 60, "Email");
        commonFunctions.clickElement(nextButtonGmailSignInPage, 10, "Next Button");
        commonFunctions.sendKeyBoolean(passwordTextBoxGmailSignInPage, password, 60, "Password");
        commonFunctions.hideKeyboard();
        commonFunctions.clickElement(nextButtonGmailPasswordPage, 60, "Next Button");
        commonFunctions.clickElement(SignInButton, 60, "Hamburger");
        isLoginSuccessful = commonFunctions.checkElementText(userNameLabelAfterLogin, expectedUserName, 60, "UserName");
        return isLoginSuccessful;
    }
}
