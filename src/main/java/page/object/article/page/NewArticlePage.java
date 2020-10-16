package page.object.article.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import page.object.common.page.NewCommonPage;

import static helpers.DriverHelper.getDriver;

public class NewArticlePage extends NewCommonPage {
    //Locator
    private By _articleTextTa = By.cssSelector("iframe#jform_articletext_ifr");

    //Elements
    private WebElement articleTextTa() {
        return getDriver().findElement(_articleTextTa);
    }

    //Methods

    /***
     * Create new article
     * @param title
     * @param articleText
     * @param category
     * @param status
     */
    public void createNewArticle(String title, String articleText, String category, String status) {
        enterTitle(title);
        enterData(articleTextTa(), articleText);
        selectCategoryItem(category);
        selectStatusItem1(status);
        clickSaveAndCloseBtn();
    }


}
