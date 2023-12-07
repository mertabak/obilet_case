import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Hook {
    protected static Logger logger = LoggerFactory.getLogger(Hook.class);

    protected static AppiumDriver<MobileElement> appiumDriver;

@BeforeScenario
    public static void setUp() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "deviceName");
            capabilities.setCapability("appPackage", "com.obilet.androidside");
            capabilities.setCapability("appActivity", "com.obilet.androidside.presentation.screen.splash.activity.SplashScreen");

            URL appiumServerURL = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new AndroidDriver<>(appiumServerURL, capabilities);
            appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterScenario
    public static void tearDown() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }
}
