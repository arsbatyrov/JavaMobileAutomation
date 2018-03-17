package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CoreTestCase extends TestCase
{
    protected AppiumDriver driver;

    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    protected static Map<String, String> capabilities;
    static {
        capabilities = new HashMap<String, String>();
        capabilities.put("platformName", "Android");
        capabilities.put("deviceName", "AndroidTestDevice");
        capabilities.put("platformVersion", "8.0");
        capabilities.put("automationName", "Appium");
        capabilities.put("appPackage", "org.wikipedia");
        capabilities.put("appActivity", ".main.MainActivity");
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities DesiredCapabilities = new DesiredCapabilities();
        for (Map.Entry capability_entry: capabilities.entrySet()) {
            String name_of_capability = capability_entry.getKey().toString();
            String value_of_capability = capability_entry.getValue().toString();
            DesiredCapabilities.setCapability(name_of_capability, value_of_capability);
        }

        driver = new AndroidDriver(new URL(AppiumURL), DesiredCapabilities);
    }

    protected void tearDown() throws Exception
    {
        driver.quit();

        super.tearDown();
    }
}
