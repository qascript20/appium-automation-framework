import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BrowserTest {

    private AppiumDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","IOS");
        dc.setCapability("appium:automationName","XCUITest");
        dc.setCapability("appium:deviceName","iPhone 15 Pro");
        dc.setCapability("browserName","Safari");

        driver = new AppiumDriver(new URL(appiumServerUrl),dc);
    }

    @Test
    public void test(){
        driver.get("https://saucedemo.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
