package framework.webelements;

import framework.core.TestLogging;
import org.openqa.selenium.By;

public class ButtonElement extends HtmlElement {

    public ButtonElement(final String label, final By by) {
        super(label, by);
    }

    public ButtonElement(final String label, final By by, int index) {
        super(label, by, index);
    }

    public void submit() {
        TestLogging.stepLog("Submit form by clicking on " + toHTML(), false);
        findElement();
        element.submit();
    }
}