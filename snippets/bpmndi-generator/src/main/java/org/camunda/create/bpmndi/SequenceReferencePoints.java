package org.camunda.create.bpmndi;

public class SequenceReferencePoints {
    private double xEntry;
    private double yEntry;
    private double xExit;
    private double yExit;

    // TODO - add more entry and exit points to provide additional choices
    public SequenceReferencePoints(double xEntry, double yEntry, double xExit, double yExit) {
        this.xEntry = xEntry;
        this.yEntry = yEntry;
        this.xExit = xExit;
        this.yExit = yExit;
    }

    public double getXEntry() {
        return this.xEntry;
    }

    public double getYEntry() {
        return this.yEntry;
    }

    public double getXExit() {
        return this.xExit;
    }

    public double getYExit() {
        return this.yExit;
    }

}
