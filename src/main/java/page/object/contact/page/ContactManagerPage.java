package page.object.contact.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

import page.object.common.page.ManagerCommonPage;

import static helpers.DriverHelper.getDriver;

public class ContactManagerPage extends ManagerCommonPage {
    //Locator
    private By _allContactTitle = By.xpath("//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1]//a[contains(@href,'/admin')]");
    private String _contactCheckBox = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')]/..//input";
    private String _contactTitle = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1]//a[contains(.,'%s')]";

    //Elements
    private List<WebElement> allContactsTitle(String title) {
        return getDriver().findElements(_allContactTitle);
    }

    private WebElement contactsTitle(String title) {
        return getDriver().findElement(By.xpath(String.format(_contactTitle, title)));
    }

    private WebElement contactCheckBox(String title) {
        return getDriver().findElement(By.xpath(String.format(_contactCheckBox, title)));
    }

    //Methods
    public boolean isContactDisplayed(String title) {
        try {
            return contactsTitle(title).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isTitleMatchesKeywordEntered(String title) {
        for (WebElement element : allContactsTitle(title)) {
            if (!getText(element).contains(title)) return false;
        }
        return true;
    }

    public void clickContactCheckBox(String title) {
        clickElement(contactCheckBox(title));
    }

}
