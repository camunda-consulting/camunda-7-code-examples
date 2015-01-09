package com.camunda.demo.connector.file;

import org.camunda.connect.spi.Connector;

public interface FileConnector extends Connector<FileRequest> {

  public static final String ID = "file-connector";

}
