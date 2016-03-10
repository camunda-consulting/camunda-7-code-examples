package org.camunda.demo.custom.query;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

@Stateless
@Named
public class TasklistService {


  public List<TaskDTO> getTasksForRegion(final String assignee, final String region) {
    ProcessEngineImpl processEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
    ProcessEngineConfigurationImpl processEngineConfiguration = processEngine.getProcessEngineConfiguration();

    MyBatisQueryCommandExecutor commandExecutor = new MyBatisQueryCommandExecutor(processEngineConfiguration, "mappings.xml");
    return commandExecutor.executeQueryCommand(new Command<List<TaskDTO>>() {

      @SuppressWarnings("unchecked")
      public List<TaskDTO> execute(CommandContext commandContext) {

        ListQueryParameterObject queryParameterObject = new ListQueryParameterObject();
        queryParameterObject.setParameter(region);
        
        return (List<TaskDTO>) commandContext.getDbEntityManager().selectList("selectTasksForRegion", queryParameterObject);
        
//        return (List<TaskDTO>) commandContext.getDbSqlSession().selectList("selectTasksForRegion", queryParameterObject);
      }
    });
  }

}
