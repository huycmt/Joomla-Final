package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.object.LoginPage;
import page.object.weblink.page.NewWebLinksPage;
import page.object.weblink.page.WebLinksManagerPage;

import static helpers.DataHelper.randomString;
import static helpers.DataHelper.url;
import static page.object.BasePage.Tab.COMPONENTS;
import static page.object.BasePage.Tab.WEB_LINKS;
import static utilities.Constant.PASSWORD;
import static utilities.Constant.PUBLISHED;
import static utilities.Constant.SAVED_WEB_LINKS_MESSAGE;
import static utilities.Constant.UNPUBLISHED;
import static utilities.Constant.USERNAME;
import static utilities.Constant.WEB_LINKS_PUBLISHED_MESSAGE;
import static utilities.Log.info;
import static utilities.Log.startLog;

public class WebLinksTest extends BaseTest {
    WebLinksManagerPage webLinksManagerPage = new WebLinksManagerPage();
    NewWebLinksPage newWebLinksPage = new NewWebLinksPage();
    LoginPage loginPage = new LoginPage();
    String url = url();

    @BeforeMethod(description = "User can create new web link with valid information")
    public void TO_JOOMLA_WEBLINKS_001() {
        startLog("TO_JOOMLA_WEBLINKS_001");

        info("[STEP 2 - 4]");
        loginPage.login(USERNAME, PASSWORD);

        info("[STEP 5]");
        webLinksManagerPage.clickTab(COMPONENTS);
        webLinksManagerPage.clickTab(WEB_LINKS);

        info("[STEP 6]");
        webLinksManagerPage.clickNewBtn();
    }

    @Test(testName = "TO_JOOMLA_WEBLINKS_003", description = "Verify user can publish an unpublished web link")
    public void TC_JOOMLA_WEBLINKS_003() {
        startLog("TO_JOOMLA_WEBLINKS_003");

        String title = randomString();
        String alias = randomString();

        info("[STEP 7 - 10]");
        newWebLinksPage.createNewWebLinks(title, alias, url, "Uncategorised", UNPUBLISHED);

        info("[STEP 11]\nVerify the web link is saved successfully");
        Assert.assertEquals(webLinksManagerPage.getMessage(), SAVED_WEB_LINKS_MESSAGE, "Message displayed incorrectly");

        webLinksManagerPage.sortByIdDescending();
        Assert.assertTrue(webLinksManagerPage.isWebLinksDisplayed(title, alias), "Web links are not displayed");

        info("[STEP 12]");
        webLinksManagerPage.clickWebLinksCheckBox(title, alias);

        info("[STEP 13]");
        webLinksManagerPage.clickPublishBtn();

        info("[STEP 14]\nVerify the web link is published successfully");
        Assert.assertEquals(webLinksManagerPage.getMessage(), WEB_LINKS_PUBLISHED_MESSAGE, "Message displayed incorrectly");

        webLinksManagerPage.sortByIdDescending();
        Assert.assertTrue(webLinksManagerPage.isIconDisplayedAsPublished(), "Web links are not displayed");
    }

    @Test(testName = "TO_JOOMLA_WEBLINKS_010", description = "Verify user can search for weblinks using the filter dropdown lists")
    public void TC_JOOMLA_WEBLINKS_010() {
        startLog("TO_JOOMLA_WEBLINKS_010");

        String title = randomString();
        String alias = randomString();

        info("[STEP 7 - 10]");
        newWebLinksPage.createNewWebLinks(title, alias, url, "Uncategorised", UNPUBLISHED);

        info("[STEP 11]\nVerify the web link is saved successfully");
        Assert.assertEquals(webLinksManagerPage.getMessage(), SAVED_WEB_LINKS_MESSAGE, "Message displayed incorrectly");

        webLinksManagerPage.sortByIdDescending();
        Assert.assertTrue(webLinksManagerPage.isWebLinksDisplayed(title, alias), "Web links are not displayed");
        info("[STEP 12]");
        webLinksManagerPage.clickSearchToolsBtn();

        info("[STEP 13]");
        webLinksManagerPage.selectCategoryInSearchTool("Uncategorised");

        info("[STEP 14]");
        webLinksManagerPage.selectStatusInSearchTool2(PUBLISHED);

        info("[STEP 15]\nVerify the property of displayed weblinks are matched with the selected criteria from the filter dropdown lists");
        Assert.assertTrue(webLinksManagerPage.isCategoriesDisplayedCorrectly("Uncategorised"), "Categories displays incorrectly");
        Assert.assertTrue(webLinksManagerPage.isIconDisplayedAsPublished(), "Attribute displayed incorrectly");
    }

}

