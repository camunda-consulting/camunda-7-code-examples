### Securing Camunda's REST API when using Spring Boot

This is an example project created with the Spring Boot Archetype to showcase how to secure the embedded REST API using Camunda's Authentication Filter.

More detailed information about how the filter works can be found in the [Docs](https://docs.camunda.org/manual/latest/reference/rest/overview/authentication/).

#### Show me the important parts

All you have to do is to add a `FilterRegistrationBean` to your Spring Boot app which registers a `ProcessEngineAuthenticationFilter`:

    @Bean
    public FilterRegistrationBean myFilterRegistration() {

      FilterRegistrationBean registration = new FilterRegistrationBean();
      registration.setFilter(new ProcessEngineAuthenticationFilter());
      registration.addUrlPatterns("/rest/*");
      registration.addInitParameter("authentication-provider", "org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
      registration.setName("camunda-auth");
      registration.setOrder(1);
      return registration;

    }

#### Run the project

You download the sample and run the app using `mvn spring-boot:run`. Then use a REST client of your choice and interact with the REST API after authenticating as the user `demo` with the password `demo`.