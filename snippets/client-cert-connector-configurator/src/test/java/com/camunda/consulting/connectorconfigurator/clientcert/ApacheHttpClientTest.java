package com.camunda.consulting.connectorconfigurator.clientcert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.junit.Test;

import connectjar.org.apache.http.client.methods.CloseableHttpResponse;
import connectjar.org.apache.http.client.methods.HttpGet;
import connectjar.org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import connectjar.org.apache.http.conn.ssl.SSLContexts;
import connectjar.org.apache.http.impl.client.CloseableHttpClient;
import connectjar.org.apache.http.impl.client.HttpClients;

public class ApacheHttpClientTest {

  KeyStore readStore() throws Exception {

      KeyStore keyStore = KeyStore.getInstance("PKCS12"); // or "PKCS12"
      
      File file = new File(Paths.get("").toAbsolutePath().toString() + "/src/test/resources/badssl.com-client.p12");
      FileInputStream is = new FileInputStream(file);
      
      keyStore.load(is, "badssl.com".toCharArray());
      return keyStore;
  }

  @Test
  public void testHttpClientWithCertificateOnHttps() throws Exception {
    KeyStore keyStore = readStore();
    
    SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, "badssl.com".toCharArray() ).build();

    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
    
    CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();

    HttpGet httpGet = new HttpGet("https://client.badssl.com");

    CloseableHttpResponse response1 = client.execute(httpGet);

    int statusCode = response1.getStatusLine().getStatusCode();

    assertEquals(200, statusCode);
  }

  @Test
  public void testHttpClientWithCertificateOnHttp() throws Exception {
    KeyStore keyStore = readStore();
    
    SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, "badssl.com".toCharArray() ).build();

    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
    
    CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();

    HttpGet httpGet = new HttpGet("http://wetterstationen.meteomedia.de/messnetz/vorhersagegrafik/103870.png?ver=1401862208");

    CloseableHttpResponse response1 = client.execute(httpGet);

    int statusCode = response1.getStatusLine().getStatusCode();

    assertEquals(200, statusCode);
  }

}
