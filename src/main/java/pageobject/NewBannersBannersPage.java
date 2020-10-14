package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpers.DriverHelper.getDriver;

public class NewBannersBannersPage extends BannersBannersManagerPage {
    //Locators
    private By _nameTb = By.cssSelector("input#jform_name");

    private By _categoryArrowDown = By.cssSelector("div#jform_catid_chzn");
    private String _categoryItem = "//fieldset//div[@id='jform_catid_chzn']//li[contains(.,'%s')]";

    private By _statusArrowDown = By.cssSelector("div#jform_state_chzn");
    private String _statusItem = "//fieldset//div[@id='jform_catid_chzn']//li[contains(.,'')]";
    private By _clientArrowDown = By.cssSelector("div#jform_cid_chzn");
    private String _clientItem = "//div[@id='jform_cid_chzn']//li[contains(.,'')]";

    private By _bannerDetails = By.xpath("//form//div[@class='form-horizontal']/ul//a[contains(.,'Banner Details')]");
    private By _saveAndCloseBtn = By.cssSelector("div#toolbar-save button");
    //Element

    private WebElement nameTb(){return getDriver().findElement(_nameTb);}
    private WebElement statusArrowDown(){return getDriver().findElement(_statusArrowDown);}
    private WebElement statusItem(String item){return getDriver().findElement(By.xpath(String.format(_statusItem,item)));}

    private WebElement categoriesArrowDown(){return getDriver().findElement(_categoryArrowDown);}
    private WebElement categoriesItem(String item){return getDriver().findElement(By.xpath(String.format(_categoryItem,item)));}

    private WebElement clientArrowDown(){return getDriver().findElement(_clientArrowDown);}
    private WebElement clientItem(String item){return getDriver().findElement(By.xpath(String.format(_clientItem,item)));}
    private WebElement saveAndCloseBtn(){return getDriver().findElement(_saveAndCloseBtn);}
    private WebElement bannerDetails(){return getDriver().findElement(_bannerDetails);}

    //Methods
    public void createNewBannersBanners(String name, String client, String item) {
        enterData(nameTb(), name);

        clickElement(categoriesArrowDown());
        clickElement(categoriesItem(item));

        clickElement(bannerDetails());

        clickElement(clientArrowDown());
        clickElement(clientItem(client));

        clickElement(saveAndCloseBtn());
    }
}
