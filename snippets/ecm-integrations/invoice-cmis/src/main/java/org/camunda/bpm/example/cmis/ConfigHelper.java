package org.camunda.bpm.example.cmis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public final class ConfigHelper {

    private static Object syncObject = new Object();
    private static Properties properties = null;

    /**
     * Utility Classe ConfigHelper.  Do not allow
     * default constructor to be called.
     */

    private ConfigHelper() {

    }

    /**
     * @return properties a key value map
     */
    protected static Properties properties() {
        synchronized (syncObject) {
            if (properties == null) {
                Properties prop = new Properties();
                try {
                    InputStream input = ConfigHelper.class.getResourceAsStream("/conf/config.properties");
                    if (input!=null) {
                    	prop.load(input);
                    	System.out.println("loaded");
                    	input.close();
                    } else {
                    	System.out.println("not loaded");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
               System.out.println(new File("config.properties").getAbsolutePath());
                properties = prop;
            }

            return properties;
        }
    }
}

