package ru.stqa.pft.rest.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {
    private int SkippedIssueId=1;

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

   @BeforeMethod
    public void ensurePreconditions(){
        Set<Issue> oldIssues = app.restAssured().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue OU").withDescription("New test description OU").withStateName("Closed");
        SkippedIssueId = app.restAssured().createIssue(newIssue);
        app.restAssured().updateIssueStatusById(SkippedIssueId,3);
        Set<Issue> newIssues = app.restAssured().getIssues();
        oldIssues.add(newIssue.withId(SkippedIssueId));
        assertEquals(newIssues, oldIssues);
    }

    @Test(enabled = false)
    public void testCreateIssue() {
        Set<Issue> oldIssues = app.restAssured().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue OU").withDescription("New test description OU");
        int issueId = app.restAssured().createIssue(newIssue);
        Set<Issue> newIssues = app.restAssured().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    @Test
    public void test() {
        skipIfNotFixed(SkippedIssueId);
        System.out.println("Issue is fixed, test executed");
    }
}
