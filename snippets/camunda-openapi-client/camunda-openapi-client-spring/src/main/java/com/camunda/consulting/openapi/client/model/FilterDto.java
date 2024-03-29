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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * FilterDto
 */
@JsonPropertyOrder({
  FilterDto.JSON_PROPERTY_ID,
  FilterDto.JSON_PROPERTY_RESOURCE_TYPE,
  FilterDto.JSON_PROPERTY_NAME,
  FilterDto.JSON_PROPERTY_OWNER,
  FilterDto.JSON_PROPERTY_QUERY,
  FilterDto.JSON_PROPERTY_PROPERTIES,
  FilterDto.JSON_PROPERTY_ITEM_COUNT
})
@JsonTypeName("FilterDto")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-11-19T11:53:20.948992+01:00[Europe/Berlin]")
public class FilterDto {
  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_RESOURCE_TYPE = "resourceType";
  private String resourceType;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_OWNER = "owner";
  private String owner;

  public static final String JSON_PROPERTY_QUERY = "query";
  private Object query;

  public static final String JSON_PROPERTY_PROPERTIES = "properties";
  private Object properties;

  public static final String JSON_PROPERTY_ITEM_COUNT = "itemCount";
  private Long itemCount;


  public FilterDto id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * The id of the filter.
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The id of the filter.")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public FilterDto resourceType(String resourceType) {
    
    this.resourceType = resourceType;
    return this;
  }

   /**
   * The resource type of the filter.
   * @return resourceType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The resource type of the filter.")
  @JsonProperty(JSON_PROPERTY_RESOURCE_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getResourceType() {
    return resourceType;
  }


  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }


  public FilterDto name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * The name of the filter.
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The name of the filter.")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public FilterDto owner(String owner) {
    
    this.owner = owner;
    return this;
  }

   /**
   * The user id of the owner of the filter.
   * @return owner
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The user id of the owner of the filter.")
  @JsonProperty(JSON_PROPERTY_OWNER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getOwner() {
    return owner;
  }


  public void setOwner(String owner) {
    this.owner = owner;
  }


  public FilterDto query(Object query) {
    
    this.query = query;
    return this;
  }

   /**
   * The query of the filter as a JSON object.
   * @return query
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The query of the filter as a JSON object.")
  @JsonProperty(JSON_PROPERTY_QUERY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Object getQuery() {
    return query;
  }


  public void setQuery(Object query) {
    this.query = query;
  }


  public FilterDto properties(Object properties) {
    
    this.properties = properties;
    return this;
  }

   /**
   * The properties of a filter as a JSON object.
   * @return properties
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The properties of a filter as a JSON object.")
  @JsonProperty(JSON_PROPERTY_PROPERTIES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Object getProperties() {
    return properties;
  }


  public void setProperties(Object properties) {
    this.properties = properties;
  }


  public FilterDto itemCount(Long itemCount) {
    
    this.itemCount = itemCount;
    return this;
  }

   /**
   *  The number of items matched by the filter itself. Note: Only exists if the query parameter &#x60;itemCount&#x60; was set to &#x60;true&#x60;
   * @return itemCount
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = " The number of items matched by the filter itself. Note: Only exists if the query parameter `itemCount` was set to `true`")
  @JsonProperty(JSON_PROPERTY_ITEM_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getItemCount() {
    return itemCount;
  }


  public void setItemCount(Long itemCount) {
    this.itemCount = itemCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FilterDto filterDto = (FilterDto) o;
    return Objects.equals(this.id, filterDto.id) &&
        Objects.equals(this.resourceType, filterDto.resourceType) &&
        Objects.equals(this.name, filterDto.name) &&
        Objects.equals(this.owner, filterDto.owner) &&
        Objects.equals(this.query, filterDto.query) &&
        Objects.equals(this.properties, filterDto.properties) &&
        Objects.equals(this.itemCount, filterDto.itemCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resourceType, name, owner, query, properties, itemCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FilterDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    query: ").append(toIndentedString(query)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
    sb.append("    itemCount: ").append(toIndentedString(itemCount)).append("\n");
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

