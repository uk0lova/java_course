package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

    @Test (enabled=true)
    public void testContactCreation() throws Exception {
        app.goTo().contactPage();
        Contacts before=app.contact().all();

        app.goTo().addContactPage();
        ContactData contact=new ContactData()
                .withFirstName("FName").withLastName("LName").withNickName("NickName").withCompanyName("Google")
                .withAddress("USA").withEmail("NickName@google.com").withHomePage("www.google.com").withBDay("1").withBMonth("January").withBYear("2001");
        app.contact().create(contact);
        app.goTo().contactPage();

        Contacts after=app.contact().all();
        assertThat(after.size(),equalTo(before.size()+1));
        assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }

}
