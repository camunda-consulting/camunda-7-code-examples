package org.camunda.bpm.demo.executify;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.camunda.bpm.model.cmmn.Cmmn;
import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.xml.ModelInstance;

public class Executify {

  public static List<ExecutableModel> makeExecutable(Map<String, InputStream> models) {
    List<ExecutableModel> executableModels = new ArrayList<ExecutableModel>();
    for (Entry<String, InputStream> entry : models.entrySet()) {
      String filename = entry.getKey();
      InputStream stream = entry.getValue();
      ModelInstance modelInstance;
      if (filename.endsWith(".bpmn")) {
        modelInstance = new BpmnExecutifier().executify(stream);
      } else if (filename.endsWith(".cmmn")) {
        modelInstance = Cmmn.readModelFromStream(stream);
      } else if (filename.endsWith(".dmn")) {
        modelInstance = Dmn.readModelFromStream(stream);
      } else {
        throw new UnsupportedOperationException("Only *.bpmn, *.cmmn and *.dmn files are supported.");
      }
      executableModels.add(new ExecutableModel(filename, modelInstance));
    }
    return executableModels;
  }

}
