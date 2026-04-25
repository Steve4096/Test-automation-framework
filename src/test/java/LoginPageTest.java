import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {

    @Test
    public void loginWithValidCredentials(){
        LoginPage loginPage=new LoginPage(page);
        DashboardPage dashboardPage=loginPage.login("Admin","admin123");

        Assert.assertTrue(dashboardPage.isDashboardVisible());
    }

    @Test
    public void loginWithInvalidUsername(){
        LoginPage loginPage=new LoginPage(page);
        loginPage.login("Steve","admin123");

        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid credentials"),"Error message not displayed");
    }

    @Test
    public void loginWithInvalidPassword(){
        LoginPage loginPage=new LoginPage(page);
        loginPage.login("Admin","Steve");

        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid credentials"),"Error message not displayed");
    }
}
