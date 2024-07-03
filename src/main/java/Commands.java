import io.appium.java_client.Location;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Commands {

    private IOSDriver iosDriver;
    private AndroidDriver androidDriver;
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    private void setIOSCapabilities() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","IOS");
        dc.setCapability("appium:automationName","XCUITest");
        dc.setCapability("appium:app",System.getProperty("user.dir")+ "/apps/UIKitCatalog.app");
        dc.setCapability("appium:deviceName","iPhone 15 Pro");

        iosDriver = new IOSDriver(new URL(APPIUM_SERVER_URL),dc);

    }
    private void setAndroidCapabilities() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","uiautomator2");
        dc.setCapability("appium:app",System.getProperty("user.dir")+ "/apps/ApiDemos.apk");

        androidDriver = new AndroidDriver(new URL(APPIUM_SERVER_URL),dc);
    }

    @Test
    public void getGeoLocation() throws MalformedURLException {
        setAndroidCapabilities();
        Location location = androidDriver.getLocation();
        Double altitude = location.getAltitude();
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        System.out.println("Altitude is: "+ altitude);
        System.out.println("Latitude is: " + latitude);
        System.out.println("Longitude is: " + longitude);
    }
}
