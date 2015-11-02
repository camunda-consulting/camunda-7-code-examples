package com.camunda.demo.versicherungsneuantrag.adapter;

import java.io.IOException;

import de.kwsoft.mtext.adapters.ws.IntegrationAdapterException_Exception;

public interface MtextService {

  public byte[] generateAndDistributeDocument(String xml, String dataBinding) throws IntegrationAdapterException_Exception, IOException;

}
