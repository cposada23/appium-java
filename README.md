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

    <!-- https://mvnrepository.com/artifact/io.appium/java-client -->
    <dependency>
        <groupId>io.appium</groupId>
        <artifactId>java-client</artifactId>
        <version>8.3.0</version>
    </dependency>

Paste it in the dependencies section of your `pom.xml` file

> You'll need a testing framework in order to execute the tests, a framework like jUnit or TestNG. In this case I'm using TestNG, so copy the TestNG Dependency (https://mvnrepository.com/artifact/org.testng/testng)  and paste it in the `pom.xml` file. Note: Replace the jUnit one if you have it there.

> Note: If you're using Java version 8, do not use the latest TestNG version, you'll have compatibility issues. For this java version use ***6.x version***.

    <!-- https://mvnrepository.com/artifact/org.testng/testng -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.7.1</version>
        <scope>test</scope>
    </dependency>

My `pom.xml` looks like this at this point:

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

## TestNG Hooks

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

    @Test  
    public void exampleTest() throws MalformedURLException {  
	      UiAutomator2Options capabilities = new UiAutomator2Options();  
	      capabilities.setDeviceName(deviceName);  
	      capabilities.setApp(appPath);  
	      URL appiumSeverURL = new URL("http:127.0.0.1:4723");  
	      AndroidDriver driver = new AndroidDriver(appiumSeverURL, capabilities);  
    }

Now run the test as TestNG Test ( In IntelliJ you should see the green play button or in other IDE right click -> Run As -> TestNG Test. The emulator should open the app...

### Start and Stop Appium Server programmatically
For this Appium has provided the Class AppiumServiceBuilder. You'll need to provide the path to the Appium Installation. Since we installed Appium using npm, the path will be where your npm global packages are installed. In mac you can see where are the node modules using this command in a terminal `npm list -g`, Copy this path and concat the following to it: `/node_modules/appium/build/lib/main.js` in my case the full path looks something like this: `/Users/camilo.posadaa/.nvm/versions/node/v18.13.0/lib/node_modules/appium/build/lib/main.js`.  This path depends on many factors ( If you are using nvm or not, and the operating system so depending on that you'll need to investigate where the global node modules are stored  for you)

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
	      URL appiumSeverURL = new URL("http://".concat(appiumIPAddress.concat(":").concat(String.valueOf(appiumPort))));  
	      // Start the driver  
	      AndroidDriver driver = new AndroidDriver(appiumSeverURL, capabilities);  
	      // Clean everything  
	      driver.quit();  
	      service.stop();  
    }

> Note: There seems to be a bug with the java-client version 8.3, if you get an error like this: `java.lang.NullPointerException: Cannot invoke "org.openqa.selenium.os.CommandLine.isRunning()" because "this.process" is null` there is a workaround listed here: `https://github.com/appium/java-client/issues/1872` what you need to do is in your pom.xml file exclude the selenium dependencies from the java-client and add selenium to your project, so my pom.xml file now looks like this:

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



## Using Appium Inspector
1. make sure you have installed Appium Server and Appium Inspector, and that Appium server is running on port 4724

> Suggestion: Create a new virtual device for manual testing with a different android version than the one that you use in the automation, that's because WebDriverIO looks for the Android version and sometimes it will use this new emulator instead of the other one

3. In the Appium inspector make sure that in the ***Remote Path*** you have ***/*** and in the port the port that you set up in the previous step for the Appium server In my case: ***4724***
4. In the Appium Inspector you need to add the capabilities to connect to the emulator:

   {
   "platformName": "Android",
   "appium:platformVersion": "< Replace with the android version that you chose when creating the emulator >",
   "appium:deviceName": "< The device name you chose when creating the emulator >",
   "appium:automationName": "UiAutomator2",
   "appium:app": "< Replace with the full path to the APK that you are using >"
   }

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

***Test class***

    public class appiumExample extends BaseTest{  
	    
	    @Test  
	    public void exampleTest() throws MalformedURLException {  
	        WebElement preferenceElement = driver.findElement(AppiumBy.accessibilityId("Preference"));  
		    preferenceElement.click();  
	    }  
    }

### Finding Elements and interacting with Elements (Android)

You can fin elements by different types of attributes

- Xpath
- Accessibility ID ( This is the preferred option to go with because you can use the same one in both Android and iOS )
- Id
- Resource Id
- index
- Class
- Text
- UiAutomator

To find an element in java, you use the `AppiumBy` class instead of the `By` class that you use for selenium ex: `driver.findElement(AppiumBy.accessibilityId("Preference"));`


