package com.camunda.consulting.tasklist.fulltext.resource;

import java.util.ArrayList;
import java.util.List;

import com.camunda.consulting.tasklist.fulltext.entity.UserTask;

public class FulltextData {

  
  public List<UserTask> fixedList() {
    List<UserTask> result = new ArrayList<UserTask>();
    UserTask userTask = new UserTask();
    userTask.setId("Id 1");
    userTask.setBusinessKey("business key 1");
    userTask.setIncidentException("some longer text");
    result.add(userTask);
    return result;
  }

}
