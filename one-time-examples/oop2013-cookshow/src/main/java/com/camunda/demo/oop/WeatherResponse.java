package com.camunda.demo.oop;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CurrentWeather")
public class WeatherResponse {

  private String temperature;
  private List<String> wind;
  
  @XmlElement(name="Temperature")
  public String getTemperature() {
    return temperature;
  }
  
  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }
  
  @XmlElement(name="Wind")
  public List<String> getWind() {
    return wind;
  }
  
  public void setWind(List<String> wind) {
    this.wind = wind;
  }

//  <CurrentWeather>
//    <Location>Stuttgart-Echterdingen, Germany (EDDS) 48-41N 009-13E 391M</Location>
//    <Time>Jan 23, 2013 - 12:50 PM EST / 2013.01.23 1750 UTC</Time>
//    <Wind> from the N (350 degrees) at 6 MPH (5 KT):0</Wind>
//    <Visibility> 4 mile(s):0</Visibility>
//    <SkyConditions> mostly cloudy</SkyConditions>
//    <Temperature> 33 F (1 C)</Temperature>
//    <Wind>Windchill: 26 F (-3 C):1</Wind>
//    <DewPoint> 28 F (-2 C)</DewPoint>
//    <RelativeHumidity> 80%</RelativeHumidity>
//    <Pressure> 29.71 in. Hg (1006 hPa)</Pressure>
//    <Status>Success</Status>
//  </CurrentWeather>
}
