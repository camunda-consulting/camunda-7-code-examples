package org.company.camunda.executionListeners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyStartListener  implements ExecutionListener{

	private static final Logger log = LoggerFactory.getLogger(MyStartListener.class);

    @Override
    public void notify(DelegateExecution execution) throws Exception {
       log.info(" MyStartListener: StartEvent executed ...");
    
    }
    
}
