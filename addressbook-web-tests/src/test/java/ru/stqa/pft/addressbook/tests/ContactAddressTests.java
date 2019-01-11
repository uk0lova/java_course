package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().addContactPage();
            app.contacts().create(new ContactData()
                    .withFirstName("FName").withLastName("LName").withNickName("NickName").withCompanyName("Google")
                    .withAddress("USA").withEmail("NickName@google.com").withEmail2("NickName2@google.com")
                    .withHomePage("www.google.com")
                    .withBDay("1").withBMonth("January").withBYear("2001")
                    .withHomePhone("222-22-22").withMobilePhone("8(911)333").withWorkPhone("444 555 67"));
        }
    }

    @Test
    public void TestContactEmails() {
        app.goTo().contactPage();
        ContactData contact = app.contacts().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}
