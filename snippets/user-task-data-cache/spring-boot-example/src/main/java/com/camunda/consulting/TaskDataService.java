package com.camunda.consulting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class TaskDataService {
  private final TaskDataRepository repository;


  public TaskDataService(TaskDataRepository repository) {
    this.repository = repository;
  }

  public TaskData save(TaskData taskData) {
    return repository.save(taskData);
  }

  public <I extends Iterable<TaskData>> I saveAll(I taskData) {
    return (I) repository.saveAll(taskData);
  }

  public Optional<TaskData> delete(TaskData taskData) {
    assert taskData.getTaskId() != null;
    return delete(taskData.getTaskId());
  }

  public Optional<TaskData> delete(String id) {
    return get(id).stream().peek(repository::delete).findFirst();
  }

  public Optional<TaskData> get(String id) {
    return repository.findById(id);
  }

  public Stream<TaskData> stream() {
    return StreamSupport.stream(repository.findAll().spliterator(), false);
  }

  public Page<TaskData> page(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public void drop() {
    repository.deleteAll();
  }
}
