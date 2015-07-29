package com.camunda.demo.remote.command.executor;

import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.Context;

import org.camunda.bpm.engine.impl.SchemaOperationsProcessEngineBuild;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;

public class RemoteCommandExecutor extends CommandInterceptor implements CommandExecutor {

  private final Logger LOGGER = Logger.getLogger(RemoteCommandExecutor.class.getName());
  private CommandExecutionService commandExecutionService;
  
  public RemoteCommandExecutor() {
  }

  public RemoteCommandExecutor(CommandExecutionService commandExecutionService) {
    this.commandExecutionService = commandExecutionService;
  }

  @Override
  public <T> T execute(Command<T> command) {
    if (command instanceof SchemaOperationsProcessEngineBuild) {
      LOGGER.info("Ignoring command 'SchemaOperationsProcessEngineBuild' for remote execution.");
      return null;
    } else if (command instanceof Serializable ) {
      LOGGER.info("Executing command '" + command.getClass().getName() + "' remotely...");
      if (commandExecutionService == null) {
        initializeEjbClient();
      }
      return commandExecutionService.execute(command);
    } else {
      throw new RuntimeException("Command '" + command.getClass().getName() + "' is not serializable and can therefore not be executed remotely.");
    }
  }

  private void initializeEjbClient() {
    try {
      final Properties props = new Properties();
//      props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//      props.put(Context.PROVIDER_URL, "remote://localhost:4447");
//      props.put(Context.SECURITY_PRINCIPAL, "camunda");
//      props.put(Context.SECURITY_CREDENTIALS, "camunda-1");
      
      // setup the ejb: namespace URL factory
      props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
      props.put("jboss.naming.client.ejb.context", true);
      
      // create the InitialContext
      final Context context = new javax.naming.InitialContext(props);

      // Lookup the Greeter bean using the ejb: namespace syntax which is explained here https://docs.jboss.org/author/display/AS71/EJB+invocations+from+a+remote+client+using+JNDI
      this.commandExecutionService = (CommandExecutionService) context.lookup("ejb:/remote-command-executor-service-0.0.1-SNAPSHOT//CommandExecutionServiceBean!com.camunda.demo.remote.command.executor.CommandExecutionService");

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
}
