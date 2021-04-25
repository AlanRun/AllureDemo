package framework.driver;

import framework.helper.OSUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;

/**
 * Driver factory, make sure webDriver Single Instance
 */
public class DriverFactory {
    private static ThreadLocal<WebDriver> driverSession = new ThreadLocal<WebDriver>();
    private static ThreadLocal<DriverFactory> factorySession = new ThreadLocal<DriverFactory>();
    private WebDriver driver;

    /**
     * Get web driver.
     *
     * @return webDriver
     */
    public static WebDriver getWebDriver() {
        return getWebDriver(false);
    }

    /**
     * Returns WebDriver instance Creates a new WebDriver Instance if it is null and isCreate is true.
     *
     * @param isCreate create webdriver or not
     * @return webDriver
     */
    public static WebDriver getWebDriver(final Boolean isCreate) {

        if (driverSession.get() == null && isCreate) {
            getDriverFactory().createWebDriver();
        }
        return driverSession.get();
    }

    /**
     * Returns DriverFactory instance, Creates new DriverFactory instance if it is null.
     *
     * @return DriverFactory
     */
    public static DriverFactory getDriverFactory() {
        if (factorySession.get() == null) {
            factorySession.set(new DriverFactory());
        }

        return factorySession.get();
    }

    /**
     * create web driver, and not set default Implicit Waits
     * @return
     * @throws Exception
     */
    public WebDriver createWebDriver() {
        System.out.println(Thread.currentThread() + ":" + new Date() + ":::Start creating web driver instance: Chrome");

        String driverPath = "./src/test/resources/";
        if (OSUtility.isWindows()) {
            driverPath = driverPath + "chromedriver.exe";
        } else if (OSUtility.isMac()) {
            driverPath = driverPath + "mac/chromedriver.exe";
        } else {
            driverPath = driverPath = "chromedriver";
        }

        System.setProperty("webdriver.chrome.driver", driverPath);

//        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driverSession.set(driver);

        System.out.println(Thread.currentThread() + ":" + new Date() + ":::Finish creating web driver instance: Chrome");
        return driver;
    }

    /**
     * Shut down webDriver
     */
    public static void tearDownDriver() {
        getWebDriver().quit();
    }

}
