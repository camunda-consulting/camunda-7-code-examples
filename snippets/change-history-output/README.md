# History Variable Filter

This example shows a process engine plugin which store only given variables in the history. 
All other variables will only be stored for runtime. 

## Configuration

The list of variable names that should be stored is a property of the plugin configuration. 

The property name must be <code>variableNames</code>, the value is a comma separated list of variable names. 

Here is a snippet of camunda.cfg.xml in Spring style


    <property name="processEnginePlugins">
      <list>
        <bean class="com.camunda.consulting.history.changeHistoryOutput.FilterVariableHistoryPlugin">
          <property name="variableNames" value="orderId, camundaTestVar"/>
        </bean>
      </list>
    </property>
  

The history level must be set to <code>full</code>. 

With the example configuration only variables with the names orderId or camundaTestVar will be saved in the database table <code>ACT\_HI\_VARINST</code>.

No Details will be saved. The database table <code>ACT\_HI\_DETAIL</code> will be left empty.

## Supress History of exclusive Gateways

Overwrite the method insertOrUpdate(HistoryEvent) from the DbHistoryEventHandler to supress the history of exclusive gateways. See the implementation how to handle the details.

