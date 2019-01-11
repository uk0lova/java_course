package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;


public class ContactPhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
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
    public void TestContactPhones(){
        app.goTo().contactPage();
        ContactData contact=app.contacts().all().iterator().next();
        ContactData contactInfoFromEditForm=app.contacts().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFromEditForm)));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

    public String mergePhones(ContactData contact){
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
                .stream().filter((s)->!s.equals(""))
                .map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }
}

