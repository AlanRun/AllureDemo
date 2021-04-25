package framework.webpage;

import framework.core.TestLogging;
import framework.helper.ConfigProperty;
import framework.helper.WaitHelper;
import org.openqa.selenium.JavascriptExecutor;

/**
 *
 */
public class PageObject extends BasePage implements IPage {

    private String htmlSource = null;

    /**
     * Constructor for non-entry point page. The control is supposed to have reached the page from other API call.
     *
     */
    public PageObject() throws Exception {
        this(null);
    }

    /**
     * Constructor
     * @param url for open
     */
    public PageObject(String url) {
        if (url != null && !"".equals(url)) {
            openUrl(url);
        }
    }

    /**
     * Open url
     * @param url
     */
    public void openUrl(String url) {
        if (this.getDriver() == null) {
            driver = driverFactory.createWebDriver();
        }

        TestLogging.stepLog("Open Url = " + url, false);
        driver.get(url);
        maximizeWindow();

        WaitHelper.sleep(200);
    }

    public void maximizeWindow() {
        try {

            driver.manage().window().maximize();
        } catch (Exception ex) {

            try {
                ((JavascriptExecutor) driver).executeScript(
                        "if (window.screen){window.moveTo(0, 0);window.resizeTo(window.screen.availWidth,window.screen.availHeight);}");
            } catch (Exception ignore) {
                TestLogging.stepLog("Unable to maximize browser window. Exception occurred: " + ignore.getMessage(), true);
            }
        }
    }

    /**
     * from config.properties get value by key
     * @param key
     * @return value
     */
    public static String getConfigValue(String key) {
        ConfigProperty cp = new ConfigProperty("./config.properties");
        String value = cp.getProperty(key);

        System.out.println("key=" + key + ", value=" + value);
        return value;
    }

    @Override
    public String getHtmlSource() {
        return htmlSource;
    }

    @Override
    public String getLocation() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }
}
