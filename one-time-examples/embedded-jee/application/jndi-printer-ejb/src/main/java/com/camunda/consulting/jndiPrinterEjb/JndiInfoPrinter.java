package com.camunda.consulting.jndiPrinterEjb;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

@Singleton
@Startup
public class JndiInfoPrinter {
  
  private static final Logger log = Logger.getLogger(JndiInfoPrinter.class.getName());
  
  private Set<String> foundNames = new HashSet<String>();
  
  @PostConstruct
  public void startUp() {
    log.info("startup");
    
    String rootNamespace = "";
    try {
      InitialContext ctx = new InitialContext();
      printJndiList(rootNamespace, ctx);
    } catch (NamingException e) {
      log.warning(e.getMessage());
    }
    
    log.info("startup finished");
    
    return;
  }

  public void printJndiList(String namespace, InitialContext ctx) {
    try {
      NamingEnumeration<NameClassPair> list = ctx.list(namespace);
      while (list.hasMoreElements()) {
        try {
          NameClassPair pair = list.nextElement();
          boolean relative = pair.isRelative();
          String jndiPrefix = pair.getName();
          String completeName;
          if (namespace.length() > 0) {
            completeName = namespace + "/" + jndiPrefix; 
          } else {
            completeName = jndiPrefix;
          }
          log.info("JNDI-Name: " + completeName +" is relative: " + relative);
          if (foundNames.add(completeName)) {
            printJndiList(completeName, ctx);
          } else {
            log.info("could not add " + completeName);
            return;
          }
        } catch (UnsupportedOperationException e) {
//          log.warning(e.getMessage());
        }
      }
    } catch (NamingException e) {
//      log.warning(e.getMessage());
    }
  }
  

}
