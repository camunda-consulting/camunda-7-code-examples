package com.camunda.consulting.delegate;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpsRequestDelegate implements JavaDelegate {
  
  private static final Logger LOG = LoggerFactory.getLogger(HttpsRequestDelegate.class);

  @Value("${sslconfig.trust-store}")
  private Resource trustStore;

  @Value("${sslconfig.trust-store-password}")
  private String trustStorePassword;
  
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    LOG.info("Doing SSL call");
    String WELCOME_URL = "https://localhost:8443/welcome";
    
    String response = restTemplate().getForObject(WELCOME_URL, String.class);

    LOG.info("Result form https: {}", response);

  }

  RestTemplate restTemplate() throws Exception {
    SSLContext sslContext = new SSLContextBuilder()
        .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray()).build();
    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
    HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
    RestTemplate restTemplate = new RestTemplate(factory);
    
    LOG.info("rest template: {}", restTemplate);
    return restTemplate;
  }

}
