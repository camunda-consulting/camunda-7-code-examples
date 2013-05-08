package com.camunda.fox;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class SysoutDelegeate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution ctx) throws Exception {
    System.out.println("Hello world! " + ctx.getVariables());
//    if (true) {
//      throw new RuntimeException("broken");
//    }
  }

}
