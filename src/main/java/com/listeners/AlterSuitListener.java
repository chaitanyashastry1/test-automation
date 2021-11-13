package com.listeners;

import com.utils.GlobalVars;
import org.testng.*;
import org.testng.xml.XmlSuite;
import java.util.List;
import java.util.Map;

public class AlterSuitListener implements IAlterSuiteListener {
    private static GlobalVars globalVars;

    @Override
    public void alter(List<XmlSuite> suites) {
        Map<String, String> parametersMap=suites.get(0).getAllParameters();
        if(System.getProperty("environment")==null && !parametersMap.isEmpty()){
            setEnvironmentVariablesFromTextNGParameters(parametersMap);
        }
        else{
            setEnvironmentVariablesFromMavenArguments();
        }
    }

    public static void setEnvironmentVariablesFromTextNGParameters(Map<String, String> parametersMap){
        globalVars=GlobalVars.getInstance();
        if(!parametersMap.isEmpty()){
            globalVars.setEnvironment(parametersMap.get("environment"));
            globalVars.setProjectName(parametersMap.get("projectName"));
            globalVars.setExecutionType(parametersMap.get("executionType"));
            globalVars.setPlatformName(parametersMap.get("platformName"));
            globalVars.setBrowser(parametersMap.get("browser"));
            globalVars.setBrowserMode(parametersMap.get("browserMode"));
            globalVars.setIsBrowserStack(parametersMap.get("isBrowserStack"));
            globalVars.setCapabilitiesJsonName(parametersMap.get("capabilitiesJson"));
            globalVars.setBuildNumber(parametersMap.get("buildNumber"));
            globalVars.setGoogleSheetFlag(parametersMap.get("googleSheetFlag"));
        }
    }

    public static void setEnvironmentVariablesFromMavenArguments(){
        globalVars=GlobalVars.getInstance();
        globalVars.setEnvironment(System.getProperty("environment"));
        globalVars.setProjectName(System.getProperty("projectName"));
        globalVars.setExecutionType(System.getProperty("executionType"));
        globalVars.setPlatformName(System.getProperty("platformName"));
        globalVars.setBrowser(System.getProperty("browser"));
        globalVars.setBrowserMode(System.getProperty("browserMode"));
        globalVars.setIsBrowserStack(System.getProperty("isBrowserStack"));
        globalVars.setCapabilitiesJsonName(System.getProperty("capabilitiesJson"));
        globalVars.setBuildNumber(System.getProperty("buildNumber"));
        globalVars.setGoogleSheetFlag(System.getProperty("googleSheetFlag"));
    }
}
