package org.camunda.create.bpmndi;

import org.camunda.bpm.model.bpmn.instance.BaseElement;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.xml.ModelInstance;

public class DrawShape {

    public static BpmnPlane drawShape(BpmnPlane plane, ModelInstance modelInstance, BpmnModelElementInstance element, double x, double y, double height, double width, boolean setHorizontal){
        BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
        bpmnShape.setBpmnElement((BaseElement) element);

        if (setHorizontal) {
            bpmnShape.setHorizontal(true);
        }

        Bounds bounds = modelInstance.newInstance(Bounds.class);
        bounds.setX(x);
        bounds.setY(y);
        bounds.setHeight(height);
        bounds.setWidth(width);
        bpmnShape.setBounds(bounds);

        plane.addChildElement(bpmnShape);

        return plane;
    }
}
