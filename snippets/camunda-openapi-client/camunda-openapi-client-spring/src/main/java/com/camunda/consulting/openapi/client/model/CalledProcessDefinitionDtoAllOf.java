/*
 * Camunda Platform REST API
 * OpenApi Spec for Camunda Platform REST API.
 *
 * The version of the OpenAPI document: 7.16.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.camunda.consulting.openapi.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * CalledProcessDefinitionDtoAllOf
 */
@JsonPropertyOrder({
  CalledProcessDefinitionDtoAllOf.JSON_PROPERTY_CALLED_FROM_ACTIVITY_IDS,
  CalledProcessDefinitionDtoAllOf.JSON_PROPERTY_CALLING_PROCESS_DEFINITION_ID
})
@JsonTypeName("CalledProcessDefinitionDto_allOf")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
public class CalledProcessDefinitionDtoAllOf {
  public static final String JSON_PROPERTY_CALLED_FROM_ACTIVITY_IDS = "calledFromActivityIds";
  private List<String> calledFromActivityIds = null;

  public static final String JSON_PROPERTY_CALLING_PROCESS_DEFINITION_ID = "callingProcessDefinitionId";
  private String callingProcessDefinitionId;


  public CalledProcessDefinitionDtoAllOf calledFromActivityIds(List<String> calledFromActivityIds) {
    
    this.calledFromActivityIds = calledFromActivityIds;
    return this;
  }

  public CalledProcessDefinitionDtoAllOf addCalledFromActivityIdsItem(String calledFromActivityIdsItem) {
    if (this.calledFromActivityIds == null) {
      this.calledFromActivityIds = new ArrayList<>();
    }
    this.calledFromActivityIds.add(calledFromActivityIdsItem);
    return this;
  }

   /**
   * Ids of the CallActivities which call this process.
   * @return calledFromActivityIds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Ids of the CallActivities which call this process.")
  @JsonProperty(JSON_PROPERTY_CALLED_FROM_ACTIVITY_IDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getCalledFromActivityIds() {
    return calledFromActivityIds;
  }


  public void setCalledFromActivityIds(List<String> calledFromActivityIds) {
    this.calledFromActivityIds = calledFromActivityIds;
  }


  public CalledProcessDefinitionDtoAllOf callingProcessDefinitionId(String callingProcessDefinitionId) {
    
    this.callingProcessDefinitionId = callingProcessDefinitionId;
    return this;
  }

   /**
   * The id of the calling process definition
   * @return callingProcessDefinitionId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the calling process definition")
  @JsonProperty(JSON_PROPERTY_CALLING_PROCESS_DEFINITION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCallingProcessDefinitionId() {
    return callingProcessDefinitionId;
  }


  public void setCallingProcessDefinitionId(String callingProcessDefinitionId) {
    this.callingProcessDefinitionId = callingProcessDefinitionId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CalledProcessDefinitionDtoAllOf calledProcessDefinitionDtoAllOf = (CalledProcessDefinitionDtoAllOf) o;
    return Objects.equals(this.calledFromActivityIds, calledProcessDefinitionDtoAllOf.calledFromActivityIds) &&
        Objects.equals(this.callingProcessDefinitionId, calledProcessDefinitionDtoAllOf.callingProcessDefinitionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(calledFromActivityIds, callingProcessDefinitionId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CalledProcessDefinitionDtoAllOf {\n");
    sb.append("    calledFromActivityIds: ").append(toIndentedString(calledFromActivityIds)).append("\n");
    sb.append("    callingProcessDefinitionId: ").append(toIndentedString(callingProcessDefinitionId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

