package org.example;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class appiumExample extends BaseTest{
    @Test
    public void exampleTest() throws MalformedURLException {
        WebElement preferenceElement = driver.findElement(AppiumBy.accessibilityId("Preference"));
        preferenceElement.click();
    }
}
