package com.base;

import com.utils.Constants;
import com.utils.GlobalVars;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.Toolkit;
import java.io.FileReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class DesktopBase extends TestBase {
    static WebDriver driver;
    static GlobalVars globalVars;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String browser;
    static DesiredCapabilities capabilities;

    @Override
    public void initializeDriver() {
        System.out.println("*******************initializeDriver start*******************");
        globalVars = GlobalVars.getInstance();
        browser = globalVars.getBrowser();
        if (driver == null || globalVars.getWebDriver() == null) {
            if (globalVars.getIsBrowserStack()) {
                initializeDriverWithBrowserStackCapabilities();
            } else {
                initializeWebDriver();
            }
        } else {
            driver = globalVars.getWebDriver();
            driver.manage().deleteAllCookies();
            driver.get(globalVars.getProdUrl());
        }
        System.out.println("*******************initializeDriver ends*******************");
    }

    public static void initializeWebDriver() {
        System.out.println("*******************initializeWebDriver start*******************");
        try {
            switch (browser) {
                case Constants.CHROME:
                    setSystemPropertyForChromeDriver();
                    if (globalVars.getBrowserMode().equalsIgnoreCase("private")) {
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--incognito");
                        //options.addArguments("--remote-allow-origins=*");
                        options.addArguments("--disable-notifications");
                        driver = new ChromeDriver(options);
                    } else {
                        driver = new ChromeDriver();
                    }
                    break;
                case Constants.FIREFOX:
                    //setSystemPropertyForFirefoxDriver();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (globalVars.getBrowserMode().equalsIgnoreCase("private")) {
                        firefoxOptions.addArguments("-private");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case Constants.OPERA:
                    //setSystemPropertyForOperaDriver();
                    if (globalVars.getBrowserMode().equalsIgnoreCase("private")) {
                        OperaOptions operaOptions = new OperaOptions();
                        operaOptions.addArguments("--private");
                        driver = new OperaDriver(operaOptions);
                    } else {
                        driver = new OperaDriver();
                    }
                    break;
                case Constants.IE:
                    //setSystemPropertyForIEDriver();
                    driver = new InternetExplorerDriver();
                    break;
                default:
                    System.out.println("Invalid browser name !!!!!!");
            }

            driver.manage().window().maximize();
            globalVars.setWebDriver(driver);
            navigateToUrl();
            System.out.println("*******************initializeWebDriver ends*******************");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void navigateToUrl() {
        System.out.println("*******************navigateToUrl start*******************");
        String urlVariable = globalVars.getEnvironment() + "Url";
        String URLToNavigate = globalVars.getProp().getProperty(urlVariable);

        driver.get(URLToNavigate);
        System.out.println("******* Navigated to URL: " + URLToNavigate);
        System.out.println("*******************navigateToUrl ends*******************");
    }

    private static void setSystemPropertyForChromeDriver() {
        if (isWindows()) {
            System.setProperty(Constants.WEB_DRIVER_CHROME, globalVars.getWebDriverPath() + "chromedriver.exe");
        } else if (isUnix()) {
            System.setProperty(Constants.WEB_DRIVER_CHROME, globalVars.getWebDriverPath() + "chromedriver_linux");
        } else if (isMac()) {
            System.setProperty(Constants.WEB_DRIVER_CHROME, globalVars.getWebDriverPath() + "chromedriver");
        }
    }

//    private static void setSystemPropertyForFirefoxDriver() {
//        if (isWindows()) {
//            System.setProperty(Constants.WEB_DRIVER_FIREFOX, globalVars.getWebDriverPath() + "geckodriver.exe");
//        } else if (isUnix()) {
//            System.setProperty(Constants.WEB_DRIVER_FIREFOX, globalVars.getWebDriverPath() + "geckodriver_linux");
//        } else if (isMac()) {
//            System.setProperty(Constants.WEB_DRIVER_FIREFOX, globalVars.getWebDriverPath() + "geckodriver");
//        }
//    }
//
//    private static void setSystemPropertyForOperaDriver() {
//        if (isWindows()) {
//            System.setProperty(Constants.WEB_DRIVER_OPERA, globalVars.getWebDriverPath() + "operadriver.exe");
//        } else if (isUnix()) {
//            System.setProperty(Constants.WEB_DRIVER_OPERA, globalVars.getWebDriverPath() + "operadriver_linux");
//        } else if (isMac()) {
//            System.setProperty(Constants.WEB_DRIVER_OPERA, globalVars.getWebDriverPath() + "operadriver");
//        }
//    }
//
//    private static void setSystemPropertyForIEDriver() {
//        if (isWindows()) {
//            System.setProperty(Constants.WEB_DRIVER_IE, globalVars.getWebDriverPath() + "IEDriverServer.exe");
//        }
//    }

    private static boolean isWindows() {
        return OS.contains("win");
    }

    private static boolean isMac() {
        return OS.contains("mac");
    }

    private static boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
    }

    @Override
    public void closeDriver() {
        driver = globalVars.getWebDriver();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception exception) {
                System.out.println("******** Exception in closeDriver() method! *****");
                System.out.println(exception.getMessage());
            }
            driver = null;
            globalVars.setWebDriver(null);
        }
    }

    public void initializeDriverWithBrowserStackCapabilities() {
        try {
            JSONParser parser = new JSONParser();
            String jsonConfig = "src/main/resources/Capabilities/" + globalVars.getProjectName() + "/" + globalVars.getCapabilitiesJsonName() + ".conf.json";
            JSONObject config = (JSONObject) parser.parse(new FileReader(jsonConfig));

            capabilities = new DesiredCapabilities();

            Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
            Iterator it = commonCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (capabilities.getCapability(pair.getKey().toString()) == null) {
                    if (pair.getKey().toString().equalsIgnoreCase("name")) {
                        capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString() + "_" + globalVars.getBuildNumber());
                    } else {
                        capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                    }
                }
            }

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-plugins");
            options.addArguments("--disable-notifications");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            String username = System.getenv("BROWSERSTACK_USERNAME");
            if (username == null) {
                username = (String) config.get("username");
            }

            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
            if (accessKey == null) {
                accessKey = (String) config.get("access_key");
            }

            driver = new RemoteWebDriver(new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"), capabilities);
            System.out.println("Initialized driver successfully");

            driver.manage().window().maximize();
            globalVars.setWebDriver(driver);
            navigateToUrl();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
