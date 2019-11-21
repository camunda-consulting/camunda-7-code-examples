package org.camunda.create.bpmndi;

public class SequenceReferencePoints {
    private String xEntry;
    private String yEntry;
    private String xExit;
    private String yExit;

    // TODO - add more entry and exit points to provide additional choices
    public SequenceReferencePoints(String xEntry, String yEntry, String xExit, String yExit) {
        this.xEntry = xEntry;
        this.yEntry = yEntry;
        this.xExit = xExit;
        this.yExit = yExit;
    }

    public String getXEntry() {
        return this.xEntry;
    }

    public String getYEntry() {
        return this.yEntry;
    }

    public String getXExit() {
        return this.xExit;
    }

    public String getYExit() {
        return this.yExit;
    }

}
