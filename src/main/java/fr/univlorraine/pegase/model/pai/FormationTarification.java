/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 21.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.pai;

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
 * FormationTarification
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T15:45:05.470760100+01:00[Europe/Paris]")
public class FormationTarification {
  public static final String SERIALIZED_NAME_CODE_PERIODE = "codePeriode";
  @SerializedName(SERIALIZED_NAME_CODE_PERIODE)
  private String codePeriode;

  public static final String SERIALIZED_NAME_CODE_FORMATION = "codeFormation";
  @SerializedName(SERIALIZED_NAME_CODE_FORMATION)
  private String codeFormation;

  public static final String SERIALIZED_NAME_CODE_TARIFICATION = "codeTarification";
  @SerializedName(SERIALIZED_NAME_CODE_TARIFICATION)
  private String codeTarification;

  public FormationTarification() { 
  }

  public FormationTarification codePeriode(String codePeriode) {
    
    this.codePeriode = codePeriode;
    return this;
  }

   /**
   * Le code de la période
   * @return codePeriode
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le code de la période")

  public String getCodePeriode() {
    return codePeriode;
  }


  public void setCodePeriode(String codePeriode) {
    this.codePeriode = codePeriode;
  }


  public FormationTarification codeFormation(String codeFormation) {
    
    this.codeFormation = codeFormation;
    return this;
  }

   /**
   * Le code de la formation
   * @return codeFormation
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le code de la formation")

  public String getCodeFormation() {
    return codeFormation;
  }


  public void setCodeFormation(String codeFormation) {
    this.codeFormation = codeFormation;
  }


  public FormationTarification codeTarification(String codeTarification) {
    
    this.codeTarification = codeTarification;
    return this;
  }

   /**
   * Le code de la tarification
   * @return codeTarification
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le code de la tarification")

  public String getCodeTarification() {
    return codeTarification;
  }


  public void setCodeTarification(String codeTarification) {
    this.codeTarification = codeTarification;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FormationTarification formationTarification = (FormationTarification) o;
    return Objects.equals(this.codePeriode, formationTarification.codePeriode) &&
        Objects.equals(this.codeFormation, formationTarification.codeFormation) &&
        Objects.equals(this.codeTarification, formationTarification.codeTarification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codePeriode, codeFormation, codeTarification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FormationTarification {\n");
    sb.append("    codePeriode: ").append(toIndentedString(codePeriode)).append("\n");
    sb.append("    codeFormation: ").append(toIndentedString(codeFormation)).append("\n");
    sb.append("    codeTarification: ").append(toIndentedString(codeTarification)).append("\n");
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

