package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.DriverHelper.getDriver;

public class BannersBannersManagerPage extends ManagerPage{
    //Locator
    private  String _bannersBanners = "//table//td[count(//th[contains(.,'Name')]/preceding-sibling::th)+1]//a[contains(.,'%s')]";


    //Element
    private List<WebElement> bannersBanners(String title){return getDriver().findElements(By.xpath(String.format(_bannersBanners,title)));}

    //Methods
    public boolean checkTitleMatchesKeywordEntered(String title) {
        for (int i = 0; i < bannersBanners(title).size(); i++) {
            if (!getText(bannersBanners(title).get(i)).contains(title)) return false;
        }
        return true;
    }

    public void clickBannersBanners(String title){
        clickElement(bannersBanners(title).get(0));
    }

}
