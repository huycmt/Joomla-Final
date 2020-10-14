package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobject.NewBannersBannersPage;
import pageobject.NewBannersCategoriesPage;
import pageobject.NewBannersClientsPage;

import  static helpers.DataHelper.*;
import static pageobject.BasePage.Tab.*;
import static utilities.Constant.*;
import static utilities.Log.info;

public class BannersBannersTest extends BaseTest {
    NewBannersBannersPage newBannersBannersPage = new NewBannersBannersPage();
    NewBannersCategoriesPage newBannersCategoriesPage = new NewBannersCategoriesPage();
    NewBannersClientsPage newBannersClientsPage = new NewBannersClientsPage();

    String nameClient = "1+"+ name();
    String nameBanner = "1+"+ name();
    String titleCategories = name();
    String email ="1+"+ email();

    @BeforeMethod(description = "User can create a new client with valid information\n" +
            "User can create a new category with valid information\n" +
            "User can create a new banner with valid information")
    public void TO_JOOMLA_BANNERS_001(){
        info("[STEP 2 - 3]");
        newBannersBannersPage.login(USERNAME,PASSWORD);

        info("[STEP 4]");
        newBannersBannersPage.clickTab(COMPONENTS);
        newBannersBannersPage.clickTab(BANNERS);
        newBannersBannersPage.clickTab(BANNERS_CLIENTS);

        info("[STEP 5]");
        newBannersBannersPage.clickNewBtn();

        info("[STEP 6 - 9]");
        newBannersClientsPage.createNewBannersClients(nameClient,nameClient,email,PUBLISHED);

        info("[STEP 10]\nA message : 'Client successfully saved' shows and new client is created");
        Assert.assertEquals(newBannersClientsPage.getMessage(),SAVED_BANNERS_CLIENTS_MESSAGE,"Message displayed incorrectly");
        Assert.assertTrue(newBannersClientsPage.checkTitleMatchesKeywordEntered(nameClient),"Clients is not displayed");

        info("[STEP 11]");
        newBannersBannersPage.clickTab(COMPONENTS);
        newBannersBannersPage.clickTab(BANNERS);
        newBannersBannersPage.clickTab(BANNERS_CATEGORIES);

        info("[STEP 12]");
        newBannersBannersPage.clickNewBtn();

        info("[STEP 13 - 14]");
        newBannersCategoriesPage.createNewBannersCategories(titleCategories,PUBLISHED);

        info("[STEP 15\nA message : 'Category successfully saved' shows and new category is created");
        Assert.assertEquals(newBannersCategoriesPage.getMessage(),SAVED_BANNERS_CATEGORIES_MESSAGE,"Message displayed incorrectly");
        Assert.assertTrue(newBannersCategoriesPage.checkTitleMatchesKeywordEntered(titleCategories),"Category is not displayed");


        info("[STEP 16]");
        newBannersBannersPage.clickTab(COMPONENTS);
        newBannersBannersPage.clickTab(BANNERS);

        info("[STEP 17]");
        newBannersBannersPage.clickNewBtn();

        info("[STEP 18 - 21]");
        newBannersBannersPage.createNewBannersBanners(nameBanner,nameClient,titleCategories);

        info("[STEP 22\nA message : 'Banner successfully saved' shows and 'Edit Banner' page displays");
        Assert.assertEquals(newBannersCategoriesPage.getMessage(),SAVED_BANNERS_BANNERS_MESSAGE,"Message displayed incorrectly");
        Assert.assertTrue(newBannersCategoriesPage.checkTitleMatchesKeywordEntered(nameBanner),"Category is not displayed");

    }
    @Test(testName = "TC_JOOMLA_BANNERS_BANNERS_002", description = "Verify that user can edit a banner")
    public void TC_JOOMLA_BANNERS_BANNERS_002(){
        info("[STEP 23]");
        newBannersBannersPage.clickTab(COMPONENTS);
        newBannersBannersPage.clickTab(BANNERS);

        info("[STEP 24]");
        newBannersBannersPage.clickBannersBanners(nameBanner);

        info("[STEP 25]");
        String newNameBanner = name();
        newBannersBannersPage.createNewBannersBanners(newNameBanner,nameClient,titleCategories);

        info("[STEP 26\nA message : 'Banner successfully saved' shows and banner is edited");
        Assert.assertEquals(newBannersCategoriesPage.getMessage(),SAVED_BANNERS_BANNERS_MESSAGE,"Message displayed incorrectly");
        Assert.assertTrue(newBannersCategoriesPage.checkTitleMatchesKeywordEntered(newNameBanner),"Category is not displayed");

    }
}
