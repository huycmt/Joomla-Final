package page.object.banner.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import page.object.common.page.NewCommonPage;

import static helpers.DriverHelper.getDriver;

public class NewBannersCategoriesPage extends NewCommonPage {
    //Locators
    private By _aliasCategory = By.cssSelector("input#jform_alias");

    //Elements
    private WebElement aliasCategory() {
        return getDriver().findElement(_aliasCategory);
    }

    //Methods
    public void createNewBannersCategories(String title, String alias, String status) {
        enterTitle(title);
        enterData(aliasCategory(), alias);
        selectStatusItem2(status);
        clickSaveAndCloseBtn();
    }

}
