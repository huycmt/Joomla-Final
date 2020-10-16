package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.object.LoginPage;
import page.object.category.page.CategoriesManagerPage;
import page.object.category.page.NewCategoriesPage;

import static helpers.DataHelper.randomString;
import static page.object.BasePage.Tab.CATEGORIES;
import static page.object.BasePage.Tab.CONTENT;
import static utilities.Constant.CATEGORY_PUBLISHED_MESSAGE;
import static utilities.Constant.PASSWORD;
import static utilities.Constant.PUBLISHED;
import static utilities.Constant.SAVED_CATEGORIES_MESSAGE;
import static utilities.Constant.UNPUBLISHED;
import static utilities.Constant.USERNAME;
import static utilities.Log.info;
import static utilities.Log.startLog;

public class CategoriesTest extends BaseTest {
    CategoriesManagerPage categoriesManagerPage = new CategoriesManagerPage();
    NewCategoriesPage newCategoriesPage = new NewCategoriesPage();
    LoginPage loginPage = new LoginPage();

    @BeforeMethod(description = "User can create a new category with valid information")
    public void TO_JOOMLA_CATEGORY_MANAGER_001() {
        startLog("TO_JOOMLA_CATEGORY_MANAGER_001");

        info("[STEP 2 - 3]");
        loginPage.login(USERNAME, PASSWORD);

        info("[STEP 4]");
        categoriesManagerPage.clickTab(CONTENT);
        categoriesManagerPage.clickTab(CATEGORIES);

        info("[STEP 5]");
        categoriesManagerPage.clickNewBtn();

    }

    @Test(testName = "TO_JOOMLA_CATEGORY_MANAGER_003", description = "Verify that user can publish a category")
    public void TO_JOOMLA_CATEGORY_MANAGER_003() {
        startLog("TO_JOOMLA_CATEGORY_MANAGER_003");

        String titleCategory = randomString();
        String aliasCategory = randomString();

        info("[STEP 6 - 7]");
        newCategoriesPage.fillDataInCategoryForm(titleCategory, aliasCategory, UNPUBLISHED);

        info("[STEP 8]");
        newCategoriesPage.clickSaveAndCloseBtn();

        info("[STEP 9]\nA message : 'Category successfully saved' shows and new category is created");
        Assert.assertEquals(categoriesManagerPage.getMessage(), SAVED_CATEGORIES_MESSAGE, "Message displayed incorrectly");

        categoriesManagerPage.sortByIdDescending();
        Assert.assertTrue(categoriesManagerPage.isTheCategoryDisplayed(titleCategory, aliasCategory), "Categories are not displayed");

        info("[STEP 10]");
        categoriesManagerPage.clickCategoryCheckBox(titleCategory, aliasCategory);

        info("[STEP 11]");
        categoriesManagerPage.clickPublishBtn();

        info("[STEP 12]]\nA message : '1 Category successfully saved' shows and Category is published");
        Assert.assertEquals(categoriesManagerPage.getMessage(), CATEGORY_PUBLISHED_MESSAGE, "Message displayed incorrectly");

        categoriesManagerPage.sortByIdDescending();
        Assert.assertTrue(categoriesManagerPage.isIconDisplayedAsPublished(titleCategory, aliasCategory));
    }

    @Test(testName = "TO_JOOMLA_CATEGORY_MANAGER_010", description = "Verify that user can create many categories by using 'Save & New' button")
    public void TO_JOOMLA_CATEGORY_MANAGER_010() {
        startLog("TO_JOOMLA_CATEGORY_MANAGER_010");

        String titleCategory = randomString();
        String aliasCategory = randomString();

        info("[STEP 6 - 7]");
        newCategoriesPage.fillDataInCategoryForm(titleCategory, aliasCategory, UNPUBLISHED);

        info("[STEP 8]");
        newCategoriesPage.clickSaveAndNewBtn();

        info("[STEP 9]\nA message : 'Category successfully saved' shows");
        Assert.assertEquals(categoriesManagerPage.getMessage(), SAVED_CATEGORIES_MESSAGE, "Message displayed incorrectly");

        info("[STEP 10]");
        String titleCategory2 = randomString();
        String aliasCategory2 = randomString();
        newCategoriesPage.fillDataInCategoryForm(titleCategory2, aliasCategory2, PUBLISHED);

        info("[STEP 11]");
        newCategoriesPage.clickSaveAndCloseBtn();

        info("[STEP 12]]\nA message : 'Category successfully saved' shows and two new categories are created");
        Assert.assertEquals(categoriesManagerPage.getMessage(), SAVED_CATEGORIES_MESSAGE, "Message displayed incorrectly");

        categoriesManagerPage.sortByIdDescending();
        Assert.assertTrue(categoriesManagerPage.isTheCategoryDisplayed(titleCategory, aliasCategory), "Categories are not displayed");
        Assert.assertTrue(categoriesManagerPage.isTheCategoryDisplayed(titleCategory2, aliasCategory2), "Categories are not displayed");
    }
}
