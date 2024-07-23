package android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ChangeOrientation {

    public AndroidDriver driver;
    ScreenOrientation currentOrientation;

    @BeforeTest
    public void setup() throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","uiautomator2");
        dc.setCapability("appium:app",System.getProperty("user.dir")+ "/apps/ApiDemos.apk");

        driver = new AndroidDriver(new URL(appiumServerUrl),dc);
    }

    @Test
    public void test(){
        currentOrientation = driver.getOrientation();
        System.out.println(currentOrientation);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        currentOrientation = driver.getOrientation();
        System.out.println(currentOrientation);
    }

//    @AfterTest
//    public void close(){
//        driver.quit();
//    }
}
