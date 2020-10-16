package page.object.contact.page;

import page.object.common.page.NewCommonPage;

public class NewContactPage extends NewCommonPage {

    //Methods
    public void createNewContact(String name, String category, String status) {
        enterName(name);

        selectCategoryItem(category);
        selectStatusItem2(status);

        clickSaveAndCloseBtn();
    }


}
