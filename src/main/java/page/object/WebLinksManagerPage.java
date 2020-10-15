package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.DriverHelper.getDriver;

public class WebLinksManagerPage extends ManagerPage {

    //Locators
    private String _webLinksCb = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')][contains(.,'%s')]/..//input[@type='checkbox']";
    private String _webLinksIcon = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')][contains(.,'%s')]/..//div[@class='btn-group']/a";
    private String _webLinksTitle = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')][contains(.,'%s')]/a";
    private String _webLinksCategory = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')][contains(.,'%s')]/div[contains(.,'%s')]";


    //Elements
    private WebElement webLinksCb(String title, String alias) {
        return getDriver().findElement(By.xpath(String.format(_webLinksCb, title, alias)));
    }

    private List<WebElement> webLinksIcon(String title, String alias) {
        return getDriver().findElements(By.xpath(String.format(_webLinksIcon, title, alias)));
    }
    private List<WebElement> webLinksTitle(String title, String alias){
        return getDriver().findElements(By.xpath(String.format(_webLinksTitle,title,alias)));
    }

    private List<WebElement> webLinksCategories(String title, String alias,String category){
        return getDriver().findElements(By.xpath(String.format(_webLinksCategory,title,alias,category)));
    }

    //Methods
    public void clickWebLinksCheckBox(String title, String alias) {
        clickElement(webLinksCb(title, alias));
    }
    public boolean isWebLinksDisplayed(String title,String alias){
        for (int i = 0; i < webLinksTitle(title, alias).size(); i++) {
            if (webLinksTitle(title, alias).get(i).isDisplayed() == false) return false;
        }
        return true;
    }

    public boolean isAttributeDisplayedCorrectly(String title, String alias) {
        for (int i = 0; i < webLinksTitle(title, alias).size(); i++) {
            if (webLinksIcon(title, alias).get(i).getAttribute("data-original-title").contains("Published and is Current.") == false) return false;
        }
        return true;
    }

    public boolean isCategoriesDisplayedCorrectly(String title, String alias,String category) {
        for (int i = 0; i < webLinksCategories(title, alias,category).size(); i++) {
            if (webLinksCategories(title, alias,category).get(i).getText().contains(category) == false) return false;
        }
        return true;
    }


}
