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
 * DroitTarificationPeriode
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T15:45:05.470760100+01:00[Europe/Paris]")
public class DroitTarificationPeriode {
  public static final String SERIALIZED_NAME_CODE_PERIODE = "codePeriode";
  @SerializedName(SERIALIZED_NAME_CODE_PERIODE)
  private String codePeriode;

  public static final String SERIALIZED_NAME_CODE_DROIT_TARIFICATION = "codeDroitTarification";
  @SerializedName(SERIALIZED_NAME_CODE_DROIT_TARIFICATION)
  private String codeDroitTarification;

  public DroitTarificationPeriode() { 
  }

  public DroitTarificationPeriode codePeriode(String codePeriode) {
    
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


  public DroitTarificationPeriode codeDroitTarification(String codeDroitTarification) {
    
    this.codeDroitTarification = codeDroitTarification;
    return this;
  }

   /**
   * Le code de la tarification
   * @return codeDroitTarification
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le code de la tarification")

  public String getCodeDroitTarification() {
    return codeDroitTarification;
  }


  public void setCodeDroitTarification(String codeDroitTarification) {
    this.codeDroitTarification = codeDroitTarification;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DroitTarificationPeriode droitTarificationPeriode = (DroitTarificationPeriode) o;
    return Objects.equals(this.codePeriode, droitTarificationPeriode.codePeriode) &&
        Objects.equals(this.codeDroitTarification, droitTarificationPeriode.codeDroitTarification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codePeriode, codeDroitTarification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DroitTarificationPeriode {\n");
    sb.append("    codePeriode: ").append(toIndentedString(codePeriode)).append("\n");
    sb.append("    codeDroitTarification: ").append(toIndentedString(codeDroitTarification)).append("\n");
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
