package com.camunda.demo.i18n.processmodel;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ProcessModelTranslator {

  private Properties translations;

  public ProcessModelTranslator(Properties translations) {
    this.translations = translations;
  }

  public String getTranslatedProcessModel(InputStream processModelInputStream) {
    try {
       // create DOM strcuture
      DocumentBuilder b = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      org.w3c.dom.Document doc = b.parse(processModelInputStream);

      // apply XPath to find all elements with attribute "name"
      XPath xPath = XPathFactory.newInstance().newXPath();
      NodeList nodes = (NodeList) xPath.evaluate("//*[@name]", doc.getDocumentElement(), XPathConstants.NODESET);

      for (int i = 0; i < nodes.getLength(); ++i) {

        // decode name in all these elements:
        Element elemenWithAttributeName = (Element) nodes.item(i);
        String i18nKey = elemenWithAttributeName.getAttribute("name");

        // decode
        if (i18nKey != null) {
          elemenWithAttributeName.setAttribute("name", translations.getProperty(i18nKey));
        }
      }

      // convert adjusted DOM to String
      DOMSource domSource = new DOMSource(doc);
      StringWriter writer = new StringWriter();
      StreamResult result = new StreamResult(writer);
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer transformer = tf.newTransformer();
      transformer.transform(domSource, result);
      String xmlDiagramInternationalized = writer.toString();

      return xmlDiagramInternationalized;
    } catch (Exception ex) {
      throw new RuntimeException("Could not translate process model", ex);
    }
  }

}
