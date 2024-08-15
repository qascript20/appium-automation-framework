package ios;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ManageApps {

    private IOSDriver driver;

    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    private static final String APP_PATH = System.getProperty("user.dir")+ "/apps/UIKitCatalog.app";

    @BeforeTest
    public void setup() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","IOS");
        dc.setCapability("appium:automationName","XCUITest");
        dc.setCapability("appium:deviceName","iPhone 15 Pro");

        driver = new IOSDriver(new URL(APPIUM_SERVER_URL),dc);
    }

    @Test
    public void isAppInstalled(){

        boolean installed = driver.isAppInstalled("com.example.apple-samplecode.UICatalog");
        System.out.println(installed);
    }

    @Test
    public void installApp(){

        driver.installApp(APP_PATH);
    }

    @Test
    public void removeApp(){

        driver.removeApp("com.example.apple-samplecode.UICatalog");
    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
