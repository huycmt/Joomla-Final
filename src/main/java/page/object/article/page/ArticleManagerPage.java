package page.object.article.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

import page.object.common.page.ManagerCommonPage;

import static helpers.DriverHelper.getDriver;

public class ArticleManagerPage extends ManagerCommonPage {

    //Locators
    private String _articleCheckBox = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/..//input";
    private By _allArticleTitle = By.xpath("//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1]");
    private String _featuredStatusIcon = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1][contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/..//a[@data-original-title='Toggle featured status.']/span";
    private String _articleTitle = "//table//td[count(//th[contains(.,'Title')]/preceding-sibling::th)+1]//a[@data-original-title='Edit'][contains(.,'%s')]";

    //Elements
    private WebElement articleCheckBox(String title, String author) {
        return getDriver().findElement(By.xpath(String.format(_articleCheckBox, title, author)));
    }

    private List<WebElement> allArticlesTitle() {
        return getDriver().findElements(_allArticleTitle);
    }

    private WebElement articleTitle(String title) {
        return getDriver().findElement(By.xpath(String.format(_articleTitle, title)));
    }

    private WebElement featuredStatusIcon(String title, String author) {
        return getDriver().findElement(By.xpath(String.format(_featuredStatusIcon, title, author)));
    }

    //Methods
    public void clickArticleCheckBox(String title, String author) {
        clickElement(articleCheckBox(title, author));
    }

    public String getAttributeFeatureIcon(String title, String author) {
        return featuredStatusIcon(title, author).getAttribute("class").trim();
    }

    public boolean isTitleMatchesKeywordEntered(String title) {
        for (WebElement element : allArticlesTitle()) {
            if (!getText(element).contains(title)) return false;
        }
        return true;
    }

    public boolean isArticleDisplayed(String title) {
        try {
            return articleTitle(title).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    /***
     * Toggle feature icon in status
     * @param title
     * @param author
     */
    public void toggleFeatured(String title, String author) {
        clickArticleCheckBox(title, author);
        clickElement(featuredStatusIcon(title, author));
    }

}
