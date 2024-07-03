package android;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SendMessage {

    private AndroidDriver driver;
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    private static final String MOBILE_NUMBER = "555-123-4567";
    private static final String MESSAGE = "This is a test message from QASCRIPT";

    @BeforeTest
    public void setup() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","uiautomator2");
        dc.setCapability("appium:appPackage","com.google.android.apps.messaging");
        dc.setCapability("appium:appActivity",".ui.ConversationListActivity");


        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL),dc);
    }

    @Test
    public void sendMessage() {
        driver.sendSMS(MOBILE_NUMBER,MESSAGE);

    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
