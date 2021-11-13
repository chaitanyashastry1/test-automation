package com.base;

import com.utils.Constants;
import com.utils.GlobalVars;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class IOSBase extends TestBase{
    static DesiredCapabilities capabilities;
    static String driverUrl;
    static GlobalVars globalVars;
    static AppiumDriver driver;
    static String platformName;

    @Override
    public void initializeDriver() {
        globalVars=GlobalVars.getInstance();
        platformName=globalVars.getPlatformName();
        driverUrl="http://"+globalVars.getAppiumServerIp()+":"+globalVars.getAppiumServerPort()+"/wd/hub";
        capabilities=new DesiredCapabilities();
        if(driver==null){

            if(globalVars.getIsBrowserStack()) {
                initializeDriverWithBrowserStackCapabilities();
            }
            else {
                if(platformName.equalsIgnoreCase(Constants.IOS_WEB) || platformName.equalsIgnoreCase(Constants.IOS_AMP)){
                    initializeIOSWebDriverWithCapabilities();
                }
                else if (platformName.equalsIgnoreCase(Constants.IOS_NATIVE)){
                    initializeIOSNativeDriverWithCapabilities();
                }
            }

        }

        else {
            if(globalVars.getPlatformName().equalsIgnoreCase(Constants.IOS_WEB)){

                driver.get(globalVars.getProdUrl());
            }

        }

    }


    public void initializeDriverWithBrowserStackCapabilities() {
        try{

            JSONParser parser = new JSONParser();
            String jsonConfig="src/main/resources/Capabilities/"+globalVars.getProjectName()+"/"+globalVars.getCapabilitiesJsonName()+".conf.json";
            JSONObject config = (JSONObject) parser.parse(new FileReader(jsonConfig));

            capabilities = new DesiredCapabilities();

            JSONArray envs = (JSONArray) config.get("environments");
            Map<String, String> envCapabilities = (Map<String, String>) envs.get(0);
            Iterator it = envCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }

            Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
            it = commonCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                if(capabilities.getCapability(pair.getKey().toString()) == null){

                    if(pair.getKey().toString().equalsIgnoreCase("name")){
                        //******* To append build number in the session name on browserstack
                        capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString()+"_"+globalVars.getBuildNumber());
                    }
                    else {
                        capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                    }
                }
            }


            String username = System.getenv("BROWSERSTACK_USERNAME");
            if(username == null) {
                username = (String) config.get("username");
            }

            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
            if(accessKey == null) {
                accessKey = (String) config.get("access_key");
            }


            capabilities.setCapability("nativeWebTap", true);


            globalVars.setAppiumDriver(driver = new IOSDriver<IOSElement>(new URL("http://"+username+":"+accessKey+"@"+config.get("server")+"/wd/hub"), capabilities));
            driver = globalVars.getAppiumDriver();

            //****** Extra step to navigate to URL in case of android web ********
            if(globalVars.getPlatformName().equalsIgnoreCase(Constants.IOS_WEB)){

                driver.get(globalVars.getProdUrl());
            }

//            driver.manage().timeouts().implicitlyWait(globalVars.getImplicitWait(), TimeUnit.SECONDS);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void initializeIOSNativeDriverWithCapabilities() {
        try
        {
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, globalVars.getDeviceNameIOS());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, globalVars.getPlatformVersionIOS());
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            capabilities.setCapability(MobileCapabilityType.UDID, globalVars.getUdid());
            capabilities.setCapability(Constants.BUNDLE_ID, globalVars.getUpdateWDABundleId());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Constants.IOS.toUpperCase());
            //capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            if(driver==null){
                globalVars.setAppiumDriver(new IOSDriver<>(new URL(driverUrl), capabilities));
                driver = globalVars.getAppiumDriver();
                driver.manage().timeouts().implicitlyWait(globalVars.getImplicitWait(), TimeUnit.SECONDS);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void initializeIOSWebDriverWithCapabilities() {
        try
        {
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, globalVars.getDeviceNameIOS());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, globalVars.getPlatformVersionIOS());
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            //capabilities.setCapability(MobileCapabilityType.UDID, globalVars.getUdid());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Constants.IOS.toUpperCase());
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, globalVars.getBrowser());
            if(driver==null){
                globalVars.setAppiumDriver(new IOSDriver<>(new URL(driverUrl), capabilities));
                driver = globalVars.getAppiumDriver();
                navigateToUrl();
                driver.manage().timeouts().implicitlyWait(globalVars.getImplicitWait(), TimeUnit.SECONDS);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private static void navigateToUrl(){
        String urlVariable=globalVars.getEnvironment()+"Url";
        if(platformName.equalsIgnoreCase(Constants.IOS_WEB)){
            driver.get(globalVars.getProp().getProperty(urlVariable));
        }
        else{
            driver.get(globalVars.getProp().getProperty(urlVariable)+"/amp");
        }

    }


    @Override
    public void closeDriver(){
        System.out.println("Inside IOS close driver");
        if(driver!=null) {
            driver.quit();
            globalVars.setAppiumDriver(null);
        }

    }
}
