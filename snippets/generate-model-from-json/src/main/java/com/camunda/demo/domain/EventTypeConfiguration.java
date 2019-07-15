package com.camunda.demo.domain;

import java.util.List;
import lombok.Data;


public class EventTypeConfiguration {

    @Data
    private String eventType;

    @Data
    private List<UserTaskData> configuration;
}