package page.object.banner.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

import page.object.common.page.ManagerCommonPage;

import static helpers.DriverHelper.getDriver;

public class BannersBannersManagerPage extends ManagerCommonPage {
    //Locator
    private String _bannersBanners = "//table//td[count(//th[contains(.,'Name')]/preceding-sibling::th)+1]//a[contains(.,'%s')]";
    private By _filterClientDrd = By.cssSelector("div#filter_client_id_chzn");
    private String _filterClientItem = "//div[@id='filter_client_id_chzn']//li[contains(.,'%s')]";
    private By _id = By.xpath("//table//td[count(//th[contains(.,'ID')]/preceding-sibling::th)+1]");
    private By _idLink = By.xpath("//table//th[contains(.,'ID')]/a");

    //Element
    private WebElement bannersBanners(String title) {
        return getDriver().findElement(By.xpath(String.format(_bannersBanners, title)));
    }

    private WebElement filterClientDrd() {
        return getDriver().findElement(_filterClientDrd);
    }

    private WebElement filterClientItem(String client) {
        return getDriver().findElement(By.xpath(String.format(_filterClientItem, client)));
    }

    private WebElement idLink() {
        return getDriver().findElement(_idLink);
    }

    private List<WebElement> listId() {
        return getDriver().findElements(_id);
    }

    //Methods
    public boolean isTheBannerDisplayed(String title) {
        try {
            return bannersBanners(title).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void selectClientInSearchTool(String client) {
        clickElement(filterClientDrd());
        clickElement(filterClientItem(client));
    }

    public void clickBannersBanners(String title) {
        clickElement(bannersBanners(title));
    }

    public void clickIdLink() {
        clickElement(idLink());
    }

    public void searchBannersByClientAndCategory(String client, String category) {
        clickSearchToolsBtn();
        selectClientInSearchTool(client);
        selectCategoryInSearchTool(category);
    }

    public boolean isIdSortByAscending() {
        boolean boo = true;
        for (int i = 0; i < listId().size() - 1; i++) {
            if (Integer.parseInt(getText(listId().get(i))) > Integer.parseInt(getText(listId().get(i + 1)))) {
                boo = false;
                break;
            }
        }
        return boo;
    }

    public boolean isIdSortByDescending() {
        boolean boo = true;
        for (int i = 0; i < listId().size() - 1; i++) {
            if (Integer.parseInt(getText(listId().get(i))) < Integer.parseInt(getText(listId().get(i)))) {
                boo = false;
                break;
            }
        }
        return boo;
    }
}
