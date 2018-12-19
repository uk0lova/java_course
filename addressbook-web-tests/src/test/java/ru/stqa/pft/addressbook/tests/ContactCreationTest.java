package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToAddContactPage();
        app.getContactHelper().createContact(new ContactData("FName", "LName", "NickName", "Google", "USA", "NickName@google.com", "www.google.com", "1", "January", "2001"));
    }

}
