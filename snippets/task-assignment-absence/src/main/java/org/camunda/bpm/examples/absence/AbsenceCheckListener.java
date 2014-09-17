package org.camunda.bpm.examples.absence;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

public class AbsenceCheckListener implements TaskListener {

  /**
   * URL of service, could be easily customized - even depending on the runtime
   * environment (local, QA, live, ...)
   */
  private String staffServiceUrl = "http://localhost:8080/absence/rest/staff";

  public void notify(DelegateTask task) {
    // Get assignee from process model
    String assignee = task.getAssignee();

    try {
      HttpURLConnection request = (HttpURLConnection) new URL(staffServiceUrl).openConnection();
      request.connect();
      String responseString = IOUtils.toString((InputStream) request.getContent());
      JSONArray responseJson = new JSONArray(responseString);
      request.disconnect();

      assignee = getAvailableAssignee(responseJson, assignee);

      task.setAssignee(assignee);

    } catch (Exception e) {
      throw new RuntimeException("Could not process absence information: " + e.getMessage(), e);
    }

  }

  public String getAvailableAssignee(JSONArray jsonArr, String assignee) {
    String assigneeAbsent = "";
    String assigneeReplacement = "";

    for (int i = 0; i < jsonArr.length(); i++) {
      // retrieve absence data for assignee
      JSONObject obj = jsonArr.getJSONObject(i);
      if (obj.getString("username").equals(assignee)) {
        assigneeAbsent = obj.getString("absent");
        assigneeReplacement = obj.getString("vertreter");
      }

      if (assigneeAbsent.equals("true")) {
        // if assignee is absent, retrieve replacement and check that for
        // absence
        assignee = getAvailableAssignee(jsonArr, assigneeReplacement);

        // TODO: check all replacements are absent as well!
      }
    }
    return assignee;
  }

}
