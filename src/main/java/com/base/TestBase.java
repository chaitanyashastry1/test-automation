package com.base;

import com.utils.*;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public abstract class TestBase {

    public static DataReader dataReader = null;
    private static TestBase testBase=null;
    private static GlobalVars globalVars;


    public static GlobalVars setup(String project){
        System.out.println("******************* setup starts here *******************");
        instantiateClasses();

        globalVars.setProjectName(project);

        if(!globalVars.getTestCaseListRunModeTrue().isEmpty()){
            //testBase.initializeGlobalVariables();
            testBase.initializeDriver();
            initializeLoggingAndReporting();
        }
        System.out.println("******************* setup ends here *******************");
        return globalVars;
    }

    public static void instantiateClasses(){
        globalVars=GlobalVars.getInstance();
        testBase= getInstance();
        dataReader = DataReader.getInstance();
    }

    public abstract void initializeDriver();

    public static TestBase getInstance(){
        if(testBase==null) {
            switch (globalVars.getPlatformName()){
                case Constants.ANDROID_NATIVE:
                case Constants.ANDROID_AMP:
                case Constants.ANDROID_WEB:
                    testBase=new AndroidBase();
                    break;
                case Constants.IOS_NATIVE:
                case Constants.IOS_AMP:
                case Constants.IOS_WEB:
                    testBase=new IOSBase();
                    break;
                case Constants.DESKTOP_WEB:
                    testBase=new DesktopBase();
                    break;
                default:
                    System.out.println("********** Incorrect Platform Name **********");
            }
        }
        return testBase;
    }






    public static void initializeLoggingAndReporting()
    {
        if(!globalVars.getIsBrowserStack()){
            if(globalVars.getIsAutoStartAppium())
                AppiumServer.startServer();

            Utils.initializeExtentReport();
            //DOMConfigurator.configure(globalVars.getLog4jPath());
            //Log.initializeLogProperties();
        }

    }

    public static void tearDownSuite(ITestContext context) {
        if(!globalVars.getTestCaseListRunModeTrue().isEmpty()){
            if(!globalVars.getIsBrowserStack()){
                if(globalVars.getIsAutoStartAppium())
                    AppiumServer.stopServer();
            }
            else{
                markTestResultOnBrowserStack(context);
            }
            testBase.closeDriver();
        }
    }

    public abstract void closeDriver();

    public static void markTestResultOnBrowserStack(ITestContext context){
        String resultMessageFailTests="";

        boolean failTestExists=!context.getFailedTests().getAllResults().isEmpty();
        if(failTestExists){
            Set<ITestResult> testResults= context.getFailedTests().getAllResults();
            for(ITestResult result: testResults){
                String msg=result.getThrowable().getMessage();
                if(msg.contains("Build info:")) {
                    msg = msg.substring(0, msg.indexOf("Build info:"));
                }
                String exceptionName= result.getThrowable().toString();
                exceptionName=exceptionName.substring(0, exceptionName.indexOf(":"));
                String exceptionMessage= "Exception: "+exceptionName+" | Message: "+ msg;

                resultMessageFailTests=resultMessageFailTests+" || Test: "+result.getName()+"| "+exceptionMessage;
            }
            resultMessageFailTests=resultMessageFailTests.replaceFirst("||","");

            System.out.println("Master message: "+resultMessageFailTests);
        }
        Utils.markTestResultOnBrowserStack(resultMessageFailTests, failTestExists);
    }

}
