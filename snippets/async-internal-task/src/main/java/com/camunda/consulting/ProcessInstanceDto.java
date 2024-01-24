package com.camunda.consulting;

public record ProcessInstanceDto(String id, String processDefinitionId, String tenantId, String businessKey) {}
