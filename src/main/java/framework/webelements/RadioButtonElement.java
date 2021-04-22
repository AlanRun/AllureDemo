package framework.webelements;

import framework.core.TestLogging;
import org.openqa.selenium.By;

public class RadioButtonElement extends HtmlElement {

    public RadioButtonElement(final String label, final By by, int index) {
        super(label, by, index);
    }

    public void check() {
        TestLogging.stepLog("check " + toHTML(), false);
        super.click();
    }

    public boolean isSelected() {
        findElement();
        return element.isSelected();
    }
}
