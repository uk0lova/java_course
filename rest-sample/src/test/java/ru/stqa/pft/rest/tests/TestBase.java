package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager();

   /* public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException, NoSuchFieldException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException, NoSuchFieldException {
        if (app.restHC().getIssueById(issueId).getStatus().equals("closed") || app.soap().getIssueById(issueId).getStatus().equals("resolved"))
            return false;
        return true;
    }*/
}
