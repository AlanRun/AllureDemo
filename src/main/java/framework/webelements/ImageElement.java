package framework.webelements;

import org.openqa.selenium.By;

public class ImageElement extends HtmlElement {

    public ImageElement(final String label, final By by) {
        super(label, by);
    }

    public ImageElement(final String label, final By by, int index) {
        super(label, by,index);
    }


    public int getHeight() {
        return super.getSize().getHeight();
    }

    public int getWidth() {
        return super.getSize().getWidth();
    }

    public String getUrl() {
        return super.getAttributeValue("src");
    }
}