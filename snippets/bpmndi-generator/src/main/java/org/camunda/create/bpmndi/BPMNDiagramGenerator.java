package org.camunda.create.bpmndi;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnDiagram;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class BPMNDiagramGenerator {
    public static void main(String[] args)  {
        try {

            // Read and parse file
            // Need to pass in two args - arg[0] is the input file and arg[1] is the output file
            if(args.length < 2) {
                System.out.println("Two arguments are required for this BPMN Diagram generator program. One for the input file followed by one for the output file.");
                return;
            }

            // read a model from a file
            File file = new File(args[0]);

            BpmnModelInstance modelInstance = Bpmn.readModelFromFile(file);
            Definitions definitions = modelInstance.getDefinitions();

            // Read document for Xpath searches
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            // For the diagram, a diagram and a plane element need to be created. The plane is set in a diagram object and the diagram is added as a child element
            BpmnDiagram bpmnDiagram = modelInstance.newInstance(BpmnDiagram.class);
            BpmnPlane plane = modelInstance.newInstance(BpmnPlane.class);

            bpmnDiagram.setBpmnPlane(plane);
            definitions.addChildElement(bpmnDiagram);

            // Set collaboration attribute on plane. There should only be one collaboration if there is any
            ArrayList<Collaboration> collaborations = (ArrayList<Collaboration>) modelInstance.getModelElementsByType(Collaboration.class);

            switch (collaborations.size()) {
                case 0:
                    // Search for process instead of a collaboration and set plane bpmn element to the process
                    ArrayList<Process> processes = (ArrayList<Process>) modelInstance.getModelElementsByType(Process.class);
                    if(processes.size() == 0) {
                        throw new Exception("Exception - neither collaboration nor process was found");
                    } else {
                        plane.setBpmnElement((BaseElement) processes.get(0));
                    }
                    break;

                case 1:
                    plane.setBpmnElement((BaseElement) collaborations.get(0));
                    break;

                default :
                    throw new Exception("Exception - more than one collaboration in BPMN input which is not allowed");
            }

            // Create a map of Pool and Lane reference points in order to draw the correct shapes in the correct lane
            HashMap<String, LanePoolReferencePoints> poolRefPoints = new HashMap<>();
            HashMap<String, LanePoolReferencePoints> laneRefPoints = new HashMap<>();

            // Create a map of elements and their associated lanes if lanes are detected
            HashMap<String, String> laneElementContent = new HashMap<>();

            // Draw pools (aka participants) - need to calculate size based on the number of swimlanes
            ArrayList<Participant> participants = (ArrayList<Participant>) modelInstance.getModelElementsByType(Participant.class);

            // Some vendors do not use pools. If they do, add the pool and any lanes. If they don't, just add lanes (the else portion)
            if(participants.size() > 0) {
                for (int i = 0; i < participants.size(); i++) {
                    BpmnModelElementInstance element = participants.get(i);
                    plane = DrawShape.drawShape(plane, modelInstance, element, 70, 100 + (1000 * i) + (i > 0 ? 100 : 0), 1000, 1530, true);
                    poolRefPoints.put(participants.get(i).getId(), new LanePoolReferencePoints(70, 100 + (1000 * i) + (i > 0 ? 100 : 0)));

                    // Get process and then the lane sets
                    int counter = 0;
                    Collection<LaneSet> laneSets = participants.get(i).getProcess().getLaneSets();
                    Iterator<LaneSet> iter = laneSets.iterator();
                    while (iter.hasNext()) {
                        LaneSet ls = iter.next();
                        Collection<Lane> lanes = ls.getLanes();
                        Iterator<Lane> iterLane = lanes.iterator();
                        while (iterLane.hasNext()) {
                            Lane lane = iterLane.next();
                            element = lane;
                            // Get flow nodes in lane and create map entries
                            Collection<FlowNode> flowNodes = lane.getFlowNodeRefs();
                            Iterator<FlowNode> iterFn = flowNodes.iterator();
                            while(iterFn.hasNext()) {
                                FlowNode fn = iterFn.next();
                                laneElementContent.put(fn.getId(),lane.getId());
                            }
                            // Draw lane
                            plane = DrawShape.drawShape(plane, modelInstance, element, 100, 100 + (500 * counter) + (i > 0 ? 1100 * i : 0), 500, 1500, true);
                            laneRefPoints.put(lane.getId(), new LanePoolReferencePoints(0, 300 * counter ));
                            counter++;
                        }
                    }
                }
            } else { // If no collaboration defined then check for processes, lane sets, and lanes
                int counter = 0;
                ArrayList<Process> processes = (ArrayList<Process>) modelInstance.getModelElementsByType(Process.class);
                Iterator<Process> iterProcess = processes.iterator();
                while (iterProcess.hasNext()) {
                    Collection<LaneSet> laneSets = iterProcess.next().getLaneSets();
                    Iterator<LaneSet> iterLaneSets = laneSets.iterator();
                    while (iterLaneSets.hasNext()) {
                        Collection<Lane> lanes = iterLaneSets.next().getLanes();
                        Iterator<Lane> iterLanes = lanes.iterator();
                        while (iterLanes.hasNext()) {
                            Lane lane = iterLanes.next();
                            BpmnModelElementInstance element = lane;
                            // Get flow nodes in lane and create map entries
                            Collection<FlowNode> flowNodes = lane.getFlowNodeRefs();
                            Iterator<FlowNode> iterFn = flowNodes.iterator();
                            while(iterFn.hasNext()) {
                                FlowNode fn = iterFn.next();
                                laneElementContent.put(fn.getId(),lane.getId());
                            }
                            // Draw lane
                            plane = DrawShape.drawShape(plane, modelInstance, element, 100, 100 + (500 * counter), 500, 1500, true);
                            laneRefPoints.put(lane.getId(), new LanePoolReferencePoints(0, 500 * counter));
                            counter++;
                        }
                    }
                }
            }

            // Create a hash map of the x and y coordinates of the start and endpoints of each shape to be a reference later on for the sequence flows.
            // As we add shapes we'll use the id of each element as a key and save the coordinates
            HashMap<String, SequenceReferencePoints> refPoints = new HashMap<>();

            // An array for shape source references to place process shapes in relative sequential order
            ArrayList<String> sourceRefs = new ArrayList<String>();
            ArrayList<String> nextSourceRefs = new ArrayList<String>();

            // Objects to search for and save references and coordinates
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Get start events
            XPathExpression searchRequest = xpath.compile("//*[contains(name(),'startEvent')]");
            NodeList eventNodes = (NodeList) searchRequest.evaluate(doc, XPathConstants.NODESET);

            int x = 0;
            // Begin diagram by drawing start events
            for (int i = 0; i < eventNodes.getLength(); i++) {
                Element eElement = (Element) eventNodes.item(i);
                BpmnModelElementInstance element = modelInstance.getModelElementById(eElement.getAttribute("id"));
                double xLane = getLaneXOffset(laneElementContent, laneRefPoints, eElement.getAttribute("id"));
                double yLane = getLaneYOffset(laneElementContent, laneRefPoints, eElement.getAttribute("id"));
                plane = DrawShape.drawShape(plane, modelInstance, element, xLane + 200, yLane + 200 + (200*i), 36, 36, false );
                refPoints.put(eElement.getAttribute("id"), new SequenceReferencePoints(xLane + 200, (yLane + 220 + i * 200),xLane + 236,(yLane + 220 + i * 200)));
                sourceRefs.add(eElement.getAttribute("id"));
            }

            x += 180;

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
                                        BpmnModelElementInstance element = modelInstance.getModelElementById(sElement.getAttribute("id"));
                                        double xLane = getLaneXOffset(laneElementContent, laneRefPoints, sElement.getAttribute("id"));
                                        double yLane = getLaneYOffset(laneElementContent, laneRefPoints, sElement.getAttribute("id"));
                                        plane = DrawShape.drawShape(plane, modelInstance, element, xLane + x, (yLane + 180 + yOffset) + y * 200, 80, 100, false );
                                        refPoints.put(sElement.getAttribute("id"), new SequenceReferencePoints(xLane + x, ((220 + yOffset + yLane) + y * 200), (xLane+ x + 100), ((yLane + 220 + yOffset) + y * 200)));

                                        // check for boundary events
                                        XPathExpression boundaryRequest = xpath.compile("//*[@attachedToRef='" + sElement.getAttribute("id") + "']");
                                        NodeList boundaryEvents = (NodeList) boundaryRequest.evaluate(doc, XPathConstants.NODESET);

                                        for (int q = 0; q < boundaryEvents.getLength(); q++) {
                                            Element bdElement = (Element) boundaryEvents.item(q);
                                            element = modelInstance.getModelElementById(bdElement.getAttribute("id"));
                                            plane = DrawShape.drawShape(plane, modelInstance, element, xLane + x + q * 40, (yLane + 240 + yOffset) + y * 200, 36, 36, false );
                                            refPoints.put(bdElement.getAttribute("id"), new SequenceReferencePoints(0, 0, ((xLane + x + 15) + q * 40), ((yLane + 275 + yOffset) + y * 200)));
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
                                        element = modelInstance.getModelElementById(sElement.getAttribute("id"));
                                        xLane = getLaneXOffset(laneElementContent, laneRefPoints, sElement.getAttribute("id"));
                                        yLane = getLaneYOffset(laneElementContent, laneRefPoints, sElement.getAttribute("id"));
                                        plane = DrawShape.drawShape(plane, modelInstance, element, xLane + x, ((yLane + 195 + yOffset) + y * 200), 50, 50, false );
                                        refPoints.put(sElement.getAttribute("id"), new SequenceReferencePoints(xLane + x, ((yLane + 220 + yOffset) + y * 200), (xLane + x + 50), ((yLane + 220 + yOffset) + y * 200)));
                                        break;

                                    case ("intermediateThrowEvent"):
                                    case ("bpmn:intermediateThrowEvent"):
                                    case ("intermediateCatchEvent"):
                                    case ("bpmn:intermediateCatchEvent"):
                                    case ("endEvent"):
                                    case ("bpmn:endEvent"):
                                        element = modelInstance.getModelElementById(sElement.getAttribute("id"));
                                        xLane = getLaneXOffset(laneElementContent, laneRefPoints, sElement.getAttribute("id"));
                                        yLane = getLaneYOffset(laneElementContent, laneRefPoints, sElement.getAttribute("id"));
                                        plane = DrawShape.drawShape(plane, modelInstance, element, xLane + x, ((yLane + 200 + yOffset) + y * 200), 36, 36, false );
                                        refPoints.put(sElement.getAttribute("id"), new SequenceReferencePoints(xLane + x, ((yLane + 220 + yOffset) + y * 200), (xLane + x + 36), ((yLane + 220 + yOffset) + y * 200)));
                                        break;

                                    case ("textAnnotation"):
                                    case ("bpmn:textAnnotation"):
                                        element = modelInstance.getModelElementById(sElement.getAttribute("id"));
                                        xLane = getLaneXOffset(laneElementContent, laneRefPoints, sElement.getAttribute("id"));
                                        yLane = getLaneYOffset(laneElementContent, laneRefPoints, sElement.getAttribute("id"));
                                        plane = DrawShape.drawShape(plane, modelInstance, element, xLane + x, ((yLane + 200 + yOffset) + y * 80), 200, 200, false );
                                        refPoints.put(sElement.getAttribute("id"), new SequenceReferencePoints(xLane + x, ((yLane + 220 + yOffset) + y * 80), (xLane + x + 36), ((yLane + 220 + yOffset) + y * 80)));
                                        break;
                                }
                            }
                        }
                    }

                    // If there are additional shapes move the y axis by 200 pixels
                    yOffset += 200;
                }

                sourceRefs.clear();
                sourceRefs.addAll(nextSourceRefs);
                nextSourceRefs.clear();
            }

            // Find and draw sequence flows now that the shapes have been drawn and the reference points for the sequence flows
            // have been established
            searchRequest = xpath.compile("//*[contains(name(),'sequenceFlow')]");
            NodeList sfNodes = (NodeList) searchRequest.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < sfNodes.getLength(); i++) {
                // TODO - add logic in drawFlow to create 'elbow' waypoints based on the relative xExit, xEntry and yExit, yEntry coordinates
                Element sfElement = (Element) sfNodes.item(i);
                plane = DrawFlow.drawFlow(plane, modelInstance,sfElement, refPoints);
            }

            searchRequest = xpath.compile("//*[contains(name(),'association')]");
            NodeList associationNodes = (NodeList) searchRequest.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < associationNodes.getLength(); i++) {
                Element aElement = (Element) associationNodes.item(i);
                plane = DrawFlow.drawFlow(plane, modelInstance,aElement, refPoints);
            }

            System.out.println("BPMN diagram generated");
            Bpmn.validateModel(modelInstance);
            File outputFile = new File(args[1]);
            Bpmn.writeModelToFile(outputFile, modelInstance);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static double getLaneXOffset(HashMap<String, String> laneElementContent, HashMap<String, LanePoolReferencePoints> laneRefPoints, String id) {
        double x = 0;
        String laneId = laneElementContent.get(id);
        if (laneId != null) {
            x = laneRefPoints.get(laneId).getXBase();
        }
        return x;
    }

    private static double getLaneYOffset(HashMap<String, String> laneElementContent, HashMap<String, LanePoolReferencePoints> laneRefPoints, String id) {
        double y = 0;
        String laneId = laneElementContent.get(id);
        if (laneId != null) {
            y = laneRefPoints.get(laneId).getYBase();
        }
        return y;
    }
}