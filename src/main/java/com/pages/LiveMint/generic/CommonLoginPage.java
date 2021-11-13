package com.pages.LiveMint.generic;


import com.pages.LiveMint.web.LoginPage;
import com.utils.Constants;
import com.utils.GlobalVars;

public abstract class CommonLoginPage {
    private static CommonLoginPage commonLoginPage;
    private static GlobalVars globalVars;

    public static CommonLoginPage getInstance(){
        System.out.println("******************* beforeMethod starts here *******************");

        globalVars= GlobalVars.getInstance();
        if(commonLoginPage==null || !globalVars.getIsLastTestCasePass()){
            switch (globalVars.getPlatformName()){
                case Constants.AMP:
                    //commonLoginPage= new com.pages.HindustanTimes.AMPPages.LoginPage();
                    break;
                case Constants.DESKTOP_WEB:
                    commonLoginPage= new LoginPage();
                    break;
                case Constants.ANDROID_AMP:
                case Constants.IOS_AMP:
                case Constants.ANDROID_WEB:
                    commonLoginPage= new com.pages.LiveMint.android_mweb.LoginPage();
                    break;
                case Constants.IOS_WEB:
                    commonLoginPage= new com.pages.LiveMint.ios_mweb.LoginPage();
                    break;
                case Constants.ANDROID_NATIVE:
                    commonLoginPage= new com.pages.LiveMint.android_native.LoginPage();
                    break;
                case Constants.IOS_NATIVE:
                    commonLoginPage= new com.pages.LiveMint.ios_native.LoginPage();
                    break;
            }
        }
        System.out.println("******************* beforeMethod ends here *******************");
        return commonLoginPage;
    }

    public abstract boolean loginWithGoogle(String email, String password, String expectedUserName);
    public abstract boolean loginWithFacebook(String email, String password, String expectedUserName);
    public abstract boolean loginWithPassword(String email, String password, String expectedUserName);
    public abstract boolean loginWithOTP(String email, String password, String emailSubjectContent, String expectedUserName);
    public abstract boolean logout();
}
