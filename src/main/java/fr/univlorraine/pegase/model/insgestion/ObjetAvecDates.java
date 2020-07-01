/*
 * Swagger Gestion - INS
 * Il s'agit de l'API de gestion - INS.
 *
 * The version of the OpenAPI document: 1.0.0
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
 * Objet avec dates de début et de fin
 */
@ApiModel(description = "Objet avec dates de début et de fin")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-06-30T15:25:34.956+02:00[Europe/Paris]")

public class ObjetAvecDates {
  public static final String SERIALIZED_NAME_EST1 = "est1";
  @SerializedName(SERIALIZED_NAME_EST1)
  protected String est1;

  public static final String SERIALIZED_NAME_DATE_DEBUT = "dateDebut";
  @SerializedName(SERIALIZED_NAME_DATE_DEBUT)
  private String dateDebut;

  public static final String SERIALIZED_NAME_DATE_FIN = "dateFin";
  @SerializedName(SERIALIZED_NAME_DATE_FIN)
  private String dateFin;

  public ObjetAvecDates() {
    this.est1 = this.getClass().getSimpleName();
  }

  public ObjetAvecDates est1(String est1) {
    
    this.est1 = est1;
    return this;
  }

   /**
   * Get est1
   * @return est1
  **/
  @ApiModelProperty(required = true, value = "")

  public String getEst1() {
    return est1;
  }


  public void setEst1(String est1) {
    this.est1 = est1;
  }


  public ObjetAvecDates dateDebut(String dateDebut) {
    
    this.dateDebut = dateDebut;
    return this;
  }

   /**
   * date de début de validité
   * @return dateDebut
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "date de début de validité")

  public String getDateDebut() {
    return dateDebut;
  }


  public void setDateDebut(String dateDebut) {
    this.dateDebut = dateDebut;
  }


  public ObjetAvecDates dateFin(String dateFin) {
    
    this.dateFin = dateFin;
    return this;
  }

   /**
   * date de fin de validité
   * @return dateFin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "date de fin de validité")

  public String getDateFin() {
    return dateFin;
  }


  public void setDateFin(String dateFin) {
    this.dateFin = dateFin;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjetAvecDates objetAvecDates = (ObjetAvecDates) o;
    return Objects.equals(this.est1, objetAvecDates.est1) &&
        Objects.equals(this.dateDebut, objetAvecDates.dateDebut) &&
        Objects.equals(this.dateFin, objetAvecDates.dateFin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(est1, dateDebut, dateFin);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjetAvecDates {\n");
    sb.append("    est1: ").append(toIndentedString(est1)).append("\n");
    sb.append("    dateDebut: ").append(toIndentedString(dateDebut)).append("\n");
    sb.append("    dateFin: ").append(toIndentedString(dateFin)).append("\n");
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

