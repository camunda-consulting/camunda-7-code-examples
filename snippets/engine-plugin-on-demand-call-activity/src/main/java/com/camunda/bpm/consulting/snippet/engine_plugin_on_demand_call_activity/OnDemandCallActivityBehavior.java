package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity;

import org.camunda.bpm.dmn.engine.impl.el.JuelExpression;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.exception.NullValueException;
import org.camunda.bpm.engine.impl.bpmn.behavior.CallActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.behavior.ServiceTaskDelegateExpressionActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.delegate.JavaDelegateInvocation;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.core.model.CallableElement;
import org.camunda.bpm.engine.impl.el.ExpressionManager;
import org.camunda.bpm.engine.impl.jobexecutor.AsyncContinuationJobHandler;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobUtil;
import org.camunda.bpm.engine.impl.persistence.entity.MessageEntity;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;
import org.camunda.bpm.engine.impl.pvm.runtime.ExecutionImpl;
import org.camunda.bpm.engine.variable.VariableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public class OnDemandCallActivityBehavior extends CallActivityBehavior {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(ActivityExecution execution) throws Exception {
        List<JobEntity> jobs = Context.getCommandContext().getJobManager().findJobsByExecutionId(execution.getId());
        if(jobs.size() > 0)
            execution.setVariable(getRetriesVariable(execution), jobs.get(0).getRetries());

        super.execute(execution);
    }

    @Override
    protected void startInstance(ActivityExecution execution, VariableMap variables, String businessKey) {
        //TRY TO START A PROCESS WITH THE KEY PROVIDED
        try {
            super.startInstance(execution, variables, businessKey);
        }
        //IN CASE THIS IS NOT FOUND, TRY TO FIND A BEAN IF THAT IS AN EXPRESSION
        catch (NullValueException e) {
            logger.info("Ignoring process without existing key...");
        }
    }

    @Override
    public void signal(ActivityExecution execution, String signalName, Object signalData) throws Exception {
        if (signalData instanceof BpmnError) {
            throw (BpmnError) signalData;
        } else if (signalData instanceof Exception) {

            Integer currentRetries = (Integer) execution.getVariable(getRetriesVariable(execution));

            if(currentRetries == null){
                currentRetries = 3;
            }

            Exception exception = (Exception) signalData;
            currentRetries--;
            execution.setVariable(getRetriesVariable(execution), currentRetries);
            createAsynchronousContinuationJob(execution, currentRetries, exception);
        } else {
            execution.setVariableLocal(getRetriesVariable(execution), null);
            leave(execution);
        }
    }

    public static void createAsynchronousContinuationJob(DelegateExecution execution, Integer retries, Exception exception) {
        MessageEntity message = new MessageEntity();
        message.setProcessInstanceId(execution.getProcessInstanceId());
        message.setProcessDefinitionId(execution.getProcessDefinitionId());
        message.setExecutionId(execution.getId());
        message.setExclusive(true);
        message.setJobHandlerType(AsyncContinuationJobHandler.TYPE);
        message.setExceptionMessage(exception.getMessage());
        message.setExceptionStacktrace(getExceptionStacktrace(exception));
        message.setRetries(retries);
        Context.getCommandContext().getJobManager().send(message);

        if(retries == 0)
            JobUtil.createIncident(message);

    }


    private String getRetriesVariable(ActivityExecution execution) {
        return "AsyncOnError_RETRIES_" + execution.getActivity().getId();
    }

    public static String getExceptionStacktrace(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
