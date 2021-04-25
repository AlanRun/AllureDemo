package framework.webelements;

import framework.core.TestLogging;
import framework.driver.DriverFactory;
import framework.core.LocatorType;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Provides methods to interact with a web page. All HTML element (ButtonElement, LinkElement, TextFieldElement, etc.)
 * extends from this class.
 */
public class HtmlElement {
    private static final int EXPLICIT_WAIT_TIME_OUT = 15;
    private static final int IMPLICITLY_WAIT_TIME_OUT = 5;

    protected WebDriver driver = DriverFactory.getWebDriver();
    protected WebElement element = null;
    private String label = null;
    private String locator = null;
    private By by = null;
    private int index = -1;

    /**
     * Find element using BY locator. Make sure to initialize the driver before calling findElement()
     *
     * @param   label  - element name for logging
     * @param   by     - By type
     *
     * @sample  {@code new HtmlElement("UserId", By.id(userid))}
     */
    public HtmlElement(final String label, final By by) {
        this.label = label;
        this.by = by;
    }

    public HtmlElement(final String label, final By by, int index) {
        this.label = label;
        this.by = by;
        this.index  = index;
    }

    /**
     * Find element using BY locator. Make sure to initialize the driver before calling findElement()
     * @param label  - element name for logging
     * @param locator
     * @param locatorType： ID, NAME, CLASS_NAME, LINK_TEXT, PARTIAL_LINK_TEXT, CSS_SELECTOR, TAG_NAME, XPATH
     */
    public HtmlElement(final String label, final String locator, final LocatorType locatorType) {
        this.label = label;
        this.locator = locator;
        this.by = getLocatorBy(locator, locatorType);
    }

    /**
     * Refreshes the WebUIDriver before locating the element, to ensure we have the current version (useful for when the
     * state of an element has changed via an AJAX or non-page-turn action).
     */
    public void init() {
        driver = DriverFactory.getWebDriver();
        element = driver.findElement(by);
    }

    /**
     * Finds the element using By type. Implicit Waits is built in createWebDriver() in WebUIDriver to handle dynamic
     * element problem. This method is invoked before all the basic operations like click, sendKeys, getText, etc. Use
     * waitForPresent to use Explicit Waits to deal with special element which needs long time to present.
     */
    protected void findElement() {

        driver = DriverFactory.getWebDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME_OUT, TimeUnit.SECONDS);

