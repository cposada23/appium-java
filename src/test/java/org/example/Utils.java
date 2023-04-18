package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class Utils {

    public enum SwipeDirections {
        LEFT("left"), RIGHT("right");
        SwipeDirections(String v) {
            value = v;
        }
        private String value;
        public String getValue() {
            return value;
        }
    }
    public AndroidDriver driver;
    public void longPress(WebElement element, int duration) {
        ((JavascriptExecutor)driver).executeScript(
                "mobile: longClickGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "duration", duration
                )
        );
    }


    public void scrollToEnd() {

        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        } while (canScrollMore);

    }

    public void swipe(WebElement element, SwipeDirections direction) {
        ((JavascriptExecutor) driver).executeScript(
                "mobile: swipeGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "direction", direction.getValue(),
                        "percent", 0.75
                )
        );

    }
}
