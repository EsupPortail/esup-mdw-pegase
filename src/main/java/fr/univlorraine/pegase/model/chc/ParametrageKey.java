/*
 * API CHC v4
 * Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v4
 *
 * The version of the OpenAPI document: 4.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.chc;

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
import java.time.LocalDate;

/**
 * ParametrageKey
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-11T17:44:47.769+01:00[Europe/Paris]")
public class ParametrageKey {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_DATE_CONSOMMATION = "dateConsommation";
  @SerializedName(SERIALIZED_NAME_DATE_CONSOMMATION)
  private LocalDate dateConsommation;


  public ParametrageKey code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Code interne du paramétrage choisi par l&#39;établissement
   * @return code
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Code interne du paramétrage choisi par l'établissement")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public ParametrageKey dateConsommation(LocalDate dateConsommation) {
    
    this.dateConsommation = dateConsommation;
    return this;
  }

   /**
   * Date de la consommation du paramétrage
   * @return dateConsommation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Date de la consommation du paramétrage")

  public LocalDate getDateConsommation() {
    return dateConsommation;
  }


  public void setDateConsommation(LocalDate dateConsommation) {
    this.dateConsommation = dateConsommation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametrageKey parametrageKey = (ParametrageKey) o;
    return Objects.equals(this.code, parametrageKey.code) &&
        Objects.equals(this.dateConsommation, parametrageKey.dateConsommation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, dateConsommation);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParametrageKey {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    dateConsommation: ").append(toIndentedString(dateConsommation)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

