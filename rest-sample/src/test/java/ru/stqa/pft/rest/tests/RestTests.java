package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = app.restHC().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue OU").withDescription("New test description OU");
        int issueId = app.restHC().createIssue(newIssue);
        Set<Issue> newIssues = app.restHC().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

  /*  @Test
    public void test() throws RemoteException, ServiceException, MalformedURLException, NoSuchFieldException {
        skipIfNotFixed(Integer.valueOf("0000002"));
        System.out.println("Issue is fixed, test executed");
    }*/
}
