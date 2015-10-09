package com.camunda.demo.versicherungsneuantrag.facade;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.impl.core.variable.type.FileValueTypeImpl;
import org.camunda.bpm.engine.impl.digest._apacheCommonsCodec.Base64;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.FileValue;

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
        "versicherungsneuantrag", 
        neuantrag.getAntragsNummer(),
        Variables.createVariables().putValueTyped( //
            "neuantrag", //
            Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create())
            .putValueTyped("newDocument", null));    
  }

  @POST
  @Path("dokument/{number}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void submitDocument(@PathParam("number") String number, VariableValueDto documentVariable) throws UnsupportedEncodingException {

   
   FileValue document = Variables    
          .fileValue((String)documentVariable.getValueInfo().get("filename"))
          .file(Base64.decodeBase64((String)documentVariable.getValue()))  // see FileValueTypeImpl.createValue    
          .mimeType((String)documentVariable.getValueInfo().get("mimeType"))
          .create();

   Execution execution = BpmPlatform.getDefaultProcessEngine().getRuntimeService().createExecutionQuery()
      .messageEventSubscriptionName("MSG_DOCUMENT_RECEIVED")
      .processVariableValueEquals(ProcessVariables.VAR_NAME_refernceId, number)
      .singleResult();
    BpmPlatform.getDefaultProcessEngine().getRuntimeService().setVariable(
        execution.getProcessInstanceId(), ProcessVariables.VAR_NAME_document, 
        document);
    
    BpmPlatform.getDefaultProcessEngine().getRuntimeService().createMessageCorrelation("MSG_DOCUMENT_RECEIVED")
      .processInstanceVariableEquals(ProcessVariables.VAR_NAME_refernceId, number)
      .correlate();
    
    // Todo: Set variables in one call when https://app.camunda.com/jira/browse/CAM-4717 is implemented

  }

}
