package org.example;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    @Test
    public void validateTotalCartAmmount() throws InterruptedException, ParseException {

        // Two products to add to cart
        String product1 = "Air Jordan 4 Retro";
        String product2 = "Jordan 6 Rings";


        // Type the name
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Camilo");
        // I need to hide the keyboard to be able to interact with the radio buttons
        driver.hideKeyboard();
        // Select gender in the radio button
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Male']")).click();
        // Open the select for the country
        driver.findElement(AppiumBy.id("android:id/text1")).click();

        // Select Colombia
        String country = "Argentina";
        // This is a scrollable element, I can use UIAutomator to do the scroll
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        String.format("new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"%s\")", country)
                )
        );
        // Click Argentina option
        driver.findElement(
                AppiumBy.xpath(
                        String.format("//android.widget.TextView[@text='%s']", country)
                )
        ).click();

        // Click letsShop button
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();


        // Validate you are in the Products screen

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
        /*
        wait.until(ExpectedConditions.attributeContains(
                driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),
                "text",
                "Products"
        ));
        */

        // Scroll down to product1
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        String.format("new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"%s\")", product1)
                )
        );
        // Add product1 to cart
        String xpath = String.format(
                "//android.widget.TextView[@text='%s']/..//*[@resource-id='com.androidsample.generalstore:id/productAddCart']",
                product1
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

        // Scroll down to product2
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        String.format("new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"%s\")", product2)
                )
        );
        // Add product1 to cart
        xpath = String.format(
                "//android.widget.TextView[@text='%s']/..//*[@resource-id='com.androidsample.generalstore:id/productAddCart']",
                product2
        );

        driver.findElement(
                AppiumBy.xpath(xpath)
        ).click();

        Thread.sleep(1000);

        // Validate the text of the button changed to ADDED TO CART

        buttonText = driver.findElement(
                AppiumBy.xpath(xpath)
        ).getText();

        Assert.assertEquals(buttonText, "ADDED TO CART");

        // Go to the cart and validate the product is there

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        // Validate I'm in the cart screen
        Thread.sleep(3000);
        wait.until(ExpectedConditions.attributeContains(
                driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),
                "text",
                "Cart"
        ));


        String xpathForCartItems = "//android.widget.TextView[@text='%s']";
        // We only have one product, so we can validate it directly
        wait.until(ExpectedConditions.visibilityOf(
            driver.findElement(AppiumBy.xpath(String.format(xpathForCartItems, product1)))
        ));

        // Get the prices and add them to calculate the total
        List<WebElement> sPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));

        // Calculate total
        List<Number> prices =  sPrices.stream().map(sPrice -> {
            try {
                return NumberFormat.getCurrencyInstance(Locale.US).parse(sPrice.getText());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        System.out.println("###################################################");
        System.out.println(prices);
        System.out.println("###################################################");

        Number total = prices.stream().reduce(0, (a, b) -> a.doubleValue() + b.doubleValue());

        System.out.println("###################################################");
        System.out.println(total);
        System.out.println("###################################################");


        // Validate the total
        Number totalInScreen = NumberFormat.getCurrencyInstance(Locale.US).parse(
                driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().replaceAll(" ", "")
        );
        System.out.println("###################################################");
        System.out.println(totalInScreen);
        System.out.println("###################################################");
        Assert.assertEquals(total, totalInScreen);
    }
}
