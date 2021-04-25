package framework.helper;

import framework.core.TestLogging;

public class WaitHelper {

    /**
     * Thread sleep
     * @param milliseconds ms
     */
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
            TestLogging.stepLog("Thread sleep: " + milliseconds + " ms", false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
