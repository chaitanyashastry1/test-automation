package com.listeners;

import com.commonFunctions.CommonFunctionsMobile;
import com.commonFunctions.CommonFunctionsWeb;
import com.utils.Constants;
import com.utils.GlobalVars;
import com.utils.Utils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static GlobalVars globalVars;
    private static CommonFunctionsWeb commonFunctions;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        globalVars=GlobalVars.getInstance();
        Utils.initializeExtentTest(iTestResult.getMethod().getMethodName());
        Utils.setAuthorInExtentReport(iTestResult);
//        Log.startTestCase(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Utils.closeExtentTest();
        globalVars.setLastTestCasePass(true);
//        Log.endTestCase(iTestResult.getTestName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("*******************onTestFailure start*******************");
        //Log.endTestCase(iTestResult.getTestName());
        Utils.logFailedStepThrowableAndAddScreenshot(iTestResult.getThrowable(), iTestResult);
        Utils.closeExtentTest();

        if(globalVars.getPlatformName().equalsIgnoreCase(Constants.DESKTOP_WEB)) {
            CommonFunctionsWeb commonFunctions = CommonFunctionsWeb.getInstance();
            commonFunctions.deleteCookiesAndNavigateToHomePage();
        }
        else if(globalVars.getPlatformName().equalsIgnoreCase(Constants.IOS_WEB)
                || globalVars.getPlatformName().equalsIgnoreCase(Constants.ANDROID_WEB)){
            CommonFunctionsMobile commonFunctions = CommonFunctionsMobile.getInstance();
            commonFunctions.deleteCookiesAndNavigateToHomePage();
        }
        else{
            CommonFunctionsMobile commonFunctions = CommonFunctionsMobile.getInstance();
            commonFunctions.closeAppAndRelaunch();
        }

        globalVars.setLastTestCasePass(false);

//        try {
//            Utils.captureScreenshot(iTestResult);
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("*******************onTestFailure ends*******************");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.getCurrentXmlTest().getAllParameters();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        //globalVars.getTestCaseListRunModeTrue().remove(iTestContext.getName());

    }

}

