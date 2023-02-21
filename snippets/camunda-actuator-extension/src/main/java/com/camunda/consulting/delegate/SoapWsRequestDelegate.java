package com.camunda.consulting.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camunda.consulting.soapclient.CountryClient;
import com.example.consumingwebservice.wsdl.GetCountryResponse;

@Component
public class SoapWsRequestDelegate implements JavaDelegate {
  
  private static final Logger LOG = LoggerFactory.getLogger(SoapWsRequestDelegate.class);
  
  @Autowired
  CountryClient countryClient;
  
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    
    GetCountryResponse countryResponse = countryClient.getCountry("Spain");
    
    LOG.info("resonse from soap: {}", countryResponse);
    LOG.info("Currency: {}", countryResponse.getCountry().getCurrency());
  }

}
