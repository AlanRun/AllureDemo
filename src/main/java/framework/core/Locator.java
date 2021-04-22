package framework.core;

import org.openqa.selenium.By;

/**
 *  Element locator, ex. id, name, css, xpath.
 */
public class Locator {

    public static By locateById(String id) {
        return By.id(id);
    }

    public static By locateByName(String name) {
        return By.name(name);
    }

    public static By locateByCss(String css) {
        return By.cssSelector(css);
    }

    public static By locateByCls(String cls) {
        return By.className(cls);
    }

    public static By locateByXpath(String xpath) {
        return By.xpath(xpath);
    }

    public static By locateByLinkText(String linkText) {
        return By.linkText(linkText);
    }

    public static By locateByPartLinkText(String partialLinkText) {
        return By.partialLinkText(partialLinkText);
    }
}
