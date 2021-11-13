package com.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utils.Constants;
import com.utils.GlobalVars;
import com.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static GlobalVars globalVars;
	 
    public synchronized static ExtentReports getReporter(){
        globalVars=GlobalVars.getInstance();
        ExtentHtmlReporter htmlReporter;
        if(extent == null){
            String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
            timeStamp="ExtentReport_"+timeStamp;

            if(globalVars.getBuildNumber()!=0){
                htmlReporter = new ExtentHtmlReporter(getCanonicalPath()+File.separator+"ExtentReports"+File.separator+"ExtentReport_"+ globalVars.getBuildNumber() + ".html");

            }
            else{
                htmlReporter = new ExtentHtmlReporter(getCanonicalPath()+File.separator+"ExtentReports"+File.separator+ timeStamp + ".html");
            }

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);


            extent.setSystemInfo("OS", Utils.getOSName());
            extent.setSystemInfo("Platform", globalVars.getPlatformName());

            if(globalVars.getPlatformName().contains(Constants.WEB)) {
                extent.setSystemInfo("Browser", globalVars.getBrowser());
            }
            extent.setSystemInfo("Environment", globalVars.getEnvironment());
            extent.setSystemInfo("Execution Type", globalVars.getExecutionType());

            //configuration items to change the look and feel
            //add content, manage tests etc
            //htmlReporter.config().setChartVisibilityOnOpen(true);
            htmlReporter.config().setDocumentTitle("Extent Report | "+globalVars.getProjectName());
            htmlReporter.config().setReportName(globalVars.getProjectName()+" | "+globalVars.getPlatformName());

            //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
            htmlReporter.config().setTheme(Theme.DARK);
            //htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        }
        return extent;
    }

    public static String getCanonicalPath(){
        String path="";
        try{
            File baseDirectory = new File(".");
            path = baseDirectory.getCanonicalPath();
        }
        catch (IOException ex){ex.printStackTrace();}
        return path;
    }
}