        List<WebElement> list = driver.findElements(by);
        if (list.size() > 0) {
            if (index == -1) {
                element = list.get(0);
            } else if (index > list.size()) {
                Assert.assertTrue(false, toString() + " not found.");
            } else {
                element = list.get(index);
            }
        } else {
            Assert.assertTrue(false,  toString() + " not found.");
        }
    }

    /**
     * Returns the underlying WebDriver WebElement.
     *
     * @return
     */
    public WebElement getElement() {
        findElement();

        return element;
    }

    /**
     * Executes the given JavaScript against the underlying WebElement.
     *
     * @param   script
     *
     * @return
     */
    public String getEval(final String script) {
        findElement();

        final String name = (String) ((JavascriptExecutor) driver).executeScript(script, element);

        return name;
    }

    /**
     * Returns the 'height' property of the underlying WebElement's Dimension.
     *
     * @return
     */
    public int getHeight() {
        findElement();

        return element.getSize().getHeight();
    }

    /**
     * Gets the Point location of the underlying WebElement.
     *
     * @return
     */
    public Point getLocation() {
        findElement();

        return element.getLocation();
    }

    /**
     * Returns the Dimension property of the underlying WebElement.
     *
     * @return
     */
    public Dimension getSize() {
        findElement();

        return element.getSize();
    }

    /**
     * Returns the HTML Tag for the underlying WebElement (div, a, input, etc).
     *
     * @return
     */
    public String getTagName() {
        findElement();

        return element.getTagName();
    }

    /**
     * Returns the text body of the underlying WebElement.
     *
     * @return
     */
    public String getText() {
        findElement();

        return element.getText();
    }

    /**
     * Returns the label used during initialization.
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get all elements in the current page with same locator.
     * @return list of WebElement
     */
    public List<WebElement> getAllElements() {
        findElement();

        return driver.findElements(by);
    }

    /**
     * Gets an attribute (using standard key-value pair) from the underlying attribute.
     *
     * @param name of element attribute
     *
     * @return
     */
    public String getAttributeValue(final String name) {
        findElement();

        return element.getAttribute(name);
    }

    /**
     * Returns the BY locator stored in the HtmlElement.
     *
     * @return
     */
    public By getBy() {
        return by;
    }

    /**
     * Returns the value for the specified CSS key.
     *
     * @param   propertyName
     *
     * @return
     */
    public String getCssValue(final String propertyName) {
        findElement();

        return element.getCssValue(propertyName);
    }

    /**
     * Searches for the element using the By locator, and indicates whether or not it exists in the page. This can be
     * used to look for hidden objects, whereas isDisplayed() only looks for things that are visible to the user
     *
     * @return
     */
    public boolean isExist() {
        findElement();
        return element != null;
    }

    /**
     * Indicates whether or not the web element is currently displayed(visible) in the browser.
     *
     * @return
     */
    public boolean isDisplayed() {

        if (isExist()) {
            return element.isDisplayed();
        }
        return false;
    }

    /**
     * Indicates whether or not the element is enabled in the browser.
     *
     * @return
     */
    public boolean isEnabled() {
        if (isExist()) {
            return element.isEnabled();
        }
        return false;
    }

    /**
     * Indicates whether or not the element is selected in the browser.
     *
     * @return
     */
    public boolean isSelected() {
        if (isExist()) {
            return element.isSelected();
        }
        return false;
    }

    /**
     * Whether or not the indicated text is contained in the element's getText() attribute.
     *
     * @param   text
     *
     * @return
     */
    public boolean isTextPresent(final String text) {
        if (isExist()) {
            return element.getText().contains(text);
        }
        return false;
    }

    public void waitExist(int timeOutInSeconds) {
//        TestLogging.stepLog("wait " + timeOutInSeconds + "s for " + toHTML() + " to be present.", false);

        final WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) { }
    }

    /**
     * Wait element to present using Explicit Waits with timeout in seconds. This method is used for special element
     * which needs long time to present.
     * @return
     */
    public boolean waitExist(final int timeout, int interval) {
//        TestLogging.stepLog("wait " + timeout + "s for " + toHTML() + " exist.", false);
        try {
            final Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeout, TimeUnit.SECONDS)
                    .pollingEvery(interval, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
            wait.until(new Function<WebDriver, WebElement>() {

                @Override
                public WebElement apply(final WebDriver driver) {
                    return driver.findElement(by);
                }
            });
        } catch (Exception e) {
//			TestLogging.logWebStep("tried for "+ timeout +" second(s) with "+ interval +" interval failed", false);
            return false;
        }

        return true;
    }

    /**
     * wait for element be clickable(enable)
     * @param timeOutInSeconds
     */
    public void waitBeClickable(long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException e) { }
    }

    /**
     * wait for element be displayed
     * @param timeOutInSeconds
     */
    public void waitBeDisplayed(long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) { }
    }

    /**
     * wait for element be selected
     * @param timeOutInSeconds
     */
    public void waitBeSelected(long timeOutInSeconds){
//        TestLogging.stepLog("wait " + timeOutInSeconds + "s for " + toHTML() + " to be selected.", false);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        try {
            wait.until(ExpectedConditions.elementToBeSelected(by));
        } catch (TimeoutException e) { }
    }

    public void click() {
        TestLogging.stepLog("click on " + toHTML(), false);
        findElement();

        element.click();
    }

    /**
     * Click element by JS.
     */
    public void clickByJS() {
        TestLogging.stepLog("click on " + toHTML(), false);
        findElement();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void moveTo() {
        TestLogging.stepLog("move to " + toHTML(), false);
        findElement();
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Forces a mouseDown event on the WebElement.
     */
    public void mouseDown() {
        TestLogging.stepLog("mouse down " + toHTML(), false);
        findElement();

        final Mouse mouse = ((HasInputDevices) driver).getMouse();
        mouse.mouseDown(null);
    }

    /**
     * Forces a mouseOver event on the WebElement.
     */
    public void mouseOver() {
        TestLogging.stepLog("mouse over " + toHTML(), false);
        findElement();

//        final Locatable hoverItem = (Locatable) element;
//        final Mouse mouse = ((HasInputDevices) driver).getMouse();
//        mouse.mouseMove(hoverItem.getCoordinates());

        // build and perform the mouseOver with Advanced User Interactions API
         Actions builder = new Actions(driver);
         builder.moveToElement(element).build().perform();
    }

    /**
     * Forces a mouseOver event on the WebElement using simulate by JavaScript way for some dynamic menu.
     */
    public void simulateMouseOver() {
        findElement();

        final String mouseOverScript =
                "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(mouseOverScript, element);
    }

    /**
     * Forces a mouseUp event on the WebElement.
     */
    public void mouseUp() {
        TestLogging.stepLog("mouse up " + toHTML(), false);
        findElement();

        final Mouse mouse = ((HasInputDevices) driver).getMouse();
        mouse.mouseUp(null);
    }

    /**
     * Sends the indicated CharSequence to the WebElement.
     *
     * @param  arg0
     */
    public void sendKeys(final CharSequence arg0) {
        TestLogging.stepLog("Send keys[" + arg0 + "] on " + toHTML(), false);
        findElement();
        element.sendKeys(arg0);
    }

    /**
     * send Enter key.
     */
    public void sendEnter(){
        sendKeys(Keys.ENTER);
    }

    /**
     * Returns a friendly string, only representing the HtmlElement's Type, LabelElement.
     *
     * @return
     */
    public String toHTML() {
        return getClass().getSimpleName().toLowerCase() + " [" + getLabel() + "]";
//                " <a style=\"font-style:normal;color:#8C8984;text-decoration:none;\" href=# \">" + "</a>"
//                getLabel() + "]: locate={" + getBy().toString() + "}";
    }

    /**
     * Returns a friendly string, representing the HtmlElement's Type, LabelElement and Locator.
     */
    public String toString() {

        if (index == -1) {
            return getClass().getSimpleName().toLowerCase() + " [" + getLabel() + "], locate={" + getBy().toString() + "}";
        } else {
            return getClass().getSimpleName().toLowerCase() + " [" + getLabel() + "], locate={" + getBy().toString()
                    + ", index=" + index + "}";
        }
    }

    private By getLocatorBy(final String locator, final LocatorType locatorType) {

        switch (locatorType) {

            case ID:
                return By.id(locator);

            case NAME:
                return By.name(locator);

            case CLASS_NAME:
                return By.className(locator);

            case LINK_TEXT:
                return By.linkText(locator);

            case PARTIAL_LINK_TEXT:
                return By.partialLinkText(locator);

            case CSS_SELECTOR:
                return By.cssSelector(locator);

            case TAG_NAME:
                return By.tagName(locator);
            default:
                return By.xpath(locator);
        }
    }
}
