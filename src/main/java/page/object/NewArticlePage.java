package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpers.DriverHelper.getDriver;

public class NewArticlePage extends NewPage  {
    //Locator
    private By _articleTextTa = By.cssSelector("iframe#jform_articletext_ifr");

    //Elements
    private WebElement articleTextTa() {
        return getDriver().findElement(_articleTextTa);
    }

    //Methods
    public void createNewArticle(String title, String articleText, String category, String status) {
        enterTitle(title);
        enterData(articleTextTa(), articleText);

        selectCategoryItem(category);
        selectStatusItem1(status);

        clickSaveAndCloseBtn();
    }


}
