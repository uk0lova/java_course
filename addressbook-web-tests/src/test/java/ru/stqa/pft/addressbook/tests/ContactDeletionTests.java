package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().goToContactsListPage();
        if(!app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("FName", "LName", "NickName", "Google", "USA", "NickName@google.com", "www.google.com", "1", "January", "2001"));
        }
        app.getNavigationHelper().goToContactsListPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
    }
}
