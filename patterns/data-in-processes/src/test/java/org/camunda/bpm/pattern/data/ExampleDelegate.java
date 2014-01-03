package org.camunda.bpm.pattern.data;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named("serviceTask")
public class ExampleDelegate implements JavaDelegate {

  @Inject
  private ExampleProcessDataAccessor variables;

  public void execute(DelegateExecution execution) throws Exception {

    // alternative 1 - possible but more code:
    // ExampleProcessDataAccessor variables2 = new  ExampleProcessDataAccessor(execution);
    
    // alternative 2 - not really nice - need to keep in mind that the map is only a copy and you have to load everything - even if you need only some variables
    // ExampleProcessDataAccessor variables3 = new ExampleProcessDataAccessor(execution.getVariables());

    // how to use it:
    String foo = variables.getFoo();
    variables.setBar(foo);
    System.out.println("## " + foo);
    variables.setHello("http:alsdkfj alöskdf aölsdjk f");
  }

}
