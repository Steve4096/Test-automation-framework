package pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage extends BasePage {
    private final Locator txtUsername;
    private final Locator txtPassword;
    private final Locator btnLogin;
    private final Locator txtErrorMessage;

    public LoginPage(Page page) {
        super(page);
        this.txtUsername= page.getByPlaceholder("Username");
        this.txtPassword=page.locator("input[name='password']");
        this.btnLogin=page.locator("button[type='submit']");
        this.txtErrorMessage = page.locator("div[role='alert']");
    }

    public void enterUsername(String username){
        txtUsername.fill(username);
    }

    public void enterPassword(String password){
        txtPassword.fill(password);
    }

    public void clickLoginButton(){
        btnLogin.click();
    }

    public DashboardPage login(String username,String password){
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

        return new DashboardPage(page);
    }

    public String getErrorMessage(){
        return txtErrorMessage.textContent();
    }
}
