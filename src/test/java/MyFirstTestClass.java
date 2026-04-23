import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.Assert;
import org.testng.annotations.*;

public class MyFirstTestClass {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private static final String Base_URL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private static final String URL_1="https://example.com";
    private static final String URL_2="https://www.google.com";
    private static final String URL_3="https://the-internet.herokuapp.com/login";

    @BeforeMethod
    public void setUp(){
        playwright=Playwright.create();
        browser=playwright.chromium().launch();
        //browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page= browser.newPage();
        page.setDefaultNavigationTimeout(3000000);
    }

    @Test
    public void loginPageValidator(){
        page.navigate(Base_URL);

        String title=page.title();
        System.out.println("The page title is "+title);
        //page.pause();
        Assert.assertTrue(
                title.contains("OrangeHRM"),
                "Expected title to contain 'OrangeHRM' but got: "+title
        );
    }

    @Test
    public void validLoginUsingPlaceholders(){
        //page.navigate(Base_URL);
        page.navigate(Base_URL,new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));

        page.getByPlaceholder("Username").fill("Admin");
        page.getByPlaceholder("Password").fill("admin123");
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Login")).click();

        page.waitForURL("**/dashboard/**");
        Assert.assertTrue(
                page.url().contains("dashboard"),
                "Log in failed-Dashboard not reached"
        );
    }

    @Test
    public void validLoginUsingAttributes(){
        page.navigate(Base_URL,new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));

        page.fill("input[name='username']","Admin");
        page.fill("input[name='password']","admin123");
        //page.getByText("Login").click();
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Login")).click();

        page.waitForURL("**/dashboard/**");
        Assert.assertTrue(
                page.url().contains("dashboard"),
                "Log in failed-Dashboard not reached"
        );

    }


    @Test
    public void invalidLogin(){
        page.navigate(Base_URL,new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));

        page.getByPlaceholder("Username").fill("Admin");
        page.getByPlaceholder("Password").fill("abracadabra");
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Login")).click();

        Locator alert= page.getByRole(AriaRole.ALERT);

        Assert.assertTrue(alert.textContent().contains("Invalid credentials"),"Undefined error message");
    }

    @AfterMethod
    public void teardown(){
        browser.close();
        playwright.close();
    }


}
