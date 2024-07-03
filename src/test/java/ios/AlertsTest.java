package ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AlertsTest {

    private IOSDriver driver;

    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    @BeforeTest
    public void setup() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","IOS");
        dc.setCapability("appium:automationName","XCUITest");
        dc.setCapability("appium:app",System.getProperty("user.dir")+ "/apps/UIKitCatalog.app");
        dc.setCapability("appium:deviceName","iPhone 15 Pro");

        driver = new IOSDriver(new URL(APPIUM_SERVER_URL),dc);
    }

    @Test
    public void simpleAlert(){

        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Simple")).click();
        driver.switchTo().alert().accept();

    }

    @Test
    public void okCancelAlert(){

        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Okay / Cancel")).click();
        driver.switchTo().alert().dismiss();

    }

    @Test
    public void textEntryAlert(){

        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Text Entry")).click();
        driver.switchTo().alert().sendKeys("This is a test");
        driver.switchTo().alert().accept();

    }

    @Test
    public void otherAlert(){

        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Other")).click();
        String msg = driver.switchTo().alert().getText();
        System.out.println(msg);
        Assert.assertTrue(msg.contains("Short Title"));
        driver.switchTo().alert().dismiss();

    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
