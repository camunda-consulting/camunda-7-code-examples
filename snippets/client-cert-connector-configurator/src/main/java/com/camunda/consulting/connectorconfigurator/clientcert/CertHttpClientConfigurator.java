package com.camunda.consulting.connectorconfigurator.clientcert;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.camunda.connect.httpclient.HttpConnector;
import org.camunda.connect.httpclient.impl.AbstractHttpConnector;
import org.camunda.connect.spi.ConnectorConfigurator;

import connectjar.org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import connectjar.org.apache.http.conn.ssl.SSLContexts;
import connectjar.org.apache.http.impl.client.CloseableHttpClient;
import connectjar.org.apache.http.impl.client.HttpClients;

public class CertHttpClientConfigurator implements ConnectorConfigurator<HttpConnector> {

  private static final String KEYSTOREPATH = "/badssl.com-client.p12";
  private static final String KEYSTOREPASS = "badssl.com";
  private static final String KEYPASS = "badssl.com";
  
  public Class<HttpConnector> getConnectorClass() {
    return HttpConnector.class;
  }

  KeyStore readStore() throws Exception {
    try (InputStream keyStoreStream = this.getClass().getResourceAsStream(KEYSTOREPATH)) {
      
      if (keyStoreStream == null) {
        throw new RuntimeException("keystorestream is null");
      }
      
      KeyStore keyStore = KeyStore.getInstance("PKCS12"); // or "PKCS12"
      keyStore.load(keyStoreStream, KEYSTOREPASS.toCharArray());
      
      return keyStore;
    }
  }

  public void configure(HttpConnector connector) {

    SSLContext sslContext;
    try {
      sslContext = SSLContexts.custom()
          .loadKeyMaterial(readStore(), KEYPASS.toCharArray()) // use null as second param if you don't have a separate key password
          .build();
      
      if (sslContext == null) {
        throw new RuntimeException("sslcontext is null");
      }
      
      SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
      
      CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
      
      ((AbstractHttpConnector) connector).setHttpClient(client);
      
      System.out.println("http client configured");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

}