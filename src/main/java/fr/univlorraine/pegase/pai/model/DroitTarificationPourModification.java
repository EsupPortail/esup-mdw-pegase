/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 27.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.pai.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.pai.model.DroitTarificationDroitElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La tarification avec ses élements de droits
 */
@ApiModel(description = "La tarification avec ses élements de droits")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-28T10:28:00.271684800+02:00[Europe/Paris]")
public class DroitTarificationPourModification {
  public static final String SERIALIZED_NAME_CODE = "code";
  public static final String SERIALIZED_NAME_LIBELLE_COURT = "libelleCourt";
  public static final String SERIALIZED_NAME_LIBELLE_LONG = "libelleLong";
  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  public static final String SERIALIZED_NAME_LISTE_ELEMENT = "listeElement";
  public static final String SERIALIZED_NAME_EXISTE_LIEN_AVEC_FORMATION_DEJA_OUVERTE_A_INSCRIPTION = "existeLienAvecFormationDejaOuverteAInscription";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;
  @SerializedName(SERIALIZED_NAME_LIBELLE_COURT)
  private String libelleCourt;
  @SerializedName(SERIALIZED_NAME_LIBELLE_LONG)
  private String libelleLong;
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;
  @SerializedName(SERIALIZED_NAME_LISTE_ELEMENT)
  private List<DroitTarificationDroitElement> listeElement = new ArrayList<DroitTarificationDroitElement>();
  @SerializedName(SERIALIZED_NAME_EXISTE_LIEN_AVEC_FORMATION_DEJA_OUVERTE_A_INSCRIPTION)
  private Boolean existeLienAvecFormationDejaOuverteAInscription;

  public DroitTarificationPourModification() { 
  }

  public DroitTarificationPourModification code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code de la tarification de droit
   * @return code
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le code de la tarification de droit")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public DroitTarificationPourModification libelleCourt(String libelleCourt) {
    
    this.libelleCourt = libelleCourt;
    return this;
  }

   /**
   * Le libellé court de la tarification de droit
   * @return libelleCourt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le libellé court de la tarification de droit")

  public String getLibelleCourt() {
    return libelleCourt;
  }


  public void setLibelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }


  public DroitTarificationPourModification libelleLong(String libelleLong) {
    
    this.libelleLong = libelleLong;
    return this;
  }

   /**
   * Le libellé long de la tarification de droit
   * @return libelleLong
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le libellé long de la tarification de droit")

  public String getLibelleLong() {
    return libelleLong;
  }


  public void setLibelleLong(String libelleLong) {
    this.libelleLong = libelleLong;
  }


  public DroitTarificationPourModification description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * La description de la tarification
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La description de la tarification")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public DroitTarificationPourModification listeElement(List<DroitTarificationDroitElement> listeElement) {
    
    this.listeElement = listeElement;
    return this;
  }

  public DroitTarificationPourModification addListeElementItem(DroitTarificationDroitElement listeElementItem) {
    this.listeElement.add(listeElementItem);
    return this;
  }

   /**
   * La liste des éléments de droits
   * @return listeElement
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La liste des éléments de droits")

  public List<DroitTarificationDroitElement> getListeElement() {
    return listeElement;
  }


  public void setListeElement(List<DroitTarificationDroitElement> listeElement) {
    this.listeElement = listeElement;
  }


  public DroitTarificationPourModification existeLienAvecFormationDejaOuverteAInscription(Boolean existeLienAvecFormationDejaOuverteAInscription) {
    
    this.existeLienAvecFormationDejaOuverteAInscription = existeLienAvecFormationDejaOuverteAInscription;
    return this;
  }

   /**
   * La tarification est-elle liée a une formation déjà ouverte à inscription ?
   * @return existeLienAvecFormationDejaOuverteAInscription
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La tarification est-elle liée a une formation déjà ouverte à inscription ?")

  public Boolean getExisteLienAvecFormationDejaOuverteAInscription() {
    return existeLienAvecFormationDejaOuverteAInscription;
  }


  public void setExisteLienAvecFormationDejaOuverteAInscription(Boolean existeLienAvecFormationDejaOuverteAInscription) {
    this.existeLienAvecFormationDejaOuverteAInscription = existeLienAvecFormationDejaOuverteAInscription;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DroitTarificationPourModification droitTarificationPourModification = (DroitTarificationPourModification) o;
    return Objects.equals(this.code, droitTarificationPourModification.code) &&
        Objects.equals(this.libelleCourt, droitTarificationPourModification.libelleCourt) &&
        Objects.equals(this.libelleLong, droitTarificationPourModification.libelleLong) &&
        Objects.equals(this.description, droitTarificationPourModification.description) &&
        Objects.equals(this.listeElement, droitTarificationPourModification.listeElement) &&
        Objects.equals(this.existeLienAvecFormationDejaOuverteAInscription, droitTarificationPourModification.existeLienAvecFormationDejaOuverteAInscription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelleCourt, libelleLong, description, listeElement, existeLienAvecFormationDejaOuverteAInscription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DroitTarificationPourModification {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    listeElement: ").append(toIndentedString(listeElement)).append("\n");
    sb.append("    existeLienAvecFormationDejaOuverteAInscription: ").append(toIndentedString(existeLienAvecFormationDejaOuverteAInscription)).append("\n");
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

