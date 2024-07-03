import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class ChangeOrientation {

    private AndroidDriver driver;
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    @BeforeTest
    public void setup() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","uiautomator2");
        dc.setCapability("appium:app",System.getProperty("user.dir")+ "/apps/ApiDemos.apk");

        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL),dc);
    }

    @Test
    public void test() throws IOException {
        ScreenOrientation currentOrientation = driver.getOrientation();
        System.out.println(currentOrientation);
        if (currentOrientation != ScreenOrientation.LANDSCAPE) {
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }
    }
    @AfterTest
    public void close(){
        driver.quit();
    }
}
