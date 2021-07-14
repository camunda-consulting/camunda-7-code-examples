package org.company.camunda;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE) //configured last
@ConditionalOnBean(type = "org.camunda.bpm.engine.ProcessEngine") //only if the ProcessEngine exists
public class MySpringConfig {

    @Configuration
    @ComponentScan(basePackages = {"org.company.camunda.plugin","org.company.camunda.delegate"})
    public static class ComponentScanConfiguration {
    }
}
