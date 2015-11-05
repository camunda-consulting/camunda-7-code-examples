package org.camunda.bpm.demo.cockpit.plugin.kpi.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ExtenstionElementReader {
  public static final String NAME = "name";
  public static final String ID = "id";

  //
  // public static String findProperty(BpmnModelInstance modelInstance, String
  // propertyName) {
  // Collection<CamundaProperties> propertiesList =
  // modelInstance.getModelElementsByType(CamundaProperties.class);
  // for (CamundaProperties properties : propertiesList) {
  // Collection<CamundaProperty> propertyCollection =
  // properties.getCamundaProperties();
  // for (CamundaProperty camundaProperty : propertyCollection) {
  // if (propertyName.equals(camundaProperty.getCamundaName())) {
  // return camundaProperty.getCamundaValue();
  // }
  // }
  // }
  // return null;
  // }
  //
  // public static List<CamundaProperty> findProperties(BpmnModelInstance
  // modelInstance, String propertyName) {
  // ArrayList<CamundaProperty> foundProperties = new
  // ArrayList<CamundaProperty>();
  //
  // Collection<CamundaProperties> propertiesList =
  // modelInstance.getModelElementsByType(CamundaProperties.class);
  // for (CamundaProperties properties : propertiesList) {
  // Collection<CamundaProperty> propertyCollection =
  // properties.getCamundaProperties();
  // for (CamundaProperty camundaProperty : propertyCollection) {
  // if (propertyName.equals(camundaProperty.getCamundaName())) {
  // // Parent 1: <camunda:properties>
  // // Parent 2: <bpmn:extensionElements>
  // // PArent 3: ACTIVITY
  // foundProperties.add(camundaProperty);
  // }
  // }
  // }
  // return foundProperties;
  // }

  public static String findProperty(Document bpmn, String propertyName) {
    List<String> propertyValues = findPropertyValues(bpmn, propertyName);
    if (propertyValues.size()>0) {
      return propertyValues.get(0);      
    } else {
      return null;
    }
  }

  public static List<String> findPropertyValues(Document bpmn, String propertyName) {
    try {
      ArrayList<String> result = new ArrayList<String>();
      
      // apply XPath to find all elements with attribute "name"
      XPath xPath = XPathFactory.newInstance().newXPath();
      NodeList nodes = (NodeList) xPath.evaluate("//*[@name='" + propertyName + "']", bpmn.getDocumentElement(), XPathConstants.NODESET);

      for (int i = 0; i < nodes.getLength(); i++) {
        result.add(((Element) nodes.item(i)).getAttribute("value"));
      }
      
      return result;      
    } catch (Exception ex) {
      throw new RuntimeException("Could not read attributes from model", ex);
    }
  }
  
  public static String findActivityAttributeForProperty(Document bpmn, String propertyName, String attribute) {
    List<String> properties = findActivityAttributesForProperty(bpmn, propertyName, attribute);
    if (properties.size() == 0) {
      return null;
    } else {
      return properties.get(0);
    }
  }

  // public static List<String> findActivityIdsForProperty(BpmnModelInstance
  // modelInstance, String propertyName) {
  // ArrayList<String> result = new ArrayList<String>();
  //
  // List<CamundaProperty> properties = findProperties(modelInstance,
  // propertyName);
  // for (CamundaProperty camundaProperty : properties) {
  // // Parent 1: <camunda:properties>
  // // Parent 2: <bpmn:extensionElements>
  // // PArent 3: ACTIVITY
  // result.add(camundaProperty.getParentElement().getParentElement().getParentElement().getAttributeValue("id"));
  // }
  // return result;
  // }

  public static Document parseBpmnXml(InputStream bpmnXml) {
    try {
      DocumentBuilder b = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document doc = b.parse(bpmnXml);
      return doc;
    } catch (Exception ex) {
      throw new RuntimeException("Could not parse BPMN XML", ex);
    }
  }

  public static List<String> findActivityAttributesForProperty(Document bpmn, String propertyName, String attribute) {
    try {
      ArrayList<String> result = new ArrayList<String>();

      // apply XPath to find all elements with attribute "name"
      XPath xPath = XPathFactory.newInstance().newXPath();
      NodeList nodes = (NodeList) xPath.evaluate("//*[@name='" + propertyName + "']", bpmn.getDocumentElement(), XPathConstants.NODESET);

      for (int i = 0; i < nodes.getLength(); ++i) {
        String activityId = ((Element) nodes.item(i).getParentNode().getParentNode().getParentNode()).getAttribute(attribute);
        result.add(activityId);
      }
      return result;
    } catch (Exception ex) {
      throw new RuntimeException("Could not read attributes from model", ex);
    }
  }

  public static String findActivityIdForProperty(Document bpmn, String propertyName) {
    return findActivityAttributeForProperty(bpmn, propertyName, ID);
  }

  public static String findActivityNameForProperty(Document bpmn, String propertyName) {
    return findActivityAttributeForProperty(bpmn, propertyName, NAME);
  }

  public static List<String> findActivityIdsForProperty(Document bpmn, String propertyName) {
    return findActivityAttributesForProperty(bpmn, propertyName, ID);
  }

  public static List<String> findActivityNamesForProperty(Document bpmn, String propertyName) {
    return findActivityAttributesForProperty(bpmn, propertyName, NAME);
  }

  private static List<String> findActivities(Document bpmn, String eventName, String attribute) {
    try {
      ArrayList<String> result = new ArrayList<String>();

      // apply XPath to find all elements with attribute "name"
      XPath xPath = XPathFactory.newInstance().newXPath();
      NodeList nodes = (NodeList) xPath.evaluate("//*[local-name()='"+eventName+"']", bpmn.getDocumentElement(), XPathConstants.NODESET);

      for (int i = 0; i < nodes.getLength(); ++i) {
        String activityId = ((Element) nodes.item(i)).getAttribute(attribute);
        result.add(activityId);
      }
      return result;
    } catch (Exception ex) {
      throw new RuntimeException("Could not read attributes from model", ex);
    }
  }
  
  public static List<String> findEndEventActivities(Document bpmn, String attribute) {
    return findActivities(bpmn, "endEvent", attribute);
  }

  public static List<String> findStartEventActivities(Document bpmn, String attribute) {
    return findActivities(bpmn, "startEvent", attribute);
  }
}
