package com.camunda.demo.connector.file;

import java.io.Closeable;

import org.camunda.connect.spi.CloseableConnectorResponse;

public interface FileResponse extends CloseableConnectorResponse, Closeable {

}
