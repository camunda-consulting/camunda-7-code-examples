package com.camunda.consulting.engineplugin;

import com.camunda.consulting.config.Config;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.history.event.*;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.h2.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import java.util.List;

public class CustomHistoryEventHandler implements HistoryEventHandler {

  private static final Logger log = LoggerFactory.getLogger(CustomHistoryEventHandler.class);
  private ElasticsearchOperations elasticsearchOperations;

  public CustomHistoryEventHandler() {
    elasticsearchOperations = new Config().elasticsearchTemplate();

  }

  @Override
  public void handleEvent(HistoryEvent historyEvent) {
    IndexQuery indexQuery = new IndexQueryBuilder()
            .withId(historyEvent.getId())
            .withObject(historyEvent)
            .build();

    IndexCoordinates indexCoordinates = IndexCoordinates.of("historyevent");

    elasticsearchOperations.index(indexQuery, indexCoordinates);
  }

  @Override
  public void handleEvents(List<HistoryEvent> historyEvents) {
    for (HistoryEvent historyEvent : historyEvents) {
      handleEvent(historyEvent);
    }
  }
}
