package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpers.DriverHelper.getDriver;

public class NewBannersCategoriesPage extends BannersCategoriesManagerPage {

    //Locators
    private By _titleTb = By.cssSelector("input#jform_title");

    private By _statusArrowDown = By.cssSelector("div#jform_published_chzn");
    private String _statusItem = "//fieldset//div[@id='jform_published_chzn']//li[contains(.,'%s')]";
    private By _saveAndCloseBtn = By.cssSelector("div#toolbar-save button");
    //Element

    private WebElement titleTb(){return getDriver().findElement(_titleTb);}
    private WebElement statusArrowDown(){return getDriver().findElement(_statusArrowDown);}
    private WebElement statusItem(String item){return getDriver().findElement(By.xpath(String.format(_statusItem,item)));}
    private WebElement saveAndCloseBtn(){return getDriver().findElement(_saveAndCloseBtn);}

    //Methods
    public void createNewBannersCategories(String title, String status) {
        enterData(titleTb(), title);

        clickElement(statusArrowDown());
        clickElement(statusItem(status));

        clickElement(saveAndCloseBtn());
    }
}
