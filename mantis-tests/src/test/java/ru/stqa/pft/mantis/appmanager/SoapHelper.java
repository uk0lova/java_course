package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;
import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        return Arrays.asList(projects).stream()
                .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
        IssueData IssueData = new IssueData();
        IssueData.setSummary(issue.getSummary());
        IssueData.setDescription(issue.getDescription());
        IssueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        IssueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add("administrator", "root", IssueData);
        IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary())
                .withDescription(createdIssueData.getDescription())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                        .withName(createdIssueData.getProject().getName()));
    }

    public Issue getIssueById(int issueId) throws MalformedURLException, ServiceException, RemoteException, NoSuchFieldException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData retrievedIssueData = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
        return new Issue().withId(retrievedIssueData.getId().intValue())
                .withSummary(retrievedIssueData.getSummary())
                .withDescription(retrievedIssueData.getDescription())
                .withStatus(retrievedIssueData.getStatus().getName())
                .withProject(new Project().withId(retrievedIssueData.getProject().getId().intValue())
                        .withName(retrievedIssueData.getProject().getName()));

    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("web.mantisConnectURL")));
    }
}
