package framework.core;

import io.qameta.allure.Attachment;

/**
 * Log methods for test operations.
 */
public class TestLogging {

    /**
     * print log in Allure report by Attachment.
     * @param message msg
     * @param failed true or false
     * @return full log
     */
    @Attachment(value = "页面操作")
    public static String stepLog(String message, final boolean failed) {
        message = (failed ? "FailedAction: " : "Action: ") + message;
//        log(message, failed, false);
        return message;
    }
}
