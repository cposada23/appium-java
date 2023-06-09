# APPIUM ( Java )

## What is Appium
- Open source automation framework for native, hybrid and mobile web apps in Android, iOS and Windows apps
- Wraps the vendor provider framework into a WebDriver api
- iOS: XCUITest
- Android: UIAutomator
- Windows: WinAppDriver

## Installing All the things

####  NodeJS:
>Recomended using a tool like nvm. If you are using mac follow the steps here: https://github.com/nvm-sh/nvm

> Verify that you have the following in you bash profile or zshrc file. To know what type of profile you are using, in a terminal run ***echo $0***
> if your terminal echoed zsh the file that you need to look is  **_~/.zshrc_**, if you don't have it, create it and paste what you see down below
> If your terminal is bash then the file you should look is -   **_~/.bash_profile_**

    export NVM_DIR="$HOME/.nvm"
	[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm
	[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"  # This loads nvm bash_completion

> Use the LTS version of node

Useful commands for nvm

    // Install the lts version
    nvm install node
    // List all node version taht you have
    nvm list
    // Use a version of node
    nvm use <node version>

#### Install Java JDK
> You can use this page to download the latests sdks: https://adoptium.net/
> Follow this page for more details on how to setup java in mac: https://mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/

After you installed the sdk, you need to set the JAVA_HOME variable in your profile ( if mac in your .zshrc or .bash_profile (read nvm installation ) or windows in your env variables )

> On mac to check where is java installed you can run the following command **_ /usr/libexec/java_home _**. You'll need this to set JAVA_HOME variable

Mac users: on your .zshrc file add the following:

    export JAVA_HOME=$(/usr/libexec/java_home)

> Now, check the java installation by running ***java --version*** on your terminal

#### Install Android Studio
> Download it from here: https://developer.android.com/studio

> Setup ANDROID_HOME

Setting up ANDROID_HOME depend again on the Operating system and type of terminal you are using, On windows take not of the installation path of android studio and set up the environment variables)
On mac add the following to your terminal profile (.zshrc or .bash_profile )

    export ANDROID_HOME=/Users/$USER/Library/Android/sdk
    export PATH=$ANDROID_HOME/platform-tools:$PATH
    export PATH=$ANDROID_HOME/tools:$PATH
    export PATH=$ANDROID_HOME/tools/bin:$PATH
    export PATH=$ANDROID_HOME/emulator:$PATH
    export ANDROID_SDK=/Users/$USER/Library/Android/sdk
    export ANDROID_SDK_ROOT=/Users/$USER/Library/Android/sdk/
    export PATH=$ANDROID_SDK/emulator:$ANDROID_SDK/tools:$PATH

Then in a terminal ( make sure you source the file ***source ~/.zshrc*** ) run the following command to test that everything got installed correctly: ***adb devices*** - should return list of devices attached

#### Setup android emulator
- Open android studio and open the device manager
- Create new device
- Select the system image ( This will be the android version )
- Give it a name
- Start the simulator ( with the play like button ) to make sure that everything is set up correctly

#### Appium

> To install version 2: ***npm i -g appium@next***
> More info here: https://www.npmjs.com/package/appium
> Check the version with: ***appium -v***

Install Appium drivers
> appium driver install xcuitest
> appium driver install uiautomator2

check the drivers installation with ***appium driver list***

#### Appium Server
> Run Appium server in the terminal like this `appium -p 4724 --allow-cors`


#### Appium Inspector
The inspector is a tool to get the selector of the elements to be able to interact with them, something similar on using the Chrome or Firefox inspection tool to get the web Elements identifiers
> Download it from here: https://github.com/appium/appium-inspector/releases

If you get an error opening Appium Inspector, give it the permissions like this:
![Screenshot 2023-04-19 at 10 46 10 AM](https://user-images.githubusercontent.com/7946622/233130068-fb920634-16e2-4fdf-9161-62011f69d5de.png)


>1.  **Remote Port:** Update port to  `4724`  and run Appium server on the same port as well by doing  `appium -p 4724`
>2.  **Remote Path:** Set the path to  `/wd/hub/`  instead of  `/` if using the Appium server from the Appium desktop or `/`  instead of  `/wd/hub/` if running Appium server from the terminal.


#### Appium Doctor
>This tool helps us verify if everything is setup correctly, install it by running: ***`npm i -g appium-doctor`***

Run ***`appium-doctor --android`*** to check all the installation


#### Maven
> https://maven.apache.org/what-is-maven.html

Go to the downloads page and Download the `Binary tar.gz archive`, then open a terminal, cd to the download folder and extract it like this: `tar -xvf apache-maven-3.6.3-bin.tar.gz`

Move the extracted folder where you want it,  and add it to the env variables in your terminal profile like this (replace it with the path to the folder)

    export M2_HOME=/Users/$USER/Library/apache-maven-3.9.1
    export PATH="${M2_HOME}/bin:${PATH}"
To test if maven was successfully installed, close the terminal and open a new one and test it with `mvn -version`

#### Set up a maven project

> The code for the initial par of this document you can find it here: https://github.com/cposada23/appium-java. If you are only interested in the final framework look here: <TODO>

For this course, I'm following with IntelliJ IDEA ( Community Edition ) but you can use Eclipse or the IDE that you prefer. Create a new Maven project and these are the dependencies you'll need:

> Get the Java Client for Appium here: https://mvnrepository.com/artifact/io.appium/java-client select the latest version

Copy the maven dependency, something like this:
```xml
<!-- https://mvnrepository.com/artifact/io.appium/java-client -->
<dependency>
    <groupId>io.appium</groupId>
    <artifactId>java-client</artifactId>
    <version>8.3.0</version>
</dependency>
```
Paste it in the dependencies section of your `pom.xml` file

> You'll need a testing framework in order to execute the tests, a framework like jUnit or TestNG. In this case I'm using TestNG, so copy the TestNG Dependency (https://mvnrepository.com/artifact/org.testng/testng)  and paste it in the `pom.xml` file. Note: Replace the jUnit one if you have it there.

> Note: If you're using Java version 8, do not use the latest TestNG version, you'll have compatibility issues. For this java version use ***6.x version***.
```xml
<!-- https://mvnrepository.com/artifact/org.testng/testng -->
<dependency>
	<groupId>org.testng</groupId>
	<artifactId>testng</artifactId>
	<version>7.7.1</version>
	<scope>test</scope>
</dependency>
```
My `pom.xml` looks like this at this point:
```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">  
     <modelVersion>4.0.0</modelVersion>  
      
	     <groupId>org.example</groupId>  
	     <artifactId>Appium-java</artifactId>  
	     <version>1.0-SNAPSHOT</version>  
	     <packaging>jar</packaging>  
	      
	     <name>Appium-java</name>  
	     <url>http://maven.apache.org</url>  
	      
	     <properties> <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>  
		     <maven.compiler.source>15</maven.compiler.source>  
		     <maven.compiler.target>15</maven.compiler.target>  
	     </properties>  
	     <dependencies>  
		     <!-- https://mvnrepository.com/artifact/io.appium/java-client -->  
		      <dependency>  
			     <groupId>io.appium</groupId>  
			     <artifactId>java-client</artifactId>  
			     <version>8.3.0</version>  
		     </dependency>  
		     <!-- https://mvnrepository.com/artifact/org.testng/testng -->  
		     <dependency>  
			     <groupId>org.testng</groupId>  
			     <artifactId>testng</artifactId>  
			     <version>7.7.1</version>  
		     </dependency>  
	     </dependencies>
     </project> 
```
## TestNG
### Hooks
> Read more about them here: https://www.browserstack.com/guide/testng-annotations-in-selenium

-   **@BeforeMethod**: This will be executed before every @test annotated method.
-   **@AfterMethod:**  This will be executed after every @test annotated method.
-   **@BeforeClass:**  This will be executed before first @Test method execution. It will be executed one only time throughout the test case.
-   **@AfterClass:**  This will be executed after all test methods in the current class have been run
-   **@BeforeTest:**  This will be executed before the first @Test annotated method. It can be executed multiple times before the test case.
-   **@AfterTest**: A method with this annotation will be executed when all @Test annotated methods complete the execution of those classes inside the <test> tag in the TestNG.xml file.
-   **@BeforeSuite**: It will run only once, before all tests in the suite are executed.
-   **@AfterSuite:**  A method with this annotation will run once after the execution of all tests in the suite is complete.
-   **@BeforeGroups**: This method will run before the first test run of that specific group.
-   **@AfterGroups**: This method will run after all test methods of that group complete their execution.

### Assertions
TestNG Comes with the `Assert` and `SoftAssert` classes to do multiple assertions:

-   assertEquals
-   assertNotEquals
-   assertTrue
-   assertFalse
-   assertNull
-   assertNotNull

### Hard Assertions
Hard assertions means that the assertion fails as soon as the condition is not met. For this types of assertions you use the class `Assert` like this `Assert.assertEquals(alertText, "WiFi settings");`

### Soft Assertions
This type of assertions "saves" when some assertion fails but it does not fail the test immediately. Whit this type of assertion you can validate multiple conditions and the test will not fail until you call the assertAll method, this method will add all the failures that it found to the TestNG Report.

To use this type of assertions you need to instantiate an object for the SoftAssert class `SoftAssert softAssert = new  SoftAssert();` and then use it for the assertions:


    softAssert.assertNull("assertion");
    softAssert.assertEquals(text, expectedText);

At the end of the test you have to call assertAll for the errors to be reported like this:

    softAssert.assertAll();



## Appium (Content that applies both for Android and iOS)

### Waits
#### Implicit wait

Implicit Wait directs the  driver to wait for a certain measure of time before throwing an exception. Once this time is set, the driver will wait for the element before the exception occurs.

Once the command is in place, Implicit Wait stays in place for the entire duration for which the browser is open. It’s default setting is 0, and the specific wait time needs to be set by the following protocol.
```java
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
```
#### Explicit Waits
Explicit wait tells the driver to wait until a certain condition occurs before throwing an exception. The types of explicit waits are the followings:

-   alertIsPresent()
-   elementSelectionStateToBe()
-   elementToBeClickable()
-   elementToBeSelected()
-   frameToBeAvaliableAndSwitchToIt()
-   invisibilityOfTheElementLocated()
-   invisibilityOfElementWithText()
-   presenceOfAllElementsLocatedBy()
-   presenceOfElementLocated()
-   textToBePresentInElement()
-   textToBePresentInElementLocated()
-   textToBePresentInElementValue()
-   titleIs()
-   titleContains()
-   visibilityOf()
-   visibilityOfAllElements()
-   visibilityOfAllElementsLocatedBy()
-   visibilityOfElementLocated()

### Finding Elements
To find an element in java, you use the `AppiumBy` class instead of the `By` class that you use for selenium ex: `driver.findElement(AppiumBy.accessibilityId("Preference"));`

Some Examples:
- **XPath:** `driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']"))  
  .click();`
- **Accessibility ID:**  `driver.findElement(AppiumBy.accessibilityId("Preference"));`
- **id:** `driver.findElement(AppiumBy.id("android:id/checkbox")).click();`
- **Class Name :** `driver.findElements(AppiumBy.className("android.widget.Button"))`

### Common Actions
- **Find Element** `driver.findElement(<replace with the proper BY locator>)`
- **Find Elements** `driver.findElements(<replace with the proper BY locator>)`
- **Click** `driver.findElement(<replace with the proper BY locator>).click()`
- **Type in textBox** `driver.findElement(<replace with proper BY Locator>).sendKeys(<Replace with what you want to type>)`
- **Get Text of an element :** `driver.findElement(<replace with the proper BY locator>).getText()`
- **Get element is displayed  :** `driver.findElement(<replace with the proper BY locator>).isDisplayed()`

## Android


#### UIAutomator
UIAutomator is a UI Testing framework that allows you to automate android apps. See: https://webdriver.io/docs/selectors/#android-uiautomator

> We use the UIAutomator2 driver, that is a wrapper that Appium provides for the UIAutomator

To install it run this command:
> `appium driver install uiautomator2`

### Creating Android driver
To create an android driver to be able to communicate and send commands to the app, you have to instantiate and object of the class `AndroidDriver` . You pass in the constructor the Appium server URL and the capabilities:

> Appium Server URL: In this case, the Appium server ( That is the one that communicates with the device and send the commands ) will be running on your localHost in the port that you specified when running the Appium Server like this: `appium -p 4724 --allow-cors`. So the URL will be something like this: `URL appiumSeverURL = new URL("http://127.0.0.1:4723");`

> You specify the capabilities using the UIAutomator2Options Class, there you'll pass the device name, android version and all the different capabilities to connect and interact with the right device: `UiAutomator2Options capabilities = new UiAutomator2Options();`

#### Example code

> My folder structure at this time looks like this:
- src
    - main
        - java
            - .....
    - test
        - java
            - org
                - example // Here will be the test code for this section
                - resources // I will put here the applications that I will use in this example
- pom.xml

> To run the example code, you'll need the maven project created and the dependencies installed. Create a new class in the example package, create a new method and annotate it to run with TestNG with the @Test annotation `@Test  
public void exampleTest()`. In the resources folder, paste the example apps, you'll find them in the `resources` package of this repository `https://github.com/cposada23/appium-java.git`.

> Don't forget to setup and start the emulator, and have the Appium server running: `appium -p 4723 --allow-cors`
```java
@Test  
public void exampleTest() throws MalformedURLException {  
	UiAutomator2Options capabilities = new UiAutomator2Options();  
	capabilities.setDeviceName(deviceName);  
	capabilities.setApp(appPath);  
	URL appiumSeverURL = new URL("http:127.0.0.1:4723");  
	AndroidDriver driver = new AndroidDriver(appiumSeverURL, capabilities);  
}
```
Now run the test as TestNG Test ( In IntelliJ you should see the green play button or in other IDE right click -> Run As -> TestNG Test. The emulator should open the app...

### Start and Stop Appium Server programmatically
For this Appium has provided the Class AppiumServiceBuilder. You'll need to provide the path to the Appium Installation. Since we installed Appium using npm, the path will be where your npm global packages are installed. In mac you can see where are the node modules using this command in a terminal `npm list -g`, Copy this path and concat the following to it: `/node_modules/appium/build/lib/main.js` in my case the full path looks something like this: `/Users/camilo.posadaa/.nvm/versions/node/v18.13.0/lib/node_modules/appium/build/lib/main.js`.  This path depends on many factors ( If you are using nvm or not, and the operating system so depending on that you'll need to investigate where the global node modules are stored  for you)
```java
@Test  
public void exampleTest() throws MalformedURLException {  
	// Start Appium Server programmatically  
	AppiumDriverLocalService service = new AppiumServiceBuilder()  
		.withAppiumJS(new File(nodeModulesAppiumPath))  
		.withIPAddress(appiumIPAddress)  
		.usingPort(appiumPort)  
		.build();  

	service.start();  
	// Set the capabilities  
	UiAutomator2Options capabilities = new UiAutomator2Options();  
	capabilities.setDeviceName(deviceName);  
	capabilities.setApp(appPath);  
	URL appiumSeverURL = new URL(
		"http://".concat(appiumIPAddress.concat(":").concat(String.valueOf(appiumPort)))
	);  
	// Start the driver  
	AndroidDriver driver = new AndroidDriver(appiumSeverURL, capabilities);  
	// Clean everything  
	driver.quit();  
	service.stop();  
}
```
> Note: There seems to be a bug with the java-client version 8.3, if you get an error like this: `java.lang.NullPointerException: Cannot invoke "org.openqa.selenium.os.CommandLine.isRunning()" because "this.process" is null` there is a workaround listed here: `https://github.com/appium/java-client/issues/1872` what you need to do is in your pom.xml file exclude the selenium dependencies from the java-client and add selenium to your project, so my pom.xml file now looks like this:
```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">  
	     <modelVersion>4.0.0</modelVersion>  
	      
	     <groupId>org.example</groupId>  
	     <artifactId>Appium-java</artifactId>  
	     <version>1.0-SNAPSHOT</version>  
	     <packaging>jar</packaging>  
	      
	     <name>Appium-java</name>  
	     <url>http://maven.apache.org</url>  
	      
	     <properties> <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>  
		     <maven.compiler.source>15</maven.compiler.source>  
		     <maven.compiler.target>15</maven.compiler.target>  
	     </properties>  
	     <dependencies>  <!-- https://mvnrepository.com/artifact/io.appium/java-client -->  
		     <dependency>  
			     <groupId>io.appium</groupId>  
			     <artifactId>java-client</artifactId>  
			     <version>8.3.0</version>  
			     <exclusions>  
				     <exclusion> 
					     <groupId>org.seleniumhq.selenium</groupId>  
					     <artifactId>selenium-remote-driver</artifactId>  
				     </exclusion>  
				     <exclusion> 
					     <groupId>org.seleniumhq.selenium</groupId>  
					     <artifactId>selenium-support</artifactId>  
				     </exclusion>  
				     <exclusion> 
					     <groupId>org.seleniumhq.selenium</groupId>  
					     <artifactId>selenium-api</artifactId>  
				     </exclusion> 
			     </exclusions> 
			 </dependency>  
			 <!-- https://mvnrepository.com/artifact/org.testng/testng -->  
		     <dependency>  
			     <groupId>org.testng</groupId>  
			     <artifactId>testng</artifactId>  
			     <version>7.7.1</version>  
		     </dependency>  
		     <dependency> 
			     <groupId>org.seleniumhq.selenium</groupId>  
			     <artifactId>selenium-java</artifactId>  
			     <version>4.8.1</version>  
		     </dependency>  
	     </dependencies>
     </project>
```


## Using Appium Inspector
1. make sure you have installed Appium Server and Appium Inspector, and that Appium server is running on port 4724

> Suggestion: Create a new virtual device for manual testing with a different android version than the one that you use in the automation, that's because WebDriverIO looks for the Android version and sometimes it will use this new emulator instead of the other one

3. In the Appium inspector make sure that in the ***Remote Path*** you have ***/*** and in the port the port that you set up in the previous step for the Appium server In my case: ***4724***
4. In the Appium Inspector you need to add the capabilities to connect to the emulator:
```json
    {
	     "platformName": "Android",
	     "appium:platformVersion": "< Replace with the android version that you chose when creating the emulator >",
	     "appium:deviceName": "< The device name you chose when creating the emulator >",
	     "appium:automationName": "UiAutomator2",
	     "appium:app": "<Replace with the full path to the APK that you are using >"
    }
```
5. Click Start Session. you should see in the Appium inspector the application that is running in the emulator. There you can inspect the elements to get the proper id to interact with them in the automation.


## Time to refactor (Introducing some TestNG Hooks and a BaseTest Class to create the driver there

We have some code that can be shared between the different test cases that we are going to write, this code is the one that starts the Appium server, sets the capabilities and creates the driver. We can move this code to a base utility class so we don't need to write this code in each and every tests

- Create a new class `BaseTest`
- Move the code that starts the server and initialize the driver to a new method inside this new class, something  like `public void configureDriver`
- We will need that this configureDriver method runs before the test starts, there is a hook from TestNG that we can user for that, so we can annotate this method with `@BeforeClass` annotation
- Create a method close the server and the driver `public void tearDown`, and move the code there. We need this method to run after all the test have ran, so we can annotate this method with the `@AfterClass`
- Our base test class, needs to extend `BaseTest` so we have all this methods and the driver available for use.

#### Example code until this lesson:

***BaseTest class***
```java
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
```

***Test class***
```java
public class appiumExample extends BaseTest{  
	@Test  
	public void exampleTest() throws MalformedURLException {  
		WebElement preferenceElement = driver.findElement(
			AppiumBy.accessibilityId("Preference")
		);  
		preferenceElement.click();  
	}  
}
```
### Finding Elements and interacting with Elements (Android)

You can fin elements by different types of attributes

- Xpath
- Accessibility ID ( This is the preferred option to go with because you can use the same one in both Android and iOS )
- Id
- Resource Id
- index
- Class
- Text
- UiAutomator: see https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/uiautomator-uiselector.md

> Remember: You can get the values of this selectors using the Appium Inspector, selecting the element that you want to interact with, and copy the selector that you want. ( **Always try to go with Accessibility ID**


### Example test case using the ApiDemos-debug.apk

1. Go to preference
2. Click on the 3 option: Preference Dependencies
3. Select the WifiCheck box
4. Click Wifi Settings
5. Type something  in the textbox

> Solution

```java
@Test  
public void exampleTest() throws MalformedURLException {  
	WebElement preferenceElement = driver.findElement(
		AppiumBy.accessibilityId("Preference")
	);  
	preferenceElement.click();  
	driver.findElement(
		AppiumBy.xpath(
			"//android.widget.TextView[@content-desc='3. Preference dependencies']"
		)
	).click();  
	driver.findElement(AppiumBy.id("android:id/checkbox")).click();  
	driver.findElement(AppiumBy.xpath(
		"(//android.widget.RelativeLayout)[2]")
	).click();  
	// Assert  
	String alertText = driver.findElement(
		AppiumBy.id("android:id/alertTitle")
	).getText();  
	Assert.assertEquals(alertText, "WiFi settings");  
	driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Test");  
	driver.findElements(
		AppiumBy.className("android.widget.Button")
	).get(1).click();  
}
```

### Mobile Gestures (Android)

> see: https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md

If you know Selenium, all that you saw before this point should look familiar (clicking on element, send keys .... etc ). But there are some gestures that are exclusive to mobile automation. Here are some of them:
- Drag And drop
- Long press
- Mobile Scrolling
- Pinch
- swapping

You make use of these gestures using the JavascriptExecutor class
#### Long press
For this you'll need the element id where you are going to press and the duration
```java
@Test  
public void longPressExample() throws MalformedURLException {  
	driver.findElement(AppiumBy.accessibilityId("Views")).click();  
	driver.findElement(AppiumBy.xpath(
		"//android.widget.TextView[@text='Expandable Lists']"
	)).click();  
	driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();  

	WebElement element = driver.findElement(
		AppiumBy.xpath(
			"//android.widget.TextView[@text='People Names']"
		)
	);  

	((JavascriptExecutor)driver).executeScript(  
		"mobile: longClickGesture",  
		ImmutableMap.of(  
			"elementId", ((RemoteWebElement) element).getId(),  
			"duration", 2000  
		)  
	);  
}
```
1. You find the element to long Press
2. call driver.executeScript
    - The script that we are executing is `longClickGesture`
3. Then you have to pass a map (key value pairs ) containing the element id and the duration
    - To get the element id, first you need to cast the WebElement to RemoteWebElement and then you can get the id `getId()`
4. Don't forget to cast the WebDriver to JavascriptExecutor

## Time to refactor (Utility class for common methods)
Since we already have the long press functionality coded, why don't we make a reusable method so we can avoid code duplication if we wan to use long press in another test file?

- Create a Utils class, and move the driver declaration there only the part that says `public AndroidDriver driver;` and create a new method `longPress` that receives as parameters the WebElement and the duration.
- In the BaseTest class extend the Utils Class

### My code now looks like this:

#### Utils `src/test/java/org/example/Utils.java`
```java
public class Utils {  
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
}
```
#### BaseTest `org/example/BaseTest.java`

```java
public class BaseTest extends Utils {  
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
```

#### Tests File `org/example/appiumExample.java`
```java
public class appiumExample extends BaseTest{  
	@Test  
	public void exampleTest() throws MalformedURLException {  
		WebElement preferenceElement = driver.findElement(AppiumBy.accessibilityId("Preference"));  
		preferenceElement.click();  
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();  
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
}
```
## Continue with gestures

#### Scrolling
For this is a good idea to use the UIAutomator and use the google engine script that they provide:
> See: https://developer.android.com/reference/androidx/test/uiautomator/UiScrollable
```java
driver.findElement(
	AppiumBy.androidUIAutomator(  
	   "new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"WebView\")"
   )  
);
```

Or use the example scroll provided here: https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md
```java
// Java
boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver)
	.executeScript(
		"mobile: flingGesture",
		ImmutableMap.of(
		    "elementId", ((RemoteWebElement) element).getId(),
		    "direction", "down",
		    "speed", 500
		)
	);
```
For example, you can make a utility function to scroll to the end of the screen like this:
```java
public void scrollToEnd() {  
      
	boolean canScrollMore;  
	do {  
       canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript(
         "mobile: scrollGesture", 
         ImmutableMap.of(  
            "left", 100, "top", 100, "width", 200, "height", 200,  
		    "direction", "down",  
		    "percent", 3.0  
	     )
	   );  
    } while (canScrollMore);  
}
```
#### Swipe Functionality

We use the `mobile: swipeGesture` script
```java
// Java
@Test
public void swipeDemoTest() throws MalformedURLException {
   driver.findElement(AppiumBy.accessibilityId("Views")).click();
   driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
   driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
   Assert.assertEquals(driver.findElement(
	   AppiumBy.xpath("(//android.widget.ImageView)[1]")
	).getAttribute("focusable"), "true");

   // Swipe
   WebElement elementToSwipe = driver.findElement(
	   AppiumBy.xpath("(//android.widget.ImageView)[1]")
   );
   ((JavascriptExecutor) driver).executeScript(
   "mobile: swipeGesture",
       ImmutableMap.of(
           "elementId", ((RemoteWebElement) elementToSwipe).getId(),
           "direction", "left",
           "percent", 0.75
       )
   );
   Assert.assertEquals(driver.findElement(
	   AppiumBy.xpath("(//android.widget.ImageView)[1]")
   ).getAttribute("focusable"), "false");
}

```

#### Drag and Drop

> We use the `mobile: dragGesture` script

For this you'll need the id of the element that you want to drag and drop and the coordinates of where you want to drag and drop it. To obtain the coordinates we use the Appium inspector, for this go to the view where you are going to perform the action and you have at the top of the app view 3 buttons, one says Tab By Coordinates, click it and then you can put the cursor in the area that you want to drop and it will say the coordinates.

![Screenshot 2023-04-19 at 9 28 57 AM](https://user-images.githubusercontent.com/7946622/233107658-a83ee573-a4b8-40be-8470-6794a0d24302.png)


```java
    @Test
    public void dragAndDropDemo() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement source = driver.findElement(
	        AppiumBy.id("io.appium.android.apis:id/drag_dot_1")
	    );

        ((JavascriptExecutor) driver).executeScript(
                "mobile: dragGesture",
                ImmutableMap.of(
                    "elementId", ((RemoteWebElement) source).getId(),
                    "endX", 649,
                    "endY", 677
                )
        );

        String result = driver.findElement(
	        AppiumBy.id("io.appium.android.apis:id/drag_result_text")
	    ).getText();
        Assert.assertEquals(result, "Dropped!");

    }
```


#### Device Orientation
For this you use the class `DeviceRotation` and use it to rotate the device

```java
DeviceRotation landScape = new DeviceRotation(0,0,90);  
driver.rotate(landScape);
``` 

#### Copy from clipboard and paste from clipboard

Use setClipboardText and getClipboardText methods

```java
driver.setClipboardText("Test");  
driver.findElement(
	AppiumBy.id("android:id/edit")
).sendKeys(driver.getClipboardText());
```

#### Keyboard events (press enter, back button, home button....)
You use the AndroidKey and KeyEvent classes for this: see https://appium.github.io/java-client/io/appium/java_client/android/nativekey/AndroidKey.html

```java
driver.pressKey(new KeyEvent(AndroidKey.HOME));
```


#### Open App using `app package` and `app activity name`

-   appPackage: technical name of the app, provided by the developers (Top level package under which the app code resides) . Ex: ‘com.google.android.youtube’
-   appActivity: Certain screen or functionality of the application. EX: MainActivity, AlertDialog

_**Why take this in consideration ?**_

1.  Access a Screen directly
2.  Save time in the automation script by not going through multiple pages, this decrease the change of having flaky tests

#### How to go directly to a particular activity:

1.  You have to know the activity name, for this:
    -   In the Appium inspector, go to the page that you want ( In the app that we are woking in this repository, lets say we wan to start our test in the App / Alert Dialogs screen )
    -   Once you are in the correct page, in Appium inspector in the middle upper section, click commands. Then click where it says “_**Select Action Group**_” and select  _**Device**_. In the new select that gets added after you click Device, click it and select  _**Android Activity**_. Once you clicked it, a popup will appear with the current activity name… In our case is "  _**.app.AlertDialogSamples**_  "
    -   I need also the package name combined with the activity name to be able to access that screen. To get the package name, close the popup that was opened in the previous step, and click in the button that says  _**current package**_. In this example the package is: "  _**io.appium.android.apis**_  "

![Screenshot 2023-04-19 at 11 28 07 AM](https://user-images.githubusercontent.com/7946622/233140188-baf7c95a-97f5-43e6-8270-8bf6bfdbb9b6.png)

In java Appium exposes a class called `Activity`

```java
String initialActivity = ".ApiDemos";  
String packageName = "io.appium.android.apis";  
Activity activity = new Activity(packageName, initialActivity);
driver.startActivity(activity);
```

## Refactor time
Now that we know how to start a particular activity, I'm doing a little refactor to the code adding `@BeforeMethod` hook, so the tests always starts in the initial activity. This way we can run all the tests

```java
    @BeforeMethod  // Excecutes before each @Test
    public void setup() throws InterruptedException {
        String initialActivity = ".ApiDemos";
        String packageName = "io.appium.android.apis";
        Activity activity = new Activity(packageName, initialActivity);
        driver.startActivity(activity);
        Thread.sleep(1000);
    }
```

## Proposed exercises
It's time to put the knowledge into practice, use the app stored in: `src/test/java/org/resources/General-Store.apk`

### Proposed test Scenarios
1. Fill the form details and verify Toad error message is displayed when the user enters a wrong input
2. Validate scrolling functionality by scrolling to an specific product
3. Validate the items selected are displayed in the checkout page

### Example test case ( Remember to change the apk path in the Basetest Class )
1. Open the app General-Store
2. Select for Country "Colombia"
3. Enter your Name in the name input
4. Select your gender using the radio buttons
5. Click lets's shop
6. Validate that you are in the Products Screen

```java
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
```

> The rest of the examples are in the repository: `src/test/java/org/example/GeneralStoreAPKExampleTests.java`


#### Handling web views

A webView is basically a view that displays web pages or web content ( when you click a link for example ). It can either be a webObject or open a webBrowser


#### Accessing the Contexts in Appium Inspector
> IMPORTANT: run Appium in the terminal like this: `appium -p 4724 --allow-cors --allow-insecure chromedriver_autodownload`
-  Open the app in the Appium inspector, then in the top menu go to Commands and in the dropdown select Context
- Then for the subGroup select Context again
- Click `Get Current Context`  It will tell you the context that you are in
- The `Get Context List` Will give you the list of contexts that are available
-
![Screenshot 2023-04-20 at 3 40 39 PM](https://user-images.githubusercontent.com/7946622/233483079-99719d62-38fa-43de-bea6-c5e36e537a28.png)

To programatically know the list of handles you use
```java
Set<String> handles =  driver.getContextHandles();
```

To switch to a Handle
```java
driver.context("handleName")
```
After you switch to the webView context, you can continue using normal selenium to automate it.


# iOS
> You'll need a mac
## Things to install
- XCode -> app Store
- Xcode Command Line Tools
    - In the a terminal: `xcode-select --install`
- Carthage: Dependency manager for macOS & iOS
    - Use a tool like brew: `brew install carthage`
- iOS deploy: Install and debug iOS apps from the command line. Designed to work on un-jailbroken devices.  `brew install ios-deploy`

> After Installing all the tools, run Appium doctor to check that everything is good `appium-doctor --ios`. If all checkmarks are green you should be good to go

>Remember to install the driver for iOS ( Both at project level and global ) `appium driver install xcuitest`

> Verify the drivers with: `appium driver list`

## Finding Elements and interacting with Elements (iOS)

You can find the elements using the selectors listed below:

- ***Accessibility ID***: Remember, this is the preferred way because it allows for cross-platform compatibility ( You can use the same selector for iOS and Android )
- ***tag name***, Usually the tag name is not unique, multiple objects can have the same tag name. There are multiple types like:
    - Layout, textView, Button
- ***X-path*** Example: `//*[@name="Alert Views"]` Learn more about Xpath here: http://www.sidar.org/recur/desdi/traduc/es/xml/xpath.html
- ***class chain*** You can say its similar to x-path but is more flexible because it gives you more ways to search for an element on the screen. You have to be explicit that you are going to use class chain using: `*-ios class chain* ` like this: `await  $('-ios class chain:<replace with the class chain>')`. Examples:
    - > learn more about this here: https://github.com/facebookarchive/WebDriverAgent/wiki/Class-Chain-Queries-Construction-Rules
    - `XCUIElementTypeWindow/XCUIElementTypeButton[3]`  - select the third child button of the first child window element
    - `XCUIElementTypeWindow/XCUIElementTypeAny[3]`  - select the third child (of any type) of the first child window
    - ``XCUIElementTypeWindow[`name CONTAINS[cd] "blabla"`]``  - select all windows, where name attribute starts with "blabla" or "BlAbla" [cd means case insensitive]

- ***predicate string***: Similar to class chains but shorter in the way you write it, using predicate strings you can select elements by different attributes like: name, value, label, type, enabled, visible... etc. You have to be explicit that you are going to use predicate string using `-ios predicate string:<replace with predicate string>`. Examples:
    - > learn more about this here: https://github.com/facebookarchive/WebDriverAgent/wiki/Predicate-Queries-Construction-Rules
    - `type == 'XCUIElementTypeButton' AND value BEGINSWITH[c] 'bla' AND visible == 1`


#### Installing a different version of iOS
> Sometimes you'll need an specific version of iOS, in the example app: MVCTodo.app we will need iOS 14.5 installed so we can run the test

To install a new iOS version open Xcode and:
-  go to Widows -> Devices And Simulators -> simulators.
- Then in the list click the plus button to create a new simulator
- Click the select for the OS Version and click Download more simulator runtimes
- On platform tab, click the plus button to search for the new runtime - select iOS
- Search for the desired runtime and install it
> Use this new run time to create a new simulator

For this example, I created a new class IOSBaseTest, and here I put the initialization of the ios driver:

> If you use the example app that I have in the repository, you'll need to have a simulator with iOS 14.5 installed, so read the section `Installing a different version of iOS`

> Remember, for iOS we use `xcuitest` driver, so we need to set the `XCUITestOptions` and create a driver from the class `IOSDriver`

```java
public class IOSBaseTest extends IOSUtils {


    private String deviceName = "iPhone11ios14";
    private String appPath = "/Users/camilo.posadaa/Documents/personal/framworks/java/Appium-java/src/test/java/org/resources/UIKitCatalog.app";
    private String nodeModulesAppiumPath = "/Users/camilo.posadaa/.nvm/versions/node/v18.13.0/lib/node_modules/appium/build/lib/main.js";
    private String appiumIPAddress = "127.0.0.1";
    int appiumPort = 4723;
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
        XCUITestOptions capabilities = new XCUITestOptions();
        capabilities.setDeviceName(deviceName);
        capabilities.setApp(appPath);
        capabilities.setPlatformVersion("14.5");
        // In IOS Appium will install the WebDriver Agent
        // So we will need to wait until it is installed an available
        capabilities.setWdaLaunchTimeout(Duration.ofSeconds(60));

        URL appiumSeverURL = new URL("http://".concat(appiumIPAddress.concat(":").concat(String.valueOf(appiumPort))));
        // Start the driver
        driver = new IOSDriver(appiumSeverURL, capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterClass
    public void tearDown () {
        // Clean
        driver.quit();
        service.close();
    }
}
```


#### Capabilities to use in the Appium Inspector ( Remember to have appium server running)

> Change them according to your settings
```json
{
  "platformName": "ios",
  "appium:platformVersion": "14.5",
  "appium:deviceName": "iPhone11ios14",
  "appium:automationName": "XCUITest",
  "appium:app": "/Users/camilo.posadaa/Documents/personal/framworks/java/Appium-java/src/test/java/org/resources/UIKitCatalog.app"
}
```

Run a basic test and see if the simulator gets opened and the app installed

```java
    @Test
    public void basicTest() {
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
    }
```

#### Use of class chain

> Class chain is similar to XPath but in iOS, locating and element with class Chain works faster than XPath, so is recommended to use class chain.

Use `AppiumBy.iOSClassChain`
> More info about class chain here https://github.com/facebookarchive/WebDriverAgent/wiki/Class-Chain-Queries-Construction-Rules

ex: Find element with text = Text Entry:

```java
driver.findElement(
	AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Text Entry'`]"
)).click();
```

#### Use of predicate String

Use: `AppiumBy.iOSNsPredicateString`

> More info here: https://github.com/facebookarchive/WebDriverAgent/wiki/Predicate-Queries-Construction-Rules


Ex: Find element with value = 'Confirm / Cancel'

```java
@Test  
public void predicateStringExample() {  
    driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();  
	driver.findElement(
		AppiumBy.iOSNsPredicateString(
			"type == 'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel'"
		)
	).click();  
}
```

EX: Find element with the value beginning with 'Confirm'

```java
@Test
public void predicateStringExample() {
    driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
    // Example with value begins with
    driver.findElement(
	    AppiumBy.iOSNsPredicateString(
		  "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'"
		)
	).click();

}
```

EX: Find element with the value ending with 'Cancel'

```java
@Test
public void predicateStringExample() {
    driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
    // Example with value begins with
    driver.findElement(
	    AppiumBy.iOSNsPredicateString(
		  "type == 'XCUIElementTypeStaticText' AND value ENDSWITH[c] 'Confirm'"
		)
	).click();

}
```

### Gestures in iOS

#### Long Press
Use `"mobile: touchAndHold"` script and specify the element like this:

```java
	   @Test
    public void longPressExample() {
        driver.findElement(AppiumBy.accessibilityId("Steppers")).click();
        WebElement element = driver.findElement(
	        AppiumBy.iOSClassChain(
		        "**/XCUIElementTypeButton[`label == 'Increment'`][3]"
		    )
	     );

        ((JavascriptExecutor)driver).executeScript(
                "mobile: touchAndHold",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "duration", 5
                )
        );
    }
