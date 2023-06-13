package com.camunda.consulting.soapclient;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.example.consumingwebservice.wsdl.GetCountryRequest;
import com.example.consumingwebservice.wsdl.GetCountryResponse;

public class CountryClient extends WebServiceGatewaySupport {

  private static final Logger LOG = LoggerFactory.getLogger(CountryClient.class);

  @Value("${sslconfig.trust-store}")
  private Resource trustStore;

  @Value("${sslconfig.trust-store-password}")
  private String trustStorePassword;
  
  public GetCountryResponse getCountry(String country) throws Exception {

    GetCountryRequest request = new GetCountryRequest();
    request.setName(country);

    LOG.info("Requesting location for " + country);

    GetCountryResponse response = (GetCountryResponse) webServiceTemplate()
        .marshalSendAndReceive("https://localhost:8443/ws/countries", request,
            new SoapActionCallback(
                "http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));

    LOG.info("resopnse: {}", response);
    return response;
  }
  
  WebServiceTemplate webServiceTemplate() throws Exception {
    LOG.info("Using parameters {}, {}", trustStore, trustStorePassword);
    SSLContext sslContext = new SSLContextBuilder()
        .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray()).build();
    LOG.info("SSL context created");
    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
    HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
        .addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor())
        .build();
        
    LOG.info("http client created");
    WebServiceMessageSender sender = new HttpComponentsMessageSender(httpClient);
    LOG.info("HttpComponentsMessageSender created");
    
    WebServiceTemplate webServiceTemplate = getWebServiceTemplate();
    LOG.info("webServiceTemplate got");
    webServiceTemplate.setMessageSender(sender);
    
    LOG.info("webservice template: {}", webServiceTemplate);
    return webServiceTemplate;
  }
  
}