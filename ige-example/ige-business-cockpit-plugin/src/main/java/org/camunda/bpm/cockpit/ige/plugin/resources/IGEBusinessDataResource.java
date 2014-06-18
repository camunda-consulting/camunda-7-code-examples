package org.camunda.bpm.cockpit.ige.plugin.resources;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.camunda.bpm.cockpit.db.QueryParameters;
import org.camunda.bpm.cockpit.ige.plugin.db.IGEBusinessPluginDto;
import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;

@Produces(MediaType.APPLICATION_JSON)
public class IGEBusinessDataResource extends AbstractCockpitPluginResource {
  
  private static Logger log = Logger.getLogger(IGEBusinessDataResource.class.getName()); 

  public IGEBusinessDataResource(String engineName) {
    super(engineName);
  }
  
  @GET
  public List<IGEBusinessPluginDto> getIGEBusinessData(@Context UriInfo uriInfo) {
    log.info("getIGEBusinessData " + uriInfo);
    QueryParameters<IGEBusinessPluginDto> parameter = new QueryParameters<IGEBusinessPluginDto>();
    IGEBusinessPluginDto igeBusinessPluginDto = new IGEBusinessPluginDto(uriInfo.getQueryParameters());
    parameter.setParameter(igeBusinessPluginDto);
    return getQueryService()
        .executeQuery("cockpit.ige.plugin.igeBusinessCockpitQuery", parameter);
  }

}
