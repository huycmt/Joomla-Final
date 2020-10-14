package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.DriverHelper.getDriver;

public class BannersClientsManagerPage extends ManagerPage {
    //Locator
    private  String _bannersClients = "//table//td[count(//th[contains(.,'Client')]/preceding-sibling::th)+1]//a[contains(.,'%s')]";


    //Element
    private List<WebElement> bannersClients(String title){return getDriver().findElements(By.xpath(String.format(_bannersClients,title)));}

    //Methods
    public boolean checkTitleMatchesKeywordEntered(String title) {
        for (int i = 0; i < bannersClients(title).size(); i++) {
            if (!getText(bannersClients(title).get(i)).contains(title)) return false;
        }
        return true;
    }


}
