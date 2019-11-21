package org.camunda.create.bpmndi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class BPMNDIGenerator {

    public static StringBuffer createBPMNDIStanza(StringBuffer sb, Document doc ) {
        try {

            // Create a hash map of the x and y coordinates of the start and endpoints of each shape to be a reference later on for the sequence flows.
            // As we add shapes we'll use the id of each element as a key and save the coordinates

            HashMap<String, SequenceReferencePoints> refPoints = new HashMap<>();

            // An array for shape source references to place process shapes in relative sequential order
            ArrayList<String> sourceRefs = new ArrayList<String>();
            ArrayList<String> nextSourceRefs = new ArrayList<String>();

            // x will determine the x axis for the placement of shapes in relative order
            int x = 180;

            // Get the process node and determine if from Process Designer or Blueworks Live
            boolean fromPD = true;
            NodeList processNodes = doc.getElementsByTagName("bpmn:process");
            if (processNodes.getLength() == 0) {
                processNodes = doc.getElementsByTagName("process");
                fromPD = false; // File is from Blueworks Live and does not have the bpmn: prefix
            }
            Element pElement = (Element) processNodes.item(0);

            sb.append("\t<bpmndi:BPMNDiagram id=\"BPMNDiagram_1\">\n");
            sb.append("\t\t<bpmndi:BPMNPlane id=\"BPMNPlane_1\" bpmnElement=\"" + pElement.getAttribute("id") + "\">\n");

            NodeList swimNodes = doc.getElementsByTagName("lane");

            if(swimNodes.getLength() > 0 ) {
                Element swElement = (Element) swimNodes.item(0);
                sb.append("\t\t\t<bpmndi:BPMNShape id=\"Participant\" bpmnElement=\""+swElement.getAttribute("id")+"\" isHorizontal=\"true\">\n");
                sb.append("\t\t\t\t<dc:Bounds x=\"144\" y=\"70\" width=\"2500\" height=\"800\" />\n");
                sb.append("\t\t\t</bpmndi:BPMNShape>\n");
            }

            // Objects to search for and save references and coordinates
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Get start events
            XPathExpression searchRequest = xpath.compile("//*[contains(name(),'startEvent')]");
            NodeList eventNodes = (NodeList) searchRequest.evaluate(doc, XPathConstants.NODESET);

            // Begin diagram by drawing start events
            for (int i = 0; i < eventNodes.getLength(); i++) {
                Element eElement = (Element) eventNodes.item(i);
                sb.append("\t\t\t<bpmndi:BPMNShape id=\"_BPMNStart_Event_" + UUID.randomUUID().toString() + "\" bpmnElement=\"" + eElement.getAttribute("id") + "\">\n");
                sb.append("\t\t\t\t<dc:Bounds x=\""+x+"\" y=\"" + (100 + i * 200) + "\" width=\"36\" height=\"36\" />\n");
                sb.append("\t\t\t</bpmndi:BPMNShape>\n");

                refPoints.put(eElement.getAttribute("id"), new SequenceReferencePoints("180",""+(120 + i * 200),"216",""+(120 + i * 200)));
                sourceRefs.add(eElement.getAttribute("id"));
            }

            // Draw next shapes
            while (sourceRefs.size() > 0) {
                // Move over 180 pixels to draw the next set of shapes
                x += 180;
                // y will determine the y axis of shape placement and the set to zero at the start of each run
                int yOffset = 0;

                for (int i = 0; i < sourceRefs.size(); i++) {
                    searchRequest = xpath.compile("//*[@sourceRef='" + sourceRefs.get(i) + "']");
                    NodeList nextShapes = (NodeList) searchRequest.evaluate(doc, XPathConstants.NODESET);

                    for (int y = 0; y < nextShapes.getLength(); y++) {
                        Element tElement = (Element) nextShapes.item(y);
                        xpath = XPathFactory.newInstance().newXPath();
                        searchRequest = xpath.compile("//*[@id='" + tElement.getAttribute("targetRef") + "']");
                        NodeList shapes = (NodeList) searchRequest.evaluate(doc, XPathConstants.NODESET);

                        for (int z = 0; z < shapes.getLength(); z++) {
                            Element sElement = (Element) shapes.item(z);
                            if (!refPoints.containsKey(sElement.getAttribute("id"))) {
                                nextSourceRefs.add(sElement.getAttribute("id"));

                                String type = sElement.getNodeName();

                                switch (type) {
                                    case ("userTask"):
                                    case ("bpmn:userTask"):
                                    case ("serviceTask"):
                                    case ("bpmn:serviceTask"):
                                    case ("businessRuleTask"):
                                    case ("bpmn:businessRuleTask"):
                                    case ("task"):
                                    case ("bpmn:task"):
                                    case ("receiveTask"):
                                    case ("bpmn:receiveTask"):
                                    case ("sendTask"):
                                    case ("bpmn:sendTask"):
                                    case ("scriptTask"):
                                    case ("bpmn:scriptTask"):
                                    case ("manualTask"):
                                    case ("bpmn:manualTask"):
                                    case ("callActivity"):
                                    case ("bpmn:callActivity"):
                                        sb.append("\t\t\t<bpmndi:BPMNShape id=\"_BPMNTypedTask_" + UUID.randomUUID().toString() + "\" bpmnElement=\"" + sElement.getAttribute("id") + "\">\n");
                                        sb.append("\t\t\t\t<dc:Bounds x=\"" + x + "\" y=\"" + ((80 + yOffset) + y * 150) + "\" width=\"100\" height=\"80\" />\n");
                                        sb.append("\t\t\t</bpmndi:BPMNShape>\n");

                                        refPoints.put(sElement.getAttribute("id"), new SequenceReferencePoints("" + x, "" + ((120 + yOffset) + y * 150), "" + (x + 100), "" + ((120 + yOffset) + y * 150)));

                                        // check for boundary events
                                        XPathExpression boundaryRequest = xpath.compile("//*[@attachedToRef='" + sElement.getAttribute("id") + "']");
                                        NodeList boundaryEvents = (NodeList) boundaryRequest.evaluate(doc, XPathConstants.NODESET);

                                        for (int q = 0; q < boundaryEvents.getLength(); q++) {
                                            Element bdElement = (Element) boundaryEvents.item(q);
                                            sb.append("\t\t\t<bpmndi:BPMNShape id=\"BoundaryEvent_" + UUID.randomUUID().toString() + "\" bpmnElement=\"" + bdElement.getAttribute("id") + "\">\n");
                                            sb.append("\t\t\t\t<dc:Bounds x=\"" + (x + q * 40) + "\" y=\"" + ((140 + yOffset) + y * 150) + "\" width=\"36\" height=\"36\" />\n");
                                            sb.append("\t\t\t\t<bpmndi:BPMNLabel>\n");
                                            sb.append("\t\t\t\t\t<dc:Bounds x=\"" + (x + q * 40) + "\" y=\"" + ((175 + yOffset) + y * 150) + "\" width=\"80\" height=\"14\" />\n");
                                            sb.append("\t\t\t\t</bpmndi:BPMNLabel>\n");
                                            sb.append("\t\t\t</bpmndi:BPMNShape>\n");

                                            refPoints.put(bdElement.getAttribute("id"), new SequenceReferencePoints("0", "0", "" + ((x + 15) + q * 40), "" + ((175 + yOffset) + y * 150)));
                                            nextSourceRefs.add(bdElement.getAttribute("id"));
                                        }
                                        break;

                                    case ("exclusiveGateway"):
                                    case ("bpmn:exclusiveGateway"):
                                    case ("inclusiveGateway"):
                                    case ("bpmn:inclusiveGateway"):
                                    case ("parallelGateway"):
                                    case ("bpmn:parallelGateway"):
                                    case ("eventBasedGateway"):
                                    case ("bpmn:eventBasedGateway"):
                                        sb.append("\t\t\t<bpmndi:BPMNShape id=\"_BPMNGateway_" + UUID.randomUUID().toString() + "\" bpmnElement=\"" + sElement.getAttribute("id") + "\">\n");
                                        sb.append("\t\t\t\t<dc:Bounds x=\"" + x + "\" y=\"" + ((95 + yOffset) + y * 100) + "\" width=\"50\" height=\"50\" />\n");
                                        sb.append("\t\t\t</bpmndi:BPMNShape>\n");

                                        refPoints.put(sElement.getAttribute("id"), new SequenceReferencePoints("" + x, "" + ((120 + yOffset) + y * 100), "" + (x + 50), "" + ((120 + yOffset) + y * 100)));
                                        break;

                                    case ("intermediateThrowEvent"):
                                    case ("bpmn:intermediateThrowEvent"):
                                    case ("intermediateCatchEvent"):
                                    case ("bpmn:intermediateCatchEvent"):
                                    case ("endEvent"):
                                    case ("bpmn:endEvent"):
                                        sb.append("\t\t\t<bpmndi:BPMNShape id=\"_BPMN_Inter_or_End_Event_" + UUID.randomUUID().toString() + "\" bpmnElement=\"" + sElement.getAttribute("id") + "\">\n");
                                        sb.append("\t\t\t\t<dc:Bounds x=\"" + x + "\" y=\"" + ((100 + yOffset) + y * 80) + "\" width=\"36\" height=\"36\" />\n");
                                        sb.append("\t\t\t</bpmndi:BPMNShape>\n");

                                        refPoints.put(sElement.getAttribute("id"), new SequenceReferencePoints("" + x, "" + ((120 + yOffset) + y * 80), "" + (x + 36), "" + ((120 + yOffset) + y * 80)));
                                        break;

                                    case ("textAnnotation"):
                                    case ("bpmn:textAnnotation"):
                                        sb.append("\t\t\t<bpmndi:BPMNShape id=\"TextAnnotation_" + UUID.randomUUID().toString() + "\" bpmnElement=\"" + sElement.getAttribute("id") + "\">\n");
                                        sb.append("\t\t\t\t<dc:Bounds x=\"" + x + "\" y=\"" + ((100 + yOffset) + y * 80) + "\" width=\"200\" height=\"200\" />\n");
                                        sb.append("\t\t\t</bpmndi:BPMNShape>\n");

                                        refPoints.put(sElement.getAttribute("id"), new SequenceReferencePoints("" + x, "" + ((120 + yOffset) + y * 80), "" + (x + 36), "" + ((120 + yOffset) + y * 80)));
                                        break;
                                }
                            }
                        }

                        // If there are additional shapes move the y axis by 150 pixels
                        yOffset += 150;
                    }
                }


                sourceRefs.clear();
                sourceRefs.addAll(nextSourceRefs);
                nextSourceRefs.clear();
            }

            // Find and draw sequence flows now that the shapes have been drawn and the reference points for the sequence flows
            // have been established
            searchRequest = xpath.compile("//*[contains(name(),'sequenceFlow')]");
            eventNodes = (NodeList) searchRequest.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < eventNodes.getLength(); i++) {
                Element eElement = (Element) eventNodes.item(i);
                sb.append("\t\t<bpmndi:BPMNEdge id=\"SequenceFlow_"+ UUID.randomUUID().toString() +"_di\" bpmnElement=\"" + eElement.getAttribute("id") + "\">\n");
                String sourceRef = eElement.getAttribute("sourceRef");
                String targetRef = eElement.getAttribute("targetRef");

                SequenceReferencePoints srp = refPoints.get(sourceRef);
                String xExit = srp.getXExit();
                String yExit = srp.getYExit();

                srp = refPoints.get(targetRef);
                String xEntry = srp.getXEntry();
                String yEntry = srp.getYEntry();

                sb.append("\t\t\t\t<di:waypoint x=\"" + xExit + "\" y=\"" + yExit + "\" />\n");
                // TODO - add logic here to create 'elbow' waypoints based on the relative xExit, xEntry and yExit, yEntry coordinates
                sb.append("\t\t\t\t<di:waypoint x=\"" + xEntry + "\" y=\"" + yEntry + "\" />\n");
                sb.append("\t\t\t</bpmndi:BPMNEdge>\n");
            }

            searchRequest = xpath.compile("//*[contains(name(),'association')]");
            eventNodes = (NodeList) searchRequest.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < eventNodes.getLength(); i++) {
                Element eElement = (Element) eventNodes.item(i);
                sb.append("\t\t\t<bpmndi:BPMNEdge id=\"Association_"+ UUID.randomUUID().toString() +"_di\" bpmnElement=\"" + eElement.getAttribute("id") + "\">\n");
                String sourceRef = eElement.getAttribute("sourceRef");
                String targetRef = eElement.getAttribute("targetRef");

                SequenceReferencePoints srp = refPoints.get(sourceRef);
                String xExit = srp.getXExit();
                String yExit = srp.getYExit();

                srp = refPoints.get(targetRef);
                String xEntry = srp.getXEntry();
                String yEntry = srp.getYEntry();

                sb.append("\t\t\t\t<di:waypoint x=\"" + xExit + "\" y=\"" + yExit + "\" />\n");
                // TODO - add logic here to create 'elbow' waypoints based on the relative xExit, xEntry and yExit, yEntry coordinates
                sb.append("\t\t\t\t<di:waypoint x=\"" + xEntry + "\" y=\"" + yEntry + "\" />\n");
                sb.append("\t\t\t</bpmndi:BPMNEdge>\n");
            }

            // Add closing tags
            sb.append("\t\t</bpmndi:BPMNPlane>\n");
            sb.append("\t</bpmndi:BPMNDiagram>\n");
            sb.append("</definitions>\n");

        } catch (Exception e) {
            System.out.println("Error occurred "+ e);
            sb.append("\t\t</bpmndi:BPMNPlane>\n");
            sb.append("\t</bpmndi:BPMNDiagram>\n");
            sb.append("</definitions>\n");
        }
        return sb;
    }
}
