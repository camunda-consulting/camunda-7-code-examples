package org.camunda.bpm.demo.executify;

import static org.camunda.bpm.demo.executify.CmmnModelInstanceHelper.*;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.cmmn.Cmmn;
import org.camunda.bpm.model.cmmn.CmmnModelInstance;
import org.camunda.bpm.model.cmmn.instance.CaseTask;
import org.camunda.bpm.model.cmmn.instance.ExtensionElements;
import org.camunda.bpm.model.cmmn.instance.IfPart;
import org.camunda.bpm.model.cmmn.instance.ProcessTask;
import org.camunda.bpm.model.cmmn.instance.Sentry;
import org.camunda.bpm.model.cmmn.instance.Task;
import org.camunda.bpm.model.cmmn.instance.camunda.CamundaIn;

public class CmmnExecutifier {

  private boolean generatePredictableConditionExpressions;
  private boolean removeDecisionRefs;
  private CmmnModelInstance modelInstance;
  private HashMap<String,ExecutableModel> executableModels = new HashMap<String, ExecutableModel>();

  public void setGeneratePredictableConditionExpressions(boolean generatePredictableConditionExpressions) {
    this.generatePredictableConditionExpressions = generatePredictableConditionExpressions;
  }

  public void setRemoveDecisionRefs(boolean removeDecisionRefs) {
    this.removeDecisionRefs = removeDecisionRefs;
  }

  public CmmnModelInstance executify(InputStream stream) {
    CmmnModelInstance modelInstance = Cmmn.readModelFromStream(stream);
    executify(modelInstance);
    return modelInstance;
  }

  public void executify(CmmnModelInstance modelInstance) {
    this.modelInstance = modelInstance;
    addMissingIfPartConditions();
    executifyProcessAndCaseTasks();
  }

  private void executifyProcessAndCaseTasks() {
    Collection<Task> callActivities = modelInstance.getModelElementsByType(Task.class);
    interationOverAllCallActivities : for (Task callActivity : callActivities) {
      if (callActivity instanceof ProcessTask || callActivity instanceof CaseTask) {
        // add process or case if missing
        String name = callActivity.getName();
        if (callActivity instanceof ProcessTask && isEmpty(((ProcessTask) callActivity).getProcess())) {
          ((ProcessTask) callActivity).setProcess("Process_SampleSubProcess");
          addSampleSubProcess();
        } else if (callActivity instanceof CaseTask && isEmpty(((CaseTask) callActivity).getCase())) {
          ((CaseTask) callActivity).setCase("Case_SampleSubCase");
          addSampleSubCase();
        }
        // add business key if missing
        ExtensionElements extensionElements = callActivity.getExtensionElements();
        if (extensionElements != null) {
          List<CamundaIn> camundaIns = extensionElements
              .getElementsQuery()
              .filterByType(CamundaIn.class)
              .list();
          for (CamundaIn camundaIn : camundaIns) {
            if (!isEmpty(camundaIn.getCamundaBusinessKey())) {
              continue interationOverAllCallActivities;
            }
          }
          CamundaIn camundaIn = extensionElements.addExtensionElement(CamundaIn.class);
          camundaIn.setCamundaBusinessKey("#{caseExecution.caseBusinessKey}");
        } else {
          extensionElements = createElement(callActivity, ExtensionElements.class);
          CamundaIn camundaIn = extensionElements.addExtensionElement(CamundaIn.class);
          camundaIn.setCamundaBusinessKey("#{caseExecution.caseBusinessKey}");
          callActivity.setExtensionElements(extensionElements);
        }
      }
    }
  }

  private void addMissingIfPartConditions() {
    Collection<Sentry> sentries = modelInstance.getModelElementsByType(Sentry.class);
    for (Sentry sentry : sentries) {
      IfPart ifPart = sentry.getIfPart();
      if (ifPart == null && (sentry.getOnParts() == null || sentry.getOnParts().isEmpty())) {
        ifPart = createElement(sentry, IfPart.class);
        sentry.setIfPart(ifPart);
      }
      if (ifPart != null) {
        if (generatePredictableConditionExpressions) {
          setExpression(ifPart, "#{caseExecution.getVariable('" + sentry.getId() + "')}");
        } else if (isEmpty(ifPart.getCondition())) {
          setExpression(ifPart, "#{true}");
        }
      }
    }
  }

  private void addSampleSubProcess() {
    String filename = "SampleSubProcess.bpmn";
    InputStream stream = getClass().getResourceAsStream("/" + filename);
    executableModels.put(filename, new ExecutableModel(filename, Bpmn.readModelFromStream(stream)));
  }

  private void addSampleSubCase() {
    String filename = "SampleSubCase.cmmn";
    InputStream stream = getClass().getResourceAsStream("/" + filename);
    executableModels.put(filename, new ExecutableModel(filename, Cmmn.readModelFromStream(stream)));
  }

  public HashMap<String,ExecutableModel> getExecutableModels() {
    return executableModels;
  }

  public void setExecutableModels(HashMap<String,ExecutableModel> executableModels) {
    this.executableModels = executableModels;
  }

}
