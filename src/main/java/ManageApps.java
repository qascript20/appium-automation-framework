import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ManageApps {

    public IOSDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","IOS");
        dc.setCapability("appium:automationName","XCUITest");
        dc.setCapability("appium:app",System.getProperty("user.dir")+ "/apps/UIKitCatalog.app");
        dc.setCapability("appium:deviceName","iPhone 15 Pro");

        driver = new IOSDriver(new URL(appiumServerUrl),dc);

    }

    @Test
    public void test(){
        //Is App Installed
        boolean appInstalled = driver.isAppInstalled("com.example.apple-samplecode.UICatalog");
        System.out.println(appInstalled);

        //Remove the app
        driver.removeApp("com.example.apple-samplecode.UICatalog");

        //Install the app
        driver.installApp(System.getProperty("user.dir")+ "/apps/UIKitCatalog.app");

    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
