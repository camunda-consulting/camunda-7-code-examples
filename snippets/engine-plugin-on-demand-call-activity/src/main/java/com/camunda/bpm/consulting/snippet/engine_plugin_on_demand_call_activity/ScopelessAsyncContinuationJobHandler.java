package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity;

import org.camunda.bpm.engine.impl.jobexecutor.AsyncContinuationJobHandler;
import org.camunda.bpm.engine.impl.pvm.runtime.operation.PvmAtomicOperation;

public class ScopelessAsyncContinuationJobHandler extends AsyncContinuationJobHandler {

  public final static String TYPE = "scopeless-async-continuation";

  @Override
  public PvmAtomicOperation findMatchingAtomicOperation(String operationName) {
    if (operationName == null) {
      return PvmAtomicOperation.ACTIVITY_EXECUTE;
    } else {
//    return super.findMatchingAtomicOperation(operationName);
      throw new UnsupportedOperationException();
    }
  }

}
