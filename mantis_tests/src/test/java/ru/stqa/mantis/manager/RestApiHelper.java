package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.*;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

public class RestApiHelper extends HelperBase{

    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }


    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }

    public void addUser(UserData user) {
        User body = new User();
        body.setUsername(user.username());
        body.setPassword(user.password());
        body.setRealName(user.realName());
        body.setEmail(user.email());
        var accessLvl = new AccessLevel();
        accessLvl.setName(user.accessLevel());
        body.accessLevel(accessLvl);
        body.enabled(user.enabled());
        body._protected(user._protected());

        UserApi apiInstance = new UserApi();// User | The user to add.
        try {
            UserAddResponse result = apiInstance.userAdd(body);
            System.out.println(result);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }
}
