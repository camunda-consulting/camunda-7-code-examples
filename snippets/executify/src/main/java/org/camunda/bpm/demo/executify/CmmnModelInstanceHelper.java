package org.camunda.bpm.demo.executify;

import java.lang.reflect.Method;

import org.camunda.bpm.model.cmmn.instance.CmmnModelElementInstance;
import org.camunda.bpm.model.cmmn.instance.Expression;

public class CmmnModelInstanceHelper {

  public static boolean isEmpty(String string) {
    return string == null || string.isEmpty();
  }

  public static boolean isEmpty(Expression expression) {
    return expression == null || expression.getTextContent() == null || expression.getTextContent().isEmpty();
  }

  @SuppressWarnings("unchecked")
  public static void setExpression(CmmnModelElementInstance parentElement, String expression) {
    try {
      for (Method method : parentElement.getClass().getMethods()) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 1 && Expression.class.isAssignableFrom(parameterTypes[0])) {
          method.invoke(parentElement, (createExpression(expression, (Class<? extends Expression>) parameterTypes[0], parentElement)));
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static <T extends Expression> T createExpression(String expression, Class<T> expressionType, CmmnModelElementInstance parentElement) {
    T expressionElement = createElement(parentElement, expressionType);
    expressionElement.setTextContent(expression);
    expressionElement.removeAttribute("id");
    return expressionElement;
  }

  public static <T extends CmmnModelElementInstance> T createElement(CmmnModelElementInstance parentElement, String id, Class<T> elementClass) {
    T element = parentElement.getModelInstance().newInstance(elementClass);
    element.setAttributeValue("id", id, true);
    parentElement.addChildElement(element);
    return element;
  }
  
  public static <T extends CmmnModelElementInstance> T createElement(CmmnModelElementInstance parentElement, Class<T> elementClass) {
    T element = parentElement.getModelInstance().newInstance(elementClass);
    parentElement.addChildElement(element);
    return element;
  }

}
