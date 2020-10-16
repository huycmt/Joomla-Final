package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.object.LoginPage;
import page.object.banner.page.BannersClientsManagerPage;
import page.object.banner.page.NewBannersClientsPage;

import static helpers.DataHelper.email;
import static helpers.DataHelper.randomString;
import static helpers.DriverHelper.getNewWindowUrl;
import static page.object.BasePage.Tab.BANNERS;
import static page.object.BasePage.Tab.BANNERS_CLIENTS;
import static page.object.BasePage.Tab.COMPONENTS;
import static utilities.Constant.BANNERS_CLIENTS_HELP_URL;
import static utilities.Constant.CLIENT_ARCHIVED_MESSAGE;
import static utilities.Constant.PASSWORD;
import static utilities.Constant.PUBLISHED;
import static utilities.Constant.SAVED_BANNERS_CLIENTS_MESSAGE;
import static utilities.Constant.USERNAME;
import static utilities.Log.info;
import static utilities.Log.startLog;

public class BannersClientsTest extends BaseTest {
    BannersClientsManagerPage bannersClientsManagerPage = new BannersClientsManagerPage();
    NewBannersClientsPage newBannersClientsPage = new NewBannersClientsPage();
    LoginPage loginPage = new LoginPage();

    String clientName = randomString();
    String contactName = randomString();
    String email = email();

    @BeforeMethod(description = "User can create a new client with valid information")
    public void TO_JOOMLA_BANNERS_CLIENTS_001() {
        startLog("TO_JOOMLA_BANNERS_CLIENTS_001");

        info("[STEP 2 - 3]");
        loginPage.login(USERNAME, PASSWORD);

        info("[STEP 4]");
        bannersClientsManagerPage.clickTab(COMPONENTS);
        bannersClientsManagerPage.clickTab(BANNERS);
        bannersClientsManagerPage.clickTab(BANNERS_CLIENTS);

        info("[STEP 5]");
        bannersClientsManagerPage.clickNewBtn();
    }

    @Test(testName = "TC_JOOMLA_BANNERS_CLIENTS_005", description = "Verify that user can archive a client")
    public void TC_JOOMLA_BANNERS_CLIENTS_005() {
        startLog("TO_JOOMLA_BANNERS_CLIENTS_005");

        info("[STEP 6 - 9]");
        newBannersClientsPage.createNewBannersClients(clientName, contactName, email, PUBLISHED);

        info("[STEP 10]\nA message : 'Client successfully saved' shows and new client is created");
        Assert.assertEquals(bannersClientsManagerPage.getMessage(), SAVED_BANNERS_CLIENTS_MESSAGE, "Message displayed incorrectly");

        bannersClientsManagerPage.sortByIdDescending();
        Assert.assertTrue(bannersClientsManagerPage.isTheClientDisplayed(clientName), "Clients is not displayed");

        info("[STEP 11]");
        bannersClientsManagerPage.clickClientCheckBox(clientName, contactName);

        info("[STEP 12]");
        bannersClientsManagerPage.clickArchiveBtn();

        info("[STEP 13]\nA message : '1 client successfully archived' shows");
        Assert.assertEquals(bannersClientsManagerPage.getMessage(), CLIENT_ARCHIVED_MESSAGE, "Message displayed incorrectly");

        info("[STEP 14]");
        bannersClientsManagerPage.clickSearchToolsBtn();
        bannersClientsManagerPage.selectStatusInSearchTool("Archived");

        info("[STEP 15]\nClient is archived");
        Assert.assertTrue(bannersClientsManagerPage.isTheClientDisplayed(clientName), "Clients is not displayed");
    }

    @Test(testName = "TO_JOOMLA_BANNERS_CLIENTS_012", description = "Verify that user can browse 'New client help' page")
    public void TC_JOOMLA_BANNERS_CLIENTS_012() {
        startLog("TO_JOOMLA_BANNERS_CLIENTS_012");

        info("[STEP 6]");
        bannersClientsManagerPage.clickHelpBtn();

        info("[STEP 7]");
        Assert.assertEquals(getNewWindowUrl(), BANNERS_CLIENTS_HELP_URL, "Url is not correct");
    }
}
