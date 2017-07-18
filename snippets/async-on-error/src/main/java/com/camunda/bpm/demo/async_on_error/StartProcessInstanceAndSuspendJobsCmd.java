package com.camunda.bpm.demo.async_on_error;

import java.util.List;

import org.camunda.bpm.engine.impl.ProcessInstantiationBuilderImpl;
import org.camunda.bpm.engine.impl.cmd.StartProcessInstanceCmd;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessInstanceWithVariablesImpl;
import org.camunda.bpm.engine.impl.persistence.entity.SuspensionState;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;

public class StartProcessInstanceAndSuspendJobsCmd extends StartProcessInstanceCmd {

  private static final long serialVersionUID = 1L;
  
  public StartProcessInstanceAndSuspendJobsCmd(ProcessInstantiationBuilderImpl instantiationBuilder) {
    super((ProcessInstantiationBuilderImpl) instantiationBuilder);
  }

  @Override
  public ProcessInstanceWithVariables execute(CommandContext commandContext) {
    ProcessInstanceWithVariables processInstanceWithVariables = super.execute(commandContext);
    // prevent the job from being executed by the JobExecutor
    List<JobEntity> jobs = ((ProcessInstanceWithVariablesImpl) processInstanceWithVariables).getExecutionEntity().getJobs();
    for (JobEntity job : jobs) {
      commandContext.getJobManager().updateJobSuspensionStateById(job.getId(), SuspensionState.SUSPENDED);
    }
    return processInstanceWithVariables;
  }
  
}
