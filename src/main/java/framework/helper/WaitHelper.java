package framework.helper;

import framework.core.TestLogging;

public class WaitHelper {

    /**
     * Thread sleep
     * @param millis ms
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
            TestLogging.stepLog("Thread sleep: " + millis + " ms", false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
