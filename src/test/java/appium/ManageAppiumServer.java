package appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class ManageAppiumServer {

    private static AppiumServiceBuilder appiumServiceBuilder;
    private static AppiumDriverLocalService appiumDriverLocalService;


    public static void startAppiumServer(){
        killAppiumSession();

        //Build Appium Service
        appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1");
        appiumServiceBuilder.usingPort(4723);
        appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL,"debug");

        //Start the server with builder
        appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumDriverLocalService.start();
    }

    public static void stopAppiumServer(){
        if(appiumDriverLocalService.isRunning() && appiumDriverLocalService!=null){
            //Stop the server
            appiumDriverLocalService.stop();
        }

    }

    private static void killAppiumSession(){
        String[] command = {"/usr/bin/killall","-KILL","node"};
        try{
            Runtime.getRuntime().exec(command);
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
}
