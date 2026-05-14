package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.lang.reflect.Method;

public class BaseTest {
    protected Browser browser;
    protected Page page;
    BrowserFactory browserFactory;
    ConfigReader configReader=ConfigReader.getInstance();

    @BeforeMethod
    public void setup(Method method){
        browserFactory=new BrowserFactory();
        browser=browserFactory.createBrowser();
        page= browser.newPage();

        page.setDefaultTimeout(600000);
        page.setDefaultNavigationTimeout(600000);

        page.navigate(configReader.getProperty("orangeHrm.url"));
    }

    @AfterMethod
    public void teardown(){
        browserFactory.closeBrowser();
    }
}
