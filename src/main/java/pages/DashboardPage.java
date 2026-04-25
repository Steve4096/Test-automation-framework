package pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage extends BasePage {
    private final Locator dashboardHeader;

    public DashboardPage(Page page) {
        super(page);
        this.dashboardHeader = page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Dashboard"));
    }

    public boolean isDashboardVisible(){
        dashboardHeader.waitFor();
        return dashboardHeader.isVisible();
    }
}
