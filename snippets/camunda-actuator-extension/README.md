# Share Job-Executor-Metrics via Spring Boot Actuator

This project contains an extension to the spring boot actuator to share the job execution metrics via micrometer.

You will get the metrics from the job executor in these endpoints:

* [http://localhost:8080/actuator/metrics/job.executions.successful](http://localhost:8080/actuator/metrics/job.executions.successful)
* [http://localhost:8080/actuator/metrics/job.executions.failed](http://localhost:8080/actuator/metrics/job.executions.failed)
* [http://localhost:8080/actuator/metrics/job.executions.rejected](http://localhost:8080/actuator/metrics/job.executions.rejected)
* [http://localhost:8080/actuator/metrics/job.acquisitions.attempted](http://localhost:8080/actuator/metrics/job.acquisitions.attempted)
* [http://localhost:8080/actuator/metrics/job.acquisitions.successful](http://localhost:8080/actuator/metrics/job.acquisitions.successful)
* [http://localhost:8080/actuator/metrics/job.acquisitions.failed](http://localhost:8080/actuator/metrics/job.acquisitions.failed)
* [http://localhost:8080/actuator/metrics/job.locks.exclusive](http://localhost:8080/actuator/metrics/job.locks.exclusive)

Once the data are published via spring boot actuator you can import them into prometheus and other monitoring tools.

To get the data from your engine, you have to copy the `JobExecutorMetricsConfiguration` class into your Camunda Spring Boot project.

## Details

The numbers are directly promoted from the internal metrics into the actuator. They are saved each 15 minutes into the database and set to 0.
See the [docs](https://docs.camunda.org/manual/7.16/user-guide/process-engine/metrics/) for further details.

You can overwrite the internal mechanism of the Metrics Reporter and for example change the interval how long the numbers are collected: [https://docs.camunda.org/manual/7.16/user-guide/process-engine/metrics/#metrics-reporter](https://docs.camunda.org/manual/7.16/user-guide/process-engine/metrics/#metrics-reporter) 

## Implementation details

Check the class [JobExecutorMetricsConfiguration](src/main/java/com/camunda/consulting/actuator/JobExecutorMetricsConfiguration.java) for implementation details.

## Required configuration

To see the data, you have to add the dependency for the `spring-boot-starter-actuator` and enable them.

This section of the `application.yaml` shows all values:

```
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always
```

## Examples

The process application contains two different processes to put some load on the Job executor and get the metrics high.

In the `util` folder you find postman collections to start a higher amount of process instances. 

## Other useful resources

* [https://www.callicoder.com/spring-boot-actuator/](https://www.callicoder.com/spring-boot-actuator/)
* [https://www.callicoder.com/spring-boot-actuator-metrics-monitoring-dashboard-prometheus-grafana/](https://www.callicoder.com/spring-boot-actuator-metrics-monitoring-dashboard-prometheus-grafana/)
* [https://mokkapps.de/blog/monitoring-spring-boot-application-with-micrometer-prometheus-and-grafana-using-custom-metrics/](https://mokkapps.de/blog/monitoring-spring-boot-application-with-micrometer-prometheus-and-grafana-using-custom-metrics/)
* [https://micrometer.io/docs/concepts](https://micrometer.io/docs/concepts)

## SSL debugging
As jvm parameter, add `-Djavax.net.debug=sslctx`
