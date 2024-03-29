/*
 * Camunda Platform REST API
 * OpenApi Spec for Camunda Platform REST API.
 *
 * The version of the OpenAPI document: 7.16.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.model.ActivityInstanceDto;
import com.camunda.consulting.openapi.client.model.AuthorizationExceptionDto;
import com.camunda.consulting.openapi.client.model.BatchDto;
import com.camunda.consulting.openapi.client.model.CorrelationMessageAsyncDto;
import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.DeleteProcessInstancesDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import java.io.File;
import com.camunda.consulting.openapi.client.model.PatchVariablesDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceModificationDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceQueryDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceSuspensionStateAsyncDto;
import com.camunda.consulting.openapi.client.model.ProcessInstanceSuspensionStateDto;
import com.camunda.consulting.openapi.client.model.SetJobRetriesByProcessDto;
import com.camunda.consulting.openapi.client.model.SetVariablesAsyncDto;
import com.camunda.consulting.openapi.client.model.SuspensionStateDto;
import com.camunda.consulting.openapi.client.model.VariableValueDto;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ProcessInstanceApi
 */
@Ignore
public class ProcessInstanceApiTest {

    private final ProcessInstanceApi api = new ProcessInstanceApi();

    
    /**
     * Correlate Message Async (POST)
     *
     * Correlates a message asynchronously to executions that are waiting for this message.  Messages will not be correlated to process definition-level start message events to start process instances.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void correlateMessageAsyncOperationTest() {
        CorrelationMessageAsyncDto correlationMessageAsyncDto = null;
        BatchDto response = api.correlateMessageAsyncOperation(correlationMessageAsyncDto);

        // TODO: test validations
    }
    
    /**
     * Delete Async Historic Query Based (POST)
     *
     * Deletes a set of process instances asynchronously (batch) based on a historic process instance query.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteAsyncHistoricQueryBasedTest() {
        DeleteProcessInstancesDto deleteProcessInstancesDto = null;
        BatchDto response = api.deleteAsyncHistoricQueryBased(deleteProcessInstancesDto);

        // TODO: test validations
    }
    
    /**
     * Delete
     *
     * Deletes a running process instance by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteProcessInstanceTest() {
        String id = null;
        Boolean skipCustomListeners = null;
        Boolean skipIoMappings = null;
        Boolean skipSubprocesses = null;
        Boolean failIfNotExists = null;
        api.deleteProcessInstance(id, skipCustomListeners, skipIoMappings, skipSubprocesses, failIfNotExists);

        // TODO: test validations
    }
    
    /**
     * Delete Process Variable
     *
     * Deletes a variable of a process instance by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteProcessInstanceVariableTest() {
        String id = null;
        String varName = null;
        api.deleteProcessInstanceVariable(id, varName);

        // TODO: test validations
    }
    
    /**
     * Delete Async (POST)
     *
     * Deletes multiple process instances asynchronously (batch).
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteProcessInstancesAsyncOperationTest() {
        DeleteProcessInstancesDto deleteProcessInstancesDto = null;
        BatchDto response = api.deleteProcessInstancesAsyncOperation(deleteProcessInstancesDto);

        // TODO: test validations
    }
    
    /**
     * Get Activity Instance
     *
     * Retrieves an Activity Instance (Tree) for a given process instance by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getActivityInstanceTreeTest() {
        String id = null;
        ActivityInstanceDto response = api.getActivityInstanceTree(id);

        // TODO: test validations
    }
    
    /**
     * Get Process Instance
     *
     * Retrieves a process instance by id, according to the &#x60;ProcessInstance&#x60; interface in the engine.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getProcessInstanceTest() {
        String id = null;
        ProcessInstanceDto response = api.getProcessInstance(id);

        // TODO: test validations
    }
    
    /**
     * Get Process Variable
     *
     * Retrieves a variable of a given process instance by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getProcessInstanceVariableTest() {
        String id = null;
        String varName = null;
        Boolean deserializeValue = null;
        VariableValueDto response = api.getProcessInstanceVariable(id, varName, deserializeValue);

        // TODO: test validations
    }
    
    /**
     * Get Process Variable (Binary)
     *
     * Retrieves the content of a Process Variable by the Process Instance id and the Process Variable name. Applicable for byte array or file Process Variables.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getProcessInstanceVariableBinaryTest() {
        String id = null;
        String varName = null;
        File response = api.getProcessInstanceVariableBinary(id, varName);

        // TODO: test validations
    }
    
    /**
     * Get Process Variables
     *
     * Retrieves all variables of a given process instance by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getProcessInstanceVariablesTest() {
        String id = null;
        Boolean deserializeValues = null;
        Map<String, VariableValueDto> response = api.getProcessInstanceVariables(id, deserializeValues);

        // TODO: test validations
    }
    
    /**
     * Get List
     *
     * Queries for process instances that fulfill given parameters. Parameters may be static as well as dynamic runtime properties of process instances. The size of the result set can be retrieved by using the Get Instance Count method.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getProcessInstancesTest() {
        String sortBy = null;
        String sortOrder = null;
        Integer firstResult = null;
        Integer maxResults = null;
        String processInstanceIds = null;
        String businessKey = null;
        String businessKeyLike = null;
        String caseInstanceId = null;
        String processDefinitionId = null;
        String processDefinitionKey = null;
        String processDefinitionKeyIn = null;
        String processDefinitionKeyNotIn = null;
        String deploymentId = null;
        String superProcessInstance = null;
        String subProcessInstance = null;
        String superCaseInstance = null;
        String subCaseInstance = null;
        Boolean active = null;
        Boolean suspended = null;
        Boolean withIncident = null;
        String incidentId = null;
        String incidentType = null;
        String incidentMessage = null;
        String incidentMessageLike = null;
        String tenantIdIn = null;
        Boolean withoutTenantId = null;
        Boolean processDefinitionWithoutTenantId = null;
        String activityIdIn = null;
        Boolean rootProcessInstances = null;
        Boolean leafProcessInstances = null;
        String variables = null;
        Boolean variableNamesIgnoreCase = null;
        Boolean variableValuesIgnoreCase = null;
        List<ProcessInstanceDto> response = api.getProcessInstances(sortBy, sortOrder, firstResult, maxResults, processInstanceIds, businessKey, businessKeyLike, caseInstanceId, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionKeyNotIn, deploymentId, superProcessInstance, subProcessInstance, superCaseInstance, subCaseInstance, active, suspended, withIncident, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, processDefinitionWithoutTenantId, activityIdIn, rootProcessInstances, leafProcessInstances, variables, variableNamesIgnoreCase, variableValuesIgnoreCase);

        // TODO: test validations
    }
    
    /**
     * Get List Count
     *
     * Queries for the number of process instances that fulfill given parameters.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getProcessInstancesCountTest() {
        String processInstanceIds = null;
        String businessKey = null;
        String businessKeyLike = null;
        String caseInstanceId = null;
        String processDefinitionId = null;
        String processDefinitionKey = null;
        String processDefinitionKeyIn = null;
        String processDefinitionKeyNotIn = null;
        String deploymentId = null;
        String superProcessInstance = null;
        String subProcessInstance = null;
        String superCaseInstance = null;
        String subCaseInstance = null;
        Boolean active = null;
        Boolean suspended = null;
        Boolean withIncident = null;
        String incidentId = null;
        String incidentType = null;
        String incidentMessage = null;
        String incidentMessageLike = null;
        String tenantIdIn = null;
        Boolean withoutTenantId = null;
        Boolean processDefinitionWithoutTenantId = null;
        String activityIdIn = null;
        Boolean rootProcessInstances = null;
        Boolean leafProcessInstances = null;
        String variables = null;
        Boolean variableNamesIgnoreCase = null;
        Boolean variableValuesIgnoreCase = null;
        CountResultDto response = api.getProcessInstancesCount(processInstanceIds, businessKey, businessKeyLike, caseInstanceId, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionKeyNotIn, deploymentId, superProcessInstance, subProcessInstance, superCaseInstance, subCaseInstance, active, suspended, withIncident, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, processDefinitionWithoutTenantId, activityIdIn, rootProcessInstances, leafProcessInstances, variables, variableNamesIgnoreCase, variableValuesIgnoreCase);

        // TODO: test validations
    }
    
    /**
     * Modify Process Instance Execution State
     *
     * Submits a list of modification instructions to change a process instance&#39;s execution state. A modification instruction is one of the following:  * Starting execution before an activity * Starting execution after an activity on its single outgoing sequence flow * Starting execution on a specific sequence flow * Canceling an activity instance, transition instance, or all instances (activity or transition) for an activity  Instructions are executed immediately and in the order they are provided in this request&#39;s body. Variables can be provided with every starting instruction.  The exact semantics of modification can be read about in the [User guide](https://docs.camunda.org/manual/develop/user-guide/process-engine/process-instance-modification/).
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void modifyProcessInstanceTest() {
        String id = null;
        ProcessInstanceModificationDto processInstanceModificationDto = null;
        api.modifyProcessInstance(id, processInstanceModificationDto);

        // TODO: test validations
    }
    
    /**
     * Modify Process Instance Execution State Async
     *
     * Submits a list of modification instructions to change a process instance&#39;s execution state async. A modification instruction is one of the following:  * Starting execution before an activity * Starting execution after an activity on its single outgoing sequence flow * Starting execution on a specific sequence flow * Cancelling an activity instance, transition instance, or all instances (activity or transition) for an activity  Instructions are executed asynchronous and in the order they are provided in this request&#39;s body. Variables can be provided with every starting instruction.  The exact semantics of modification can be read about in the [User guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/process-instance-modification/).
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void modifyProcessInstanceAsyncOperationTest() {
        String id = null;
        ProcessInstanceModificationDto processInstanceModificationDto = null;
        BatchDto response = api.modifyProcessInstanceAsyncOperation(id, processInstanceModificationDto);

        // TODO: test validations
    }
    
    /**
     * Update/Delete Process Variables
     *
     * Updates or deletes the variables of a process instance by id. Updates precede deletions. So, if a variable is updated AND deleted, the deletion overrides the update.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void modifyProcessInstanceVariablesTest() {
        String id = null;
        PatchVariablesDto patchVariablesDto = null;
        api.modifyProcessInstanceVariables(id, patchVariablesDto);

        // TODO: test validations
    }
    
    /**
     * Get List (POST)
     *
     * Queries for process instances that fulfill given parameters through a JSON object. This method is slightly more powerful than the Get Instances method because it allows filtering by multiple process variables of types &#x60;string&#x60;, &#x60;number&#x60; or &#x60;boolean&#x60;.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void queryProcessInstancesTest() {
        Integer firstResult = null;
        Integer maxResults = null;
        ProcessInstanceQueryDto processInstanceQueryDto = null;
        List<ProcessInstanceDto> response = api.queryProcessInstances(firstResult, maxResults, processInstanceQueryDto);

        // TODO: test validations
    }
    
    /**
     * Get List Count (POST)
     *
     * Queries for the number of process instances that fulfill the given parameters. This method takes the same message body as the Get Instances (POST) method and therefore it is slightly more powerful than the Get Instance Count method.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void queryProcessInstancesCountTest() {
        ProcessInstanceQueryDto processInstanceQueryDto = null;
        CountResultDto response = api.queryProcessInstancesCount(processInstanceQueryDto);

        // TODO: test validations
    }
    
    /**
     * Update Process Variable
     *
     * Sets a variable of a given process instance by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void setProcessInstanceVariableTest() {
        String id = null;
        String varName = null;
        VariableValueDto variableValueDto = null;
        api.setProcessInstanceVariable(id, varName, variableValueDto);

        // TODO: test validations
    }
    
    /**
     * Update Process Variable (Binary)
     *
     * Sets the serialized value for a binary variable or the binary value for a file variable.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void setProcessInstanceVariableBinaryTest() {
        String id = null;
        String varName = null;
        File data = null;
        String valueType = null;
        api.setProcessInstanceVariableBinary(id, varName, data, valueType);

        // TODO: test validations
    }
    
    /**
     * Set Job Retries Async (POST)
     *
     * Create a batch to set retries of jobs associated with given processes asynchronously.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void setRetriesByProcessTest() {
        SetJobRetriesByProcessDto setJobRetriesByProcessDto = null;
        BatchDto response = api.setRetriesByProcess(setJobRetriesByProcessDto);

        // TODO: test validations
    }
    
    /**
     * Set Job Retries Async Historic Query Based (POST)
     *
     * Create a batch to set retries of jobs asynchronously based on a historic process instance query.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void setRetriesByProcessHistoricQueryBasedTest() {
        SetJobRetriesByProcessDto setJobRetriesByProcessDto = null;
        BatchDto response = api.setRetriesByProcessHistoricQueryBased(setJobRetriesByProcessDto);

        // TODO: test validations
    }
    
    /**
     * Set Variables Async (POST)
     *
     * Update or create runtime process variables in the root scope of process instances.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void setVariablesAsyncOperationTest() {
        SetVariablesAsyncDto setVariablesAsyncDto = null;
        BatchDto response = api.setVariablesAsyncOperation(setVariablesAsyncDto);

        // TODO: test validations
    }
    
    /**
     * Activate/Suspend In Group
     *
     * Activates or suspends process instances by providing certain criteria:  # Activate/Suspend Process Instance By Process Definition Id * &#x60;suspend&#x60; * &#x60;processDefinitionId&#x60;  # Activate/Suspend Process Instance By Process Definition Key  * &#x60;suspend&#x60; * &#x60;processDefinitionKey&#x60; * &#x60;processDefinitionTenantId&#x60; * &#x60;processDefinitionWithoutTenantId&#x60;  # Activate/Suspend Process Instance In Group * &#x60;suspend&#x60; * &#x60;processInstanceIds&#x60; * &#x60;processInstanceQuery&#x60; * &#x60;historicProcessInstanceQuery&#x60;
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateSuspensionStateTest() {
        ProcessInstanceSuspensionStateDto processInstanceSuspensionStateDto = null;
        api.updateSuspensionState(processInstanceSuspensionStateDto);

        // TODO: test validations
    }
    
    /**
     * Activate/Suspend In Batch
     *
     * Activates or suspends process instances asynchronously with a list of process instance ids, a process instance query, and/or a historical process instance query.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateSuspensionStateAsyncOperationTest() {
        ProcessInstanceSuspensionStateAsyncDto processInstanceSuspensionStateAsyncDto = null;
        BatchDto response = api.updateSuspensionStateAsyncOperation(processInstanceSuspensionStateAsyncDto);

        // TODO: test validations
    }
    
    /**
     * Activate/Suspend Process Instance By Id
     *
     * Activates or suspends a given process instance by id.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateSuspensionStateByIdTest() {
        String id = null;
        SuspensionStateDto suspensionStateDto = null;
        api.updateSuspensionStateById(id, suspensionStateDto);

        // TODO: test validations
    }
    
}
