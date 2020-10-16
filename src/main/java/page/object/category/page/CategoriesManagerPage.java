package page.object.category.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

import page.object.common.page.ManagerCommonPage;

import static helpers.DriverHelper.getDriver;

public class CategoriesManagerPage extends ManagerCommonPage {

    //Locators
    private String _categoriesCb = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')][contains(.,'%s')]/..//input[@type='checkbox']";
    private String _categoriesIcon = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')][contains(.,'%s')]/..//div[@class='btn-group']/a";
    private String _categoryTitle = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')][contains(.,'%s')]/a";

    //Elements
    private WebElement categoriesCb(String category, String alias) {
        return getDriver().findElement(By.xpath(String.format(_categoriesCb, category, alias)));
    }

    private WebElement categoriesIcon(String category, String alias) {
        return getDriver().findElement(By.xpath(String.format(_categoriesIcon, category, alias)));
    }

    private WebElement categoriesTitle(String category, String alias) {
        return getDriver().findElement(By.xpath(String.format(_categoryTitle, category, alias)));
    }

    //Methods
    public void clickCategoryCheckBox(String category, String alias) {
        clickElement(categoriesCb(category, alias));
    }

    public boolean isTheCategoryDisplayed(String category, String alias) {
        try {
            return categoriesTitle(category, alias).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isIconDisplayedAsPublished(String category, String alias) {
        return categoriesIcon(category, alias).getAttribute("data-original-title").equalsIgnoreCase("Unpublish Item");
    }
}
