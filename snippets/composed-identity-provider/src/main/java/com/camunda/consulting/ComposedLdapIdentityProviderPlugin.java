package com.camunda.consulting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.identity.impl.ldap.plugin.LdapIdentityProviderPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComposedLdapIdentityProviderPlugin extends LdapIdentityProviderPlugin {
  public static final String CATALINA_BASE = "catalina.base";
  public static final String CATALINA_HOME = "catalina.home";
  private static final Logger LOG =
      LoggerFactory.getLogger(ComposedLdapIdentityProviderPlugin.class);
  // this is for the tomcat
  protected String additionalUsers = "additionalUsers.properties";
  protected Properties additionalUserProperties;

  public String getAdditionalUsers() {
    return additionalUsers;
  }

  public void setAdditionalUsers(String additionalUsers) {
    this.additionalUsers = additionalUsers;
  }

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    // apply everything else
    super.preInit(processEngineConfiguration);
    // replace the session factory
    ComposedLdapIdentityProviderFactory ldapIdentityProviderFactory =
        new ComposedLdapIdentityProviderFactory();
    ldapIdentityProviderFactory.setLdapConfiguration(this);
    processEngineConfiguration.setIdentityProviderSessionFactory(ldapIdentityProviderFactory);
    if (additionalUserProperties == null) {
      LOG.info(
          "additionalUserProperties not set, loading from catalina conf file {}", additionalUsers);
      additionalUserProperties = loadAdditionalUsersProperties();
    }
    processProperties(additionalUserProperties);
  }

  private void processProperties(Properties properties) {
    AdditionalUserCreator.getInstance().createUsers(properties);
  }

  private Properties loadAdditionalUsersProperties() {
    String catalinaHome = System.getProperty(CATALINA_BASE);
    if (catalinaHome == null) {
      catalinaHome = System.getProperty(CATALINA_HOME);
    }
    String ldapConfigurationFile =
        catalinaHome + File.separator + "conf" + File.separator + additionalUsers;
    LOG.debug("Loading ldap configuration from {}", ldapConfigurationFile);
    Properties properties = new Properties();
    try (InputStream in = new FileInputStream(ldapConfigurationFile)) {
      properties.load(in);
    } catch (IOException e) {
      LOG.error("Error while load properties", e);
      throw new RuntimeException(e);
    }
    LOG.debug("Properties loaded");
    return properties;
  }
}
