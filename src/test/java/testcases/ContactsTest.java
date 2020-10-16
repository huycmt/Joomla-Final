package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.object.LoginPage;
import page.object.contact.page.ContactManagerPage;
import page.object.contact.page.NewContactPage;

import static helpers.DataHelper.randomString;
import static page.object.BasePage.Tab.COMPONENTS;
import static page.object.BasePage.Tab.CONTACTS;
import static utilities.Constant.CONTACT_CATEGORY;
import static utilities.Constant.PASSWORD;
import static utilities.Constant.PUBLISHED;
import static utilities.Constant.SAVED_CONTACT_MESSAGE;
import static utilities.Constant.USERNAME;
import static utilities.Log.info;
import static utilities.Log.startLog;

public class ContactsTest extends BaseTest {
    ContactManagerPage contactManagerPage = new ContactManagerPage();
    NewContactPage newContactPage = new NewContactPage();
    LoginPage loginPage = new LoginPage();
    String name = randomString();

    @BeforeMethod(description = "User can create new contact with valid information")
    public void TO_JOOMLA_CONTACTS_001() {
        startLog("TO_JOOMLA_CONTACTS_001");

        info("[STEP 2 - 4]");
        loginPage.login(USERNAME, PASSWORD);

        info("[STEP 5]");
        contactManagerPage.clickTab(COMPONENTS);
        contactManagerPage.clickTab(CONTACTS);

        info("[STEP 6]");
        contactManagerPage.clickNewBtn();

        info("[STEP 7 - 10]");
        newContactPage.createNewContact(name, CONTACT_CATEGORY.get(1), PUBLISHED);


        info("[STEP 11]\nVerify the contact is saved successfully");
        Assert.assertEquals(contactManagerPage.getMessage(), SAVED_CONTACT_MESSAGE, "Message displayed incorrectly");

        contactManagerPage.sortByIdDescending();
        Assert.assertTrue(contactManagerPage.isContactDisplayed(name), "Contact is not displayed");
    }

    @Test(testName = "TO_JOOMLA_CONTACTS_002", description = "User can edit a contact")
    public void TO_JOOMLA_CONTACTS_002() {
        startLog("TO_JOOMLA_CONTACTS_002");

        info("[STEP 12]");
        contactManagerPage.clickTab(COMPONENTS);
        contactManagerPage.clickTab(CONTACTS);

        info("[STEP 13]");
        contactManagerPage.clickContactCheckBox(name);

        info("[STEP 14]");
        contactManagerPage.clickEditBtn();

        info("[STEP 15 - 17]");
        String newName = randomString();
        newContactPage.createNewContact(newName, CONTACT_CATEGORY.get(0), PUBLISHED);

        info("[STEP 18]\nVerify the contact is saved successfully");
        Assert.assertEquals(contactManagerPage.getMessage(), SAVED_CONTACT_MESSAGE, "Message displayed incorrectly");

        contactManagerPage.sortByIdDescending();
        Assert.assertTrue(contactManagerPage.isContactDisplayed(newName), "Contact is not displayed");
    }

    @Test(testName = "TO_JOOMLA_CONTACTS_009", description = "User can search for contacts using the filter text field")
    public void TO_JOOMLA_CONTACTS_009() {
        startLog("TO_JOOMLA_CONTACTS_009");

        info("[STEP 12 - 13]");
        contactManagerPage.findByTitle(name);

        info("[STEP 14]\nVerify user can search for contacts using the filter text field");
        Assert.assertTrue(contactManagerPage.isTitleMatchesKeywordEntered(name), "The titles of displayed contacts are not matched with the entered keyword");
    }
}
