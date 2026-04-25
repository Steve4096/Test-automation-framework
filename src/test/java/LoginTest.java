import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {
    ConfigReader configReader=ConfigReader.getInstance();

    @Test
    public void loginWithValidCredentials(){
        LoginPage loginPage=new LoginPage(page);
        DashboardPage dashboardPage=loginPage.login(configReader.getProperty("admin.username"), configReader.getProperty("admin.password"));

        Assert.assertTrue(dashboardPage.isDashboardVisible());
    }

    @Test
    public void loginWithInvalidUsername(){
        LoginPage loginPage=new LoginPage(page);
        loginPage.login("Steve",configReader.getProperty("admin.password"));

        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid credentials"),"Error message not displayed");
    }

    @Test
    public void loginWithInvalidPassword(){
        LoginPage loginPage=new LoginPage(page);
        loginPage.login(configReader.getProperty("admin.username"),"Steve");

        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid credentials"),"Error message not displayed");
    }

    //Add method to log in with no credentials,no username and no password
}
