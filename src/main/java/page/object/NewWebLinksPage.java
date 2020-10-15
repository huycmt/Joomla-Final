package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpers.DriverHelper.getDriver;

public class NewWebLinksPage extends NewPage {
    //Locators
    private By _url = By.cssSelector("input#jform_url");
    private By _alias = By.cssSelector("input#jform_alias");

    //Elements
    private WebElement url(){return getDriver().findElement(_url);}
    private WebElement alias(){return getDriver().findElement(_alias);}

    //Methods
    public void createNewWebLinks(String title,String alias,String url,String category,String status){
        enterTitle(title);
        enterData(alias(),alias);
        enterData(url(),url);
        selectCategoryItem(category);
        selectStatusItem1(status);
        clickSaveAndCloseBtn();

    }

}
