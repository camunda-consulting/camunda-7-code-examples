

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ActivityTypes;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricExternalTaskLogEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricIncidentEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.event.HistoryEventTypes;
import org.camunda.bpm.engine.impl.history.event.UserOperationLogEntryEventEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyTestListener {
    

    @Autowired
	private RuntimeService runtime;

	private static final Logger log = LoggerFactory.getLogger(MyTestListener.class);

	@EventListener
	public void handleEvent(HistoryEvent historyEvent) {

        log.info("Receivend an HistoryEvent: \n" + historyEvent);
	}
}
