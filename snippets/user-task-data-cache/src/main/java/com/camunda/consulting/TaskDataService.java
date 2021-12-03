package com.camunda.consulting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
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

  public Page<TaskData> page(Pageable pageable) {
    List<TaskData> list = taskDataList.values()
        .stream()
        .sorted(Comparator.comparing(TaskData::getTaskId))
        .collect(Collectors.toList());
    return new PageImpl<>(list, pageable, list.size());
  }

  public void drop() {
    taskDataList.clear();
  }
}
