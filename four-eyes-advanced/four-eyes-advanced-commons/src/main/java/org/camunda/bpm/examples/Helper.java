package org.camunda.bpm.examples;

import org.camunda.bpm.engine.impl.bpmn.diagram.Bpmn20NamespaceContext;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

/**
 * Helper class basically for maintaining constants and XML handling using plain
 * DOM
 * 
 * @author ruecker
 */
public class Helper {

  public static final String PROCESS_VARIABLE_NAME = "4_EYES_LAST_USER";
  public static final String FOUR_EYES_GROUP_NAME = "fourEyeGroup";
  public static final String FOUR_EYES_ROLE_NAME = "role";

  /**
   * Namespace must be added to your process definiton:
   * 
   * xmlns:fox="http://www.camunda.com/fox"
   */
  public static final String FOX_NS = "http://www.camunda.com/fox";
  public static final String ELEMENT_NAME_EXTENSION_ELEMENTS = "extensionElements";

  public static Element getUserTaskExtensions(InputStream inputStream, String activityId, String elementName) {
    try {
      // add our own namespace to context already knowing BPMN 2.0 and Activiti namespaces
      Bpmn20NamespaceContext bpmn20Namespace = new Bpmn20NamespaceContext() {

        public Bpmn20NamespaceContext addFoxNamespace() {
          namespaceUris.put("fox", FOX_NS);
          return this;
        }
      }.addFoxNamespace();

      XPathFactory factory = XPathFactory.newInstance();
      XPath xPath = factory.newXPath();
      xPath.setNamespaceContext(bpmn20Namespace);

      XPathExpression xPathExpression = xPath.compile("//bpmn:userTask[@id='" + activityId + "']/bpmn:extensionElements/fox:" + elementName);
      return (Element) xPathExpression.evaluate(new InputSource(inputStream), XPathConstants.NODE);
    } catch (Exception ex) {
      throw new RuntimeException("Exception on evaluating process definition", ex);
    }
  }

  public static String getVariableName(String foxGroupName) {
    return PROCESS_VARIABLE_NAME + "_" + foxGroupName;
  }

}
