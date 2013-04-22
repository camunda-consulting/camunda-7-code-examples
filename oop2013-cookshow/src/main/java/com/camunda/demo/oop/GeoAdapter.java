package com.camunda.demo.oop;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

@Named("geoAdapter")
public class GeoAdapter {
  
  private static Logger log = Logger.getLogger(GeoAdapter.class.getName());

  @Inject
  @Named
  private Order order;

  public void retrieveCity() throws Exception {
    ClientRequest request = new ClientRequest("http://api.geonames.org/postalCodeSearchJSON");
    request.queryParameter("postalcode", order.getZip());
    request.queryParameter("country", "DE");
    request.queryParameter("username", "camunda");
    request.accept("application/json");
    ClientResponse<String> response = request.get(String.class);
    
    JSONObject jsonObject = new JSONObject(response.getEntity());
    JSONArray jsonArray = jsonObject.getJSONArray("postalCodes");
    
    if (jsonArray.length()==0) {
      throw new BpmnError("CITY_NOT_FOUND");
    }
    
    String city = jsonArray.getJSONObject(0).getString("placeName");
    
    log.info("### city retrieved: " + city);
    
    order.setCity(city);
  }

}
