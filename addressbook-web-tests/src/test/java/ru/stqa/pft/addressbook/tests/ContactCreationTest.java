package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        File photo=new File("src/test/resources/crimea.jpeg");

        app.goTo().addContactPage();
        ContactData contact = new ContactData()
                .withFirstName("FName").withLastName("LName").withNickName("NickName").withCompanyName("Google")
                .withAddress("Russia, Saint-Petergburg, 18 line V.O. 1-1")
                .withEmail("NickName@google.com").withEmail2("NickName2@google.com")
                .withHomePage("www.google.com")
                .withBDay("1").withBMonth("January").withBYear("2001")
                .withHomePhone("222-22-22").withMobilePhone("8(911)333").withWorkPhone("444 555 67")
                .withPhoto(photo);
        app.contact().create(contact);
        app.goTo().contactPage();

        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
