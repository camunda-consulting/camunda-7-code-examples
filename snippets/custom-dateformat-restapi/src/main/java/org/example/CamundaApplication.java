package org.example;

import org.camunda.bpm.engine.rest.CustomJacksonDateFormatListener;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@SpringBootApplication
@EnableProcessApplication("openapiexample")
public class CamundaApplication {

  public static void main(String... args) {
    SpringApplication.run(CamundaApplication.class, args);
  }

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
}
