package com.camunda.demo.domain;

import java.util.List;
import lombok.Data;


public class UserTaskData {
    
    @Data
    private String id;
    
    @Data
    private String name;

    @Data
    private String formkey;

    @Data
    private List<UserTaskData> children;

    
}