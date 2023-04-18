package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    private String deviceName = "Nexus 6P API 31";
    private String appPath = "/Users/camilo.posadaa/Documents/personal/framworks/java/Appium-java/src/test/java/org/resources/ApiDemos-debug.apk";
    private String nodeModulesAppiumPath = "/Users/camilo.posadaa/.nvm/versions/node/v18.13.0/lib/node_modules/appium/build/lib/main.js";
    private String appiumIPAddress = "127.0.0.1";
    int appiumPort = 4723;
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    @BeforeClass
    public void configureAppium () throws MalformedURLException {
        // Start Appium Server programmatically
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(nodeModulesAppiumPath))
                .withIPAddress(appiumIPAddress)
                .usingPort(appiumPort)
                .build();

        service.start();
        // Set the capabilities
        UiAutomator2Options capabilities = new UiAutomator2Options();
        capabilities.setDeviceName(deviceName);
        capabilities.setApp(appPath);
        URL appiumSeverURL = new URL("http://".concat(appiumIPAddress.concat(":").concat(String.valueOf(appiumPort))));
        // Start the driver
        driver = new AndroidDriver(appiumSeverURL, capabilities);

    }
    @AfterClass
    public void tearDown () {
        // Clean
        driver.quit();
        service.close();
    }

}
