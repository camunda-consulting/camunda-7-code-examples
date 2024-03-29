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
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * HistoricDecisionInputInstanceDto
 */
@JsonPropertyOrder({
  HistoricDecisionInputInstanceDto.JSON_PROPERTY_ID,
  HistoricDecisionInputInstanceDto.JSON_PROPERTY_DECISION_INSTANCE_ID,
  HistoricDecisionInputInstanceDto.JSON_PROPERTY_CLAUSE_ID,
  HistoricDecisionInputInstanceDto.JSON_PROPERTY_CLAUSE_NAME,
  HistoricDecisionInputInstanceDto.JSON_PROPERTY_ERROR_MESSAGE,
  HistoricDecisionInputInstanceDto.JSON_PROPERTY_TYPE,
  HistoricDecisionInputInstanceDto.JSON_PROPERTY_CREATE_TIME,
  HistoricDecisionInputInstanceDto.JSON_PROPERTY_REMOVAL_TIME,
  HistoricDecisionInputInstanceDto.JSON_PROPERTY_ROOT_PROCESS_INSTANCE_ID,
  HistoricDecisionInputInstanceDto.JSON_PROPERTY_VALUE,
  HistoricDecisionInputInstanceDto.JSON_PROPERTY_VALUE_INFO
})
@JsonTypeName("HistoricDecisionInputInstanceDto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
public class HistoricDecisionInputInstanceDto {
  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_DECISION_INSTANCE_ID = "decisionInstanceId";
  private String decisionInstanceId;

  public static final String JSON_PROPERTY_CLAUSE_ID = "clauseId";
  private String clauseId;

  public static final String JSON_PROPERTY_CLAUSE_NAME = "clauseName";
  private String clauseName;

  public static final String JSON_PROPERTY_ERROR_MESSAGE = "errorMessage";
  private String errorMessage;

  public static final String JSON_PROPERTY_TYPE = "type";
  private String type;

  public static final String JSON_PROPERTY_CREATE_TIME = "createTime";
  private OffsetDateTime createTime;

  public static final String JSON_PROPERTY_REMOVAL_TIME = "removalTime";
  private OffsetDateTime removalTime;

  public static final String JSON_PROPERTY_ROOT_PROCESS_INSTANCE_ID = "rootProcessInstanceId";
  private String rootProcessInstanceId;

  public static final String JSON_PROPERTY_VALUE = "value";
  private Object value;

  public static final String JSON_PROPERTY_VALUE_INFO = "valueInfo";
  private Map<String, Object> valueInfo = null;


  public HistoricDecisionInputInstanceDto id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * The id of the decision input value.
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the decision input value.")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public HistoricDecisionInputInstanceDto decisionInstanceId(String decisionInstanceId) {
    
    this.decisionInstanceId = decisionInstanceId;
    return this;
  }

   /**
   * The id of the decision instance the input value belongs to.
   * @return decisionInstanceId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the decision instance the input value belongs to.")
  @JsonProperty(JSON_PROPERTY_DECISION_INSTANCE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDecisionInstanceId() {
    return decisionInstanceId;
  }


  public void setDecisionInstanceId(String decisionInstanceId) {
    this.decisionInstanceId = decisionInstanceId;
  }


  public HistoricDecisionInputInstanceDto clauseId(String clauseId) {
    
    this.clauseId = clauseId;
    return this;
  }

   /**
   * The id of the clause the input value belongs to.
   * @return clauseId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the clause the input value belongs to.")
  @JsonProperty(JSON_PROPERTY_CLAUSE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getClauseId() {
    return clauseId;
  }


  public void setClauseId(String clauseId) {
    this.clauseId = clauseId;
  }


  public HistoricDecisionInputInstanceDto clauseName(String clauseName) {
    
    this.clauseName = clauseName;
    return this;
  }

   /**
   * The name of the clause the input value belongs to.
   * @return clauseName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The name of the clause the input value belongs to.")
  @JsonProperty(JSON_PROPERTY_CLAUSE_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getClauseName() {
    return clauseName;
  }


  public void setClauseName(String clauseName) {
    this.clauseName = clauseName;
  }


  public HistoricDecisionInputInstanceDto errorMessage(String errorMessage) {
    
    this.errorMessage = errorMessage;
    return this;
  }

   /**
   * An error message in case a Java Serialized Object could not be de-serialized.
   * @return errorMessage
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "An error message in case a Java Serialized Object could not be de-serialized.")
  @JsonProperty(JSON_PROPERTY_ERROR_MESSAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getErrorMessage() {
    return errorMessage;
  }


  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }


  public HistoricDecisionInputInstanceDto type(String type) {
    
    this.type = type;
    return this;
  }

   /**
   * The value type of the variable.
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The value type of the variable.")
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  public HistoricDecisionInputInstanceDto createTime(OffsetDateTime createTime) {
    
    this.createTime = createTime;
    return this;
  }

   /**
   * The time the variable was inserted.  [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;.
   * @return createTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The time the variable was inserted.  [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) `yyyy-MM-dd'T'HH:mm:ss.SSSZ`.")
  @JsonProperty(JSON_PROPERTY_CREATE_TIME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public OffsetDateTime getCreateTime() {
    return createTime;
  }


  public void setCreateTime(OffsetDateTime createTime) {
    this.createTime = createTime;
  }


  public HistoricDecisionInputInstanceDto removalTime(OffsetDateTime removalTime) {
    
    this.removalTime = removalTime;
    return this;
  }

   /**
   * The time after which the entry should be removed by the History Cleanup job. [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;.
   * @return removalTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The time after which the entry should be removed by the History Cleanup job. [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) `yyyy-MM-dd'T'HH:mm:ss.SSSZ`.")
  @JsonProperty(JSON_PROPERTY_REMOVAL_TIME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public OffsetDateTime getRemovalTime() {
    return removalTime;
  }


  public void setRemovalTime(OffsetDateTime removalTime) {
    this.removalTime = removalTime;
  }


  public HistoricDecisionInputInstanceDto rootProcessInstanceId(String rootProcessInstanceId) {
    
    this.rootProcessInstanceId = rootProcessInstanceId;
    return this;
  }

   /**
   * The process instance id of the root process instance that initiated the process containing this entry.
   * @return rootProcessInstanceId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The process instance id of the root process instance that initiated the process containing this entry.")
  @JsonProperty(JSON_PROPERTY_ROOT_PROCESS_INSTANCE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRootProcessInstanceId() {
    return rootProcessInstanceId;
  }


  public void setRootProcessInstanceId(String rootProcessInstanceId) {
    this.rootProcessInstanceId = rootProcessInstanceId;
  }


  public HistoricDecisionInputInstanceDto value(Object value) {
    
    this.value = value;
    return this;
  }

   /**
   * The variable&#39;s value. Value differs depending on the variable&#39;s type and on the &#x60;disableCustomObjectDeserialization&#x60; parameter.
   * @return value
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The variable's value. Value differs depending on the variable's type and on the `disableCustomObjectDeserialization` parameter.")
  @JsonProperty(JSON_PROPERTY_VALUE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Object getValue() {
    return value;
  }


  public void setValue(Object value) {
    this.value = value;
  }


  public HistoricDecisionInputInstanceDto valueInfo(Map<String, Object> valueInfo) {
    
    this.valueInfo = valueInfo;
    return this;
  }

  public HistoricDecisionInputInstanceDto putValueInfoItem(String key, Object valueInfoItem) {
    if (this.valueInfo == null) {
      this.valueInfo = new HashMap<>();
    }
    this.valueInfo.put(key, valueInfoItem);
    return this;
  }

   /**
   * A JSON object containing additional, value-type-dependent properties.  For variables of type &#x60;Object&#x60;, the following properties are returned:  * &#x60;objectTypeName&#x60;: A string representation of the object&#39;s type name.  * &#x60;serializationDataFormat&#x60;: The serialization format used to store the variable.
   * @return valueInfo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A JSON object containing additional, value-type-dependent properties.  For variables of type `Object`, the following properties are returned:  * `objectTypeName`: A string representation of the object's type name.  * `serializationDataFormat`: The serialization format used to store the variable.")
  @JsonProperty(JSON_PROPERTY_VALUE_INFO)
  @JsonInclude(content = JsonInclude.Include.ALWAYS, value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, Object> getValueInfo() {
    return valueInfo;
  }


  public void setValueInfo(Map<String, Object> valueInfo) {
    this.valueInfo = valueInfo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HistoricDecisionInputInstanceDto historicDecisionInputInstanceDto = (HistoricDecisionInputInstanceDto) o;
    return Objects.equals(this.id, historicDecisionInputInstanceDto.id) &&
        Objects.equals(this.decisionInstanceId, historicDecisionInputInstanceDto.decisionInstanceId) &&
        Objects.equals(this.clauseId, historicDecisionInputInstanceDto.clauseId) &&
        Objects.equals(this.clauseName, historicDecisionInputInstanceDto.clauseName) &&
        Objects.equals(this.errorMessage, historicDecisionInputInstanceDto.errorMessage) &&
        Objects.equals(this.type, historicDecisionInputInstanceDto.type) &&
        Objects.equals(this.createTime, historicDecisionInputInstanceDto.createTime) &&
        Objects.equals(this.removalTime, historicDecisionInputInstanceDto.removalTime) &&
        Objects.equals(this.rootProcessInstanceId, historicDecisionInputInstanceDto.rootProcessInstanceId) &&
        Objects.equals(this.value, historicDecisionInputInstanceDto.value) &&
        Objects.equals(this.valueInfo, historicDecisionInputInstanceDto.valueInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, decisionInstanceId, clauseId, clauseName, errorMessage, type, createTime, removalTime, rootProcessInstanceId, value, valueInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HistoricDecisionInputInstanceDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    decisionInstanceId: ").append(toIndentedString(decisionInstanceId)).append("\n");
    sb.append("    clauseId: ").append(toIndentedString(clauseId)).append("\n");
    sb.append("    clauseName: ").append(toIndentedString(clauseName)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    removalTime: ").append(toIndentedString(removalTime)).append("\n");
    sb.append("    rootProcessInstanceId: ").append(toIndentedString(rootProcessInstanceId)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    valueInfo: ").append(toIndentedString(valueInfo)).append("\n");
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

