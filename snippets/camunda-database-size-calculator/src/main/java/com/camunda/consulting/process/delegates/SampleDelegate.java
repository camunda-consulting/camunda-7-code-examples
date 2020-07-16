package com.camunda.consulting.process.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("sample")
public class SampleDelegate implements JavaDelegate {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.debug("Called sample delegate");

        Long number = (Long) execution.getVariable("number");

        if(number == null){
            number = 0L;
        }
        else{
            number += 1;
        }

        execution.setVariable("number", number);
    }
}
