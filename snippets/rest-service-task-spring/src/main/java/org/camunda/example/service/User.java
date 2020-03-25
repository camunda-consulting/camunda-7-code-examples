package org.camunda.example.service;

import lombok.Data;

@Data
public class User {
    int id;
    String email;
    String first_name;
    String last_name;
    String avatar;
}
