package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;

public class appiumExample extends BaseTest{
    @Test
    public void exampleTest() throws MalformedURLException {
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
    public void longPressExample() throws MalformedURLException {
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
    public void scrollDemoTest() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
        "new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"WebView\")")
        );
    }

    @Test
    public void scrollToEndDemoTest() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        scrollToEnd();
    }


    @Test
    public void swipeDemoTest() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "true");

        // Swipe
        WebElement elementToSwipe = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));

        swipe(elementToSwipe, SwipeDirections.LEFT);

        Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "false");

    }
}
