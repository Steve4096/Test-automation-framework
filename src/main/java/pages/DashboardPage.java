package pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage extends BasePage {
    private final Locator dashboardHeader;
    private final Locator btnPIM;

    public DashboardPage(Page page) {
        super(page);
        this.dashboardHeader = page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Dashboard"));
        this.btnPIM=page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("PIM"));
    }

    public boolean isDashboardVisible(){
        dashboardHeader.waitFor();
        return dashboardHeader.isVisible();
    }

    public void clickPIMButton(){
        btnPIM.click();
    }

    public PIMPage navigateToPIM(){
        clickPIMButton();
        return new PIMPage(page);
    }
}
