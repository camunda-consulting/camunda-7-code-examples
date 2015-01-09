package com.camunda.demo.connector.file.impl;

import org.camunda.connect.spi.ConnectorProvider;

import com.camunda.demo.connector.file.FileConnector;

public class FileConnectorProviderImpl implements ConnectorProvider {

  public String getConnectorId() {
    return FileConnector.ID;
  }

  public FileConnector createConnectorInstance() {
    return new FileConnectorImpl(FileConnector.ID);
  }

}
