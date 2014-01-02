package org.camunda.bpm.examples;

import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.helper.ClassDelegate;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

import java.util.List;
import java.util.logging.Logger;

/**
 * {@link BpmnParseListener} which reads additional configuration from the BPMN
 * 2.0 XML and provides them via Properties during runtime, see
 * {@link TaskListService} for a usage example.
 * 
 * This is the most convenient way to read the extensions (and to validate them
 * during deployment time) but it has to be configured into the platform.
 * 
 * @author ruecker
 */
public class FourEyesExtensionsParseListener extends AbstractBpmnParseListener {

  private static final Logger logger = Logger.getLogger(FourEyesExtensionsParseListener.class.getName());

  public void parseUserTask(Element element, ScopeImpl scope, ActivityImpl activity) {
    Element extensionElements = element.element(Helper.ELEMENT_NAME_EXTENSION_ELEMENTS);
    if (extensionElements != null) {
      Element fourEyesGroupName = extensionElements.elementNS(Helper.FOX_NS, Helper.FOUR_EYES_GROUP_NAME);
      if (fourEyesGroupName != null) {
        String fourEyesGroupNameString = fourEyesGroupName.attribute("name");
        activity.setProperty(Helper.FOUR_EYES_GROUP_NAME, fourEyesGroupNameString);
        String fourEyesGroupRoleString = fourEyesGroupName.attribute("role");
        activity.setProperty(Helper.FOUR_EYES_ROLE_NAME, fourEyesGroupRoleString);

        addFourEyesTaskListener(activity);
      }
    }

  }

  /**
   * Add TaskListener on "complete" event generically every time, so we don't
   * have to add it in the XML.
   */
  private void addFourEyesTaskListener(ActivityImpl activity) {
    UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activity.getActivityBehavior();

    boolean listenerAlreadyExists = false;

    // check that the listener wasn't added in the XML explicitly
    List<TaskListener> existingListeners = userTaskActivityBehavior.getTaskDefinition().getTaskListeners().get("complete");
    for (TaskListener taskListener : existingListeners) {
      if (taskListener instanceof ClassDelegate &&
          ((ClassDelegate)taskListener).getClassName().equals(TaskCompletionListener.class.getName())) {
        listenerAlreadyExists = true;
        logger.info(TaskCompletionListener.class.getSimpleName() + " was already explicitly added to usertask in the bpmn xml.");
        break;
      }
    }

    if (!listenerAlreadyExists) {
      logger.info("Adding " + TaskCompletionListener.class.getSimpleName() + " implicitly to usertask.");
      ClassDelegate taskListener = new ClassDelegate(TaskCompletionListener.class, null);
      userTaskActivityBehavior.getTaskDefinition().addTaskListener("complete", taskListener);
    }

  }

}
