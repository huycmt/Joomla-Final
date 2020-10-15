package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.DriverHelper.*;

import static utilities.Log.info;

public class BannersClientsManagerPage extends ManagerPage {
    //Locator
    private  String _bannersClients = "//table//td[count(//th[contains(.,'Client')]/preceding-sibling::th)+1]//a[contains(.,'%s')]";
    private String _clientCheckBox = "//table//td[count(//th[contains(.,'Client')]/preceding-sibling::th)+1][contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/..//input";
    //Element
    private List<WebElement> bannersClients(String title){return getDriver().findElements(By.xpath(String.format(_bannersClients,title)));}
    private WebElement clientCheckBox(String client,String contact){
        return getDriver().findElement(By.xpath(String.format(_clientCheckBox,client,contact)));
    }
    //Methods
    public boolean checkTitleMatchesKeywordEntered(String title) {
        for (int i = 0; i < bannersClients(title).size(); i++) {
            info(getText(bannersClients(title).get(i))+" "+title);
            if (!getText(bannersClients(title).get(i)).contains(title)) return false;
        }
        return true;
    }


    public void clickClientCheckBox(String client,String contact){
        clickElement(clientCheckBox(client,contact));
    }

    public String getUrlHelpPage(){
        switchWindow();
        return getDriver().getCurrentUrl();
    }


}
