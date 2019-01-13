package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testGroupForContact"));
        }

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
    public void addContactToGroupTest() {
        ContactData modifiedContact = app.db().contacts().iterator().next();
        Groups groupsBefore = modifiedContact.getGroups();
        GroupData modifiedGroup = chooseGroupFor(modifiedContact);

        app.goTo().contactPage();
        app.contacts().addContactToGroup(modifiedContact.getId(), modifiedGroup);

        Groups groupsAfter = app.db().contacts(modifiedContact.getId()).iterator().next().getGroups();
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(modifiedGroup)));
    }
}
