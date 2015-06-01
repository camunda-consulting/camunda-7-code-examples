package com.camunda.consulting.extendedSerialization.configuration;

import java.util.logging.Logger;

import org.camunda.spin.impl.json.jackson.format.JacksonJsonDataFormat;
import org.camunda.spin.spi.DataFormatConfigurator;
import org.joda.money.Money;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.camunda.consulting.extendedSerialization.serializer.LocalDateDeserializer;
import com.camunda.consulting.extendedSerialization.serializer.LocalDateSerializer;
import com.camunda.consulting.extendedSerialization.serializer.MoneyDeserializer;
import com.camunda.consulting.extendedSerialization.serializer.MoneySerializer;

public class JodaDataFormatConfigurator implements DataFormatConfigurator<JacksonJsonDataFormat> {
  
  private static final Logger log = Logger.getLogger(JodaDataFormatConfigurator.class.getName());

  @Override
  public Class<JacksonJsonDataFormat> getDataFormatClass() {
    return JacksonJsonDataFormat.class;
  }

  @Override
  public void configure(JacksonJsonDataFormat dataFormat) {
    log.info("Configure Money serializer and deserializer");
    ObjectMapper objectMapper = dataFormat.getObjectMapper();
    
    SimpleModule module = new SimpleModule();
    module.addDeserializer(Money.class, new MoneyDeserializer());
    module.addSerializer(Money.class, new MoneySerializer());
    module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
    module.addSerializer(LocalDate.class, new LocalDateSerializer());
    objectMapper.registerModule(module);
    log.info("additional serializers added to object mapper");
  }

}
