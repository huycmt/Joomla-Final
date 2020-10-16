package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.DriverHelper.getDriver;
import static helpers.DriverHelper.waitForElementVisibility;
import static helpers.DriverHelper.waitUntilElementClickable;
import static utilities.Log.info;

public class BasePage {
    WebDriverWait wait;
    //Locators
    private String _tabLevel1 = "//ul[@id='menu']/li/a[normalize-space(text())='%s']";//content
    private String _tabLevel2 = "//ul[@id='menu']/li/a[normalize-space(text())='%s']/following-sibling::ul/li/a[normalize-space(text())='%s']";//articles
    private String _sideTab = "//div[@id='sidebar']//a[contains(.,'%s')]";

    //Elements
    private WebElement contentTab() {
        return getDriver().findElement(By.xpath(String.format(_tabLevel1, "Content")));
    }

    private WebElement articlesTab() {
        return getDriver().findElement(By.xpath(String.format(_tabLevel2, "Content", "Articles")));
    }

    private WebElement componentsTab() {
        return getDriver().findElement(By.xpath(String.format(_tabLevel1, "Components")));
    }

    private WebElement contactTab() {
        return getDriver().findElement(By.xpath(String.format(_tabLevel2, "Components", "Contacts")));
    }

    private WebElement bannersTab() {
        return getDriver().findElement(By.xpath(String.format(_tabLevel2, "Components", "Banners")));
    }

    private WebElement bannersClientsTab() {
        return getDriver().findElement(By.xpath(String.format(_sideTab, "Clients")));
    }

    private WebElement bannersCategoriesTab() {
        return getDriver().findElement(By.xpath(String.format(_sideTab, "Categories")));
    }

    private WebElement categoriesTab() {
        return getDriver().findElement(By.xpath(String.format(_tabLevel2, "Content", "Categories")));
    }

    private WebElement webLinksTab() {
        return getDriver().findElement(By.xpath(String.format(_tabLevel2, "Components", "Web Links")));
    }

    //Methods
    public void clickTab(Tab tab) {
        switch (tab) {
            case CONTENT:
                clickElement(contentTab());
                break;
            case ARTICLES:
                clickElement(articlesTab());
                break;
            case COMPONENTS:
                clickElement(componentsTab());
                break;
            case CONTACTS:
                clickElement(contactTab());
                break;
            case BANNERS:
                clickElement(bannersTab());
                break;
            case BANNERS_CLIENTS:
                clickElement(bannersClientsTab());
                break;
            case BANNERS_CATEGORIES:
                clickElement(bannersCategoriesTab());
                break;
            case CATEGORIES:
                clickElement(categoriesTab());
                break;
            case WEB_LINKS:
                clickElement(webLinksTab());
                break;
        }
    }

    public void enterData(WebElement element, String data) {
        info("\t\tEnter data for the " + element.getAttribute("name") + " field: " + data);
        element.sendKeys(data);
    }

    public void clickElement(WebElement element) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        info("\t\tClick on " + getText(element) + " " + stackTrace[2].getMethodName());
        waitForElementVisibility(element);
        waitUntilElementClickable(element);
        element.click();
    }


    public String getText(WebElement element) {
        waitForElementVisibility(element);
        return element.getText().trim();
    }


    public enum Tab {
        CONTENT,
        ARTICLES,
        COMPONENTS,
        CONTACTS,
        BANNERS,
        BANNERS_CLIENTS,
        BANNERS_CATEGORIES,
        CATEGORIES,
        WEB_LINKS
    }
}
