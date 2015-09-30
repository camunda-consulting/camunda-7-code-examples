package com.camunda.demo.versicherungsneuantrag.facade;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import com.camunda.demo.versicherungsneuantrag.ProcessVariables;
import com.camunda.demo.versicherungsneuantrag.model.Neuantrag;

@Path("/")
public class AntragOnlineFacade {
  
//  public static final String PROCESS_KEY_WITHOUT_MTEXT = "versicherungsneuantrag";
//  public static final String PROCESS_KEY_WITH_MTEXT = "versicherungsneuantragMitDokumentenerstellung";
   
  @POST
  @Path("neuantrag/{processDefinitionKey}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void submitNewAntrag(@PathParam("processDefinitionKey") String processDefinitionKey, Neuantrag neuantrag) {    
    BpmPlatform.getDefaultProcessEngine().getRuntimeService().startProcessInstanceByKey( //
        processDefinitionKey, 
        neuantrag.getAntragsNummer(),
        Variables.createVariables().putValueTyped("neuantrag", //
            Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create()));    
  }

  @POST
  @Path("dokument/{number}")
  public void submitNewAntrag(@PathParam("number") String number) {    
    BpmPlatform.getDefaultProcessEngine().getRuntimeService().createMessageCorrelation("MSG_DOCUMENT_RECEIVED")
      .processInstanceVariableEquals(ProcessVariables.VAR_NAME_refernceId, number)
      .correlate();
  }

}
