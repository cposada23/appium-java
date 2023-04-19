package org.example;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeneralStoreAPKExampleTests extends BaseTest {
    @Test
    public void fillFormExample() {
        // Type the name
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Camilo");
        // I need to hide the keyboard to be able to interact with the radio buttons
        driver.hideKeyboard();
        // Select gender in the radio button
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Male']")).click();
        // Open the select for the country
        driver.findElement(AppiumBy.id("android:id/text1")).click();

        // Select Colombia
        String country = "Colombia";
        // This is a scrollable element, I can use UIAutomator to do the scroll
        driver.findElement(
            AppiumBy.androidUIAutomator(
                String.format("new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"%s\")", country)
            )
        );
        // Click Colombia option
        driver.findElement(
            AppiumBy.xpath(
                String.format("//android.widget.TextView[@text='%s']", country)
            )
        ).click();

        // Click letsShop button
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();


        // Validate you are in the Products screen
        WebElement productsBar = driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@text='Products']")
        );

        Assert.assertEquals(productsBar.isDisplayed(), true);
    }
}
