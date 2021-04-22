package demo;

import framework.core.SeleniumTestPlan;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class DemoTest extends SeleniumTestPlan {

    @Description("百度搜索百度搜索百度搜索")
    @Test(description = "百度搜索")
    public void baiduSearchTest() throws Exception {
        String url = "http://www.baidu.com";
        System.out.println("url:"+url);

        DemoPage page = new DemoPage(url);

        page.pageAction1();

    }

    @Test(description = "搜狗搜索")
    public void sougouSearchTest() throws Exception {
        String url = "https://www.sogou.com";

        DemoPage page = new DemoPage(url);
        page.pageAction2();
    }
}