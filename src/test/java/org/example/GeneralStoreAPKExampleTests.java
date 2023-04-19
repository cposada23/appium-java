package org.example;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

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

    @Test
    public void validateToastMessage() {
        // Click letsShop button
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        // Validate the toast message
        String toastMessage = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getText();
        Assert.assertEquals(toastMessage, "Please enter your name");
    }


    @Test
    public void addToCartExample() throws InterruptedException {
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

        // Scroll down to product
        String elementText = "Jordan 6 Rings";
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        String.format("new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"%s\")", elementText)
                )
        );

        String xpath = String.format(
            "//android.widget.TextView[@text='%s']/..//*[@resource-id='com.androidsample.generalstore:id/productAddCart']",
            elementText
        );

        driver.findElement(
            AppiumBy.xpath(xpath)
        ).click();

        Thread.sleep(1000);

        // Validate the text of the button changed to ADDED TO CART

        String buttonText = driver.findElement(
                AppiumBy.xpath(xpath)
        ).getText();

        Assert.assertEquals(buttonText, "ADDED TO CART");

        // Go to the cart and validate the product is there

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        // Validate I'm in the cart screen

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
        wait.until(ExpectedConditions.attributeContains(
            driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),
            "text",
            "Cart"
        ));


        // We only have one product, so we can validate it directly
        String productText = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(productText, elementText);

    }
}
