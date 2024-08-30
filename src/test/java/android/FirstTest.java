package android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstTest {


    public AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","uiautomator2");
        dc.setCapability("appium:app",System.getProperty("user.dir")+ "/apps/ApiDemos.apk");
        dc.setCapability("appium:deviceName","moto_e20");
        dc.setCapability("appium:platformVersion","11");

        driver = new AndroidDriver(new URL(appiumServerUrl),dc);
    }

    @Test
    public void test(){
        driver.findElement(AppiumBy.accessibilityId("Accessibility")).click();
    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
