# Custom Date Format in REST API
This example shows how to enable custom dateformats when querying camunda endpoints, allowing e.g. the query curl 'http://localhost:8080/engine-rest/deployment?before=2023-03-21T22:21:30.110%2B01%3A00'. <br>
Relevant code snippet in CamundaApplication:
```
  @Bean
  public ServletContextInitializer initializer() {
    return new ServletContextInitializer() {
      @Override
      public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.addListener(new CustomJacksonDateFormatListener());
                servletContext.setInitParameter("org.camunda.bpm.engine.rest.jackson.dateFormat",
                "yyyy-MM-dd'T'HH:mm:ss");
      }
    };
  }
```

## curl example without customization
```
➜  ~ echo "With default spring boot app"
With default spring boot app
➜  ~ curl 'http://localhost:8080/engine-rest/deployment?before=2100-03-21T22:21:30.110%2B01%3A00'
{"type":"InvalidRequestException","message":"Cannot set query parameter 'before' to value '2023-03-21T22:21:30.110+01:00': Cannot convert value \"2023-03-21T22:21:30.110+01:00\" to java type java.util.Date","code":null}%
```

## curl example with customization
```
➜  ~ echo "With ServletContextInitializer setting jackson dateFormat"
With ServletContextInitializer setting jackson dateFormat
➜  ~ curl 'http://localhost:8080/engine-rest/deployment?before=2100-03-21T22:21:30.110%2B01%3A00'
[{"links":[],"id":"f6f834a8-c825-11ed-bf43-da6cab247c35","name":"openapiexample","source":"process application","deploymentTime":"2023-03-21T21:21:30","tenantId":null}]%
➜  ~
```