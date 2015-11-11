package com.camunda.demo.environment;

import java.util.Random;

public class StatisticsHelper {
  
  public static Random random = new Random();
//  private static Map<String, BaseStream> randomStreams;
  
//  private static Map<String, NormalDistribution> distributions = new HashMap<String, NormalDistribution>();
  
  public static int nextSample(String name, int upperBound) {
//    if (!randomStreams.containsKey(name)) {
      return random.nextInt(upperBound);
  }
  public static int nextSample(int upperBound) {
    return random.nextInt(upperBound);
}
  
//  public static int nextIntSample(String name, ) {
//  if (!distributions.containsKey(task.getTaskDefinitionKey())) {
//    BaseElement taskElement = engine.getRepositoryService().getBpmnModelInstance(pi.getProcessDefinitionId()).getModelElementById(task.getTaskDefinitionKey());
//    double durationMean = Double.parseDouble(readCamundaProperty(taskElement, "durationMean"));
//    double durationStandardDeviation = Double.parseDouble(readCamundaProperty(taskElement, "durationStandardDeviation"));
//    
//    distributions.put(task.getTaskDefinitionKey(), new NormalDistribution(durationMean, durationStandardDeviation));
//  }
//  }
}
