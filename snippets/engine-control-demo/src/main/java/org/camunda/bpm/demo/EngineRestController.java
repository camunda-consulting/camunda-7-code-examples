package org.camunda.bpm.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.persistence.entity.MessageEntity;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/engine-management")
@RequiredArgsConstructor
public class EngineRestController {

    private final ProcessEngine engine;
    private final SpringProcessEngineConfiguration configuration;

    @PostMapping("/start")
    public void startEngine(){

        configuration.buildProcessEngine();
    }

    @PostMapping("/stop")
    public void stopEngine(){
        engine.close();
        configuration.setDbMetricsReporter(null);
    }

    @GetMapping("/jobs")
    public List<Job> getAllJobs(){
        return engine.getManagementService().createJobQuery().list();
    }


}
