package com.camunda.consulting.extendedSerialization.serializer;

import java.io.IOException;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate> {
  
  public LocalDateSerializer() {
    super(LocalDate.class);
  }

  @Override
  public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
    jgen.writeString(value.toString());
  }

}
