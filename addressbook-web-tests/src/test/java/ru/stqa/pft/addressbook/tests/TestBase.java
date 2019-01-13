package ru.stqa.pft.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] p) {
        logger.info("Stop test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contacts().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((c) -> new ContactData().withId(c
                            .getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName())
                            .withAddress(c.getAddress()).withAllEmails(c.getAllEmails())
                            .withAllPhones(c.getAllPhones()))
                    .collect(Collectors.toSet())));
        }
    }

    public GroupData chooseGroupFor(ContactData modifiedContact) {
        Random rand = new Random();
        Groups groups = app.db().groups();
        GroupData choosenGroup = null;

        for (GroupData group : groups) {
            if (!modifiedContact.getGroups().contains(group)) {
                choosenGroup = group;
                break;
            }
        }

        if (choosenGroup == null) {
            String groupName = "testGroupForContact" + rand.nextInt(1000);
            choosenGroup = new GroupData().withName(groupName);
            app.goTo().groupPage();
            app.group().create(choosenGroup);
            choosenGroup = app.db().groups(groupName).iterator().next();
        }

        return choosenGroup;
    }
}
