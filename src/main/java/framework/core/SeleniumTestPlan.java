package framework.core;

import framework.driver.DriverFactory;
import framework.helper.ConfigProperty;
import framework.listener.TestngListener;
import org.testng.ITestContext;
import org.testng.annotations.*;

/**
 * This class initializes context, sets up and tears down and clean up drivers An STF test should extend this class.
 */
@Listeners({TestngListener.class})
public abstract class SeleniumTestPlan {

    public static String getConfigValue(String key) {
        ConfigProperty cp = new ConfigProperty("./config.properties");
        String value = cp.getProperty(key);

        System.out.println("key=" + key + ", value=" + value);
        return value;
    }

    /**
     * @param   testContext
     *
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeTestSuite(final ITestContext testContext) {
        System.out.println("##############################################################################");
        DriverFactory.getWebDriver(true);
    }

//    /**
//     * Configure Test Params setting.
//     *
//     * @param  xmlTest
//     */
//    @BeforeTest(alwaysRun = true)
//    public void beforeTest(final ITestContext testContext, final XmlTest xmlTest) {
//    }

//    @BeforeMethod(alwaysRun = true)
//    public void beforeTestMethod(final Object[] parameters, final Method method, final ITestContext testContex,
//                                 final XmlTest xmlTest) {
//        logger.info(Thread.currentThread() + " Start method " + method.getName());
//    }

//    /**
//     * clean up.
//     *
//     * @param  parameters
//     * @param  method
//     * @param  testContex
//     * @param  xmlTest
//     */
//    @AfterMethod(alwaysRun = true)
//    public void afterTestMethod(final Object[] parameters, final Method method, final ITestContext testContex,
//                                final XmlTest xmlTest) {
//        logger.info(Thread.currentThread() + " Finish method " + method.getName());
//    }

    @AfterSuite(alwaysRun = true)
    public void afterTestSuite() {
        DriverFactory.tearDownDriver();
    }


}