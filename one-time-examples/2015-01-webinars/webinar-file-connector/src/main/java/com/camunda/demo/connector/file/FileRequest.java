package com.camunda.demo.connector.file;
import org.camunda.connect.spi.ConnectorRequest;


public interface FileRequest extends ConnectorRequest<FileResponse> {
  
  public static String PARAM_NAME_FILE_PATH = "filePath"; 
  public static String PARAM_NAME_FILE_CONTENT = "fileContent"; 

}
