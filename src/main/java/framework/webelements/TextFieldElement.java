package framework.webelements;

import framework.core.TestLogging;
import org.openqa.selenium.By;

public class TextFieldElement extends HtmlElement {
    public TextFieldElement(final String label, final By by) {
        super(label, by);
    }

    public TextFieldElement(final String label, final By by, int index) {
        super(label, by, index);
    }

    public void clear() {
        TestLogging.stepLog("Remove data From " + toHTML(), false);
        findElement();
        if (element != null && !element.getAttribute("type").equalsIgnoreCase("file")) {
            element.clear();
        }
    }

    public void type(final String keysToSend) {
        if (keysToSend == null || keysToSend.equals("")) {
            TestLogging.stepLog("Enter empty data on " + toHTML(), false);
        } else {
            sendKeys(keysToSend);
        }
    }

    public void clearAndType(final String keysToSend) {
        clear();
        type(keysToSend);
    }
}