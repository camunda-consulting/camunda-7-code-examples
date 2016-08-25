package org.camunda.bpm.demo.executify;

import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.model.xml.ModelInstance;
import org.camunda.bpm.model.xml.impl.util.IoUtil;


public class ExecutableModel {

  private String filename;
  private ModelInstance modelInstance;

  public ExecutableModel(String filename, ModelInstance modelInstance) {
    this.filename = filename;
    this.modelInstance = modelInstance;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public ModelInstance getModelInstance() {
    return modelInstance;
  }

  public void setModelInstance(ModelInstance modelInstance) {
    this.modelInstance = modelInstance;
  }

  public String getXmlString() {
    return IoUtil.convertXmlDocumentToString(modelInstance.getDocument());
  }
  public void addToDeployment(DeploymentBuilder deployment) {
    deployment.addString(filename, getXmlString());
  }

  

}
