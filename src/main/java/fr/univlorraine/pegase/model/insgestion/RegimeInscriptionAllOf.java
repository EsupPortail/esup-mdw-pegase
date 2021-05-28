/*
 * Swagger Gestion - INS
 * Il s'agit de l'API de gestion - INS.
 *
 * The version of the OpenAPI document: 1.4.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.insgestion;

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

/**
 * Régime sur lequel réaliser une inscription sur une formation
 */
@ApiModel(description = "Régime sur lequel réaliser une inscription sur une formation")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-05-27T10:35:31.587+02:00[Europe/Paris]")
public class RegimeInscriptionAllOf {
  public static final String SERIALIZED_NAME_TEMOIN_C_V_E_C = "temoinCVEC";
  @SerializedName(SERIALIZED_NAME_TEMOIN_C_V_E_C)
  private Boolean temoinCVEC;

  public static final String SERIALIZED_NAME_FINANCEMENT_POSSIBLE = "financementPossible";
  @SerializedName(SERIALIZED_NAME_FINANCEMENT_POSSIBLE)
  private Boolean financementPossible;

  public static final String SERIALIZED_NAME_DROIT_A_BOURSE = "droitABourse";
  @SerializedName(SERIALIZED_NAME_DROIT_A_BOURSE)
  private Boolean droitABourse;


  public RegimeInscriptionAllOf temoinCVEC(Boolean temoinCVEC) {
    
    this.temoinCVEC = temoinCVEC;
    return this;
  }

   /**
   * Régime d&#39;inscription nécessitant le paiement de la CVEC
   * @return temoinCVEC
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Régime d'inscription nécessitant le paiement de la CVEC")

  public Boolean getTemoinCVEC() {
    return temoinCVEC;
  }


  public void setTemoinCVEC(Boolean temoinCVEC) {
    this.temoinCVEC = temoinCVEC;
  }


  public RegimeInscriptionAllOf financementPossible(Boolean financementPossible) {
    
    this.financementPossible = financementPossible;
    return this;
  }

   /**
   * Régime d&#39;inscription permettant un financement
   * @return financementPossible
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Régime d'inscription permettant un financement")

  public Boolean getFinancementPossible() {
    return financementPossible;
  }


  public void setFinancementPossible(Boolean financementPossible) {
    this.financementPossible = financementPossible;
  }


  public RegimeInscriptionAllOf droitABourse(Boolean droitABourse) {
    
    this.droitABourse = droitABourse;
    return this;
  }

   /**
   * Régime d&#39;inscription donnant droit à une bourse
   * @return droitABourse
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Régime d'inscription donnant droit à une bourse")

  public Boolean getDroitABourse() {
    return droitABourse;
  }


  public void setDroitABourse(Boolean droitABourse) {
    this.droitABourse = droitABourse;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegimeInscriptionAllOf regimeInscriptionAllOf = (RegimeInscriptionAllOf) o;
    return Objects.equals(this.temoinCVEC, regimeInscriptionAllOf.temoinCVEC) &&
        Objects.equals(this.financementPossible, regimeInscriptionAllOf.financementPossible) &&
        Objects.equals(this.droitABourse, regimeInscriptionAllOf.droitABourse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(temoinCVEC, financementPossible, droitABourse);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegimeInscriptionAllOf {\n");
    sb.append("    temoinCVEC: ").append(toIndentedString(temoinCVEC)).append("\n");
    sb.append("    financementPossible: ").append(toIndentedString(financementPossible)).append("\n");
    sb.append("    droitABourse: ").append(toIndentedString(droitABourse)).append("\n");
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

