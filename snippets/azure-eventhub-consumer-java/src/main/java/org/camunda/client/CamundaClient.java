package org.camunda.client;

import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;
import com.camunda.consulting.openapi.client.model.ProcessInstanceWithVariablesDto;
import com.camunda.consulting.openapi.client.model.StartProcessInstanceDto;
import com.camunda.consulting.openapi.client.model.VariableValueDto;
import lombok.extern.slf4j.Slf4j;

// Depends on https://github.com/camunda-consulting/code/tree/master/snippets/camunda-openapi-client

@Slf4j
public class CamundaClient {

    //key of process definition you would like to start
    public static final String PROCESSKEY = "SecondProcess";

    public static void startProcess(String payload, String businessKey) {

        //base path of Camunda REST API to connect to
        ApiClient client = Configuration.getDefaultApiClient()
                .setBasePath("http://localhost:8080/engine-rest");

        ProcessDefinitionApi processDefinitionApi = new ProcessDefinitionApi(client);

        //process variable name to sue for payload can be adjusted here
        StartProcessInstanceDto startPIDTO = new StartProcessInstanceDto()
                .businessKey(businessKey)
                .putVariablesItem("payload", (new VariableValueDto()).value(payload).type("String"));
        try {
            ProcessInstanceWithVariablesDto pi = processDefinitionApi.startProcessInstanceByKey(PROCESSKEY, startPIDTO);
            log.info("Started process instance id {}", pi.getId());
        } catch (ApiException e) {
            log.error("Exception starting process instance by key {}. Status code: {} \n Reason: {} \n Headers: {}",
                    PROCESSKEY, e.getCode(), e.getResponseBody(), e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}