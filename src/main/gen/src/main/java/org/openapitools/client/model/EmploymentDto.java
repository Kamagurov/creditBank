/*
 * Credit Bank API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openapitools.client.JSON;

/**
 * EmploymentDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-06-23T17:22:42.826814800+03:00[Europe/Moscow]")
public class EmploymentDto {
  public static final String SERIALIZED_NAME_EMPLOYMENT_UUID = "employmentUuid";
  @SerializedName(SERIALIZED_NAME_EMPLOYMENT_UUID)
  private UUID employmentUuid;

  /**
   * Gets or Sets status
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    ACTIVE("ACTIVE"),
    
    INACTIVE("INACTIVE");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return StatusEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private StatusEnum status;

  public static final String SERIALIZED_NAME_EMPLOYMENT_I_N_N = "employmentINN";
  @SerializedName(SERIALIZED_NAME_EMPLOYMENT_I_N_N)
  private String employmentINN;

  public static final String SERIALIZED_NAME_SALARY = "salary";
  @SerializedName(SERIALIZED_NAME_SALARY)
  private Double salary;

  /**
   * Gets or Sets position
   */
  @JsonAdapter(PositionEnum.Adapter.class)
  public enum PositionEnum {
    MANAGER("MANAGER"),
    
    ENGINEER("ENGINEER"),
    
    SALES("SALES");

    private String value;

    PositionEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static PositionEnum fromValue(String value) {
      for (PositionEnum b : PositionEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<PositionEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final PositionEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public PositionEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return PositionEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_POSITION = "position";
  @SerializedName(SERIALIZED_NAME_POSITION)
  private PositionEnum position;

  public static final String SERIALIZED_NAME_WORK_EXPERIENCE_TOTAL = "workExperienceTotal";
  @SerializedName(SERIALIZED_NAME_WORK_EXPERIENCE_TOTAL)
  private Integer workExperienceTotal;

  public static final String SERIALIZED_NAME_WORK_EXPERIENCE_CURRENT = "workExperienceCurrent";
  @SerializedName(SERIALIZED_NAME_WORK_EXPERIENCE_CURRENT)
  private Integer workExperienceCurrent;

  public EmploymentDto() {
  }

  public EmploymentDto employmentUuid(UUID employmentUuid) {
    
    this.employmentUuid = employmentUuid;
    return this;
  }

   /**
   * Get employmentUuid
   * @return employmentUuid
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public UUID getEmploymentUuid() {
    return employmentUuid;
  }


  public void setEmploymentUuid(UUID employmentUuid) {
    this.employmentUuid = employmentUuid;
  }


  public EmploymentDto status(StatusEnum status) {
    
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public StatusEnum getStatus() {
    return status;
  }


  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  public EmploymentDto employmentINN(String employmentINN) {
    
    this.employmentINN = employmentINN;
    return this;
  }

   /**
   * Get employmentINN
   * @return employmentINN
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getEmploymentINN() {
    return employmentINN;
  }


  public void setEmploymentINN(String employmentINN) {
    this.employmentINN = employmentINN;
  }


  public EmploymentDto salary(Double salary) {
    
    this.salary = salary;
    return this;
  }

   /**
   * Get salary
   * @return salary
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Double getSalary() {
    return salary;
  }


  public void setSalary(Double salary) {
    this.salary = salary;
  }


  public EmploymentDto position(PositionEnum position) {
    
    this.position = position;
    return this;
  }

   /**
   * Get position
   * @return position
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public PositionEnum getPosition() {
    return position;
  }


  public void setPosition(PositionEnum position) {
    this.position = position;
  }


  public EmploymentDto workExperienceTotal(Integer workExperienceTotal) {
    
    this.workExperienceTotal = workExperienceTotal;
    return this;
  }

   /**
   * Get workExperienceTotal
   * @return workExperienceTotal
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Integer getWorkExperienceTotal() {
    return workExperienceTotal;
  }


  public void setWorkExperienceTotal(Integer workExperienceTotal) {
    this.workExperienceTotal = workExperienceTotal;
  }


  public EmploymentDto workExperienceCurrent(Integer workExperienceCurrent) {
    
    this.workExperienceCurrent = workExperienceCurrent;
    return this;
  }

   /**
   * Get workExperienceCurrent
   * @return workExperienceCurrent
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Integer getWorkExperienceCurrent() {
    return workExperienceCurrent;
  }


  public void setWorkExperienceCurrent(Integer workExperienceCurrent) {
    this.workExperienceCurrent = workExperienceCurrent;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmploymentDto employmentDto = (EmploymentDto) o;
    return Objects.equals(this.employmentUuid, employmentDto.employmentUuid) &&
        Objects.equals(this.status, employmentDto.status) &&
        Objects.equals(this.employmentINN, employmentDto.employmentINN) &&
        Objects.equals(this.salary, employmentDto.salary) &&
        Objects.equals(this.position, employmentDto.position) &&
        Objects.equals(this.workExperienceTotal, employmentDto.workExperienceTotal) &&
        Objects.equals(this.workExperienceCurrent, employmentDto.workExperienceCurrent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employmentUuid, status, employmentINN, salary, position, workExperienceTotal, workExperienceCurrent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmploymentDto {\n");
    sb.append("    employmentUuid: ").append(toIndentedString(employmentUuid)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    employmentINN: ").append(toIndentedString(employmentINN)).append("\n");
    sb.append("    salary: ").append(toIndentedString(salary)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    workExperienceTotal: ").append(toIndentedString(workExperienceTotal)).append("\n");
    sb.append("    workExperienceCurrent: ").append(toIndentedString(workExperienceCurrent)).append("\n");
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


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("employmentUuid");
    openapiFields.add("status");
    openapiFields.add("employmentINN");
    openapiFields.add("salary");
    openapiFields.add("position");
    openapiFields.add("workExperienceTotal");
    openapiFields.add("workExperienceCurrent");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("employmentUuid");
    openapiRequiredFields.add("status");
    openapiRequiredFields.add("employmentINN");
    openapiRequiredFields.add("salary");
    openapiRequiredFields.add("position");
    openapiRequiredFields.add("workExperienceTotal");
    openapiRequiredFields.add("workExperienceCurrent");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to EmploymentDto
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (EmploymentDto.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in EmploymentDto is not found in the empty JSON string", EmploymentDto.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!EmploymentDto.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `EmploymentDto` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : EmploymentDto.openapiRequiredFields) {
        if (jsonObj.get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj.toString()));
        }
      }
      if ((jsonObj.get("employmentUuid") != null && !jsonObj.get("employmentUuid").isJsonNull()) && !jsonObj.get("employmentUuid").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `employmentUuid` to be a primitive type in the JSON string but got `%s`", jsonObj.get("employmentUuid").toString()));
      }
      if ((jsonObj.get("status") != null && !jsonObj.get("status").isJsonNull()) && !jsonObj.get("status").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `status` to be a primitive type in the JSON string but got `%s`", jsonObj.get("status").toString()));
      }
      if ((jsonObj.get("employmentINN") != null && !jsonObj.get("employmentINN").isJsonNull()) && !jsonObj.get("employmentINN").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `employmentINN` to be a primitive type in the JSON string but got `%s`", jsonObj.get("employmentINN").toString()));
      }
      if ((jsonObj.get("salary") != null && !jsonObj.get("salary").isJsonNull()) && !jsonObj.get("salary").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `salary` to be a primitive type in the JSON string but got `%s`", jsonObj.get("salary").toString()));
      }
      if ((jsonObj.get("position") != null && !jsonObj.get("position").isJsonNull()) && !jsonObj.get("position").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `position` to be a primitive type in the JSON string but got `%s`", jsonObj.get("position").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!EmploymentDto.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'EmploymentDto' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<EmploymentDto> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(EmploymentDto.class));

       return (TypeAdapter<T>) new TypeAdapter<EmploymentDto>() {
           @Override
           public void write(JsonWriter out, EmploymentDto value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public EmploymentDto read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of EmploymentDto given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of EmploymentDto
  * @throws IOException if the JSON string is invalid with respect to EmploymentDto
  */
  public static EmploymentDto fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, EmploymentDto.class);
  }

 /**
  * Convert an instance of EmploymentDto to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

