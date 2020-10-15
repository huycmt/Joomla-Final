package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.object.CategoriesManagerPage;
import page.object.NewCategoriesPage;
import static helpers.DataHelper.*;
import static page.object.BasePage.Tab.*;
import static utilities.Constant.*;
import static utilities.Log.info;

public class CategoriesTest extends BaseTest {
    CategoriesManagerPage categoriesManagerPage = new CategoriesManagerPage();
    NewCategoriesPage newCategoriesPage = new NewCategoriesPage();
    String titleCategory = title();
    String aliasCategory = word();
    String titleCategory1 = title();
    String aliasCategory1 = word();

    @BeforeMethod(description = "User can create a new category with valid information")
    public void TO_JOOMLA_CATEGORY_MANAGER_001(){
        info("[STEP 2 - 3]");
        categoriesManagerPage.login(USERNAME,PASSWORD);

        info("[STEP 4]");
        categoriesManagerPage.clickTab(CONTENT);
        categoriesManagerPage.clickTab(CATEGORIES);

        info("[STEP 5]");
        categoriesManagerPage.clickNewBtn();

    }
    @Test(testName = "TO_JOOMLA_CATEGORY_MANAGER_003", description = "Verify that user can publish a category")
    public void TO_JOOMLA_CATEGORY_MANAGER_003(){
        info("[STEP 6 - 7]");
        newCategoriesPage.createdNewCategory(titleCategory,aliasCategory,UNPUBLISHED);

        info("[STEP 8]");
        newCategoriesPage.clickSaveAndCloseBtn();

        info("[STEP 9]\nA message : 'Category successfully saved' shows and new category is created");
        Assert.assertEquals(categoriesManagerPage.getMessage(),SAVED_CATEGORIES_MESSAGE,"Message displayed incorrectly");

        categoriesManagerPage.selectSortItem();
        Assert.assertTrue(categoriesManagerPage.isCategoryDisplayed(titleCategory,aliasCategory),"Categories are not displayed");

        info("[STEP 10]");
        categoriesManagerPage.clickCategoryCheckBox(titleCategory,aliasCategory);

        info("[STEP 11]");
        categoriesManagerPage.clickPublishBtn();

        info("[STEP 12]]\nA message : '1 Category successfully saved' shows and Category is published");
        Assert.assertEquals(categoriesManagerPage.getMessage(),CATEGORY_PUBLISHED_MESSAGE,"Message displayed incorrectly");

        categoriesManagerPage.selectSortItem();
        Assert.assertTrue(categoriesManagerPage.isAttributeDisplayedCorrectly(titleCategory,aliasCategory));
    }
    @Test(testName = "TO_JOOMLA_CATEGORY_MANAGER_010", description = "Verify that user can create many categories by using \"Save & New\" button")
    public void TO_JOOMLA_CATEGORY_MANAGER_010(){
        info("[STEP 6 - 7]");
        newCategoriesPage.createdNewCategory(titleCategory1,aliasCategory1,UNPUBLISHED);

        info("[STEP 8]");
        newCategoriesPage.clickSaveAndNewBtn();

        info("[STEP 9]\nA message : 'Category successfully saved' shows");
        Assert.assertEquals(categoriesManagerPage.getMessage(),SAVED_CATEGORIES_MESSAGE,"Message displayed incorrectly");

        info("[STEP 10]");
        String titleCategory2 = title();
        String aliasCategory2 = word();
        newCategoriesPage.createdNewCategory(titleCategory2,aliasCategory2,PUBLISHED);

        info("[STEP 11]");
        newCategoriesPage.clickSaveAndCloseBtn();

        info("[STEP 12]]\nA message : 'Category successfully saved' shows and two new categories are created");
        Assert.assertEquals(categoriesManagerPage.getMessage(),SAVED_CATEGORIES_MESSAGE,"Message displayed incorrectly");

        categoriesManagerPage.selectSortItem();
        Assert.assertTrue(categoriesManagerPage.isCategoryDisplayed(titleCategory,aliasCategory),"Categories are not displayed");
        Assert.assertTrue(categoriesManagerPage.isCategoryDisplayed(titleCategory2,aliasCategory2),"Categories are not displayed");

    }
}
