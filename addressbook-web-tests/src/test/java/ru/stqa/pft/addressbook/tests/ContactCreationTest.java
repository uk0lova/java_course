package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToContactsListPage();
        List<ContactData> before=app.getContactHelper().getContactList();
        
        app.getNavigationHelper().goToAddContactPage();
        ContactData contact=new ContactData("FName", "LName", "NickName", "Google", "USA", "NickName@google.com", "www.google.com", "1", "January", "2001");
        app.getContactHelper().createContact(contact);

        app.getNavigationHelper().goToContactsListPage();
        List<ContactData> after=app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size()+1);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2)->Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
