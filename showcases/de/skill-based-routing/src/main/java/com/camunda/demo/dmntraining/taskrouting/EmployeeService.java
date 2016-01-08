package com.camunda.demo.dmntraining.taskrouting;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
	
	public List<Employee> getQualifiedAndAvailableEmployees(List<String> requiredSkills) {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("john", 5000, "10xxx", 7)); // [1000..10000] = "Senior"
		employees.add(new Employee("mary", 12000, "22xxx", 5)); // > 100000 = "Experte"
		employees.add(new Employee("peter", 150, "10xxx", 2)); // < 1000 = "Junior"
		return employees;
	}

}
