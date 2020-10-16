package page.object.banner.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

import page.object.common.page.ManagerCommonPage;

import static helpers.DriverHelper.getDriver;


public class BannersClientsManagerPage extends ManagerCommonPage {
    //Locator
    private String _bannersClients = "//table//td[count(//th[contains(.,'Client')]/preceding-sibling::th)+1]//a[contains(.,'%s')]";
    private String _clientCheckBox = "//table//td[count(//th[contains(.,'Client')]/preceding-sibling::th)+1][contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/..//input";

    //Element
    private WebElement bannersClients(String title) {
        return getDriver().findElement(By.xpath(String.format(_bannersClients, title)));
    }

    private WebElement clientCheckBox(String client, String contact) {
        return getDriver().findElement(By.xpath(String.format(_clientCheckBox, client, contact)));
    }

    //Methods
    public boolean isTheClientDisplayed(String client) {
        try {
            return bannersClients(client).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void clickClientCheckBox(String client, String contact) {
        clickElement(clientCheckBox(client, contact));
    }


}
