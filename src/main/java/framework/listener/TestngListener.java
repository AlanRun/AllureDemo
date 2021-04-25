package framework.listener;

import framework.driver.DriverFactory;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Listen test class, and handle result.
 */
public class TestngListener extends TestListenerAdapter  {

    /**
     * The handle for result is failed.
     * @param tr
     */
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        WebDriver driver = DriverFactory.getWebDriver();
        getCurrentURL(driver);
        takePhoto(driver);
    }


    @Attachment(value = "Fail Url As:", type = "text/plain")
    public String getCurrentURL(WebDriver driver){
        return driver.getCurrentUrl();
    }


    /**
     * Attach ScreenShot when result is failed.
     * @param driver
     * @return
     */
    @Attachment(value = "Fail ScreenShot As:", type = "image/png")
    public byte[]  takePhoto(WebDriver driver){
        byte[] screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        return screenshotAs;
    }
}