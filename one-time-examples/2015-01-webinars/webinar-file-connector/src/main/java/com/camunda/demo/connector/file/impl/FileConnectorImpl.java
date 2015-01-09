package com.camunda.demo.connector.file.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.camunda.connect.impl.AbstractConnector;
import org.camunda.connect.spi.ConnectorResponse;

import com.camunda.demo.connector.file.FileConnector;
import com.camunda.demo.connector.file.FileRequest;
import com.camunda.demo.connector.file.FileResponse;

public class FileConnectorImpl extends AbstractConnector<FileRequest, FileResponse> implements FileConnector {

  public FileConnectorImpl() {
    super(FileConnector.ID);
  }

  public FileConnectorImpl(String connectorId) {
    super(connectorId);
  }

  public FileRequest createRequest() {
    return new FileRequestImpl(this);
  }

  public ConnectorResponse execute(FileRequest request) {
    String filePath = request.getRequestParameter(FileRequest.PARAM_NAME_FILE_PATH);
    String fileContent = request.getRequestParameter(FileRequest.PARAM_NAME_FILE_CONTENT);

    try {
      File file = new File(filePath);
      if (!file.exists()) {
          if (!file.createNewFile()) {
            throw new RuntimeException("Cannot create file: " + filePath);
          }
      }
      
      // write file
      FileWriter fileWriter = new FileWriter(file);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(fileContent);
      bufferedWriter.close();
      fileWriter.close();
      
    } catch (Exception ex) {
      throw new RuntimeException("Error while writing file : " + filePath, ex);
    }

    return new FileResponseImpl();
  }

}
