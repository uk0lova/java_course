package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactModificationTests extends TestBase {

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
    public void testContactModification() throws Exception {
        app.goTo().contactPage();

        Set<ContactData> before=app.contact().all();
        ContactData modifiedContact=before.iterator().next();

        ContactData contact=new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("FNameNew")
                .withLastName("LName").withNickName("NickName").withCompanyName("Google")
                .withAddress("USA").withEmail("NickName@google.com").withHomePage("www.google.com")
                .withBDay("1").withBMonth("January").withBYear("2001");
        app.contact().modify(contact);

        Set<ContactData> after=app.contact().all();
        Assert.assertEquals(after.size(),before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before,after);
    }
}
