import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SendMessage {

    private AndroidDriver driver;
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    public static final String PHONE_NUMBER = "555-123-4567";

    public static final String MESSAGE = "Hello. This is a test message from QASCRIPT";

    WebDriverWait wait;

    @BeforeTest
    public void setup() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName","Android");
        dc.setCapability("appium:automationName","uiautomator2");
        dc.setCapability("appium:appPackage","com.google.android.apps.messaging");
        dc.setCapability("appium:appActivity",".ui.ConversationListActivity");

        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL),dc);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
    }

    @Test
    public void sendMessage() {

        driver.sendSMS(PHONE_NUMBER,MESSAGE);
        disableShareVideo();
    }

    @Test
    public void deleteMessage(){

        disableShareVideo();
        WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.google.android.apps.messaging:id/conversation_name")));
        WebElement messageSnippet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.google.android.apps.messaging:id/conversation_snippet")));
        Assert.assertEquals(messageSnippet.getAttribute("text"), MESSAGE);
        message.click();

        WebElement messageOptions = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("More conversation options")));
        messageOptions.click();

        WebElement deleteOption = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().text(\"Delete\")")));
        deleteOption.click();

        WebElement deleteConfirm = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.androidUIAutomator("android:id/button1")));
        deleteConfirm.click();

    }

    private void disableShareVideo(){
        try{
            WebElement shareVideo = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.google.android.apps.messaging:id/secondary_action_button")));
            if(shareVideo.isDisplayed()){
                shareVideo.click();
            }
        }
        catch (Exception e){
        }
    }

//    @AfterTest
//    public void close(){
//        driver.quit();
//    }
}
