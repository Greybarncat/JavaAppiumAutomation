package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase
{
    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp () throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = this.getDriverByPlatformEnv(new URL(AppiumURL), capabilities);
        
    }

    @Override
    protected void tearDown () throws Exception
    {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","AndroidTestDevice");
            capabilities.setCapability("platformVersion","8.0");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("app","Users/akraynov/Documents/GitHub/JavaAppiumAutomation/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName","iOs");
            capabilities.setCapability("deviceName","iPhone 8");
            capabilities.setCapability("platformVersion","13.7");
            capabilities.setCapability("app","Users/akraynov/Documents/GitHub/JavaAppiumAutomation/apks/Wikipedia.app");
        } else {
            throw new Exception ("Cannot get run platform from env variable. Platform value " + platform);
        }

        return capabilities;
    }

    private AppiumDriver getDriverByPlatformEnv(URL url, DesiredCapabilities capabilities) throws Exception
    {
        String platform = System.getenv("PLATFORM");

        if (platform.equals(PLATFORM_ANDROID)) {
            return new AndroidDriver(url, capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            return new IOSDriver(url, capabilities);
        } else {
            throw new Exception ("Cannot get platform driver from env variable. Platform value " + platform);
        }
    }
}
