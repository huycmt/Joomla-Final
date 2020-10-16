package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.object.LoginPage;
import page.object.banner.page.BannersBannersManagerPage;
import page.object.banner.page.BannersCategoriesManagerPage;
import page.object.banner.page.BannersClientsManagerPage;
import page.object.banner.page.NewBannersBannersPage;
import page.object.banner.page.NewBannersCategoriesPage;
import page.object.banner.page.NewBannersClientsPage;

import static helpers.DataHelper.email;
import static helpers.DataHelper.randomString;
import static page.object.BasePage.Tab.BANNERS;
import static page.object.BasePage.Tab.BANNERS_CATEGORIES;
import static page.object.BasePage.Tab.BANNERS_CLIENTS;
import static page.object.BasePage.Tab.COMPONENTS;
import static utilities.Constant.PASSWORD;
import static utilities.Constant.PUBLISHED;
import static utilities.Constant.SAVED_BANNERS_BANNERS_MESSAGE;
import static utilities.Constant.SAVED_BANNERS_CLIENTS_MESSAGE;
import static utilities.Constant.SAVED_CATEGORIES_MESSAGE;
import static utilities.Constant.USERNAME;
import static utilities.Log.info;
import static utilities.Log.startLog;

public class BannersBannersTest extends BaseTest {
    BannersBannersManagerPage bannersBannersManagerPage = new BannersBannersManagerPage();
    BannersCategoriesManagerPage bannersCategoriesManagerPage = new BannersCategoriesManagerPage();
    BannersClientsManagerPage bannersClientsManagerPage = new BannersClientsManagerPage();
    NewBannersBannersPage newBannersBannersPage = new NewBannersBannersPage();
    NewBannersCategoriesPage newBannersCategoriesPage = new NewBannersCategoriesPage();
    NewBannersClientsPage newBannersClientsPage = new NewBannersClientsPage();
    LoginPage loginPage = new LoginPage();

    String clientName = randomString();
    String categoryTitle = randomString();
    String bannerName = randomString();
    String email = email();

    @BeforeMethod(description = "User can create a new client with valid information\n" +
            "User can create a new category with valid information\n" +
            "User can create a new banner with valid information")
    public void TO_JOOMLA_BANNERS_001() {
        startLog("TO_JOOMLA_BANNERS_001");

        info("[STEP 2 - 3]");
        loginPage.login(USERNAME, PASSWORD);

        info("[STEP 4]");
        bannersClientsManagerPage.clickTab(COMPONENTS);
        bannersClientsManagerPage.clickTab(BANNERS);
        bannersClientsManagerPage.clickTab(BANNERS_CLIENTS);

        info("[STEP 5]");
        bannersClientsManagerPage.clickNewBtn();

        info("[STEP 6 - 9]");
        newBannersClientsPage.createNewBannersClients(clientName, clientName, email, PUBLISHED);

        info("[STEP 10]\nA message : 'Client successfully saved' shows and new client is created");
        Assert.assertEquals(bannersClientsManagerPage.getMessage(), SAVED_BANNERS_CLIENTS_MESSAGE, "Message displayed incorrectly");

        bannersClientsManagerPage.sortByIdDescending();
        Assert.assertTrue(bannersClientsManagerPage.isTheClientDisplayed(clientName), "Clients is not displayed");

        info("[STEP 11]");
        bannersCategoriesManagerPage.clickTab(COMPONENTS);
        bannersCategoriesManagerPage.clickTab(BANNERS);
        bannersCategoriesManagerPage.clickTab(BANNERS_CATEGORIES);

        info("[STEP 12]");
        bannersCategoriesManagerPage.clickNewBtn();

        info("[STEP 13 - 14]");
        String categoryAlias = randomString();
        newBannersCategoriesPage.createNewBannersCategories(categoryTitle, categoryAlias, PUBLISHED);

        info("[STEP 15\nA message : 'Category successfully saved' shows and new category is created");
        Assert.assertEquals(bannersCategoriesManagerPage.getMessage(), SAVED_CATEGORIES_MESSAGE, "Message displayed incorrectly");

        bannersCategoriesManagerPage.sortByIdDescending();
        Assert.assertTrue(bannersCategoriesManagerPage.isTheCategoryDisplayed(categoryTitle), "Category is not displayed");

        info("[STEP 16]");
        bannersBannersManagerPage.clickTab(COMPONENTS);
        bannersBannersManagerPage.clickTab(BANNERS);

        info("[STEP 17]");
        bannersBannersManagerPage.clickNewBtn();

        info("[STEP 18 - 21]");
        newBannersBannersPage.createNewBannersBanners(bannerName, clientName, categoryTitle);

        info("[STEP 22\nA message : 'Banner successfully saved' shows and 'Edit Banner' page displays");
        Assert.assertEquals(bannersBannersManagerPage.getMessage(), SAVED_BANNERS_BANNERS_MESSAGE, "Message displayed incorrectly");

        bannersBannersManagerPage.sortByIdDescending();
        Assert.assertTrue(bannersBannersManagerPage.isTheBannerDisplayed(bannerName), "Banner is not displayed");
    }

