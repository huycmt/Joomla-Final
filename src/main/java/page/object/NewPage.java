package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpers.DriverHelper.getDriver;

public class NewPage extends BasePage{
    //Locators
    private By _titleTb = By.cssSelector("input#jform_title");
    private By _nameTb = By.cssSelector("input#jform_name");

    private By _categoryArrowDown = By.cssSelector("div#jform_catid_chzn");
    private String _categoryItem = "//div[@id='jform_catid_chzn']//li[contains(.,'%s')]";

    private By _statusArrowDown1 = By.cssSelector("div#jform_state_chzn");
    private String _statusItem1 = "//div[@id='jform_state_chzn']//li[contains(.,'%s')]";

    private By _statusArrowDown2 = By.cssSelector("div#jform_published_chzn");
    private String _statusItem2 = "//div[@id='jform_published_chzn']//li[contains(.,'%s')]";

    private By _saveAndCloseBtn = By.cssSelector("div#toolbar-save button");
    private By _saveAndNewBtn = By.cssSelector("div#toolbar-save-new");


    //Elements
    private WebElement titleTb() {
        return getDriver().findElement(_titleTb);
    }
    private WebElement nameTb(){return getDriver().findElement(_nameTb);}

    private WebElement statusArrowDown1(){return getDriver().findElement(_statusArrowDown1);}
    private WebElement statusItem1(String item){return getDriver().findElement(By.xpath(String.format(_statusItem1,item)));}

    private WebElement statusArrowDown2(){return getDriver().findElement(_statusArrowDown2);}
    private WebElement statusItem2(String item){return getDriver().findElement(By.xpath(String.format(_statusItem2,item)));}

    private WebElement categoriesArrowDown(){return getDriver().findElement(_categoryArrowDown);}
    private WebElement categoriesItem(String item){return getDriver().findElement(By.xpath(String.format(_categoryItem,item)));}

    private WebElement saveAndCloseBtn(){return getDriver().findElement(_saveAndCloseBtn);}
    private WebElement saveAndNewBtn(){return getDriver().findElement(_saveAndNewBtn);}

    //Methods
    public void enterTitle(String title){
        enterData(titleTb(),title);
    }
    public void enterName(String name){
        enterData(nameTb(),name);
    }

    public void selectStatusItem1(String item) {
        clickElement(statusArrowDown1());
        clickElement(statusItem1(item));
    }

    public void selectStatusItem2(String item) {
        clickElement(statusArrowDown2());
        clickElement(statusItem2(item));
    }

    public void selectCategoryItem(String item) {
        clickElement(categoriesArrowDown());
        clickElement(categoriesItem(item));
    }
    public void clickSaveAndCloseBtn(){
        clickElement(saveAndCloseBtn());
    }
    public void clickSaveAndNewBtn(){
        clickElement(saveAndNewBtn());
    }
}
