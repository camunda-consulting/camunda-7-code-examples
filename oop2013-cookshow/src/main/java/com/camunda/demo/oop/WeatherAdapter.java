package com.camunda.demo.oop;

import java.io.StringReader;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXB;

import net.webservicex.GlobalWeather;

@Named("weatherAdapter")
public class WeatherAdapter {
  
  private static Logger log = Logger.getLogger(WeatherAdapter.class.getName());
  
  @Inject
  @Named
  private Order order;
  

  public void getWeather() {
    String weatherResponse = new GlobalWeather().getGlobalWeatherSoap().getWeather(order.getCity(), "Germany");
    
    log.info("### retrieved weather response :" + weatherResponse);
    
    WeatherResponse weather = JAXB.unmarshal(new StringReader(weatherResponse), WeatherResponse.class);
    
    order.setWeatherInfo(weather.getTemperature());
  }
}
