package org.camunda.bpm.engine.impl.persistence.entity;

public class JobUtil {

    public static void createIncident(JobEntity jobEntity){
        jobEntity.createFailedJobIncident();
    }
}
