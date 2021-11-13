package com.utils;

import com.annotation.Author;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.reporter.ExtentManager;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

public class Utils {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    private static GlobalVars globalVars;
    private static String OS = System.getProperty("os.name").toLowerCase();

    /*This function will initialize the ExtentReport object*/
    public static void initializeExtentReport() {
        globalVars=GlobalVars.getInstance();
        extentReports = ExtentManager.getReporter();
    }

    /*This function will initialize the ExtentTest object*/
    public static void initializeExtentTest(String methodName) {
        globalVars=GlobalVars.getInstance();
        extentTest=extentReports.createTest(methodName+" | "+ globalVars.getProjectName(), globalVars.getProjectName());

    }

    /*
     * This function will be called after every test method
     */
    public static void closeExtentTest() {
        extentTest.getExtent().flush();
    }

    /*This function will log each step of a test case*/
    public static void logStepInfo(String message) {
        extentTest.log(Status.PASS, message);
        Reporter.log(message);
    }

    public static void logFailedStepThrowableAndAddScreenshot(Throwable throwable, ITestResult iTestResult) {
        extentTest.fail(throwable);
        //extentTest.log(Status.FAIL, throwable.getMessage() + extentTest.addScreenCaptureFromBase64String(takeScreenShot(iTestResult.getName()), "error"));

    }

    public static void setAuthorInExtentReport(ITestResult iTestResult) {
        String testCaseAuthor=iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Author.class).name();
        extentTest.assignAuthor(testCaseAuthor);
    }


    /*Function to log the steps info in extent report*/
    public static void logStepInfo(boolean isResult, String stepInfo)
    {

        System.out.println("-"+stepInfo);
        if(isResult) {
            extentTest.log(Status.PASS, "-"+stepInfo);
        }
        else {
            extentTest.log(Status.FAIL, "-"+stepInfo);
            //extentTest.log(Status.FAIL, extentTest.addScreenCaptureFromBase64String(takeScreenShot()));
            //extentTest.log(Status.FAIL, extentTest.addScreenCaptureFromBase64String(takeScreenShot()));

        }
    }

    /*Function to log the action method steps info in extent report*/
    public static void logActionMethodsInfo(String stepInfo)
    {
        extentTest.log(Status.INFO, stepInfo);
    }

    /*Function to log the action method steps info in extent report*/
//    public static void logActionMethodsInfo(String stepInfo)
//    {
//        String[] messages=stepInfo.split(",,");
//        for(String message: messages) {
//            extentTest.log(Status.INFO, "-"+message);
//        }
//    }

    public static String takeScreenShot(String testName){
        String platformName=globalVars.getPlatformName();
        TakesScreenshot takesScreenshot;
        String dest;
        String base64 = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
        Date date = new Date();

        if(platformName.contains(Constants.ANDROID) || platformName.contains(Constants.IOS)) {
            takesScreenshot = (TakesScreenshot)globalVars.getAppiumDriver();
        }
        else {
            takesScreenshot =  (TakesScreenshot) globalVars.getWebDriver();
        }

        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        dest = System.getProperty("user.dir") + "/src/main/resources/screenshots/screenshot_"+testName + dateFormat.format(date)
                + ".png";
        File destination = new File(dest);
        try {
            FileUtils.copyFile(source, destination);

            InputStream inputStream = new FileInputStream(dest);
            byte[] imageBytes = IOUtils.toByteArray(inputStream);
            base64 = Base64.getEncoder().encodeToString(imageBytes);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return base64;
    }

    public static String getOSName(){
        if(isWindows()){
            return "Windows";
        }
        else if(isUnix()){
            return "Unix";
        }
        else if(isMac()){
            return "MAC";
        }

        return "";
    }

    private static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }

    private static boolean isMac() {

        return (OS.indexOf("mac") >= 0);

    }

    private static boolean isUnix() {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );

    }

    public static void markTestResultOnBrowserStack(String message, boolean failTestExists){
        JavascriptExecutor jse;
        if(globalVars.getPlatformName().equalsIgnoreCase(Constants.DESKTOP_WEB)){
            jse = (JavascriptExecutor)globalVars.getWebDriver();
        }
        else{
            jse=globalVars.getAppiumDriver();
        }

        if(failTestExists) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"" + message + "\"}}");
        }
        else{
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"All test cases passed!!!\"}}");
        }
    }


    public static HashMap<String, String> getParamsMap(String params){
        HashMap<String, String> paramsMap=new HashMap<>();
        try {
            if (!params.equalsIgnoreCase(Constants.NA) && !params.isEmpty()) {
                String[] paramsArray = params.split("\\|\\|");
                for (String param : paramsArray) {
                    if (param.contains("::")) {
                        paramsMap.put(param.split("::")[0].trim(), param.split("::")[1].trim());
                    } else {
                        System.out.println("****** param key value separator : missing !!! *********");
                        System.out.println("****** param key-value: " + param);
                    }
                }
            } else {
                System.out.println("******** either params data is empty or N/A !!! *********");
            }
        }
        catch (NullPointerException nullPointerException){
            System.out.println("***** No params data found ******");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return paramsMap;
    }
}
