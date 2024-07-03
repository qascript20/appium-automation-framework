import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PhoneCall {

    private AndroidDriver driver;
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    public static final String PHONE_NUMBER = "5551234567";

    @BeforeTest
    public void setup() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","uiautomator2");
        dc.setCapability("appium:app",System.getProperty("user.dir")+ "/apps/ApiDemos.apk");
        dc.setCapability("autoGrantPermissions",true);

        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL),dc);
    }

    @Test
    public void test() throws IOException, InterruptedException {

        driver.makeGsmCall(PHONE_NUMBER, GsmCallActions.CALL);
        Thread.sleep(2000);
        driver.makeGsmCall(PHONE_NUMBER,GsmCallActions.ACCEPT);
        driver.findElement(AppiumBy.accessibilityId("Accessibility")).click();
        Thread.sleep(2000);
        driver.makeGsmCall(PHONE_NUMBER,GsmCallActions.CANCEL);

    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
