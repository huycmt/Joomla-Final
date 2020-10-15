package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpers.DriverHelper.getDriver;

public class NewBannersBannersPage extends NewPage {
    //Locators
    private By _clientArrowDown = By.cssSelector("div#jform_cid_chzn");
    private String _clientItem = "//div[@id='jform_cid_chzn']//li[contains(.,'')]";
    private By _clientTb = By.cssSelector("div#jform_cid_chzn input");
    private By _bannerDetails = By.xpath("//form//div[@class='form-horizontal']/ul//a[contains(.,'Banner Details')]");
    private By _details = By.xpath("//form//div[@class='form-horizontal']/ul//a[text()='Details']");

    //Element
    private WebElement clientArrowDown(){return getDriver().findElement(_clientArrowDown);}
    private WebElement clientTb(){return getDriver().findElement(_clientTb);}
    private WebElement clientItem(String item){return getDriver().findElement(By.xpath(String.format(_clientItem,item)));}
    private WebElement bannerDetails(){return getDriver().findElement(_bannerDetails);}
    private WebElement details(){ return getDriver().findElement(_details);}

    //Methods
    public void createNewBannersBanners(String name, String client, String category) {
        enterName(name);
        clickElement(details());
        selectCategoryItem(category);
        clickElement(bannerDetails());

        clickElement(clientArrowDown());
        enterData(clientTb(),client);
        clickElement(clientItem(client));

        clickSaveAndCloseBtn();
    }
}
