package com.camunda.consulting.extendedSerialization.plugins;

import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.spin.DataFormats;
import org.camunda.spin.impl.json.jackson.format.JacksonJsonDataFormat;
import org.joda.money.Money;

import spinjar.com.fasterxml.jackson.databind.ObjectMapper;
import spinjar.com.fasterxml.jackson.databind.module.SimpleModule;

import com.camunda.consulting.extendedSerialization.serializer.MoneyDeserializer;
import com.camunda.consulting.extendedSerialization.serializer.MoneySerializer;

public class SerializerProcessEnginePlugin extends AbstractProcessEnginePlugin {
  
  private static final Logger log = Logger.getLogger(SerializerProcessEnginePlugin.class.getName());

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {
    log.info("postProcessEngineBuild");
    JacksonJsonDataFormat jsonDataFormat = (JacksonJsonDataFormat) DataFormats.getDataFormat("application/json");
    ObjectMapper objectMapper = jsonDataFormat.getObjectMapper();
    
    SimpleModule module = new SimpleModule();
    module.addDeserializer(Money.class, new MoneyDeserializer());
    module.addSerializer(Money.class, new MoneySerializer());
    objectMapper.registerModule(module);
    log.info("additional serializers registered");
  }

}
