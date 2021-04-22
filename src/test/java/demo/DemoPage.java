package demo;

import framework.webelements.ButtonElement;
import framework.webelements.TextFieldElement;
import framework.webpage.PageObject;
import io.qameta.allure.Step;

import static framework.core.Locator.*;

public class DemoPage extends PageObject {

    public DemoPage(String url) throws Exception {
        super(url);
    }

    // *****************************************************************************************************************
    // ************************************************** 定义元素 ******************************************************
    // *****************************************************************************************************************

    TextFieldElement kwInput = new TextFieldElement("Input TextField", locateById("kw"));
    ButtonElement btn = new ButtonElement("Submit button", locateById("s11"));
    TextFieldElement query1 = new TextFieldElement("Input textFiled", locateById("query1"));

    // *****************************************************************************************************************
    // ************************************************** 页面操作 ******************************************************
    // *****************************************************************************************************************

    @Step("kw sendKeys galen2016")
    public void pageAction1() {
        String content  = "galen2016";

        kwInput.type(content);
        btn.click();
    }

    @Step("query1 sendKeys galen2016")
    public void pageAction2() {
        String content = "galen2016";

        query1.type(content);
    }
}
