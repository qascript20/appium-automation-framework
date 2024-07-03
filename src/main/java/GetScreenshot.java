import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class GetScreenshot {

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
