package org.camunda.bpm.demo.executify;

import static org.camunda.bpm.demo.executify.CmmnModelInstanceHelper.*;

import java.io.InputStream;
import java.util.Collection;

import org.camunda.bpm.model.cmmn.instance.ConditionExpression;
import org.camunda.bpm.model.cmmn.Cmmn;
import org.camunda.bpm.model.cmmn.CmmnModelInstance;
import org.camunda.bpm.model.cmmn.instance.CmmnElement;
import org.camunda.bpm.model.cmmn.instance.IfPart;
import org.camunda.bpm.model.cmmn.instance.Sentry;

public class CmmnExecutifier {

  private boolean generatePredictableConditionExpressions;
  private boolean removeDecisionRefs;
  private CmmnModelInstance modelInstance;

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
          setExpression(ifPart, "#{execution.getVariable('" + sentry.getId() + "')}");
        } else if (isEmpty(ifPart.getCondition())) {
          setExpression(ifPart, "#{true}");
        }
      }
    }
  }

}
