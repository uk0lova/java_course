package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData group = new GroupData().withName("testGroupForContact");
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(group);
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().addContactPage();
            app.contacts().create(new ContactData()
                    .withFirstName("FName").withLastName("LName").withNickName("NickName").withCompanyName("Google")
                    .withAddress("USA").withEmail("NickName@google.com").withEmail2("NickName2@google.com")
                    .withHomePage("www.google.com")
                    .withBDay("1").withBMonth("January").withBYear("2001")
                    .withHomePhone("222-22-22").withMobilePhone("8(911)333").withWorkPhone("444 555 67")
                    .inGroup(group));
        }
    }

    @Test
    public void removeContactFromGroupTest() {
        ContactData modifiedContact = app.db().contacts().iterator().next();
        int contactId = modifiedContact.getId();

        if (modifiedContact.getGroups().size() == 0) {
            GroupData modifiedGroup = chooseGroupFor(modifiedContact);
            app.goTo().contactPage();
            app.contacts().addContactToGroup(contactId, modifiedGroup);
        }

        modifiedContact = app.db().contacts(contactId).iterator().next();
        Groups groupsBefore = modifiedContact.getGroups();
        GroupData modifiedGroup = modifiedContact.getGroups().iterator().next();

        app.goTo().contactPage();
        app.contacts().removeContactFromGroup(contactId, modifiedGroup);

        Groups groupsAfter = app.db().contacts(contactId).iterator().next().getGroups();
        assertThat(groupsAfter, equalTo(groupsBefore.without(modifiedGroup)));
    }
}
