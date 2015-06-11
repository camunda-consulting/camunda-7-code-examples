package com.camunda.bpm.demo.soap_connector_with_ws_security;

import java.io.StringReader;
import java.io.StringWriter;

import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.message.WSSecHeader;
import org.apache.wss4j.dom.message.WSSecUsernameToken;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

@Named
public class WSSUsernameTokenGenerator {
  
  public String addToken(String soapMessage) throws WSSecurityException {
    WSSecUsernameToken builder = new WSSecUsernameToken();
     builder.setPasswordType(WSConstants.PASSWORD_TEXT);
     builder.setUserInfo("Alice", "ecilA");
     builder.addNonce();
     builder.addCreated();
     Document doc = convertStringToDocument(soapMessage);
     WSSecHeader secHeader = new WSSecHeader();
     secHeader.insertSecurityHeader(doc);
     Document signedDoc = builder.build(doc, secHeader);
    String soapMessageWithHeader = convertDocumentToString(signedDoc);
    // replacements to work with SoapUI message templates
    return soapMessageWithHeader.replace("<soapenv:Header/>", "").replace("<Header>", "<soapenv:Header>").replace("</Header>", "</soapenv:Header>");
  }

  private static String convertDocumentToString(Document doc) {
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer;
    try {
        transformer = tf.newTransformer();
        // below code to remove XML declaration
        // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        String output = writer.getBuffer().toString();
        return output;
    } catch (TransformerException e) {
        e.printStackTrace();
    }
     
    return null;
}

private static Document convertStringToDocument(String xmlStr) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
    DocumentBuilder builder;  
    try 
    {  
        builder = factory.newDocumentBuilder();  
        Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
        return doc;
    } catch (Exception e) {  
        e.printStackTrace();  
    } 
    return null;
}
}
