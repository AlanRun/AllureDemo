package framework.webelements;

import framework.core.TestLogging;
import org.openqa.selenium.By;

public class LinkElement extends HtmlElement {

    public LinkElement(final String label, final By by) {
        super(label, by);
    }

    public LinkElement(final String label, final By by, int index) {
        super(label, by, index);
    }

    public String getUrl() {
        return super.getAttributeValue("href");
    }
}