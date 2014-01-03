package org.camunda.bpm.pattern.guard;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static org.apache.commons.lang3.StringUtils.trimToNull;

import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.helper.ClassDelegate;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.task.TaskDefinition;
import org.camunda.bpm.engine.impl.util.xml.Element;

import com.google.common.base.Function;

/**
 * Adds {@link Guard}s dynamically while parsing in standalone engine.
 * Configured in camunda-needle.cfg.xml. To activate the guards, add a
 * Properties File "camunda-guards.properties" to "src/test/resources". The file
 * should map activity-Id to Guard implementation class.
 * 
 * @author Jan Galinski, Holisticon AG
 */
public class GuardBpmnParseListener extends AbstractBpmnParseListener {

  public static final String RESOURCE_BUNDLE_GUARDS = "camunda-guards";

  /**
   * load mapping from guards.properties
   */
  private final Properties guards = LOAD_GUARD_PROPERTIES.apply(RESOURCE_BUNDLE_GUARDS);

  /**
   * Never null, returns empty properties when no RB is found.
   */
  public static Function<String, Properties> LOAD_GUARD_PROPERTIES = new Function<String, Properties>() {

    public Properties apply(final String resourceBundleName) {
      final Properties result = new Properties();
      try {
        final ResourceBundle bundle = ResourceBundle.getBundle(trimToEmpty(resourceBundleName));
        final Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
          final String key = keys.nextElement();
          result.put(trimToEmpty(key), trimToNull((String) bundle.getObject(key)));
        }
      } catch (final MissingResourceException e) {
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "Resource bundle not found: " + resourceBundleName);
      }

      return result;
    }
  };

  @Override
  public void parseServiceTask(final Element serviceTaskElement, final ScopeImpl scope, final ActivityImpl activity) {
    super.parseServiceTask(serviceTaskElement, scope, activity);
    addExecutionListener(activity);
  }

  /**
   * Adds aa {@link ActivityGuard} on {@link ExecutionListener#EVENTNAME_START}
   * and {@link ExecutionListener#EVENTNAME_END} if one is configured in the
   * properties file.
   * 
   * @param activity
   *          the current Activity in process
   */
  private void addExecutionListener(final ActivityImpl activity) {
    final String guardClass = guardClass(activity);

    if (isNotBlank(guardClass)) {
      activity.addExecutionListener(ExecutionListener.EVENTNAME_START, classDelegateFor(guardClass));
      activity.addExecutionListener(ExecutionListener.EVENTNAME_END, classDelegateFor(guardClass));
    }
  }

  @Override
  public final void parseUserTask(final Element userTaskElement, final ScopeImpl scope, final ActivityImpl activity) {
    final String guardClass = guardClass(activity);

    final ActivityBehavior behavior = activity.getActivityBehavior();
    if (behavior instanceof UserTaskActivityBehavior) {
      final TaskDefinition userTask = ((UserTaskActivityBehavior) behavior).getTaskDefinition();

      if (isNotBlank(guardClass)) {
        userTask.addTaskListener(TaskListener.EVENTNAME_CREATE, classDelegateFor(guardClass));
        userTask.addTaskListener(TaskListener.EVENTNAME_COMPLETE, classDelegateFor(guardClass));
      }

    }
  }

  private String guardClass(final ActivityImpl activity) {
    return guardClass(activity.getActivityId());
  }

  private String guardClass(final String activityId) {
    return guards.getProperty(activityId);
  }

  private ClassDelegate classDelegateFor(final String fqn) {
    return new ClassDelegate(fqn, null);
  }

}
