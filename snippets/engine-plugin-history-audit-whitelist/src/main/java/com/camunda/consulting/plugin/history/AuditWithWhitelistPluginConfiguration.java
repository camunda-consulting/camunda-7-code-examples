package com.camunda.consulting.plugin.history;

import lombok.Data;

@Data
public class AuditWithWhitelistPluginConfiguration {

    protected String variableWhitelist = "";
}
