package com.camunda.consulting.rest.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationProviderWithBypass extends HttpBasicAuthenticationProvider {
  
  private static final String GROUPS_KEY = "groups";
  private static final String PASSWORD_KEY = "passwd";
  private static final String USER_ID_KEY = "userId";
  public static final String BYPASS_USER_CONFIG_FILE_NAME = "bypassUser.properties";
  public static final String CATALINA_BASE = "catalina.base";
  public static final String CATALINA_HOME = "catalina.home";
  
  private static final Logger log = LoggerFactory.getLogger(AuthenticationFilterWithBypass.class);
  
  private String bypassUserId;
  private String bypassUserPw;
  private List<String> bypassGroupIds;
  
  public AuthenticationProviderWithBypass() {
    log.info("Initializing AuthenticationProviderWithBypass");
    readBypassValues();
  }
  
  private void readBypassValues() {
    String catalinaHome = System.getProperty(CATALINA_BASE);
    if (catalinaHome == null) {
      catalinaHome = System.getProperty(CATALINA_HOME);
    }

    String bypassConfigFileLocation = catalinaHome + File.separator + "conf" + File.separator + BYPASS_USER_CONFIG_FILE_NAME;
    File configFile = new File(bypassConfigFileLocation);
    if (configFile.exists()) {
      Properties properties = new Properties();
      try {
        properties.load(new FileInputStream(configFile));
        if (properties.containsKey(USER_ID_KEY)) {
          bypassUserId = properties.getProperty(USER_ID_KEY);
          log.info("bypass userId set successfully");
        } else {
          log.info("key '{}' not found in {}, bypass impossible", USER_ID_KEY, bypassConfigFileLocation);
          bypassUserId = null;
        }
        
        if (properties.containsKey(PASSWORD_KEY)) {
          bypassUserPw = properties.getProperty(PASSWORD_KEY);
          log.info("bypass password set successfully");
        } else {
          log.info("key '{}' not found in {}, bypass impossible", PASSWORD_KEY, bypassConfigFileLocation);
          bypassUserPw = null;
        }
        
        if (properties.containsKey(GROUPS_KEY)) {
          String groupsStr = properties.getProperty(GROUPS_KEY);
          List<String> groups = Arrays.asList(groupsStr.split("\\s*,\\s*"));
          bypassGroupIds = groups;
          log.info("bypass groups set successfully");
        } else {
          log.info("key '{}' not found in {}, bypass impossible", GROUPS_KEY, bypassConfigFileLocation);
          bypassGroupIds = null;
        }
      } catch (FileNotFoundException e) {
        log.warn(e.getLocalizedMessage());
      } catch (IOException e) {
        log.warn(e.getLocalizedMessage());
      }
    } else {
      log.info("config file for bypassUser: {} not found", bypassConfigFileLocation);
      log.info("No config file for bypassUser found, bypass impossible");
      bypassUserId = null;
      bypassUserPw = null;
      bypassGroupIds = null;
    }
  }

  @Override
  protected boolean isAuthenticated(ProcessEngine engine, String userName, String password) {
    log.debug("Check authentication for {}", userName);
    if (userName.equals(bypassUserId) && password.equals(bypassUserPw)) {
      log.debug("Authenticated as bypassUser");
      return true;
    }
    return engine.getIdentityService().checkPassword(userName, password);
  }
  
  public String getBypassUserId() {
    return bypassUserId;
  }
  
  /**
   * @return the configured groups or null for invalid configuration
   */
  public List<String> getBypassGroupIds() {
    return bypassGroupIds;
  }

}
