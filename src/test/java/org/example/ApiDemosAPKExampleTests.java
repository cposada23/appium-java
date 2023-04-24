package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ApiDemosAPKExampleTests extends AndroidBaseTest {

    @BeforeMethod
    public void setup() throws InterruptedException {
        String initialActivity = ".ApiDemos";
        String packageName = "io.appium.android.apis";
        Activity activity = new Activity(packageName, initialActivity);
        driver.startActivity(activity);
        Thread.sleep(1000);
    }

    @Test
    public void exampleTest() {
        WebElement preferenceElement = driver.findElement(AppiumBy.accessibilityId("Preference"));
        preferenceElement.click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']"))
                .click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();

        // Assert
        String alertText = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertText, "WiFi settings");


        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Test");
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
    }

    @Test
    public void longPressExample() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Expandable Lists']")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

        WebElement element = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']"));
        longPress(element, 2000);
        // Assert that the menu appears
        Assert.assertTrue(driver.findElement(AppiumBy.id("android:id/title")).isDisplayed());
        String menuText = driver.findElement(AppiumBy.id("android:id/title")).getText();
        Assert.assertEquals(menuText, "Sample menu");
    }

    @Test
    public void scrollDemoTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
        "new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"WebView\")")
        );
    }

    @Test
    public void scrollToEndDemoTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        scrollToEnd();
    }


    @Test
    public void swipeDemoTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "true");

        // Swipe
        WebElement elementToSwipe = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));

        swipe(elementToSwipe, SwipeDirections.LEFT);

        Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "false");

    }

    @Test
    public void dragAndDropDemo() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));

        dragAndDrop(source, 649, 677);

        String result = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(result, "Dropped!");

    }

    @Test
    public void deviceOrientation(){
        WebElement preferenceElement = driver.findElement(AppiumBy.accessibilityId("Preference"));
        preferenceElement.click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']"))
                .click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        DeviceRotation landScape = new DeviceRotation(0,0,90);
        driver.rotate(landScape);

        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();


        // Assert
        String alertText = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertText, "WiFi settings");

        driver.setClipboardText("Test");
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();

        driver.pressKey(new KeyEvent(AndroidKey.HOME));

    }


    @Test
    public void startActivityExample () throws InterruptedException {
        String initialActivity = ".ApiDemos";
        String toActivity = ".preference.AdvancedPreferences";
        String packageName = "io.appium.android.apis";
        Activity activity = new Activity(packageName, toActivity);
        driver.startActivity(activity);
        Thread.sleep(5000);
    }
}
