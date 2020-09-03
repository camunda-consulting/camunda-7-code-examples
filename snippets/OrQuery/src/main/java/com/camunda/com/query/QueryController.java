package com.camunda.com.query;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@RestController
public class QueryController {

	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;

	@GetMapping("/taskQuery")
	public Map<String, String> taskQuery() {

		// Create Hashmap to hold json response
		HashMap<String, String> map = new HashMap<String, String>();

		// Create list to hold all tasks based on queries
		List<Task> listWithDuplicates = taskService.createTaskQuery()
				// and
				.taskCandidateGroup("camunda-admin").processVariableValueEquals("bu", "HR123").list();

		listWithDuplicates.addAll(taskService.createTaskQuery()
				// or
				.or().taskCandidateUser("demo1").processVariableValueEquals("age", 20).endOr().list());

		listWithDuplicates.addAll(taskService.createTaskQuery()
				// and
				.processVariableValueEquals("country", "MEX").processVariableValueEquals("currency", "EUR").list());

		// Create new list and eliminate duplicates
		List<Task> listWithOutDuplicates = removeDuplicates(listWithDuplicates);

		System.out.println("Number of tasks retrieved " + listWithOutDuplicates.size());
		// Maybe loop through results and then present back as JSON Array
		map.put("Number of tasks", String.valueOf(listWithOutDuplicates.size()));

		return map;

	}

	public static ArrayList<Task> removeDuplicates(List<Task> listWithDuplicatesCopy) {

		// Create a new ArrayList
		ArrayList<Task> newList = new ArrayList<Task>();

		// Traverse through the first list
		for (Task element : listWithDuplicatesCopy) {

			// If this element is not present in newList
			// then add it
			if (!newList.contains(element)) {

				newList.add(element);
			}
		}

		// return the new list
		return newList;
	}

}
