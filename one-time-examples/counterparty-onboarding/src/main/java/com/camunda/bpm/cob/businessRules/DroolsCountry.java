package com.camunda.bpm.cob.businessRules;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DroolsCountry {

    private String name;
    private String hint;
    private boolean highRisk = false;

}
