package com.camunda.demo.bpmn_validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.camunda.bpm.engine.ProcessEngineException;

public class BpmnValidationVisualizer {

//  private static Pattern REGEXP_MISSING_SEQUENCE_FLOW_CONDITION
//    = Pattern.compile("Exclusive Gateway 'ExclusiveGateway_1' has outgoing sequence flow '([^']+)' without condition which is not the default flow. | process.bpmn | line 0 | column 0");

  private static Pattern REGEXP_GENERIC_EXCEPTION
    = Pattern.compile("([^\\|]+).*\\| elements? (.+)" + System.getProperty("line.separator"));

  public static Map<String, List<String>> getErrors(ProcessEngineException e) {
    Map<String, List<String>> errors = new HashMap<String, List<String>>();
    String message = e.getMessage();
    Matcher matcher = REGEXP_GENERIC_EXCEPTION.matcher(message);
    while (matcher.find()) {
      String[] bpmnElementIds = matcher.group(2).split(",");
      for (String bpmnElement : bpmnElementIds) {
        List<String> errorsForBpmnElement;
        if (errors.containsKey(bpmnElement)) {
          errorsForBpmnElement = errors.get(bpmnElement);
        } else {
          errorsForBpmnElement = new ArrayList<String>();
          errors.put(bpmnElement, errorsForBpmnElement);
        }
        errorsForBpmnElement.add(matcher.group(1));
      }
    }
    return errors;
  }

}
