/*
 * API CHC v5
 * Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v5 
 *
 * The version of the OpenAPI document: 5.0.0
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
import fr.univlorraine.pegase.model.chc.Nomenclature;
import fr.univlorraine.pegase.model.chc.TypeAmenagementAllOf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.time.LocalDate;

/**
 * TypeAmenagement
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-05-27T10:38:20.872+02:00[Europe/Paris]")
public class TypeAmenagement extends Nomenclature {
  public static final String SERIALIZED_NAME_MOTIF = "motif";
  @SerializedName(SERIALIZED_NAME_MOTIF)
  private String motif;

  public static final String SERIALIZED_NAME_PORTEE = "portee";
  @SerializedName(SERIALIZED_NAME_PORTEE)
  private String portee;

  public static final String SERIALIZED_NAME_PRISE_EN_COMPTE = "priseEnCompte";
  @SerializedName(SERIALIZED_NAME_PRISE_EN_COMPTE)
  private String priseEnCompte;

  public static final String SERIALIZED_NAME_DATE_CONSOMMATION = "dateConsommation";
  @SerializedName(SERIALIZED_NAME_DATE_CONSOMMATION)
  private LocalDate dateConsommation;


  public TypeAmenagement motif(String motif) {
    
    this.motif = motif;
    return this;
  }

   /**
   * motif de type amenagement
   * @return motif
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "motif de type amenagement")

  public String getMotif() {
    return motif;
  }


  public void setMotif(String motif) {
    this.motif = motif;
  }


  public TypeAmenagement portee(String portee) {
    
    this.portee = portee;
    return this;
  }

   /**
   * Portée du  type d&#39;aménagement
   * @return portee
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Portée du  type d'aménagement")

  public String getPortee() {
    return portee;
  }


  public void setPortee(String portee) {
    this.portee = portee;
  }


  public TypeAmenagement priseEnCompte(String priseEnCompte) {
    
    this.priseEnCompte = priseEnCompte;
    return this;
  }

   /**
   * Prise en compte du type d&#39;aménagement
   * @return priseEnCompte
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Prise en compte du type d'aménagement")

  public String getPriseEnCompte() {
    return priseEnCompte;
  }


  public void setPriseEnCompte(String priseEnCompte) {
    this.priseEnCompte = priseEnCompte;
  }


  public TypeAmenagement dateConsommation(LocalDate dateConsommation) {
    
    this.dateConsommation = dateConsommation;
    return this;
  }

   /**
   * Date du début de la validité de la nomenclature
   * @return dateConsommation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Date du début de la validité de la nomenclature")

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
    TypeAmenagement typeAmenagement = (TypeAmenagement) o;
    return Objects.equals(this.motif, typeAmenagement.motif) &&
        Objects.equals(this.portee, typeAmenagement.portee) &&
        Objects.equals(this.priseEnCompte, typeAmenagement.priseEnCompte) &&
        Objects.equals(this.dateConsommation, typeAmenagement.dateConsommation) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(motif, portee, priseEnCompte, dateConsommation, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TypeAmenagement {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    motif: ").append(toIndentedString(motif)).append("\n");
    sb.append("    portee: ").append(toIndentedString(portee)).append("\n");
    sb.append("    priseEnCompte: ").append(toIndentedString(priseEnCompte)).append("\n");
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

