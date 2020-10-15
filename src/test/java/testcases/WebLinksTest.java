package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.object.NewWebLinksPage;
import page.object.WebLinksManagerPage;

import static helpers.DataHelper.*;
import static page.object.BasePage.Tab.*;
import static utilities.Constant.*;
import static utilities.Log.info;

public class WebLinksTest extends BaseTest {
    WebLinksManagerPage webLinksManagerPage = new WebLinksManagerPage();
    NewWebLinksPage  newWebLinksPage = new NewWebLinksPage();
    String title = title();
    String title2 = title();
    String alias = word();
    String alias2 = word();
    String url = url();



    @BeforeMethod(description = "User can create new web link with valid information")
    public void TO_JOOMLA_WEBLINKS_001(){
        info("[STEP 2 - 4]");
        webLinksManagerPage.login(USERNAME,PASSWORD);

        info("[STEP 5]");
        webLinksManagerPage.clickTab(COMPONENTS);
        webLinksManagerPage.clickTab(WEB_LINKS);

        info("[STEP 6]");
        webLinksManagerPage.clickNewBtn();

    }
    @Test(testName = "TO_JOOMLA_WEBLINKS_003", description = "Verify user can publish an unpublished web link")
    public void TC_JOOMLA_WEBLINKS_003(){

        info("[STEP 7 - 10]");
        newWebLinksPage.createNewWebLinks(title,alias,url,"Uncategorised",UNPUBLISHED);

        info("[STEP 11]\nVerify the web link is saved successfully");
        Assert.assertEquals(webLinksManagerPage.getMessage(),SAVED_WEB_LINKS_MESSAGE,"Message displayed incorrectly");

        webLinksManagerPage.selectSortItem();
        Assert.assertTrue(webLinksManagerPage.isWebLinksDisplayed(title,alias),"Web links are not displayed");

        info("[STEP 12]");
        webLinksManagerPage.clickWebLinksCheckBox(title,alias);

        info("[STEP 13]");
        webLinksManagerPage.clickPublishBtn();

        info("[STEP 14]\nVerify the web link is published successfully");
        Assert.assertEquals(webLinksManagerPage.getMessage(),WEB_LINKS_PUBLISHED_MESSAGE,"Message displayed incorrectly");

        webLinksManagerPage.selectSortItem();
        Assert.assertTrue(webLinksManagerPage.isAttributeDisplayedCorrectly(title,alias),"Web links are not displayed");
    }
    @Test(testName = "TO_JOOMLA_WEBLINKS_010", description = "Verify user can search for weblinks using the filter dropdown lists")
    public void TC_JOOMLA_WEBLINKS_010(){

        info("[STEP 7 - 10]");
        newWebLinksPage.createNewWebLinks(title2,alias2,url,"Uncategorised",UNPUBLISHED);

        info("[STEP 11]\nVerify the web link is saved successfully");
        Assert.assertEquals(webLinksManagerPage.getMessage(),SAVED_WEB_LINKS_MESSAGE,"Message displayed incorrectly");

        webLinksManagerPage.selectSortItem();
        Assert.assertTrue(webLinksManagerPage.isWebLinksDisplayed(title2,alias2),"Web links are not displayed");
        info("[STEP 12]");
        webLinksManagerPage.clickSearchToolsBtn();

        info("[STEP 13]");
        webLinksManagerPage.clickCategoryItemInSearchTool("Uncategorised");

        info("[STEP 14]");
        webLinksManagerPage.clickStatusItemInSearchTool2(PUBLISHED);

        info("[STEP 15]\nVerify the property of displayed weblinks are matched with the selected criteria from the filter dropdown lists");
        Assert.assertTrue(webLinksManagerPage.isCategoriesDisplayedCorrectly(title2,alias2,"Uncategorised"),"Categories displays incorrectly");
        Assert.assertTrue(webLinksManagerPage.isAttributeDisplayedCorrectly(title2,alias2),"Attribute displayed incorrectly");
    }

    }

