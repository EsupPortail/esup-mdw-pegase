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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.Date;

/**
 * Periode
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-28T10:28:00.271684800+02:00[Europe/Paris]")
public class Periode {
  public static final String SERIALIZED_NAME_CODE = "code";
  public static final String SERIALIZED_NAME_LIBELLE_AFFICHAGE = "libelleAffichage";
  public static final String SERIALIZED_NAME_ACTIVE = "active";
  public static final String SERIALIZED_NAME_VALIDE_OU_FUTURE = "valideOuFuture";
  public static final String SERIALIZED_NAME_ANNEE_UNIVERSITAIRE = "anneeUniversitaire";
  public static final String SERIALIZED_NAME_DATE_DEBUT = "dateDebut";
  public static final String SERIALIZED_NAME_DATE_FIN = "dateFin";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;
  @SerializedName(SERIALIZED_NAME_LIBELLE_AFFICHAGE)
  private String libelleAffichage;
  @SerializedName(SERIALIZED_NAME_ACTIVE)
  private Boolean active;
  @SerializedName(SERIALIZED_NAME_VALIDE_OU_FUTURE)
  private Boolean valideOuFuture;
  @SerializedName(SERIALIZED_NAME_ANNEE_UNIVERSITAIRE)
  private Integer anneeUniversitaire;
  @SerializedName(SERIALIZED_NAME_DATE_DEBUT)
  private Date dateDebut;
  @SerializedName(SERIALIZED_NAME_DATE_FIN)
  private Date dateFin;

  public Periode() { 
  }

  public Periode code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Code de la période - identifiant unique
   * @return code
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Code de la période - identifiant unique")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public Periode libelleAffichage(String libelleAffichage) {
    
    this.libelleAffichage = libelleAffichage;
    return this;
  }

   /**
   * Le libellé affichage de la période
   * @return libelleAffichage
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libellé affichage de la période")

  public String getLibelleAffichage() {
    return libelleAffichage;
  }


  public void setLibelleAffichage(String libelleAffichage) {
    this.libelleAffichage = libelleAffichage;
  }


  public Periode active(Boolean active) {
    
    this.active = active;
    return this;
  }

   /**
   * Les périodes actives
   * @return active
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Les périodes actives")

  public Boolean getActive() {
    return active;
  }


  public void setActive(Boolean active) {
    this.active = active;
  }


  public Periode valideOuFuture(Boolean valideOuFuture) {
    
    this.valideOuFuture = valideOuFuture;
    return this;
  }

   /**
   * Les périodes valides ou futures
   * @return valideOuFuture
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Les périodes valides ou futures")

  public Boolean getValideOuFuture() {
    return valideOuFuture;
  }


  public void setValideOuFuture(Boolean valideOuFuture) {
    this.valideOuFuture = valideOuFuture;
  }


  public Periode anneeUniversitaire(Integer anneeUniversitaire) {
    
    this.anneeUniversitaire = anneeUniversitaire;
    return this;
  }

   /**
   * L&#39;année universitaire
   * @return anneeUniversitaire
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "L'année universitaire")

  public Integer getAnneeUniversitaire() {
    return anneeUniversitaire;
  }


  public void setAnneeUniversitaire(Integer anneeUniversitaire) {
    this.anneeUniversitaire = anneeUniversitaire;
  }


  public Periode dateDebut(Date dateDebut) {
    
    this.dateDebut = dateDebut;
    return this;
  }

   /**
   * La date de début de la période
   * @return dateDebut
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La date de début de la période")

  public Date getDateDebut() {
    return dateDebut;
  }


  public void setDateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
  }


  public Periode dateFin(Date dateFin) {
    
    this.dateFin = dateFin;
    return this;
  }

   /**
   * La date de fin de la période
   * @return dateFin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La date de fin de la période")

  public Date getDateFin() {
    return dateFin;
  }


  public void setDateFin(Date dateFin) {
    this.dateFin = dateFin;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Periode periode = (Periode) o;
    return Objects.equals(this.code, periode.code) &&
        Objects.equals(this.libelleAffichage, periode.libelleAffichage) &&
        Objects.equals(this.active, periode.active) &&
        Objects.equals(this.valideOuFuture, periode.valideOuFuture) &&
        Objects.equals(this.anneeUniversitaire, periode.anneeUniversitaire) &&
        Objects.equals(this.dateDebut, periode.dateDebut) &&
        Objects.equals(this.dateFin, periode.dateFin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelleAffichage, active, valideOuFuture, anneeUniversitaire, dateDebut, dateFin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Periode {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleAffichage: ").append(toIndentedString(libelleAffichage)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    valideOuFuture: ").append(toIndentedString(valideOuFuture)).append("\n");
    sb.append("    anneeUniversitaire: ").append(toIndentedString(anneeUniversitaire)).append("\n");
    sb.append("    dateDebut: ").append(toIndentedString(dateDebut)).append("\n");
    sb.append("    dateFin: ").append(toIndentedString(dateFin)).append("\n");
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

