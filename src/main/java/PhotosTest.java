import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class PhotosTest {

    private AndroidDriver driver;
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    private static final String ANDROID_PHOTO_PATH = "/mnt/sdcard/Pictures";

    @BeforeTest
    public void setup() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","uiautomator2");
        dc.setCapability("appium:appPackage","com.google.android.apps.photos");
        dc.setCapability("appium:appActivity",".home.HomeActivity");
        //dc.setCapability("noReset",true);

        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL),dc);
    }

    @Test
    public void test() throws IOException {
        File image = new File("resources/images/MonaLisa.jpg").getAbsoluteFile();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.google.android.apps.photos:id/touch_outside"))).click();
        driver.pushFile(ANDROID_PHOTO_PATH +"/"+ image.getName(),image);
    }

//    @AfterTest
//    public void close(){
//        driver.quit();
//    }

}
