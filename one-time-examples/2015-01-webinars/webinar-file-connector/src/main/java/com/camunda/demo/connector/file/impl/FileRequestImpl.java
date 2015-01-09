package com.camunda.demo.connector.file.impl;

import org.camunda.connect.impl.AbstractConnectorRequest;

import com.camunda.demo.connector.file.FileConnector;
import com.camunda.demo.connector.file.FileRequest;
import com.camunda.demo.connector.file.FileResponse;

public class FileRequestImpl extends AbstractConnectorRequest<FileResponse> implements FileRequest {

  public FileRequestImpl(FileConnector connector) {
    super(connector);
  }

}
