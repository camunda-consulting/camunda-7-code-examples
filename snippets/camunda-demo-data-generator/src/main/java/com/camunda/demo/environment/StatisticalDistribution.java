package com.camunda.demo.environment;

import org.apache.commons.math3.distribution.NormalDistribution;

public class StatisticalDistribution {
  private String type = "erlang";
//  private double mean=94.3247;
//  private double standardDeviation=52.8106;
  private NormalDistribution distribution;
  
  public StatisticalDistribution(double mean, double standardDeviation)  {
    distribution = new NormalDistribution(mean, standardDeviation);    
  }
  
  public double nextSample() {
    return distribution.sample();
  }
}
