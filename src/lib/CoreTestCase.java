package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase
{
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities DesiredCapabilities = new DesiredCapabilities();
        DesiredCapabilities.setCapability("platformName", "Android");
        DesiredCapabilities.setCapability("deviceName", "AndroidTestDevice");
        DesiredCapabilities.setCapability("platformVersion", "8.0");
        DesiredCapabilities.setCapability("automationName", "Appium");
        DesiredCapabilities.setCapability("appPackage", "org.wikipedia");
        DesiredCapabilities.setCapability("appActivity", ".main.MainActivity");

        driver = new AndroidDriver(new URL(AppiumURL), DesiredCapabilities);
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();

        super.tearDown();
    }
}
