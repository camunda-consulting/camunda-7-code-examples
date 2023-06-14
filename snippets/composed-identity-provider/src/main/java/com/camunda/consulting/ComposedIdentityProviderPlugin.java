package com.camunda.consulting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.identity.db.DbIdentityServiceProvider;
import org.camunda.bpm.engine.impl.interceptor.SessionFactory;
import org.camunda.bpm.engine.impl.persistence.GenericManagerFactory;
import org.camunda.bpm.identity.impl.ldap.LdapConfiguration;
import org.camunda.bpm.identity.impl.ldap.LdapIdentityProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComposedIdentityProviderPlugin extends AbstractProcessEnginePlugin {
  public static final String CATALINA_BASE = "catalina.base";
  public static final String CATALINA_HOME = "catalina.home";
  private static final Logger LOG = LoggerFactory.getLogger(ComposedIdentityProviderPlugin.class);
  /** Our actual ldap configurations are saved here */
  private final List<LdapConfiguration> ldapConfigurations;

  /**
   * this is just for the tomcat, a list of file names where the configurations reside inside the
   * conf folder
   */
  protected String ldapConfigurationFiles;
  /** if true, the database identity provider is applied as well */
  protected boolean useDatabase;

  /** This constructor is used by the tomcat */
  public ComposedIdentityProviderPlugin() {
    ldapConfigurations = new ArrayList<>();
  }

  /**
   * This constructor can be used to hand over the ldap configurations directly
   *
   * @param ldapConfigurations the ldap configurations to apply
   */
  public ComposedIdentityProviderPlugin(List<LdapConfiguration> ldapConfigurations) {
    this.ldapConfigurations = ldapConfigurations;
  }

  public List<LdapConfiguration> getLdapConfigurations() {
    return ldapConfigurations;
  }

  public String getLdapConfigurationFiles() {
    return ldapConfigurationFiles;
  }

  public void setLdapConfigurationFiles(String ldapConfigurationFiles) {
    this.ldapConfigurationFiles = ldapConfigurationFiles;
  }

  public boolean isUseDatabase() {
    return useDatabase;
  }

  public void setUseDatabase(boolean useDatabase) {
    this.useDatabase = useDatabase;
  }

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    // find all relevant session factories
    List<SessionFactory> identityProviderFactories = findIdentityProviderFactories();
    if (identityProviderFactories.isEmpty()) {
      // the list is empty, nothing to do here
      LOG.info("No identity providers configured for composition, switching to default");
    } else {
      // if the list contains entries, apply it
      processEngineConfiguration.setIdentityProviderSessionFactory(
          new ComposedIdentityProviderFactory(identityProviderFactories));
    }
  }

  /**
   * parses the ldap configuration files if present to ldap configurations and then adds ldap
   * identity provider factories to the result
   *
   * @return a list of session factories relevant for the composed identity provider factory
   */
  private List<SessionFactory> findIdentityProviderFactories() {
    List<SessionFactory> identityProviderFactories = new ArrayList<>();
    if (ldapConfigurationFiles != null) {
      Arrays.stream(ldapConfigurationFiles.split(","))
          .map(String::trim)
          .forEach(this::createLdapConfiguration);
    }
    ldapConfigurations.forEach(
        ldapConfiguration -> {
          LdapIdentityProviderFactory factory = new LdapIdentityProviderFactory();
          factory.setLdapConfiguration(ldapConfiguration);
          identityProviderFactories.add(factory);
        });

    if (useDatabase) {
      // Taken from ProcessEngineConfigurationImpl.initIdentityProviderSessionFactory
      identityProviderFactories.add(new GenericManagerFactory(DbIdentityServiceProvider.class));
    }
    return identityProviderFactories;
  }

  /**
   * Creates a ldap configuration from the referenced file inside the tomcat conf and adds it to the
   * list of ldap configurations
   *
   * @param ldapConfigurationString the string containing the file name of the configuration
   */
  private void createLdapConfiguration(String ldapConfigurationString) {
    String catalinaHome = System.getProperty(CATALINA_BASE);
    if (catalinaHome == null) {
      catalinaHome = System.getProperty(CATALINA_HOME);
    }
    String ldapConfigurationFile =
        catalinaHome + File.separator + "conf" + File.separator + ldapConfigurationString;
    LOG.debug("Loading ldap configuration from {}", ldapConfigurationFile);
    Properties properties = new Properties();
    try (InputStream in = new FileInputStream(ldapConfigurationFile)) {
      properties.load(in);
    } catch (IOException e) {
      LOG.error("Error while load properties", e);
      throw new RuntimeException(e);
    }
    LOG.debug("Properties loaded");
    LdapConfiguration configuration = new LdapConfiguration();
    setIfPresent(properties, "initialContextFactory", configuration::setInitialContextFactory);
    setIfPresent(properties, "securityAuthentication", configuration::setSecurityAuthentication);
    setIfPresent(properties, "serverUrl", configuration::setServerUrl);
    setIfPresent(properties, "managerDn", configuration::setManagerDn);
    setIfPresent(properties, "managerPassword", configuration::setManagerPassword);
    setIfPresent(properties, "baseDn", configuration::setBaseDn);
    setIfPresent(properties, "userDnPattern", configuration::setUserDnPattern);
    setIfPresent(properties, "userSearchBase", configuration::setUserSearchBase);
    setIfPresent(properties, "userSearchFilter", configuration::setUserSearchFilter);
    setIfPresent(properties, "groupSearchBase", configuration::setGroupSearchBase);
    setIfPresent(properties, "groupSearchFilter", configuration::setGroupSearchFilter);
    setIfPresent(properties, "userIdAttribute", configuration::setUserIdAttribute);
    setIfPresent(properties, "userFirstnameAttribute", configuration::setUserFirstnameAttribute);
    setIfPresent(properties, "userLastnameAttribute", configuration::setUserLastnameAttribute);
    setIfPresent(properties, "userEmailAttribute", configuration::setUserEmailAttribute);
    setIfPresent(properties, "userPasswordAttribute", configuration::setUserPasswordAttribute);
    setIfPresent(properties, "groupIdAttribute", configuration::setGroupIdAttribute);
    setIfPresent(properties, "groupNameAttribute", configuration::setGroupNameAttribute);
    setIfPresent(properties, "groupTypeAttribute", configuration::setGroupTypeAttribute);
    setIfPresent(properties, "memberOf", configuration::setGroupMemberAttribute);
    setIfPresent(
        properties,
        "sortControlSupported",
        Boolean::valueOf,
        configuration::setSortControlSupported);
    setIfPresent(properties, "useSsl", Boolean::valueOf, configuration::setUseSsl);
    setIfPresent(properties, "usePosixGroups", Boolean::valueOf, configuration::setUsePosixGroups);
    setIfPresent(
        properties, "allowAnonymousLogin", Boolean::valueOf, configuration::setAllowAnonymousLogin);
    setIfPresent(
        properties,
        "authorizationCheckEnabled",
        Boolean::valueOf,
        configuration::setAuthorizationCheckEnabled);
    setIfPresent(properties, "pageSize", Integer::valueOf, configuration::setPageSize);
    ldapConfigurations.add(configuration);
  }

  private void setIfPresent(Properties properties, String property, Consumer<String> setter) {
    setIfPresent(properties, property, s -> s, setter);
  }

  private <T> void setIfPresent(
      Properties properties, String property, Function<String, T> mapper, Consumer<T> setter) {
    String value = properties.getProperty(property);
    if (value != null) {
      LOG.debug("Applying value of property {}", property);
      setter.accept(mapper.apply(value));
    } else {
      LOG.debug("No value present for property {}", property);
    }
  }
}
