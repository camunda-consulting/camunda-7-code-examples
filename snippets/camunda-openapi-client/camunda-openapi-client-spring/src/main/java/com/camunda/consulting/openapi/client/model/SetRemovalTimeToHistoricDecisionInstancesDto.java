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
import com.camunda.consulting.openapi.client.model.AbstractSetRemovalTimeDto;
import com.camunda.consulting.openapi.client.model.HistoricDecisionInstanceQueryDto;
import com.camunda.consulting.openapi.client.model.SetRemovalTimeToHistoricDecisionInstancesDtoAllOf;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * SetRemovalTimeToHistoricDecisionInstancesDto
 */
@JsonPropertyOrder({
  SetRemovalTimeToHistoricDecisionInstancesDto.JSON_PROPERTY_HIERARCHICAL,
  SetRemovalTimeToHistoricDecisionInstancesDto.JSON_PROPERTY_HISTORIC_DECISION_INSTANCE_QUERY,
  SetRemovalTimeToHistoricDecisionInstancesDto.JSON_PROPERTY_HISTORIC_DECISION_INSTANCE_IDS,
  SetRemovalTimeToHistoricDecisionInstancesDto.JSON_PROPERTY_ABSOLUTE_REMOVAL_TIME,
  SetRemovalTimeToHistoricDecisionInstancesDto.JSON_PROPERTY_CLEARED_REMOVAL_TIME,
  SetRemovalTimeToHistoricDecisionInstancesDto.JSON_PROPERTY_CALCULATED_REMOVAL_TIME
})
@JsonTypeName("SetRemovalTimeToHistoricDecisionInstancesDto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
public class SetRemovalTimeToHistoricDecisionInstancesDto {
  public static final String JSON_PROPERTY_HIERARCHICAL = "hierarchical";
  private Boolean hierarchical;

  public static final String JSON_PROPERTY_HISTORIC_DECISION_INSTANCE_QUERY = "historicDecisionInstanceQuery";
  private HistoricDecisionInstanceQueryDto historicDecisionInstanceQuery;

  public static final String JSON_PROPERTY_HISTORIC_DECISION_INSTANCE_IDS = "historicDecisionInstanceIds";
  private List<String> historicDecisionInstanceIds = null;

  public static final String JSON_PROPERTY_ABSOLUTE_REMOVAL_TIME = "absoluteRemovalTime";
  private OffsetDateTime absoluteRemovalTime;

  public static final String JSON_PROPERTY_CLEARED_REMOVAL_TIME = "clearedRemovalTime";
  private Boolean clearedRemovalTime;

  public static final String JSON_PROPERTY_CALCULATED_REMOVAL_TIME = "calculatedRemovalTime";
  private Boolean calculatedRemovalTime;


  public SetRemovalTimeToHistoricDecisionInstancesDto hierarchical(Boolean hierarchical) {
    
    this.hierarchical = hierarchical;
    return this;
  }

   /**
   * Sets the removal time to all historic decision instances in the hierarchy. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior.
   * @return hierarchical
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Sets the removal time to all historic decision instances in the hierarchy. Value may only be `true`, as `false` is the default behavior.")
  @JsonProperty(JSON_PROPERTY_HIERARCHICAL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getHierarchical() {
    return hierarchical;
  }


  public void setHierarchical(Boolean hierarchical) {
    this.hierarchical = hierarchical;
  }


  public SetRemovalTimeToHistoricDecisionInstancesDto historicDecisionInstanceQuery(HistoricDecisionInstanceQueryDto historicDecisionInstanceQuery) {
    
    this.historicDecisionInstanceQuery = historicDecisionInstanceQuery;
    return this;
  }

   /**
   * Get historicDecisionInstanceQuery
   * @return historicDecisionInstanceQuery
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_HISTORIC_DECISION_INSTANCE_QUERY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public HistoricDecisionInstanceQueryDto getHistoricDecisionInstanceQuery() {
    return historicDecisionInstanceQuery;
  }


  public void setHistoricDecisionInstanceQuery(HistoricDecisionInstanceQueryDto historicDecisionInstanceQuery) {
    this.historicDecisionInstanceQuery = historicDecisionInstanceQuery;
  }


  public SetRemovalTimeToHistoricDecisionInstancesDto historicDecisionInstanceIds(List<String> historicDecisionInstanceIds) {
    
    this.historicDecisionInstanceIds = historicDecisionInstanceIds;
    return this;
  }

  public SetRemovalTimeToHistoricDecisionInstancesDto addHistoricDecisionInstanceIdsItem(String historicDecisionInstanceIdsItem) {
    if (this.historicDecisionInstanceIds == null) {
      this.historicDecisionInstanceIds = new ArrayList<>();
    }
    this.historicDecisionInstanceIds.add(historicDecisionInstanceIdsItem);
    return this;
  }

   /**
   * The ids of the historic decision instances to set the removal time for.
   * @return historicDecisionInstanceIds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The ids of the historic decision instances to set the removal time for.")
  @JsonProperty(JSON_PROPERTY_HISTORIC_DECISION_INSTANCE_IDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getHistoricDecisionInstanceIds() {
    return historicDecisionInstanceIds;
  }


  public void setHistoricDecisionInstanceIds(List<String> historicDecisionInstanceIds) {
    this.historicDecisionInstanceIds = historicDecisionInstanceIds;
  }


  public SetRemovalTimeToHistoricDecisionInstancesDto absoluteRemovalTime(OffsetDateTime absoluteRemovalTime) {
    
    this.absoluteRemovalTime = absoluteRemovalTime;
    return this;
  }

   /**
   * The date for which the instances shall be removed. Value may not be &#x60;null&#x60;.  **Note:** Cannot be set in conjunction with &#x60;clearedRemovalTime&#x60; or &#x60;calculatedRemovalTime&#x60;.
   * @return absoluteRemovalTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The date for which the instances shall be removed. Value may not be `null`.  **Note:** Cannot be set in conjunction with `clearedRemovalTime` or `calculatedRemovalTime`.")
  @JsonProperty(JSON_PROPERTY_ABSOLUTE_REMOVAL_TIME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public OffsetDateTime getAbsoluteRemovalTime() {
    return absoluteRemovalTime;
  }


  public void setAbsoluteRemovalTime(OffsetDateTime absoluteRemovalTime) {
    this.absoluteRemovalTime = absoluteRemovalTime;
  }


  public SetRemovalTimeToHistoricDecisionInstancesDto clearedRemovalTime(Boolean clearedRemovalTime) {
    
    this.clearedRemovalTime = clearedRemovalTime;
    return this;
  }

   /**
   * Sets the removal time to &#x60;null&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior.  **Note:** Cannot be set in conjunction with &#x60;absoluteRemovalTime&#x60; or &#x60;calculatedRemovalTime&#x60;.
   * @return clearedRemovalTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Sets the removal time to `null`. Value may only be `true`, as `false` is the default behavior.  **Note:** Cannot be set in conjunction with `absoluteRemovalTime` or `calculatedRemovalTime`.")
  @JsonProperty(JSON_PROPERTY_CLEARED_REMOVAL_TIME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getClearedRemovalTime() {
    return clearedRemovalTime;
  }


  public void setClearedRemovalTime(Boolean clearedRemovalTime) {
    this.clearedRemovalTime = clearedRemovalTime;
  }


  public SetRemovalTimeToHistoricDecisionInstancesDto calculatedRemovalTime(Boolean calculatedRemovalTime) {
    
    this.calculatedRemovalTime = calculatedRemovalTime;
    return this;
  }

   /**
   * The removal time is calculated based on the engine&#39;s configuration settings. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior.  **Note:** Cannot be set in conjunction with &#x60;absoluteRemovalTime&#x60; or &#x60;clearedRemovalTime&#x60;.
   * @return calculatedRemovalTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The removal time is calculated based on the engine's configuration settings. Value may only be `true`, as `false` is the default behavior.  **Note:** Cannot be set in conjunction with `absoluteRemovalTime` or `clearedRemovalTime`.")
  @JsonProperty(JSON_PROPERTY_CALCULATED_REMOVAL_TIME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getCalculatedRemovalTime() {
    return calculatedRemovalTime;
  }


  public void setCalculatedRemovalTime(Boolean calculatedRemovalTime) {
    this.calculatedRemovalTime = calculatedRemovalTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SetRemovalTimeToHistoricDecisionInstancesDto setRemovalTimeToHistoricDecisionInstancesDto = (SetRemovalTimeToHistoricDecisionInstancesDto) o;
    return Objects.equals(this.hierarchical, setRemovalTimeToHistoricDecisionInstancesDto.hierarchical) &&
        Objects.equals(this.historicDecisionInstanceQuery, setRemovalTimeToHistoricDecisionInstancesDto.historicDecisionInstanceQuery) &&
        Objects.equals(this.historicDecisionInstanceIds, setRemovalTimeToHistoricDecisionInstancesDto.historicDecisionInstanceIds) &&
        Objects.equals(this.absoluteRemovalTime, setRemovalTimeToHistoricDecisionInstancesDto.absoluteRemovalTime) &&
        Objects.equals(this.clearedRemovalTime, setRemovalTimeToHistoricDecisionInstancesDto.clearedRemovalTime) &&
        Objects.equals(this.calculatedRemovalTime, setRemovalTimeToHistoricDecisionInstancesDto.calculatedRemovalTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hierarchical, historicDecisionInstanceQuery, historicDecisionInstanceIds, absoluteRemovalTime, clearedRemovalTime, calculatedRemovalTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SetRemovalTimeToHistoricDecisionInstancesDto {\n");
    sb.append("    hierarchical: ").append(toIndentedString(hierarchical)).append("\n");
    sb.append("    historicDecisionInstanceQuery: ").append(toIndentedString(historicDecisionInstanceQuery)).append("\n");
    sb.append("    historicDecisionInstanceIds: ").append(toIndentedString(historicDecisionInstanceIds)).append("\n");
    sb.append("    absoluteRemovalTime: ").append(toIndentedString(absoluteRemovalTime)).append("\n");
    sb.append("    clearedRemovalTime: ").append(toIndentedString(clearedRemovalTime)).append("\n");
    sb.append("    calculatedRemovalTime: ").append(toIndentedString(calculatedRemovalTime)).append("\n");
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

