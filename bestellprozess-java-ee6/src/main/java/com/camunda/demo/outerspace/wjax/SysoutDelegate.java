package com.camunda.demo.outerspace.wjax;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;


public class SysoutDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    System.out.println("I am here: " + 
    ((ExecutionEntity)execution).getActivityId());
    
    Thread.sleep(30*1000);
    
    System.out.println("finished");
//    
//    System.out.println( " ORDER ####### " + 
//            execution.getVariable("orderId"));
  }

}
