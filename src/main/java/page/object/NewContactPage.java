package page.object;

public class NewContactPage extends NewPage {

    //Methods
    public void createNewContact(String name, String category, String status) {
        enterName(name);

        selectCategoryItem(category);
        selectStatusItem2(status);

        clickSaveAndCloseBtn();
    }



}
