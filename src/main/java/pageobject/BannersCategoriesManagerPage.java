package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.DriverHelper.getDriver;

public class BannersCategoriesManagerPage extends ManagerPage {
    //Locator
    private  String _bannersCategories = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1]//a[contains(.,'%s')]";


    //Element
    private List<WebElement> bannersCategories(String title){return getDriver().findElements(By.xpath(String.format(_bannersCategories,title)));}

    //Methods
    public boolean checkTitleMatchesKeywordEntered(String title) {
        for (int i = 0; i < bannersCategories(title).size(); i++) {
            if (!getText(bannersCategories(title).get(i)).contains(title)) return false;
        }
        return true;
    }

}
