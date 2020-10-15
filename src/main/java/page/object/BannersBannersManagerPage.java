package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.DriverHelper.getDriver;

public class BannersBannersManagerPage extends ManagerPage{
    //Locator
    private  String _bannersBanners = "//table//td[count(//th[contains(.,'Name')]/preceding-sibling::th)+1]//a[contains(.,'%s')]";
    private By _filterClientDrd = By.cssSelector("div#filter_client_id_chzn");
    private String _filterClientItem = "//div[@id='filter_client_id_chzn']//li[contains(.,'%s')]";
    private By _id = By.xpath("//table//td[count(//th[contains(.,'ID')]/preceding-sibling::th)+1]");
    private By _idLink = By.xpath("//table//th[contains(.,'ID')]/a");

    //Element
    private List<WebElement> bannersBanners(String title){return getDriver().findElements(By.xpath(String.format(_bannersBanners,title)));}

    private WebElement filterClientDrd(){return getDriver().findElement(_filterClientDrd);}
    private WebElement filterClientItem(String client){return getDriver().findElement(By.xpath(String.format(_filterClientItem,client)));}

    private WebElement idLink(){return getDriver().findElement(_idLink);}
    private List<WebElement> listId(){return getDriver().findElements(_id);}
    //Methods
    public boolean isTitleDisplayed(String title) {
        for (int i = 0; i < bannersBanners(title).size(); i++) {
            if (bannersBanners(title).get(i).isDisplayed() == false) return false;
        }
        return true;
    }

    public boolean checkTitleMatchesKeywordEntered(String title) {
        for (int i = 0; i < bannersBanners(title).size(); i++) {
            if (!getText(bannersBanners(title).get(i)).contains(title)) return false;
        }
        return true;
    }

    public void clickBannersBanners(String title){
        clickElement(bannersBanners(title).get(0));
    }
    public void clickIdLink(){
        clickElement(idLink());
    }

    public void searchBannersByClientAndCategory(String client,String category){
        clickSearchToolsBtn();

        clickElement(filterClientDrd());
        clickElement(filterClientItem(client));

        clickCategoryItemInSearchTool(category);

    }
    public boolean checkIdAscending(){
        boolean boo= true;
        for (int i = 0; i <listId().size()-1 ; i++) {
            if(Integer.parseInt(getText(listId().get(i))) > Integer.parseInt(getText(listId().get(i+1)))){
                boo = false;
                break;
            }
        }
        return boo;
    }

    public boolean checkIdDescending(){
        boolean boo= true;
        for (int i = 0; i <listId().size()-1 ; i++) {
            if(Integer.parseInt(getText(listId().get(i))) < Integer.parseInt(getText(listId().get(i)))){
                boo = false;
                break;
            }
        }
        return boo;
    }
}
