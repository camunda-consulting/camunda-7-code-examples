package org.company.camunda.historyListeners;


import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyHistoryListener {
    

    @Autowired
	private RuntimeService runtime;

	private static final Logger log = LoggerFactory.getLogger(MyHistoryListener.class);

	@EventListener
	public void handleEvent(HistoryEvent historyEvent) {

        log.info("Receivend an HistoryEvent: \n" + historyEvent);
	}
}
