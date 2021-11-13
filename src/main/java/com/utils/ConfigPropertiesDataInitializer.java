package com.utils;

import com.base.TestBase;

import java.io.InputStream;
import java.util.Properties;

public class ConfigPropertiesDataInitializer {
    private static GlobalVars globalVars;
    static InputStream input = null;
    private static Properties prop;
    private static ConfigPropertiesDataInitializer configPropertiesDataInitializer;

    public static ConfigPropertiesDataInitializer getInstance(){
        if(configPropertiesDataInitializer==null){
            configPropertiesDataInitializer=new ConfigPropertiesDataInitializer();
            globalVars=GlobalVars.getInstance();
        }
        return configPropertiesDataInitializer;
    }

    public void initializeConfigPropertiesVariables(){
        try {
            String projectName=globalVars.getProjectName();
            globalVars.setProp(new Properties());
            prop=globalVars.getProp();
            globalVars.setWorkingDir(System.getProperty("user.dir"));
            input = TestBase.class.getClassLoader().getResourceAsStream(projectName+"_config.properties");
            prop.load(input);
            initializeConfigPropertiesData();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeConfigPropertiesData(){

        boolean isPlatformAndroidNative=globalVars.getPlatformName().contains(Constants.ANDROID);
        boolean isPlatformIOSNative=globalVars.getPlatformName().contains(Constants.IOS);

        globalVars.setUatUrl(prop.getProperty(Constants.UAT_URL));
        globalVars.setStgUrl(prop.getProperty(Constants.STG_URL));
        globalVars.setPreProdUrl(prop.getProperty(Constants.PRE_PROD_URL));
        globalVars.setProdUrl(prop.getProperty(Constants.PROD_URL));
        globalVars.setIsAutoStartAppium(Boolean.parseBoolean(prop.getProperty(Constants.IS_AUTO_START_APPIUM)));
        globalVars.setJiraUrl(prop.getProperty(Constants.JIRA_URL));
        globalVars.setJiraUserName(prop.getProperty(Constants.JIRA_USERNAME));
        globalVars.setJiraPassword(prop.getProperty(Constants.JIRA_PASSWORD));
        globalVars.setJiraDefectAssignee(prop.getProperty(Constants.JIRA_DEFECT_ASSIGNEE));
        globalVars.setMaxRetry(Integer.parseInt(prop.getProperty(Constants.MAX_RETRY)));
        globalVars.setImplicitWait(Integer.parseInt(prop.getProperty(Constants.IMPLICIT_WAIT)));
        globalVars.setJiraProjectName(prop.getProperty(Constants.JIRA_PROJECT_NAME));
        globalVars.setJiraDefectType(prop.getProperty(Constants.JIRA_DEFECT_TYPE));
        globalVars.setAppiumServerIp(prop.getProperty(Constants.APPIUM_SERVER_IP));
        globalVars.setAppiumServerPort(prop.getProperty(Constants.APPIUM_SERVER_PORT));
        globalVars.setSpreadsheetId(prop.getProperty(Constants.SPREADSHEET_ID));

        if(isPlatformAndroidNative){
            initializeAndroidSpecificConfigData();
        }
        else if(isPlatformIOSNative){
            initializeIOSSpecificConfigData();
        }
    }


    private void initializeAndroidSpecificConfigData(){
        globalVars.setAndroidAPKFileName(prop.getProperty(Constants.ANDROID_APK_FILE_NAME));
        globalVars.setAppPackage(prop.getProperty(Constants.APP_PACKAGE));
        globalVars.setAppActivity(prop.getProperty(Constants.APP_ACTIVITY));
        globalVars.setAppWaitPackage(prop.getProperty(Constants.APP_WAIT_PACKAGE));
        globalVars.setDeviceNameAndroid(prop.getProperty(Constants.DEVICE_NAME_ANDROID));
        globalVars.setPlatformVersionAndroid(prop.getProperty(Constants.PLATFORM_VERSION_ANDROID));
    }

    private void initializeIOSSpecificConfigData(){
        globalVars.setJiraDefectType(prop.getProperty(Constants.IPA_FILE_NAME));
        globalVars.setJiraDefectType(prop.getProperty(Constants.UDID));
        globalVars.setJiraDefectType(prop.getProperty(Constants.XCODE_ORG_ID));
        globalVars.setJiraDefectType(prop.getProperty(Constants.UPDATE_WDA_BUNDLE_ID));
        globalVars.setJiraDefectType(prop.getProperty(Constants.XCODE_SIGNING_ID));
        globalVars.setDeviceNameIOS(prop.getProperty(Constants.DEVICE_NAME_IOS));
        globalVars.setPlatformVersionIOS(prop.getProperty(Constants.PLATFORM_VERSION_IOS));
    }
}
