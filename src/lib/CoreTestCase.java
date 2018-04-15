package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase
{
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities DesiredCapabilities = this.getCapabilitiesByPlatformEnv();
        driver = new AndroidDriver(new URL(AppiumURL), DesiredCapabilities);
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception
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
        driver.runAppInBackground(seconds);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities DesiredCapabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            DesiredCapabilities.setCapability("platformName", "Android");
            DesiredCapabilities.setCapability("deviceName", "AndroidTestDevice");
            DesiredCapabilities.setCapability("platformVersion", "8.1");
            DesiredCapabilities.setCapability("automationName", "Appium");
            DesiredCapabilities.setCapability("appPackage", "org.wikipedia");
            DesiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        } else if (platform.equals(PLATFORM_IOS)) {
            DesiredCapabilities.setCapability("app", "/Users/vitalijkotov/JavaMobileAutomation/apks/Wikipedia.app");
            DesiredCapabilities.setCapability("platformName", "iOS");
            DesiredCapabilities.setCapability("platformVersion", "11.3");
            DesiredCapabilities.setCapability("deviceName", "iPhone SE");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value: " + platform);
        }

        return DesiredCapabilities;
    }
}
