package page.object.banner.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import page.object.common.page.NewCommonPage;

import static helpers.DriverHelper.getDriver;

public class NewBannersClientsPage extends NewCommonPage {
    //Locators
    private By _contactNameTb = By.cssSelector("input#jform_contact");
    private By _contactEmailTb = By.cssSelector("input#jform_email");

    //Element
    private WebElement contactNameTb() {
        return getDriver().findElement(_contactNameTb);
    }

    private WebElement contactEmailTb() {
        return getDriver().findElement(_contactEmailTb);
    }

    //Methods
    public void createNewBannersClients(String name, String contactName, String contactEmail, String status) {
        enterName(name);
        enterData(contactNameTb(), contactName);
        enterData(contactEmailTb(), contactEmail);

        selectStatusItem1(status);
        clickSaveAndCloseBtn();
    }
}
