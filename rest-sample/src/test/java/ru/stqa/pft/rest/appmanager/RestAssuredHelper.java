package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import ru.stqa.pft.rest.model.Issue;

import java.util.Set;

public class RestAssuredHelper {
    protected ApplicationManager app;

    public RestAssuredHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Issue> getIssues() {
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json?limit=200").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    public String getIssueStateById(int issueId) {
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement issue = issues.getAsJsonArray().get(0);
        return issue.getAsJsonObject().get("state_name").getAsString();
    }

    public Issue getIssueById(int issueId) {
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement issue = issues.getAsJsonArray().get(0);
        return new Issue().withId(issue.getAsJsonObject().get("id").getAsInt())
                .withSubject(issue.getAsJsonObject().get("subject").getAsString())
                .withDescription(issue.getAsJsonObject().get("description").getAsString())
                .withStateName(issue.getAsJsonObject().get("state_name").getAsString());
    }

    public int createIssue(Issue newIssue) {
        String json = RestAssured.given()
                .param("subject", newIssue.getSubject())
                .param("description", newIssue.getDescription())
                .param("issue%5Bstate%5D", 3)//newIssue.getStateName())
                .post("http://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public void updateIssueStatusById(int issueId, int status) {
        String json = RestAssured.given()
                .param("method", "update")
                .param("issue%5Bstate%5D", status)
                .post("http://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
        }

}
