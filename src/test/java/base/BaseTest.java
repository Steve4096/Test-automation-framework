package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {
    protected Browser browser;
    protected Page page;
    BrowserFactory browserFactory;
    ConfigReader configReader=ConfigReader.getInstance();

    @BeforeMethod
    public void setup(){
        browserFactory=new BrowserFactory();
        browser=browserFactory.createBrowser();
        page= browser.newPage();
        page.navigate(configReader.getProperty("OrangeHrm.url"));
    }

    @AfterMethod
    public void teardown(){
        browserFactory.closeBrowser();
    }
}
