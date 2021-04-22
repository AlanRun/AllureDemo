package framework.webpage;

import framework.core.TestLogging;
import framework.driver.DriverFactory;
import framework.webelements.HtmlElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Base page for all.
 */
public class BasePage {
    private static final long EXPLICIT_WAIT_TIME_OUT = 10;
    protected final DriverFactory driverFactory = DriverFactory.getDriverFactory();
    public static WebDriver driver = DriverFactory.getWebDriver();

    public BasePage() { }

    public WebDriver getDriver() {
        driver = DriverFactory.getWebDriver();
        return driver;
    }

    /**
     * In time check element exist(maybe not visible)
     * @param element
     * @param timeOutInSeconds
     */
    public void assertElementExist(final HtmlElement element, int timeOutInSeconds) {
        element.waitExist(timeOutInSeconds);
        TestLogging.stepLog("Assert element: " + element.toHTML() + " exist.", false);
        assertHTML(element.isExist(), element.toString() + " not found.");
    }

    public boolean isTextPresent(final String text) {
        Assert.assertNotNull(text, "isTextPresent: text should not be null!");
        driver = DriverFactory.getWebDriver();

        final WebElement body = driver.findElement(By.tagName("body"));
        return body.getText().contains(text);
    }

    /**
     *  Full Page whether contains text
     * @param text
     */
    public void assertTextPresent(final String text) {
        TestLogging.stepLog("Assert text=[" + text + "] exist.", false);
        assertHTML(isTextPresent(text), "Text= {" + text + "} not found.");
    }

    /**
     * Element whether contains text in time
     * @param element
     * @param text
     * @param timeOutInSeconds
     */
    public void assertElementTextPresent(HtmlElement element, final String text, int timeOutInSeconds) {
        element.waitExist(timeOutInSeconds);
        TestLogging.stepLog("Assert element: " + element.toHTML() + " exist.", false);
        assertHTML(element.isTextPresent(text), element.toString() + " not contains text = {" + text + "}");
    }

    /**
     *
     * @param element
     */
    public void assertElementNotExist(final HtmlElement element) {
        TestLogging.stepLog("Assert element: " + element.toHTML() + " not exist.", false);
        assertHTML(!element.isExist(), element.toString() + " found.");
    }

    /**
     * Assert element is display(visible)
     * @param element
     * @param timeOutInSeconds
     */
    public void assertElementDisplay(final HtmlElement element, int timeOutInSeconds){
        element.waitBeDisplayed(timeOutInSeconds);
        TestLogging.stepLog("Assert element: " + element.toHTML() + " is display.", false);
        assertHTML(element.isDisplayed(), element.toString() + " not found or visible.");
    }

    protected void assertHTML(final boolean condition, final String message) {

        if (!condition) {

            Assert.assertTrue(condition, message);
        }
    }
}
