package framework.webelements;

import framework.core.TestLogging;
import org.openqa.selenium.By;

public class LabelElement extends HtmlElement {
    public LabelElement(final String label, final By by) {
        super(label, by);
    }

    public LabelElement(final String label, final By by, int index) {
        super(label, by, index);
    }

    public String getText() {
        TestLogging.stepLog("get text from " + toHTML(), false);
        return super.getText();
    }

    public boolean isTextPresent(final String pattern) {
        String text = getText();
        return (text != null && (text.contains(pattern) || text.matches(pattern)));
    }

    @Deprecated
    public String getExpectedText() {
        return null;
    }
}