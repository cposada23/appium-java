package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class appiumExample {

    String deviceName = "Nexus 6P API 31";
    String appPath = "/Users/camilo.posadaa/Documents/personal/framworks/java/Appium-java/src/test/java/org/resources/ApiDemos-debug.apk";

    @Test
    public void exampleTest() throws MalformedURLException {
        UiAutomator2Options capabilities = new UiAutomator2Options();
        capabilities.setDeviceName(deviceName);
        capabilities.setApp(appPath);
        URL appiumSeverURL = new URL("http://127.0.0.1:4723");
        AndroidDriver driver = new AndroidDriver(appiumSeverURL, capabilities);
    }
}
