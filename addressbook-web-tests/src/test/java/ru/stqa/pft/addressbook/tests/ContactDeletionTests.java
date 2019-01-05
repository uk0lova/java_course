package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().contactPage();
        if(app.contact().list().size()==0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData("FName", "LName", "NickName", "Google", "USA", "NickName@google.com", "www.google.com", "1", "January", "2001"));
        }
    }

    @Test (enabled=true)
    public void testContactDeletion() throws Exception {
        app.goTo().contactPage();

        List<ContactData> before=app.contact().list();
        int index=before.size()-1;

        app.contact().delete(index);
        app.goTo().contactPage();

        List<ContactData> after=app.contact().list();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(index);
        Assert.assertEquals(before,after);
    }
}
