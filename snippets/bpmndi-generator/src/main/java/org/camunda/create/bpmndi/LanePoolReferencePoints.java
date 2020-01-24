package org.camunda.create.bpmndi;

public class LanePoolReferencePoints {
    private double xBase;
    private double yBase;

    // TODO - add more entry and exit points to provide additional choices
    public LanePoolReferencePoints(double xBase, double yBase) {
        this.xBase = xBase;
        this.yBase = yBase;
    }

    public double getXBase() {
        return this.xBase;
    }

    public double getYBase() {
        return this.yBase;
    }
}
