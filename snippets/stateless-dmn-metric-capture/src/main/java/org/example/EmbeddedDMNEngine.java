package org.example;

import org.camunda.bpm.dmn.engine.*;
import org.camunda.bpm.dmn.engine.impl.metrics.DefaultEngineMetricCollector;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Logger;


public class EmbeddedDMNEngine {

    private final static Logger LOGGER = Logger.getLogger(EmbeddedDMNEngine.class.getName());

    public static void main (String[] args) throws Exception {
        // create dmn engine config
        DmnEngineConfiguration configuration = DmnEngineConfiguration.createDefaultDmnEngineConfiguration();

        // Create metric collector
        DefaultEngineMetricCollector metricCollector = new DefaultEngineMetricCollector();

        // add metric collector to config
        configuration.setEngineMetricCollector(metricCollector);

        // build engine using config
        DmnEngine dmnEngine = configuration.buildEngine();

        // read sample dmn and convert to inputstream
        File file = new File("./src/main/resources/sample.dmn");
        InputStream in = new FileInputStream(file);
        InputStream in2 = new FileInputStream(file);

        // create inputs to dmns
        HashMap<String, Object> map = new HashMap<>();
        map.put("input", "yes"); // or no
        map.put("output", "");

        // add rules to engine
        DmnDecision dmnd1 = dmnEngine.parseDecision("Decision_1", in);
        DmnDecision dmnd2 = dmnEngine.parseDecision("Decision_2", in2);

        // evaluate decision(s) (repeat as needed)
        DmnDecisionResult dmndr = dmnEngine.evaluateDecision(dmnd1, map);
        String result = dmndr.getSingleEntry();
        LOGGER.info("Result of Decision 1 is "+result);

        dmndr = dmnEngine.evaluateDecision(dmnd1, map);
        result = dmndr.getSingleEntry();
        LOGGER.info("Result of Decision 1 is "+result);

        dmndr = dmnEngine.evaluateDecision(dmnd2, map);
        result = dmndr.getSingleEntry();
        LOGGER.info("Result of Decision 2 is "+result);


        // get executed DIs
        long dis = dmnEngine.getConfiguration().getEngineMetricCollector().getExecutedDecisionInstances();
        LOGGER.info("Executed DIs are "+ dis);

        // at this point the DI data should be sent somewhere for safekeeping
    }
}

