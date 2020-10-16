package page.object.weblink.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

import page.object.common.page.ManagerCommonPage;

import static helpers.DriverHelper.getDriver;

public class WebLinksManagerPage extends ManagerCommonPage {

    //Locators
    private String _webLinksCb = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')][contains(.,'%s')]/..//input[@type='checkbox']";
    private By _webLinksIcon = By.xpath("//table//td[count(//th[contains(.,'Status')]/preceding-sibling::th)+1]/..//div[@class='btn-group']/a");
    private String _webLinksTitle = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')][contains(.,'%s')]/a";
    private By _webLinksCategory = By.xpath("//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1]/div");

    //Elements
    private WebElement webLinksCb(String title, String alias) {
        return getDriver().findElement(By.xpath(String.format(_webLinksCb, title, alias)));
    }

    private List<WebElement> webLinksIcon() {
        return getDriver().findElements(_webLinksIcon);
    }

    private WebElement webLinksTitle(String title, String alias) {
        return getDriver().findElement(By.xpath(String.format(_webLinksTitle, title, alias)));
    }

    private List<WebElement> webLinksCategories() {
        return getDriver().findElements(_webLinksCategory);
    }

    //Methods
    public void clickWebLinksCheckBox(String title, String alias) {
        clickElement(webLinksCb(title, alias));
    }

    public boolean isWebLinksDisplayed(String title, String alias) {
        try {
            return webLinksTitle(title, alias).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isIconDisplayedAsPublished() {
        for (WebElement element : webLinksIcon()) {
            if (element.getAttribute("data-original-title").contains("Published and is Current.") == false)
                return false;
        }
        return true;
    }

    public boolean isCategoriesDisplayedCorrectly(String category) {
        for (WebElement element : webLinksCategories()) {
            if (element.getText().contains(category) == false)
                return false;
        }
        return true;
    }


}
