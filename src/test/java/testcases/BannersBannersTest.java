package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.object.BannersBannersManagerPage;
import page.object.BannersCategoriesManagerPage;
import page.object.BannersClientsManagerPage;
import page.object.NewBannersBannersPage;
import page.object.NewBannersCategoriesPage;
import page.object.NewBannersClientsPage;

import  static helpers.DataHelper.*;
import static page.object.BasePage.Tab.*;
import static utilities.Constant.*;
import static utilities.Log.info;

public class BannersBannersTest extends BaseTest {
    BannersBannersManagerPage bannersBannersManagerPage = new BannersBannersManagerPage();
    BannersCategoriesManagerPage bannersCategoriesManagerPage = new BannersCategoriesManagerPage();
    BannersClientsManagerPage bannersClientsManagerPage = new BannersClientsManagerPage();
    NewBannersBannersPage newBannersBannersPage = new NewBannersBannersPage();
    NewBannersCategoriesPage newBannersCategoriesPage = new NewBannersCategoriesPage();
    NewBannersClientsPage newBannersClientsPage = new NewBannersClientsPage();

    String clientName = name();
    String categoryTitle = name();
    String bannerName = name();
    String email = email();

    @BeforeMethod(description = "User can create a new client with valid information\n" +
            "User can create a new category with valid information\n" +
            "User can create a new banner with valid information")
    public void TO_JOOMLA_BANNERS_001(){
        info("[STEP 2 - 3]");
        bannersBannersManagerPage.login(USERNAME,PASSWORD);

        info("[STEP 4]");
        bannersClientsManagerPage.clickTab(COMPONENTS);
        bannersClientsManagerPage.clickTab(BANNERS);
        bannersClientsManagerPage.clickTab(BANNERS_CLIENTS);

        info("[STEP 5]");
        bannersClientsManagerPage.clickNewBtn();

        info("[STEP 6 - 9]");
        newBannersClientsPage.createNewBannersClients(clientName, clientName,email,PUBLISHED);

        info("[STEP 10]\nA message : 'Client successfully saved' shows and new client is created");
        Assert.assertEquals(bannersClientsManagerPage.getMessage(),SAVED_BANNERS_CLIENTS_MESSAGE,"Message displayed incorrectly");

        bannersClientsManagerPage.selectSortItem();
        Assert.assertTrue(bannersClientsManagerPage.checkTitleMatchesKeywordEntered(clientName),"Clients is not displayed");

        info("[STEP 11]");
        bannersCategoriesManagerPage.clickTab(COMPONENTS);
        bannersCategoriesManagerPage.clickTab(BANNERS);
        bannersCategoriesManagerPage.clickTab(BANNERS_CATEGORIES);

        info("[STEP 12]");
        bannersCategoriesManagerPage.clickNewBtn();

        info("[STEP 13 - 14]");
        String categoryAlias = name();
        newBannersCategoriesPage.createNewBannersCategories(categoryTitle,categoryAlias,PUBLISHED);

        info("[STEP 15\nA message : 'Category successfully saved' shows and new category is created");
        Assert.assertEquals(bannersCategoriesManagerPage.getMessage(),SAVED_CATEGORIES_MESSAGE,"Message displayed incorrectly");

        bannersCategoriesManagerPage.selectSortItem();
        Assert.assertTrue(bannersCategoriesManagerPage.checkTitleMatchesKeywordEntered(categoryTitle),"Category is not displayed");


        info("[STEP 16]");
        bannersBannersManagerPage.clickTab(COMPONENTS);
        bannersBannersManagerPage.clickTab(BANNERS);

        info("[STEP 17]");
        bannersBannersManagerPage.clickNewBtn();

        info("[STEP 18 - 21]");
        newBannersBannersPage.createNewBannersBanners(bannerName, clientName, categoryTitle);

        info("[STEP 22\nA message : 'Banner successfully saved' shows and 'Edit Banner' page displays");
        Assert.assertEquals(bannersBannersManagerPage.getMessage(),SAVED_BANNERS_BANNERS_MESSAGE,"Message displayed incorrectly");

        bannersBannersManagerPage.selectSortItem();
        Assert.assertTrue(bannersBannersManagerPage.checkTitleMatchesKeywordEntered(bannerName),"Banner is not displayed");

    }
    @Test(testName = "TC_JOOMLA_BANNERS_BANNERS_002", description = "Verify that user can edit a banner")
    public void TC_JOOMLA_BANNERS_BANNERS_002(){
        info("[STEP 23]");
        newBannersBannersPage.clickTab(COMPONENTS);
        newBannersBannersPage.clickTab(BANNERS);

        info("[STEP 24]");
        bannersBannersManagerPage.clickBannersBanners(bannerName);

        info("[STEP 25]");
        String newNameBanner = name();
        newBannersBannersPage.createNewBannersBanners(newNameBanner, clientName, categoryTitle);

        info("[STEP 26\nA message : 'Banner successfully saved' shows and banner is edited");
        Assert.assertEquals(bannersBannersManagerPage.getMessage(),SAVED_BANNERS_BANNERS_MESSAGE,"Message displayed incorrectly");

        bannersBannersManagerPage.selectSortItem();
        Assert.assertTrue(bannersBannersManagerPage.checkTitleMatchesKeywordEntered(newNameBanner),"Banner is not displayed");

    }

    @Test(testName = "TC_JOOMLA_BANNERS_BANNERS_009", description = "Verify that user can search a banner by using filter dropdown lists")
    public void TC_JOOMLA_BANNERS_BANNERS_009(){
        info("[STEP 23]");
        newBannersBannersPage.clickTab(COMPONENTS);
        newBannersBannersPage.clickTab(BANNERS);

        info("[STEP 24]");
        bannersBannersManagerPage.searchBannersByClientAndCategory(clientName, categoryTitle);

        info("[STEP 25\nRecently created banner displays");

        bannersBannersManagerPage.selectSortItem();
        Assert.assertTrue(bannersBannersManagerPage.checkTitleMatchesKeywordEntered(bannerName),"Banner is not displayed");

    }

    @Test(testName = "TC_JOOMLA_BANNERS_BANNERS_016", description = "Verify that user can sort items displayed in banner table by ID")
    public void TC_JOOMLA_BANNERS_BANNERS_016(){
        info("[STEP 23]");
        newBannersBannersPage.clickTab(COMPONENTS);
        newBannersBannersPage.clickTab(BANNERS);

        info("[STEP 24]");
        bannersBannersManagerPage.clickIdLink();

        info("[STEP 25\nItems are sorted ascending by ID in banner table");
        Assert.assertTrue(bannersBannersManagerPage.checkIdAscending(),"Items sorted incorrectly");

        info("[STEP 27]");
        bannersBannersManagerPage.clickIdLink();

        info("[STEP 28\nItems are sorted descending by ID in banner table");
        Assert.assertTrue(bannersBannersManagerPage.checkIdDescending(),"Items sorted incorrectly");

    }
}
