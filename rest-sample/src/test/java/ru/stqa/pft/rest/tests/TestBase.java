package ru.stqa.pft.rest.tests;
import org.testng.SkipException;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager();

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) {
        if (app.restAssured().getIssueById(issueId).getStateName().equals("Closed") ||
                app.restAssured().getIssueById(issueId).getStateName().equals("Resolved"))
            return false;
        return true;
    }
}
