package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
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

        page.setDefaultTimeout(120000);
        page.setDefaultNavigationTimeout(120000);

        page.navigate(configReader.getProperty("orangeHrm.url"),new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED).setTimeout(120000));
    }

    @AfterMethod
    public void teardown(){
        browserFactory.closeBrowser();
    }
}
