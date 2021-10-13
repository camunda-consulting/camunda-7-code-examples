package com.camunda.consulting.openapi.client.handler;

import com.camunda.consulting.openapi.client.handler.ApiClient;

import com.camunda.consulting.openapi.client.model.CountResultDto;
import com.camunda.consulting.openapi.client.model.ExceptionDto;
import com.camunda.consulting.openapi.client.model.HistoricTaskInstanceDto;
import com.camunda.consulting.openapi.client.model.HistoricTaskInstanceQueryDto;
import com.camunda.consulting.openapi.client.model.HistoricTaskInstanceReportResultDto;
import java.time.OffsetDateTime;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-10-13T17:49:51.183809+02:00[Europe/Berlin]")
@Component("com.camunda.consulting.openapi.client.handler.HistoricTaskInstanceApi")
public class HistoricTaskInstanceApi {
    private ApiClient apiClient;

    public HistoricTaskInstanceApi() {
        this(new ApiClient());
    }

    @Autowired
    public HistoricTaskInstanceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Get Task Report (Historic)
     * Retrieves a report of completed tasks. When the report type is set to &#x60;count&#x60;, the report contains a list of completed task counts where an entry contains the task name, the definition key of the task, the process definition id, the process definition key, the process definition name and the count of how many tasks were completed for the specified key in a given period. When the report type is set to &#x60;duration&#x60;, the report contains a minimum, maximum and average duration value of all completed task instances in a given period.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;completedAfter&#x60; parameter is supplied, but the date format is wrong. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param reportType **Mandatory.** Specifies the kind of the report to execute. To retrieve a report about the duration of process instances the value must be set to &#x60;duration&#x60;. For a report of the completed tasks in a specific timespan the value must be set to &#x60;count&#x60;. (optional)
     * @param periodUnit When the report type is set to &#x60;duration&#x60;, this parameter is **mandatory**. Specifies the granularity of the report. Valid values are &#x60;month&#x60; and &#x60;quarter&#x60;. (optional)
     * @param completedBefore Restrict to tasks that were completed before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param completedAfter Restrict to tasks that were completed after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param groupBy When the report type is set to &#x60;count&#x60;, this parameter is **mandatory**. Groups the tasks report by a given criterion. Valid values are &#x60;taskName&#x60; and &#x60;processDefinition&#x60;. (optional)
     * @return List&lt;HistoricTaskInstanceReportResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricTaskInstanceReportResultDto> getHistoricTaskInstanceReport(String reportType, String periodUnit, OffsetDateTime completedBefore, OffsetDateTime completedAfter, String groupBy) throws RestClientException {
        return getHistoricTaskInstanceReportWithHttpInfo(reportType, periodUnit, completedBefore, completedAfter, groupBy).getBody();
    }