```

#### Scrolling

Use `mobile: scroll` script, specify the element you want to scroll to and the direction:

```java
    @Test
    public void scrollExample() {
        WebElement element = driver.findElement(
	        AppiumBy.accessibilityId("Web View")
	    );

        ((JavascriptExecutor)driver).executeScript(
                "mobile: scroll",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "direction", "down"
                )
        );

        element.click();
    }
```

#### Automate Picker components
Use `sendKeys` with the value you want to select

#### Sliding
Use `sendKeys` and pass the percentage that you want  to select with the slider. The value that you send in the sendKeys method, should be a value between 0 and 1.

Ex: slide to 100%

```java
WebElement slider = driver.findElement(AppiumBy.id("id"));
slider.sendKeys("1%");
```

#### Swipe
Use: `mobile: swipe` script, specify the element (Optional) and the direction to do the swipe (left or right)
> If you don't specify the element on where to apply the swipe, Appium will do the swipe in the center of the screen.

```java
WebElement element = driver.findElement(AppiumBy.accessibilityId("accessibility id of element"));  
  
((JavascriptExecutor)driver).executeScript(  
        "mobile: swipe",  
  ImmutableMap.of(  
      "elementId", ((RemoteWebElement) element).getId(),  
	  "direction", "left"  
  )  
);  
  
element.click();
```


# Framework

> The code and instructions for the framework are in this repository: https://github.com/cposada23/appium-java-framework 
