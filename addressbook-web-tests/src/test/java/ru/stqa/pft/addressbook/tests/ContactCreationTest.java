package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTest extends TestBase {

    @Test (enabled=true)
    public void testContactCreation() throws Exception {
        app.goTo().contactPage();
        Set<ContactData> before=app.contact().all();

        app.goTo().addContactPage();
        ContactData contact=new ContactData()
                .withFirstName("FName").withLastName("LName").withNickName("NickName").withCompanyName("Google")
                .withAddress("USA").withEmail("NickName@google.com").withHomePage("www.google.com").withBDay("1").withBMonth("January").withBYear("2001");
        app.contact().create(contact);

        app.goTo().contactPage();
        Set<ContactData> after=app.contact().all();
        Assert.assertEquals(after.size(),before.size()+1);

        contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before,after);
    }

}
