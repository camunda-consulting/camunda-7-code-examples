package com.camunda.consulting;

import javax.ejb.Singleton;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class TaskDataService {
  private final Map<String, TaskData> taskDataList = new HashMap<>();

  public TaskData save(TaskData taskData) {
    assert taskData.getTaskId() != null;
    taskDataList.put(taskData.getTaskId(), taskData);
    return taskData;
  }

  public Optional<TaskData> delete(TaskData taskData) {
    assert taskData.getTaskId() != null;
    return delete(taskData.getTaskId());
  }

  public Optional<TaskData> delete(String id) {
    return Optional.ofNullable(taskDataList.remove(id));
  }

  public Optional<TaskData> get(String id) {
    return Optional.ofNullable(taskDataList.get(id));
  }

  public Stream<TaskData> stream() {
    return taskDataList.values().stream();
  }

  public List<TaskData> list() {
    return taskDataList.values()
        .stream()
        .sorted(Comparator.comparing(TaskData::getTaskId))
        .collect(Collectors.toList());
  }

  public void drop() {
    taskDataList.clear();
  }
}
