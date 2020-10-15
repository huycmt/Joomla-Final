package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.DriverHelper.getDriver;

public class CategoriesManagerPage extends ManagerPage {

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
    private List<WebElement> categoriesTitle(String category,String alias){
        return getDriver().findElements(By.xpath(String.format(_categoryTitle,category,alias)));
    }

    //Methods
    public void clickCategoryCheckBox(String category, String alias) {
        clickElement(categoriesCb(category, alias));
    }
    public boolean isCategoryDisplayed(String category,String alias){
        for (int i = 0; i < categoriesTitle(category, alias).size(); i++) {
            if (categoriesTitle(category, alias).get(i).isDisplayed() == false) return false;
        }
        return true;

    }
    public boolean isAttributeDisplayedCorrectly(String category, String alias) {
            return categoriesIcon(category, alias).getAttribute("data-original-title").equalsIgnoreCase("Unpublish Item");
        }


    }
