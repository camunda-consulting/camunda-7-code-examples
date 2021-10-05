package com.camunda.consulting.rest;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Complete Security Configuration Properties for Camunda REST Api.
 */
@Component
@ConfigurationProperties(prefix = "rest.security")
@Validated
public class RestApiSecurityConfigurationProperties {

  /**
   * rest.security.enabled:
   * 
   * Rest Security is enabled by default. Switch off by setting this flag to
   * {@code false}.
   */
  private Boolean enabled = true;

  /**
   * rest.security.provider:
   * 
   * The name of the spring.security.oauth2.client.provider to use
   */
  @NotEmpty
  private String provider;

  /**
   * rest.security.required-audience:
   * 
   * Required Audience.
   */
  @NotEmpty
  private String requiredAudience;

  // ------------------------------------------------------------------------

  /**
   * @return the requiredAudience
   */
  public String getRequiredAudience() {
    return requiredAudience;
  }

  /**
   * @param requiredAudience
   *          the requiredAudience to set
   */
  public void setRequiredAudience(String requiredAudience) {
    this.requiredAudience = requiredAudience;
  }

  /**
   * @return the enabled
   */
  public Boolean getEnabled() {
    return enabled;
  }

  /**
   * @param enabled
   *          the enabled to set
   */
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * @return the provider
   */
  public String getProvider() {
    return provider;
  }

  /**
   * @param provider
   *          the provider to set
   */
  public void setProvider(String provider) {
    this.provider = provider;
  }

}
