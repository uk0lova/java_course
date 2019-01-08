package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().contactPage();
        if(app.contact().all().size()==0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData()
                    .withFirstName("FName").withLastName("LName").withNickName("NickName").withCompanyName("Google")
                    .withAddress("USA").withEmail("NickName@google.com").withHomePage("www.google.com").withBDay("1").withBMonth("January").withBYear("2001"));
        }
    }

    @Test (enabled=true)
    public void testContactDeletion() throws Exception {
        app.goTo().contactPage();

        Contacts before=app.contact().all();
        ContactData deletedContact=before.iterator().next();

        app.contact().delete(deletedContact);
        app.goTo().contactPage();

        Contacts after=app.contact().all();
        assertThat(after.size(),equalTo(before.size()-1));
        assertThat(after,equalTo(before.without(deletedContact)));
    }
}
