package com.camunda.demo.dmn.feel.operator;

import java.lang.reflect.Field;

import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.dmn.configuration.ProcessEngineDmnEngineConfiguration;
import org.camunda.bpm.engine.impl.el.ExpressionManager;
import org.camunda.bpm.engine.impl.history.parser.HistoryDecisionTableListener;
import org.camunda.bpm.engine.impl.scripting.engine.ScriptingEngines;

public class CustomFeelProcessEnginePlugin implements ProcessEnginePlugin {

  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    // cannot do this here because historyLevel (and maybe others) are not yet initialized
  }

  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    // so do it here
    try {
      
//      ScriptingEngines scriptingEngines = processEngineConfiguration.getScriptingEngines();
//      HistoryDecisionTableListener historyDecisionTableListener = new HistoryDecisionTableListener(processEngineConfiguration.getDmnHistoryEventProducer(),
//          processEngineConfiguration.getHistoryLevel());
//      ExpressionManager expressionManager = processEngineConfiguration.getExpressionManager();
//
//      ProcessEngineDmnEngineConfiguration dmnEngineConfiguration = new ProcessEngineDmnEngineConfiguration(scriptingEngines, historyDecisionTableListener,
//          expressionManager);

      DmnEngineConfiguration dmnEngineConfiguration = processEngineConfiguration.getDmnEngineConfiguration();
      dmnEngineConfiguration.feelEngineFactory(new CustomFeelEngineFactory());      
      DmnEngine dmnEngine = dmnEngineConfiguration.buildEngine();

      Field dmnEngineConfigurationField = getField(processEngineConfiguration.getClass(), "dmnEngineConfiguration");
      dmnEngineConfigurationField.setAccessible(true);
      dmnEngineConfigurationField.set(processEngineConfiguration, dmnEngineConfiguration);

      Field dmnEngineField = getField(processEngineConfiguration.getClass(), "dmnEngine");
      dmnEngineField.setAccessible(true);
      dmnEngineField.set(processEngineConfiguration, dmnEngine);

    } catch (Exception ex) {
      throw new RuntimeException("Could not configure DMN Engine", ex);
    }    
  }

  public void postProcessEngineBuild(ProcessEngine processEngine) {
  }

  public static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
    try {
      return clazz.getDeclaredField(fieldName);
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null) {
        throw e;
      } else {
        return getField(superClass, fieldName);
      }
    }
  }
}
