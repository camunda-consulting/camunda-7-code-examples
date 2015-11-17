package com.camunda.demo.environment;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserProperties {

  private static Logger LOG = Logger.getLogger(UserProperties.class.getName());

  public static String CONFIG_PROPERTIES_PATH = System.getProperty("user.home") + File.separator + ".camunda" + File.separator + "build.properties";

  public static String readProperty(String key) {
    if (new File(CONFIG_PROPERTIES_PATH).exists()) {
      try {
        Properties properties = new Properties();
        properties.load(new FileReader(new File(CONFIG_PROPERTIES_PATH)));
        if (properties.containsKey(key)) {
          return properties.getProperty(key);
        }
      } catch (Exception ex) {
        LOG.log(Level.WARNING, "Could not read '" + key + "' from " + CONFIG_PROPERTIES_PATH, ex);
      }
    }
    return null;
  }
}