    @Test(testName = "TC_JOOMLA_BANNERS_BANNERS_002", description = "Verify that user can edit a banner")
    public void TC_JOOMLA_BANNERS_BANNERS_002() {
        startLog("TC_JOOMLA_BANNERS_BANNERS_002");

        info("[STEP 23]");
        newBannersBannersPage.clickTab(COMPONENTS);
        newBannersBannersPage.clickTab(BANNERS);

        info("[STEP 24]");
        bannersBannersManagerPage.clickBannersBanners(bannerName);

        info("[STEP 25]");
        String newNameBanner = randomString();
        newBannersBannersPage.createNewBannersBanners(newNameBanner, clientName, categoryTitle);

        info("[STEP 26\nA message : 'Banner successfully saved' shows and banner is edited");
        Assert.assertEquals(bannersBannersManagerPage.getMessage(), SAVED_BANNERS_BANNERS_MESSAGE, "Message displayed incorrectly");

        bannersBannersManagerPage.sortByIdDescending();
        Assert.assertTrue(bannersBannersManagerPage.isTheBannerDisplayed(newNameBanner), "Banner is not displayed");
    }

    @Test(testName = "TC_JOOMLA_BANNERS_BANNERS_009", description = "Verify that user can search a banner by using filter dropdown lists")
    public void TC_JOOMLA_BANNERS_BANNERS_009() {
        startLog("TC_JOOMLA_BANNERS_BANNERS_009");

        info("[STEP 23]");
        newBannersBannersPage.clickTab(COMPONENTS);
        newBannersBannersPage.clickTab(BANNERS);

        info("[STEP 24]");
        bannersBannersManagerPage.searchBannersByClientAndCategory(clientName, categoryTitle);

        info("[STEP 25\nRecently created banner displays");
        bannersBannersManagerPage.sortByIdDescending();
        Assert.assertTrue(bannersBannersManagerPage.isTheBannerDisplayed(bannerName), "Banner is not displayed");

    }

    @Test(testName = "TC_JOOMLA_BANNERS_BANNERS_016", description = "Verify that user can sort items displayed in banner table by ID")
    public void TC_JOOMLA_BANNERS_BANNERS_016() {
        startLog("TC_JOOMLA_BANNERS_BANNERS_016");

        info("[STEP 23]");
        newBannersBannersPage.clickTab(COMPONENTS);
        newBannersBannersPage.clickTab(BANNERS);

        info("[STEP 24]");
        bannersBannersManagerPage.clickIdLink();

        info("[STEP 25\nItems are sorted ascending by ID in banner table");
        Assert.assertTrue(bannersBannersManagerPage.isIdSortByAscending(), "Items sorted incorrectly");

        info("[STEP 27]");
        bannersBannersManagerPage.clickIdLink();

        info("[STEP 28\nItems are sorted descending by ID in banner table");
        Assert.assertTrue(bannersBannersManagerPage.isIdSortByDescending(), "Items sorted incorrectly");
    }
}