    /**
     * Get Task Report (Historic)
     * Retrieves a report of completed tasks. When the report type is set to &#x60;count&#x60;, the report contains a list of completed task counts where an entry contains the task name, the definition key of the task, the process definition id, the process definition key, the process definition name and the count of how many tasks were completed for the specified key in a given period. When the report type is set to &#x60;duration&#x60;, the report contains a minimum, maximum and average duration value of all completed task instances in a given period.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;completedAfter&#x60; parameter is supplied, but the date format is wrong. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param reportType **Mandatory.** Specifies the kind of the report to execute. To retrieve a report about the duration of process instances the value must be set to &#x60;duration&#x60;. For a report of the completed tasks in a specific timespan the value must be set to &#x60;count&#x60;. (optional)
     * @param periodUnit When the report type is set to &#x60;duration&#x60;, this parameter is **mandatory**. Specifies the granularity of the report. Valid values are &#x60;month&#x60; and &#x60;quarter&#x60;. (optional)
     * @param completedBefore Restrict to tasks that were completed before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param completedAfter Restrict to tasks that were completed after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param groupBy When the report type is set to &#x60;count&#x60;, this parameter is **mandatory**. Groups the tasks report by a given criterion. Valid values are &#x60;taskName&#x60; and &#x60;processDefinition&#x60;. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricTaskInstanceReportResultDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricTaskInstanceReportResultDto>> getHistoricTaskInstanceReportWithHttpInfo(String reportType, String periodUnit, OffsetDateTime completedBefore, OffsetDateTime completedAfter, String groupBy) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/task/report", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "reportType", reportType));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "periodUnit", periodUnit));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "completedBefore", completedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "completedAfter", completedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "groupBy", groupBy));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<HistoricTaskInstanceReportResultDto>> returnType = new ParameterizedTypeReference<List<HistoricTaskInstanceReportResultDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Tasks (Historic)
     * Queries for historic tasks that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Task Count](https://docs.camunda.org/manual/7.16/reference/rest/history/task/get-task-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskId Filter by task id. (optional)
     * @param taskParentTaskId Filter by parent task id. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceBusinessKey Filter by process instance business key. (optional)
     * @param processInstanceBusinessKeyIn Filter by process instances with one of the give business keys. The keys need to be in a comma-separated list. (optional)
     * @param processInstanceBusinessKeyLike Filter by  process instance business key that has the parameter value as a substring. (optional)
     * @param executionId Filter by the id of the execution that executed the task. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Restrict to tasks that belong to a process definition with the given key. (optional)
     * @param processDefinitionName Restrict to tasks that belong to a process definition with the given name. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by the id of the case execution that executed the task. (optional)
     * @param caseDefinitionId Filter by case definition id. (optional)
     * @param caseDefinitionKey Restrict to tasks that belong to a case definition with the given key. (optional)
     * @param caseDefinitionName Restrict to tasks that belong to a case definition with the given name. (optional)
     * @param activityInstanceIdIn Only include tasks which belong to one of the passed comma-separated activity instance ids. (optional)
     * @param taskName Restrict to tasks that have the given name. (optional)
     * @param taskNameLike Restrict to tasks that have a name with the given parameter value as substring. (optional)
     * @param taskDescription Restrict to tasks that have the given description. (optional)
     * @param taskDescriptionLike Restrict to tasks that have a description that has the parameter value as a substring. (optional)
     * @param taskDefinitionKey Restrict to tasks that have the given key. (optional)
     * @param taskDefinitionKeyIn Restrict to tasks that have one of the passed comma-separated task definition keys. (optional)
     * @param taskDeleteReason Restrict to tasks that have the given delete reason. (optional)
     * @param taskDeleteReasonLike Restrict to tasks that have a delete reason that has the parameter value as a substring. (optional)
     * @param taskAssignee Restrict to tasks that the given user is assigned to. (optional)
     * @param taskAssigneeLike Restrict to tasks that are assigned to users with the parameter value as a substring. (optional)
     * @param taskOwner Restrict to tasks that the given user owns. (optional)
     * @param taskOwnerLike Restrict to tasks that are owned by users with the parameter value as a substring. (optional)
     * @param taskPriority Restrict to tasks that have the given priority. (optional)
     * @param assigned If set to &#x60;true&#x60;, restricts the query to all tasks that are assigned. (optional)
     * @param unassigned If set to &#x60;true&#x60;, restricts the query to all tasks that are unassigned. (optional)
     * @param finished Only include finished tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param unfinished Only include unfinished tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param processFinished Only include tasks of finished processes. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param processUnfinished Only include tasks of unfinished processes. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskDueDate Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskDueDateBefore Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskDueDateAfter Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param withoutTaskDueDate Only include tasks which have no due date. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskFollowUpDate Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskFollowUpDateBefore Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskFollowUpDateAfter Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedBefore Restrict to tasks that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to tasks that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to tasks that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to tasks that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A task instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic task instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskVariables Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.   Valid operator values are: * &#x60;eq&#x60; - equal to; * &#x60;neq&#x60; - not equal to; * &#x60;gt&#x60; - greater than; * &#x60;gteq&#x60; - greater than or equal to; * &#x60;lt&#x60; - lower than; * &#x60;lteq&#x60; - lower than or equal to; * &#x60;like&#x60;.  &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include tasks that belong to process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.   Valid operator values are: * &#x60;eq&#x60; - equal to; * &#x60;neq&#x60; - not equal to; * &#x60;gt&#x60; - greater than; * &#x60;gteq&#x60; - greater than or equal to; * &#x60;lt&#x60; - lower than; * &#x60;lteq&#x60; - lower than or equal to; * &#x60;like&#x60;; * &#x60;notLike&#x60;.  &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match the variable name provided in &#x60;taskVariables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match the variable value provided in &#x60;taskVariables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param taskInvolvedUser Restrict to tasks with a historic identity link to the given user. (optional)
     * @param taskInvolvedGroup Restrict to tasks with a historic identity link to the given group. (optional)
     * @param taskHadCandidateUser Restrict to tasks with a historic identity link to the given candidate user. (optional)
     * @param taskHadCandidateGroup Restrict to tasks with a historic identity link to the given candidate group. (optional)
     * @param withCandidateGroups Only include tasks which have a candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withoutCandidateGroups Only include tasks which have no candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return List&lt;HistoricTaskInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricTaskInstanceDto> getHistoricTaskInstances(String taskId, String taskParentTaskId, String processInstanceId, String processInstanceBusinessKey, String processInstanceBusinessKeyIn, String processInstanceBusinessKeyLike, String executionId, String processDefinitionId, String processDefinitionKey, String processDefinitionName, String caseInstanceId, String caseExecutionId, String caseDefinitionId, String caseDefinitionKey, String caseDefinitionName, String activityInstanceIdIn, String taskName, String taskNameLike, String taskDescription, String taskDescriptionLike, String taskDefinitionKey, String taskDefinitionKeyIn, String taskDeleteReason, String taskDeleteReasonLike, String taskAssignee, String taskAssigneeLike, String taskOwner, String taskOwnerLike, Integer taskPriority, Boolean assigned, Boolean unassigned, Boolean finished, Boolean unfinished, Boolean processFinished, Boolean processUnfinished, OffsetDateTime taskDueDate, OffsetDateTime taskDueDateBefore, OffsetDateTime taskDueDateAfter, Boolean withoutTaskDueDate, OffsetDateTime taskFollowUpDate, OffsetDateTime taskFollowUpDateBefore, OffsetDateTime taskFollowUpDateAfter, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, String tenantIdIn, Boolean withoutTenantId, String taskVariables, String processVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String taskInvolvedUser, String taskInvolvedGroup, String taskHadCandidateUser, String taskHadCandidateGroup, Boolean withCandidateGroups, Boolean withoutCandidateGroups, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        return getHistoricTaskInstancesWithHttpInfo(taskId, taskParentTaskId, processInstanceId, processInstanceBusinessKey, processInstanceBusinessKeyIn, processInstanceBusinessKeyLike, executionId, processDefinitionId, processDefinitionKey, processDefinitionName, caseInstanceId, caseExecutionId, caseDefinitionId, caseDefinitionKey, caseDefinitionName, activityInstanceIdIn, taskName, taskNameLike, taskDescription, taskDescriptionLike, taskDefinitionKey, taskDefinitionKeyIn, taskDeleteReason, taskDeleteReasonLike, taskAssignee, taskAssigneeLike, taskOwner, taskOwnerLike, taskPriority, assigned, unassigned, finished, unfinished, processFinished, processUnfinished, taskDueDate, taskDueDateBefore, taskDueDateAfter, withoutTaskDueDate, taskFollowUpDate, taskFollowUpDateBefore, taskFollowUpDateAfter, startedBefore, startedAfter, finishedBefore, finishedAfter, tenantIdIn, withoutTenantId, taskVariables, processVariables, variableNamesIgnoreCase, variableValuesIgnoreCase, taskInvolvedUser, taskInvolvedGroup, taskHadCandidateUser, taskHadCandidateGroup, withCandidateGroups, withoutCandidateGroups, sortBy, sortOrder, firstResult, maxResults).getBody();
    }

    /**
     * Get Tasks (Historic)
     * Queries for historic tasks that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Task Count](https://docs.camunda.org/manual/7.16/reference/rest/history/task/get-task-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskId Filter by task id. (optional)
     * @param taskParentTaskId Filter by parent task id. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceBusinessKey Filter by process instance business key. (optional)
     * @param processInstanceBusinessKeyIn Filter by process instances with one of the give business keys. The keys need to be in a comma-separated list. (optional)
     * @param processInstanceBusinessKeyLike Filter by  process instance business key that has the parameter value as a substring. (optional)
     * @param executionId Filter by the id of the execution that executed the task. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Restrict to tasks that belong to a process definition with the given key. (optional)
     * @param processDefinitionName Restrict to tasks that belong to a process definition with the given name. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by the id of the case execution that executed the task. (optional)
     * @param caseDefinitionId Filter by case definition id. (optional)
     * @param caseDefinitionKey Restrict to tasks that belong to a case definition with the given key. (optional)
     * @param caseDefinitionName Restrict to tasks that belong to a case definition with the given name. (optional)
     * @param activityInstanceIdIn Only include tasks which belong to one of the passed comma-separated activity instance ids. (optional)
     * @param taskName Restrict to tasks that have the given name. (optional)
     * @param taskNameLike Restrict to tasks that have a name with the given parameter value as substring. (optional)
     * @param taskDescription Restrict to tasks that have the given description. (optional)
     * @param taskDescriptionLike Restrict to tasks that have a description that has the parameter value as a substring. (optional)
     * @param taskDefinitionKey Restrict to tasks that have the given key. (optional)
     * @param taskDefinitionKeyIn Restrict to tasks that have one of the passed comma-separated task definition keys. (optional)
     * @param taskDeleteReason Restrict to tasks that have the given delete reason. (optional)
     * @param taskDeleteReasonLike Restrict to tasks that have a delete reason that has the parameter value as a substring. (optional)
     * @param taskAssignee Restrict to tasks that the given user is assigned to. (optional)
     * @param taskAssigneeLike Restrict to tasks that are assigned to users with the parameter value as a substring. (optional)
     * @param taskOwner Restrict to tasks that the given user owns. (optional)
     * @param taskOwnerLike Restrict to tasks that are owned by users with the parameter value as a substring. (optional)
     * @param taskPriority Restrict to tasks that have the given priority. (optional)
     * @param assigned If set to &#x60;true&#x60;, restricts the query to all tasks that are assigned. (optional)
     * @param unassigned If set to &#x60;true&#x60;, restricts the query to all tasks that are unassigned. (optional)
     * @param finished Only include finished tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param unfinished Only include unfinished tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param processFinished Only include tasks of finished processes. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param processUnfinished Only include tasks of unfinished processes. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskDueDate Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskDueDateBefore Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskDueDateAfter Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param withoutTaskDueDate Only include tasks which have no due date. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskFollowUpDate Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskFollowUpDateBefore Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskFollowUpDateAfter Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedBefore Restrict to tasks that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to tasks that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to tasks that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to tasks that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A task instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic task instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskVariables Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.   Valid operator values are: * &#x60;eq&#x60; - equal to; * &#x60;neq&#x60; - not equal to; * &#x60;gt&#x60; - greater than; * &#x60;gteq&#x60; - greater than or equal to; * &#x60;lt&#x60; - lower than; * &#x60;lteq&#x60; - lower than or equal to; * &#x60;like&#x60;.  &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include tasks that belong to process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.   Valid operator values are: * &#x60;eq&#x60; - equal to; * &#x60;neq&#x60; - not equal to; * &#x60;gt&#x60; - greater than; * &#x60;gteq&#x60; - greater than or equal to; * &#x60;lt&#x60; - lower than; * &#x60;lteq&#x60; - lower than or equal to; * &#x60;like&#x60;; * &#x60;notLike&#x60;.  &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match the variable name provided in &#x60;taskVariables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match the variable value provided in &#x60;taskVariables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param taskInvolvedUser Restrict to tasks with a historic identity link to the given user. (optional)
     * @param taskInvolvedGroup Restrict to tasks with a historic identity link to the given group. (optional)
     * @param taskHadCandidateUser Restrict to tasks with a historic identity link to the given candidate user. (optional)
     * @param taskHadCandidateGroup Restrict to tasks with a historic identity link to the given candidate group. (optional)
     * @param withCandidateGroups Only include tasks which have a candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withoutCandidateGroups Only include tasks which have no candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param sortBy Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. (optional)
     * @param sortOrder Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. (optional)
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @return ResponseEntity&lt;List&lt;HistoricTaskInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricTaskInstanceDto>> getHistoricTaskInstancesWithHttpInfo(String taskId, String taskParentTaskId, String processInstanceId, String processInstanceBusinessKey, String processInstanceBusinessKeyIn, String processInstanceBusinessKeyLike, String executionId, String processDefinitionId, String processDefinitionKey, String processDefinitionName, String caseInstanceId, String caseExecutionId, String caseDefinitionId, String caseDefinitionKey, String caseDefinitionName, String activityInstanceIdIn, String taskName, String taskNameLike, String taskDescription, String taskDescriptionLike, String taskDefinitionKey, String taskDefinitionKeyIn, String taskDeleteReason, String taskDeleteReasonLike, String taskAssignee, String taskAssigneeLike, String taskOwner, String taskOwnerLike, Integer taskPriority, Boolean assigned, Boolean unassigned, Boolean finished, Boolean unfinished, Boolean processFinished, Boolean processUnfinished, OffsetDateTime taskDueDate, OffsetDateTime taskDueDateBefore, OffsetDateTime taskDueDateAfter, Boolean withoutTaskDueDate, OffsetDateTime taskFollowUpDate, OffsetDateTime taskFollowUpDateBefore, OffsetDateTime taskFollowUpDateAfter, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, String tenantIdIn, Boolean withoutTenantId, String taskVariables, String processVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String taskInvolvedUser, String taskInvolvedGroup, String taskHadCandidateUser, String taskHadCandidateGroup, Boolean withCandidateGroups, Boolean withoutCandidateGroups, String sortBy, String sortOrder, Integer firstResult, Integer maxResults) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/task", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskId", taskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskParentTaskId", taskParentTaskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKey", processInstanceBusinessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyIn", processInstanceBusinessKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyLike", processInstanceBusinessKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionName", processDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionId", caseExecutionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionId", caseDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionKey", caseDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionName", caseDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskName", taskName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskNameLike", taskNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDescription", taskDescription));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDescriptionLike", taskDescriptionLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDefinitionKey", taskDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDefinitionKeyIn", taskDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDeleteReason", taskDeleteReason));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDeleteReasonLike", taskDeleteReasonLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskAssignee", taskAssignee));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskAssigneeLike", taskAssigneeLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskOwner", taskOwner));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskOwnerLike", taskOwnerLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskPriority", taskPriority));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigned", assigned));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "unassigned", unassigned));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finished", finished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "unfinished", unfinished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processFinished", processFinished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processUnfinished", processUnfinished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDueDate", taskDueDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDueDateBefore", taskDueDateBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDueDateAfter", taskDueDateAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTaskDueDate", withoutTaskDueDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskFollowUpDate", taskFollowUpDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskFollowUpDateBefore", taskFollowUpDateBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskFollowUpDateAfter", taskFollowUpDateAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedBefore", startedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedAfter", startedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedBefore", finishedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedAfter", finishedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskVariables", taskVariables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processVariables", processVariables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNamesIgnoreCase", variableNamesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValuesIgnoreCase", variableValuesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskInvolvedUser", taskInvolvedUser));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskInvolvedGroup", taskInvolvedGroup));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskHadCandidateUser", taskHadCandidateUser));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskHadCandidateGroup", taskHadCandidateGroup));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withCandidateGroups", withCandidateGroups));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutCandidateGroups", withoutCandidateGroups));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortOrder", sortOrder));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<HistoricTaskInstanceDto>> returnType = new ParameterizedTypeReference<List<HistoricTaskInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Task Count
     * Queries for the number of historic tasks that fulfill the given parameters. Takes the same parameters as the [Get Tasks (Historic)](https://docs.camunda.org/manual/7.16/reference/rest/history/task/get-task-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskId Filter by task id. (optional)
     * @param taskParentTaskId Filter by parent task id. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceBusinessKey Filter by process instance business key. (optional)
     * @param processInstanceBusinessKeyIn Filter by process instances with one of the give business keys. The keys need to be in a comma-separated list. (optional)
     * @param processInstanceBusinessKeyLike Filter by  process instance business key that has the parameter value as a substring. (optional)
     * @param executionId Filter by the id of the execution that executed the task. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Restrict to tasks that belong to a process definition with the given key. (optional)
     * @param processDefinitionName Restrict to tasks that belong to a process definition with the given name. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by the id of the case execution that executed the task. (optional)
     * @param caseDefinitionId Filter by case definition id. (optional)
     * @param caseDefinitionKey Restrict to tasks that belong to a case definition with the given key. (optional)
     * @param caseDefinitionName Restrict to tasks that belong to a case definition with the given name. (optional)
     * @param activityInstanceIdIn Only include tasks which belong to one of the passed comma-separated activity instance ids. (optional)
     * @param taskName Restrict to tasks that have the given name. (optional)
     * @param taskNameLike Restrict to tasks that have a name with the given parameter value as substring. (optional)
     * @param taskDescription Restrict to tasks that have the given description. (optional)
     * @param taskDescriptionLike Restrict to tasks that have a description that has the parameter value as a substring. (optional)
     * @param taskDefinitionKey Restrict to tasks that have the given key. (optional)
     * @param taskDefinitionKeyIn Restrict to tasks that have one of the passed comma-separated task definition keys. (optional)
     * @param taskDeleteReason Restrict to tasks that have the given delete reason. (optional)
     * @param taskDeleteReasonLike Restrict to tasks that have a delete reason that has the parameter value as a substring. (optional)
     * @param taskAssignee Restrict to tasks that the given user is assigned to. (optional)
     * @param taskAssigneeLike Restrict to tasks that are assigned to users with the parameter value as a substring. (optional)
     * @param taskOwner Restrict to tasks that the given user owns. (optional)
     * @param taskOwnerLike Restrict to tasks that are owned by users with the parameter value as a substring. (optional)
     * @param taskPriority Restrict to tasks that have the given priority. (optional)
     * @param assigned If set to &#x60;true&#x60;, restricts the query to all tasks that are assigned. (optional)
     * @param unassigned If set to &#x60;true&#x60;, restricts the query to all tasks that are unassigned. (optional)
     * @param finished Only include finished tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param unfinished Only include unfinished tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param processFinished Only include tasks of finished processes. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param processUnfinished Only include tasks of unfinished processes. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskDueDate Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskDueDateBefore Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskDueDateAfter Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param withoutTaskDueDate Only include tasks which have no due date. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskFollowUpDate Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskFollowUpDateBefore Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskFollowUpDateAfter Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedBefore Restrict to tasks that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to tasks that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to tasks that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to tasks that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A task instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic task instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskVariables Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.   Valid operator values are: * &#x60;eq&#x60; - equal to; * &#x60;neq&#x60; - not equal to; * &#x60;gt&#x60; - greater than; * &#x60;gteq&#x60; - greater than or equal to; * &#x60;lt&#x60; - lower than; * &#x60;lteq&#x60; - lower than or equal to; * &#x60;like&#x60;.  &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include tasks that belong to process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.   Valid operator values are: * &#x60;eq&#x60; - equal to; * &#x60;neq&#x60; - not equal to; * &#x60;gt&#x60; - greater than; * &#x60;gteq&#x60; - greater than or equal to; * &#x60;lt&#x60; - lower than; * &#x60;lteq&#x60; - lower than or equal to; * &#x60;like&#x60;; * &#x60;notLike&#x60;.  &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match the variable name provided in &#x60;taskVariables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match the variable value provided in &#x60;taskVariables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param taskInvolvedUser Restrict to tasks with a historic identity link to the given user. (optional)
     * @param taskInvolvedGroup Restrict to tasks with a historic identity link to the given group. (optional)
     * @param taskHadCandidateUser Restrict to tasks with a historic identity link to the given candidate user. (optional)
     * @param taskHadCandidateGroup Restrict to tasks with a historic identity link to the given candidate group. (optional)
     * @param withCandidateGroups Only include tasks which have a candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withoutCandidateGroups Only include tasks which have no candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto getHistoricTaskInstancesCount(String taskId, String taskParentTaskId, String processInstanceId, String processInstanceBusinessKey, String processInstanceBusinessKeyIn, String processInstanceBusinessKeyLike, String executionId, String processDefinitionId, String processDefinitionKey, String processDefinitionName, String caseInstanceId, String caseExecutionId, String caseDefinitionId, String caseDefinitionKey, String caseDefinitionName, String activityInstanceIdIn, String taskName, String taskNameLike, String taskDescription, String taskDescriptionLike, String taskDefinitionKey, String taskDefinitionKeyIn, String taskDeleteReason, String taskDeleteReasonLike, String taskAssignee, String taskAssigneeLike, String taskOwner, String taskOwnerLike, Integer taskPriority, Boolean assigned, Boolean unassigned, Boolean finished, Boolean unfinished, Boolean processFinished, Boolean processUnfinished, OffsetDateTime taskDueDate, OffsetDateTime taskDueDateBefore, OffsetDateTime taskDueDateAfter, Boolean withoutTaskDueDate, OffsetDateTime taskFollowUpDate, OffsetDateTime taskFollowUpDateBefore, OffsetDateTime taskFollowUpDateAfter, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, String tenantIdIn, Boolean withoutTenantId, String taskVariables, String processVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String taskInvolvedUser, String taskInvolvedGroup, String taskHadCandidateUser, String taskHadCandidateGroup, Boolean withCandidateGroups, Boolean withoutCandidateGroups) throws RestClientException {
        return getHistoricTaskInstancesCountWithHttpInfo(taskId, taskParentTaskId, processInstanceId, processInstanceBusinessKey, processInstanceBusinessKeyIn, processInstanceBusinessKeyLike, executionId, processDefinitionId, processDefinitionKey, processDefinitionName, caseInstanceId, caseExecutionId, caseDefinitionId, caseDefinitionKey, caseDefinitionName, activityInstanceIdIn, taskName, taskNameLike, taskDescription, taskDescriptionLike, taskDefinitionKey, taskDefinitionKeyIn, taskDeleteReason, taskDeleteReasonLike, taskAssignee, taskAssigneeLike, taskOwner, taskOwnerLike, taskPriority, assigned, unassigned, finished, unfinished, processFinished, processUnfinished, taskDueDate, taskDueDateBefore, taskDueDateAfter, withoutTaskDueDate, taskFollowUpDate, taskFollowUpDateBefore, taskFollowUpDateAfter, startedBefore, startedAfter, finishedBefore, finishedAfter, tenantIdIn, withoutTenantId, taskVariables, processVariables, variableNamesIgnoreCase, variableValuesIgnoreCase, taskInvolvedUser, taskInvolvedGroup, taskHadCandidateUser, taskHadCandidateGroup, withCandidateGroups, withoutCandidateGroups).getBody();
    }

    /**
     * Get Task Count
     * Queries for the number of historic tasks that fulfill the given parameters. Takes the same parameters as the [Get Tasks (Historic)](https://docs.camunda.org/manual/7.16/reference/rest/history/task/get-task-query/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param taskId Filter by task id. (optional)
     * @param taskParentTaskId Filter by parent task id. (optional)
     * @param processInstanceId Filter by process instance id. (optional)
     * @param processInstanceBusinessKey Filter by process instance business key. (optional)
     * @param processInstanceBusinessKeyIn Filter by process instances with one of the give business keys. The keys need to be in a comma-separated list. (optional)
     * @param processInstanceBusinessKeyLike Filter by  process instance business key that has the parameter value as a substring. (optional)
     * @param executionId Filter by the id of the execution that executed the task. (optional)
     * @param processDefinitionId Filter by process definition id. (optional)
     * @param processDefinitionKey Restrict to tasks that belong to a process definition with the given key. (optional)
     * @param processDefinitionName Restrict to tasks that belong to a process definition with the given name. (optional)
     * @param caseInstanceId Filter by case instance id. (optional)
     * @param caseExecutionId Filter by the id of the case execution that executed the task. (optional)
     * @param caseDefinitionId Filter by case definition id. (optional)
     * @param caseDefinitionKey Restrict to tasks that belong to a case definition with the given key. (optional)
     * @param caseDefinitionName Restrict to tasks that belong to a case definition with the given name. (optional)
     * @param activityInstanceIdIn Only include tasks which belong to one of the passed comma-separated activity instance ids. (optional)
     * @param taskName Restrict to tasks that have the given name. (optional)
     * @param taskNameLike Restrict to tasks that have a name with the given parameter value as substring. (optional)
     * @param taskDescription Restrict to tasks that have the given description. (optional)
     * @param taskDescriptionLike Restrict to tasks that have a description that has the parameter value as a substring. (optional)
     * @param taskDefinitionKey Restrict to tasks that have the given key. (optional)
     * @param taskDefinitionKeyIn Restrict to tasks that have one of the passed comma-separated task definition keys. (optional)
     * @param taskDeleteReason Restrict to tasks that have the given delete reason. (optional)
     * @param taskDeleteReasonLike Restrict to tasks that have a delete reason that has the parameter value as a substring. (optional)
     * @param taskAssignee Restrict to tasks that the given user is assigned to. (optional)
     * @param taskAssigneeLike Restrict to tasks that are assigned to users with the parameter value as a substring. (optional)
     * @param taskOwner Restrict to tasks that the given user owns. (optional)
     * @param taskOwnerLike Restrict to tasks that are owned by users with the parameter value as a substring. (optional)
     * @param taskPriority Restrict to tasks that have the given priority. (optional)
     * @param assigned If set to &#x60;true&#x60;, restricts the query to all tasks that are assigned. (optional)
     * @param unassigned If set to &#x60;true&#x60;, restricts the query to all tasks that are unassigned. (optional)
     * @param finished Only include finished tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param unfinished Only include unfinished tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param processFinished Only include tasks of finished processes. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param processUnfinished Only include tasks of unfinished processes. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskDueDate Restrict to tasks that are due on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskDueDateBefore Restrict to tasks that are due before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskDueDateAfter Restrict to tasks that are due after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param withoutTaskDueDate Only include tasks which have no due date. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskFollowUpDate Restrict to tasks that have a followUp date on the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskFollowUpDateBefore Restrict to tasks that have a followUp date before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param taskFollowUpDateAfter Restrict to tasks that have a followUp date after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedBefore Restrict to tasks that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param startedAfter Restrict to tasks that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedBefore Restrict to tasks that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param finishedAfter Restrict to tasks that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. (optional)
     * @param tenantIdIn Filter by a comma-separated list of tenant ids. A task instance must have one of the given tenant ids. (optional)
     * @param withoutTenantId Only include historic task instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param taskVariables Only include tasks that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.   Valid operator values are: * &#x60;eq&#x60; - equal to; * &#x60;neq&#x60; - not equal to; * &#x60;gt&#x60; - greater than; * &#x60;gteq&#x60; - greater than or equal to; * &#x60;lt&#x60; - lower than; * &#x60;lteq&#x60; - lower than or equal to; * &#x60;like&#x60;.  &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param processVariables Only include tasks that belong to process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.   Valid operator values are: * &#x60;eq&#x60; - equal to; * &#x60;neq&#x60; - not equal to; * &#x60;gt&#x60; - greater than; * &#x60;gteq&#x60; - greater than or equal to; * &#x60;lt&#x60; - lower than; * &#x60;lteq&#x60; - lower than or equal to; * &#x60;like&#x60;; * &#x60;notLike&#x60;.  &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. (optional)
     * @param variableNamesIgnoreCase Match the variable name provided in &#x60;taskVariables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. (optional)
     * @param variableValuesIgnoreCase Match the variable value provided in &#x60;taskVariables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. (optional)
     * @param taskInvolvedUser Restrict to tasks with a historic identity link to the given user. (optional)
     * @param taskInvolvedGroup Restrict to tasks with a historic identity link to the given group. (optional)
     * @param taskHadCandidateUser Restrict to tasks with a historic identity link to the given candidate user. (optional)
     * @param taskHadCandidateGroup Restrict to tasks with a historic identity link to the given candidate group. (optional)
     * @param withCandidateGroups Only include tasks which have a candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @param withoutCandidateGroups Only include tasks which have no candidate group. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> getHistoricTaskInstancesCountWithHttpInfo(String taskId, String taskParentTaskId, String processInstanceId, String processInstanceBusinessKey, String processInstanceBusinessKeyIn, String processInstanceBusinessKeyLike, String executionId, String processDefinitionId, String processDefinitionKey, String processDefinitionName, String caseInstanceId, String caseExecutionId, String caseDefinitionId, String caseDefinitionKey, String caseDefinitionName, String activityInstanceIdIn, String taskName, String taskNameLike, String taskDescription, String taskDescriptionLike, String taskDefinitionKey, String taskDefinitionKeyIn, String taskDeleteReason, String taskDeleteReasonLike, String taskAssignee, String taskAssigneeLike, String taskOwner, String taskOwnerLike, Integer taskPriority, Boolean assigned, Boolean unassigned, Boolean finished, Boolean unfinished, Boolean processFinished, Boolean processUnfinished, OffsetDateTime taskDueDate, OffsetDateTime taskDueDateBefore, OffsetDateTime taskDueDateAfter, Boolean withoutTaskDueDate, OffsetDateTime taskFollowUpDate, OffsetDateTime taskFollowUpDateBefore, OffsetDateTime taskFollowUpDateAfter, OffsetDateTime startedBefore, OffsetDateTime startedAfter, OffsetDateTime finishedBefore, OffsetDateTime finishedAfter, String tenantIdIn, Boolean withoutTenantId, String taskVariables, String processVariables, Boolean variableNamesIgnoreCase, Boolean variableValuesIgnoreCase, String taskInvolvedUser, String taskInvolvedGroup, String taskHadCandidateUser, String taskHadCandidateGroup, Boolean withCandidateGroups, Boolean withoutCandidateGroups) throws RestClientException {
        Object postBody = null;
        
        String path = apiClient.expandPath("/history/task/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskId", taskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskParentTaskId", taskParentTaskId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceId", processInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKey", processInstanceBusinessKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyIn", processInstanceBusinessKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processInstanceBusinessKeyLike", processInstanceBusinessKeyLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "executionId", executionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionId", processDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionKey", processDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processDefinitionName", processDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseInstanceId", caseInstanceId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseExecutionId", caseExecutionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionId", caseDefinitionId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionKey", caseDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "caseDefinitionName", caseDefinitionName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "activityInstanceIdIn", activityInstanceIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskName", taskName));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskNameLike", taskNameLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDescription", taskDescription));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDescriptionLike", taskDescriptionLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDefinitionKey", taskDefinitionKey));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDefinitionKeyIn", taskDefinitionKeyIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDeleteReason", taskDeleteReason));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDeleteReasonLike", taskDeleteReasonLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskAssignee", taskAssignee));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskAssigneeLike", taskAssigneeLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskOwner", taskOwner));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskOwnerLike", taskOwnerLike));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskPriority", taskPriority));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "assigned", assigned));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "unassigned", unassigned));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finished", finished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "unfinished", unfinished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processFinished", processFinished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processUnfinished", processUnfinished));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDueDate", taskDueDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDueDateBefore", taskDueDateBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskDueDateAfter", taskDueDateAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTaskDueDate", withoutTaskDueDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskFollowUpDate", taskFollowUpDate));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskFollowUpDateBefore", taskFollowUpDateBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskFollowUpDateAfter", taskFollowUpDateAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedBefore", startedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "startedAfter", startedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedBefore", finishedBefore));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "finishedAfter", finishedAfter));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tenantIdIn", tenantIdIn));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutTenantId", withoutTenantId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskVariables", taskVariables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "processVariables", processVariables));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableNamesIgnoreCase", variableNamesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "variableValuesIgnoreCase", variableValuesIgnoreCase));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskInvolvedUser", taskInvolvedUser));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskInvolvedGroup", taskInvolvedGroup));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskHadCandidateUser", taskHadCandidateUser));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "taskHadCandidateGroup", taskHadCandidateGroup));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withCandidateGroups", withCandidateGroups));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "withoutCandidateGroups", withoutCandidateGroups));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = {  };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<CountResultDto> returnType = new ParameterizedTypeReference<CountResultDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Tasks (Historic) (POST)
     * Queries for historic tasks that fulfill the given parameters. This method is slightly more powerful than the [Get Tasks (Historic)](https://docs.camunda.org/manual/7.16/reference/rest/history/task/get-task-query/) method because it allows filtering by multiple process or task variables of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;. The size of the result set can be retrieved by using the [Get Task Count (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/task/post-task-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param historicTaskInstanceQueryDto  (optional)
     * @return List&lt;HistoricTaskInstanceDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<HistoricTaskInstanceDto> queryHistoricTaskInstances(Integer firstResult, Integer maxResults, HistoricTaskInstanceQueryDto historicTaskInstanceQueryDto) throws RestClientException {
        return queryHistoricTaskInstancesWithHttpInfo(firstResult, maxResults, historicTaskInstanceQueryDto).getBody();
    }

    /**
     * Get Tasks (Historic) (POST)
     * Queries for historic tasks that fulfill the given parameters. This method is slightly more powerful than the [Get Tasks (Historic)](https://docs.camunda.org/manual/7.16/reference/rest/history/task/get-task-query/) method because it allows filtering by multiple process or task variables of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;. The size of the result set can be retrieved by using the [Get Task Count (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/task/post-task-query-count/) method.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param firstResult Pagination of results. Specifies the index of the first result to return. (optional)
     * @param maxResults Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. (optional)
     * @param historicTaskInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;List&lt;HistoricTaskInstanceDto&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<HistoricTaskInstanceDto>> queryHistoricTaskInstancesWithHttpInfo(Integer firstResult, Integer maxResults, HistoricTaskInstanceQueryDto historicTaskInstanceQueryDto) throws RestClientException {
        Object postBody = historicTaskInstanceQueryDto;
        
        String path = apiClient.expandPath("/history/task", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "firstResult", firstResult));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "maxResults", maxResults));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = { 
            "application/json"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<HistoricTaskInstanceDto>> returnType = new ParameterizedTypeReference<List<HistoricTaskInstanceDto>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
    /**
     * Get Task Count (POST)
     * Queries for the number of historic tasks that fulfill the given parameters. Takes the same parameters as the [Get Tasks (Historic)](https://docs.camunda.org/manual/7.16/reference/rest/history/task/get-task-query/) method. Corresponds to the size of the result set of the [Get Tasks (Historic) (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/task/post-task-query/) method and takes the same parameters.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicTaskInstanceQueryDto  (optional)
     * @return CountResultDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public CountResultDto queryHistoricTaskInstancesCount(HistoricTaskInstanceQueryDto historicTaskInstanceQueryDto) throws RestClientException {
        return queryHistoricTaskInstancesCountWithHttpInfo(historicTaskInstanceQueryDto).getBody();
    }

    /**
     * Get Task Count (POST)
     * Queries for the number of historic tasks that fulfill the given parameters. Takes the same parameters as the [Get Tasks (Historic)](https://docs.camunda.org/manual/7.16/reference/rest/history/task/get-task-query/) method. Corresponds to the size of the result set of the [Get Tasks (Historic) (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/task/post-task-query/) method and takes the same parameters.
     * <p><b>200</b> - Request successful.
     * <p><b>400</b> - Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.
     * @param historicTaskInstanceQueryDto  (optional)
     * @return ResponseEntity&lt;CountResultDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<CountResultDto> queryHistoricTaskInstancesCountWithHttpInfo(HistoricTaskInstanceQueryDto historicTaskInstanceQueryDto) throws RestClientException {
        Object postBody = historicTaskInstanceQueryDto;
        
        String path = apiClient.expandPath("/history/task/count", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] contentTypes = { 
            "application/json"
         };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<CountResultDto> returnType = new ParameterizedTypeReference<CountResultDto>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, contentType, authNames, returnType);
    }
}
