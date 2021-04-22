package framework.webelements;

import framework.core.TestLogging;
import org.openqa.selenium.By;

public class CheckBoxElement extends HtmlElement {

    public CheckBoxElement(final String label, final By by) {
        super(label, by);
    }

    public CheckBoxElement(final String label, final By by, int index) {
        super(label, by, index);
    }

    public void check() {
        TestLogging.stepLog("check " + toHTML(), false);
        if (!isSelected()) {
            super.click();
        }
    }

    public boolean isSelected() {
        findElement();
        return element.isSelected();
    }

    public void uncheck() {
        TestLogging.stepLog("uncheck " + toHTML(), false);
        if (isSelected()) {
            super.click();
        }
    }
}