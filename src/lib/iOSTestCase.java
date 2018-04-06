package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class iOSTestCase extends TestCase
{
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities DesiredCapabilities = new DesiredCapabilities();
        DesiredCapabilities.setCapability("app", "/Users/vitalijkotov/JavaMobileAutomation/apks/Wikipedia.app");
        DesiredCapabilities.setCapability("platformName", "iOS");
        DesiredCapabilities.setCapability("platformVersion", "11.2");
        DesiredCapabilities.setCapability("deviceName", "iPhone SE");

        driver = new IOSDriver(new URL(AppiumURL), DesiredCapabilities);
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
}
