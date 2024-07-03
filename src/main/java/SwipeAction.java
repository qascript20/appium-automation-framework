
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class SwipeAction {

    public AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        String appiumServerUrl = "http://127.0.0.1:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","uiautomator2");
        dc.setCapability("appium:app",System.getProperty("user.dir")+ "/apps/ApiDemos.apk");

        driver = new AndroidDriver(new URL(appiumServerUrl),dc);
    }

    @Test
    public void test(){

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(3000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Animation")));

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Dimension dimension = driver.manage().window().getSize();
        int start = (int) (dimension.getHeight() * 0.95);
        int stop = (int) (dimension.getHeight() * 0.01);

        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), new Point(0,start)));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),new Point(0,stop)));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
        driver.findElement(AppiumBy.accessibilityId("Visibility"));


    }

//    @AfterTest
//    public void close(){
//        driver.quit();
//    }

}
