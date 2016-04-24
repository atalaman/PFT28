package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class TestBase {

  public void skipIfNotFixed(int issueId) throws IOException {
    if(isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    String status = getIssue(issueId).getState_name();
    return !("Resolved".equals(status) || "Closed".equals(status)) ;
  }

  public Issue getIssue(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement element = parsed.getAsJsonObject().get("issues");
    Set<Issue> issues = new Gson().fromJson(element, new TypeToken<Set<Issue>>(){}.getType());
    assertEquals(issues.size(), 1);
    return issues.iterator().next();
  }

  protected Executor getExecutor() {
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }

}
