package com.camunda.consulting.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SslConfig {

  private static final Logger LOG = LoggerFactory.getLogger(SslConfig.class);

  @Autowired
  private Environment env;

  @PostConstruct
  private void configureSSL() {
    if ("true".equals(env.getProperty("sslconfig.enabled"))) {
      
      LOG.info("Get SSL config details");
      
      System.setProperty("javax.net.ssl.keyStore", env.getProperty("sslconfig.key-store"));
      System.setProperty("javax.net.ssl.keyStorePassword", env.getProperty("sslconfig.key-store-password"));
      System.setProperty("javax.net.ssl.trustStore", env.getProperty("sslconfig.trust-store"));
      System.setProperty("javax.net.ssl.trustStorePassword", env.getProperty("sslconfig.trust-store-password"));
      
      LOG.info("SSL config completed");
    }
  }
}