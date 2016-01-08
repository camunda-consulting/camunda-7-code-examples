package com.camunda.demo.dmntraining.taskrouting;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
	
	public List<Employee> getQualifiedAndAvailableEmployees(List<String> requiredSkills) {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("john", "Senior", "10xxx", 7));
		employees.add(new Employee("mary", "Experte", "22xxx", 5));
		employees.add(new Employee("peter", "Junior", "10xxx", 2));
		return employees;
	}

}
