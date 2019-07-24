package com.camunda.demo.domain;

import java.util.List;
import lombok.Data;

@Data
public class EventTypeConfiguration {

    private String eventType;

    private List<UserTaskData> configuration;
}