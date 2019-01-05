package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().goToContactsListPage();
        if(!app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("FName", "LName", "NickName", "Google", "USA", "NickName@google.com", "www.google.com", "1", "January", "2001"));
        }
        app.getNavigationHelper().goToContactsListPage();

        List<ContactData> before=app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().deleteSelectedContacts();

        List<ContactData> after=app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(before.size()-1);
        Assert.assertEquals(before,after);
    }
}
