package com.camunda.demo.domain;

import java.util.List;
import lombok.Data;

@Data
public class UserTaskData {
    
    private String id;
    
    private String name;

    private String formkey;

    private List<UserTaskData> children;

    
}