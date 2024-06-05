import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AirplaneMode {

    public AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","uiautomator2");
        dc.setCapability("appium:appPackage","com.android.settings");
        dc.setCapability("appium:appActivity",".Settings");

        driver = new AndroidDriver(new URL(appiumServerUrl),dc);
    }

    @Test
    public void setAirplaneModeOn(){
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.android.settings:id/text_frame\").instance(0)")).click();
        WebElement element = driver.findElement(AppiumBy.id("com.android.settings:id/switchWidget"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        Assert.assertTrue(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Airplane mode is on\")")).isDisplayed());
    }
//    @AfterTest
//    public void close(){
//        driver.quit();
//    }
}
