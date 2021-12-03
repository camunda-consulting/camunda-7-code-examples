package com.camunda.consulting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("task-data")
public class TaskDataController {
  private final TaskDataService taskDataService;

  @Autowired
  public TaskDataController(TaskDataService taskDataService) {this.taskDataService = taskDataService;}

  @GetMapping
  public Page<TaskDataDto> list(Pageable pageable) {
    return taskDataService.page(pageable).map(this::map);
  }

  private TaskDataDto map(TaskData taskData) {
    TaskDataDto dto = new TaskDataDto();
    dto.setTaskId(taskData.getTaskId());
    dto.setBankCode(taskData.getBankCode());
    dto.setWorkStep(taskData.getWorkStep());
    dto.setSingleProcess(taskData.getSingleProcess());
    dto.setOrderNumber(taskData.getOrderNumber());
    return dto;
  }
}
