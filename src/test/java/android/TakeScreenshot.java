package android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class TakeScreenshot {

    public AndroidDriver driver;

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
    public void test() throws IOException {
        try{
            driver.findElement(AppiumBy.accessibilityId("Accessibilit"));
        }
        catch (Exception e){
            captureScreenshot();
        }

    }

    private void captureScreenshot() throws IOException {
        Date today = new Date();
        File screenFile = driver.getScreenshotAs(OutputType.FILE);
        File saveFile = new File("resources/screenshots/screenshot" + "_" + today + ".png");
        FileUtils.copyFile(screenFile,saveFile);
    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
