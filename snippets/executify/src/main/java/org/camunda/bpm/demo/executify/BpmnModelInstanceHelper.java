package org.camunda.bpm.demo.executify;

import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.Expression;

public class BpmnModelInstanceHelper {

  public static boolean isEmpty(String string) {
    return string == null || string.isEmpty();
  }

  public static boolean isEmpty(Expression expression) {
    return expression == null || expression.getTextContent() == null || expression.getTextContent().isEmpty();
  }

  public static <T extends Expression> void setExpression(BpmnModelElementInstance parentElement, Class<T> expressionType, String expression) {
    try {
      parentElement.getClass().getMethod("set" + expressionType.getSimpleName(), expressionType).invoke(parentElement, (createExpression(expression, expressionType, parentElement)));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static <T extends Expression> T createExpression(String expression, Class<T> expressionType, BpmnModelElementInstance parentElement) {
    T expressionElement = createElement(parentElement, expressionType);
    expressionElement.setTextContent(expression);
    expressionElement.removeAttribute("id");
    return expressionElement;
  }

  public static <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement, String id, Class<T> elementClass) {
    T element = parentElement.getModelInstance().newInstance(elementClass);
    element.setAttributeValue("id", id, true);
    parentElement.addChildElement(element);
    return element;
  }
  
  public static <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement, Class<T> elementClass) {
    T element = parentElement.getModelInstance().newInstance(elementClass);
    parentElement.addChildElement(element);
    return element;
  }

}
