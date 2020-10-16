package page.object.banner.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

import page.object.common.page.ManagerCommonPage;

import static helpers.DriverHelper.getDriver;

public class BannersCategoriesManagerPage extends ManagerCommonPage {
    //Locator
    private String _bannersCategories = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1]//a[contains(.,'%s')]";

    //Element
    private WebElement bannersCategories(String title) {
        return getDriver().findElement(By.xpath(String.format(_bannersCategories, title)));
    }

    //Methods
    public boolean isTheCategoryDisplayed(String category) {
        try {
            return bannersCategories(category).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
