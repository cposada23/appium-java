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

## Android


#### UIAutomator
UIAutomator is a UI Testing framework that allows you to automate android apps. See: https://webdriver.io/docs/selectors/#android-uiautomator

> We use the UIAutomator2 driver, that is a wrapper that Appium provides for the UIAutomator

To install it run this command:
> `appium driver install uiautomator2`

### Creating Android driver
To create an android driver to be able to communicate and send commands to the app, you have to instantiate and object of the class `AndroidDriver` . You pass in the constructor the Appium server URL and the capabilities:

> Appium Server URL: In this case, the Appium server ( That is the one that communicates with the device and send the commands ) will be running on your localHost in the port that you specified when running the Appium Server like this: `appium -p 4724 --allow-cors`. So the URL will be something like this: `URL appiumSeverURL = new URL("http:127.0.0.1:4723");`

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

> To run the example code, you'll need the maven project created and the dependencies installed. Create a new class in the example package, create a new method and annotate it to run with test ng with the @Test annotation `@Test  
public void exampleTest()`. In the resources' folder, paste the example apps, you'll find them in the `resources` package of this repository `https://github.com/cposada23/appium-java.git`.  








