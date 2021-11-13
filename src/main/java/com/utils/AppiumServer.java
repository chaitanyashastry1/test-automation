package com.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.ServerSocket;

public class AppiumServer {
    private static AppiumDriverLocalService service=null;
    private static AppiumServiceBuilder builder;
    private static DesiredCapabilities cap;
    private static GlobalVars globalVars;


    public static void startServer() {
        globalVars=GlobalVars.getInstance();
        int appiumServerPort=Integer.parseInt(globalVars.getAppiumServerPort());
        if(!checkIfServerIsRunning(appiumServerPort)){
            cap = new DesiredCapabilities();
            cap.setCapability(Constants.NO_RESET, Constants.FALSE);
            builder = new AppiumServiceBuilder();
            builder.withIPAddress(globalVars.getAppiumServerIp());
            builder.usingPort(appiumServerPort);
            builder.withCapabilities(cap);
            builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
            builder.withArgument(GeneralServerFlag.LOG_LEVEL,Constants.ERROR);

            try{
                AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
                service.start();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void stopServer() {
        service.stop();
    }

    public static boolean checkIfServerIsRunning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

}
