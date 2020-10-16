package page.object.common.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import page.object.BasePage;

import static helpers.DriverHelper.getDriver;

public class ManagerCommonPage extends BasePage {
    //Locators
    private By _newBtn = By.xpath("//div[@class='btn-toolbar']//button[contains(.,'New')]");
    private By _editBtn = By.cssSelector("div#toolbar-edit>button");
    private By _savedMessage = By.cssSelector("div#system-message-container>div.alert.alert-success>div.alert-message");
    private By _searchBtn = By.cssSelector("button.btn.hasTooltip[type='submit']");
    private By _filterSearch = By.cssSelector("input#filter_search");
    private By _sortDrd = By.cssSelector("div#list_fullordering_chzn");
    private String _sortBy = "//div[@id='list_fullordering_chzn']//li[contains(.,'%s')]";
    private By _searchTools = By.xpath("//button[@class='btn hasTooltip js-stools-btn-filter']");
    private By _archiveBtn = By.cssSelector("div#toolbar-archive>button");
    private By _statusDrdInSearchTool1 = By.cssSelector("div#filter_state_chzn");
    private String _statusItemInSearchTool1 = "//div[@id='filter_state_chzn']//li[contains(.,'%s')]";
    private By _statusDrdInSearchTool2 = By.cssSelector("div#filter_published_chzn");
    private String _statusItemInSearchTool2 = "//div[@id='filter_published_chzn']//li[contains(.,'%s')]";
    private By _filterCategoryDrd = By.cssSelector("div#filter_category_id_chzn");
    private String _filterCategoryItem = "//div[@id='filter_category_id_chzn']//li[contains(.,'%s')]";
    private By _helpBtn = By.xpath("//button[@class='btn btn-small'][contains(.,'Help')]");
    private By _publishBtn = By.xpath("//button[@class='btn btn-small button-publish']");

    //Elements
    private WebElement newBtn() {
        return getDriver().findElement(_newBtn);
    }

    private WebElement editBtn() {
        return getDriver().findElement(_editBtn);
    }

    private WebElement savedMessage() {
        return getDriver().findElement(_savedMessage);
    }

    private WebElement searchBtn() {
        return getDriver().findElement(_searchBtn);
    }

    private WebElement filterSearch() {
        return getDriver().findElement(_filterSearch);
    }

    private WebElement sortDrd() {
        return getDriver().findElement(_sortDrd);
    }

    private WebElement sortBy(String by) {
        return getDriver().findElement(By.xpath(String.format(_sortBy, by)));
    }

    private WebElement searchTool() {
        return getDriver().findElement(_searchTools);
    }

    private WebElement archiveBtn() {
        return getDriver().findElement(_archiveBtn);
    }

    private WebElement statusDrdInSearchTool1() {
        return getDriver().findElement(_statusDrdInSearchTool1);
    }

    private WebElement statusItemInSearchTool1(String status) {
        return getDriver().findElement(By.xpath(String.format(_statusItemInSearchTool1, status)));
    }

    private WebElement statusDrdInSearchTool2() {
        return getDriver().findElement(_statusDrdInSearchTool2);
    }

    private WebElement statusItemInSearchTool2(String status) {
        return getDriver().findElement(By.xpath(String.format(_statusItemInSearchTool2, status)));
    }

    private WebElement filterCategoryDrd() {
        return getDriver().findElement(_filterCategoryDrd);
    }

    private WebElement filterCategoryItem(String category) {
        return getDriver().findElement(By.xpath(String.format(_filterCategoryItem, category)));
    }

    private WebElement helpBtn() {
        return getDriver().findElement(_helpBtn);
    }

    private WebElement publishBtn() {
        return getDriver().findElement(_publishBtn);
    }

    //Methods
    public String getMessage() {
        return getText(savedMessage());
    }

    public void clickNewBtn() {
        clickElement(newBtn());
    }

    public void findByTitle(String text) {
        enterData(filterSearch(), text);
        clickElement(searchBtn());
    }

    public void clickEditBtn() {
        clickElement(editBtn());
    }

    public void sortByIdDescending() {
        clickElement(sortDrd());
        clickElement(sortBy("ID descending"));
    }

    public void clickSearchToolsBtn() {
        clickElement(searchTool());
    }

    public void clickArchiveBtn() {
        clickElement(archiveBtn());
    }

    public void selectStatusInSearchTool(String status) {
        clickElement(statusDrdInSearchTool1());
        clickElement(statusItemInSearchTool1(status));
    }

    public void selectStatusInSearchTool2(String status) {
        clickElement(statusDrdInSearchTool2());
        clickElement(statusItemInSearchTool2(status));
    }

    public void selectCategoryInSearchTool(String category) {
        clickElement(filterCategoryDrd());
        clickElement(filterCategoryItem(category));
    }

    public void clickHelpBtn() {
        clickElement(helpBtn());
    }

    public void clickPublishBtn() {
        clickElement(publishBtn());
    }

}
